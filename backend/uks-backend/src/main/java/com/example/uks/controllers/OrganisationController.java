package com.example.uks.controllers;

import com.example.uks.dto.organisation.OrganisationDTO;
import com.example.uks.dto.repository.RepositoryDTO;
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
import java.util.List;

@RestController
@RequestMapping(value = "api/organisation")
public class OrganisationController {

    @Autowired
    private OrganisationService organisationService;




}
