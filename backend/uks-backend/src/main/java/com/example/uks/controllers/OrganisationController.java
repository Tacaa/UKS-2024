package com.example.uks.controllers;


import com.example.uks.dto.organisation.OrganisationDTO;
import com.example.uks.dto.organisation.OrganisationUpdateDTO;
import com.example.uks.dto.organisation.OrganisationCreateDTO;

import com.example.uks.dto.repository.RepositoryDTO;
import com.example.uks.dto.team.TeamDTO;
import com.example.uks.exceptions.AccessDeniedException;
import com.example.uks.exceptions.AttributeNotUniqueException;
import com.example.uks.exceptions.OrganisationNotFound;
import com.example.uks.exceptions.UserNotFound;
import com.example.uks.model.Organisation;
import com.example.uks.model.Team;
import com.example.uks.services.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/organisation")
public class OrganisationController {

    @Autowired
    private OrganisationService organisationService;
  
    @PutMapping("/{orgId}")
    public ResponseEntity<Map<String, Object>> updateOrganisation(@PathVariable Integer orgId, @RequestBody OrganisationUpdateDTO dto) {
        try{
            Organisation updated = organisationService.updateOrganisation(orgId, dto);
            Map<String, Object> response = new HashMap<>();
            response.put("message", null);
            response.put("data", OrganisationDTO.from(updated));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (OrganisationNotFound e){
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{orgId}")
    public ResponseEntity<Void> deactivateOrganisation(@PathVariable Integer orgId, @RequestParam Integer ownerId) {
        try{
            organisationService.deactivateOrganisation(orgId, ownerId);
            return ResponseEntity.noContent().build();
        }catch (OrganisationNotFound e){

            return ResponseEntity.notFound().build();
        }
    }
  

    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrganisation(@RequestBody OrganisationCreateDTO dto) {
        try{
            Organisation organisation = organisationService.createOrganisation(dto);

            Map<String, Object> response = new HashMap<>();
            response.put("message", null);
            response.put("data", OrganisationDTO.from(organisation));
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        }catch (AttributeNotUniqueException e){
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (UserNotFound e){
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    } 
   

    
  
    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getUserOrganisations(@PathVariable Integer userId) {
        try{
            List<Organisation> organisations = organisationService.getUserOrganisations(userId);

            List<OrganisationDTO> organisationDTOs = new ArrayList<>();
            for (Organisation organisation : organisations) {
                organisationDTOs.add(OrganisationDTO.from(organisation));
            }

            Map<String, Object> response = new HashMap<>();
            response.put("message", "");
            response.put("data", organisationDTOs);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (UserNotFound e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User not found");
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getOrganisationById(@PathVariable Integer id) {
        try{
            Organisation organisation = organisationService.getOrganisationById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "");
            response.put("data", OrganisationDTO.from(organisation));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (OrganisationNotFound e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Organisation not found");
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{orgId}/teams")
    public ResponseEntity<Map<String, Object>> getOrganisationTeams(
            @PathVariable Integer orgId,
            @RequestParam Integer userId) {

        Map<String, Object> response = new HashMap<>();

        try {
            List<Team> teams = organisationService.getTeamsForMember(orgId, userId);
            List<TeamDTO> dtoList = teams.stream()
                    .map(TeamDTO::from)
                    .collect(Collectors.toList());

            response.put("message", "");
            response.put("data", dtoList);
            return ResponseEntity.ok(response);

        } catch (OrganisationNotFound e) {
            response.put("message", "Organisation not found");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (AccessDeniedException e) {
            response.put("message", e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);

        }
    }

}
