package com.example.uks.controllers;

import com.example.uks.dto.organisation.OrganisationDTO;
import com.example.uks.dto.repository.RepositoryDTO;
import com.example.uks.exceptions.OrganisationNotFound;
import com.example.uks.exceptions.UserNotFound;
import com.example.uks.model.Organisation;
import com.example.uks.services.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/organisation")
public class OrganisationController {

    @Autowired
    private OrganisationService organisationService;

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
}
