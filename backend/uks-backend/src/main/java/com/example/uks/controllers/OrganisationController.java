package com.example.uks.controllers;

import com.example.uks.dto.organisation.OrganisationDTO;
import com.example.uks.dto.organisation.OrganisationUpdateDTO;
import com.example.uks.dto.repository.RepositoryDTO;
import com.example.uks.exceptions.AttributeNotUniqueException;
import com.example.uks.exceptions.OrganisationNotFound;
import com.example.uks.exceptions.UserNotFound;
import com.example.uks.model.Organisation;
import com.example.uks.services.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
