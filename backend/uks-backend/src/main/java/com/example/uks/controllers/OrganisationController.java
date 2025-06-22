package com.example.uks.controllers;

import com.example.uks.dto.organisation.OrganisationCreateDTO;
import com.example.uks.dto.organisation.OrganisationDTO;
import com.example.uks.dto.repository.RepositoryDTO;
import com.example.uks.exceptions.AttributeNotUniqueException;
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
}
