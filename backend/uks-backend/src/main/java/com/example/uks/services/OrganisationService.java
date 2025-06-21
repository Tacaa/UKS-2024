package com.example.uks.services;

import com.example.uks.dto.organisation.OrganisationCreateDTO;
import com.example.uks.exceptions.UserNotFound;
import com.example.uks.model.Organisation;
import com.example.uks.model.User;
import com.example.uks.repositories.OrganisationRepository;
import com.example.uks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrganisationService {

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private UserRepository userRepository;

    public Organisation createOrganisation(OrganisationCreateDTO dto) {
        if (organisationRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Organisation with this name already exists.");
        }

        User owner = userRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        Organisation organisation = new Organisation();
        organisation.setName(dto.getName());
        organisation.setDescription(dto.getDescription());
        organisation.setImage(dto.getImage());
        organisation.setOwner(owner);
        organisation.setDeactivated(false);
        organisation.getMembers().add(owner);

        return organisationRepository.save(organisation);
    }
}
