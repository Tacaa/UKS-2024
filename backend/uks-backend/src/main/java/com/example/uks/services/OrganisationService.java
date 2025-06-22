package com.example.uks.services;

import com.example.uks.dto.organisation.OrganisationUpdateDTO;
import com.example.uks.exceptions.OrganisationNotFound;
import com.example.uks.model.Organisation;
import com.example.uks.model.Repository;
import com.example.uks.repositories.OrganisationRepository;
import com.example.uks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganisationService {

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private UserRepository userRepository;


    public Organisation updateOrganisation(Integer orgId, OrganisationUpdateDTO dto) {
        Organisation organisation = organisationRepository.findByIdAndOwner_Id(orgId, dto.getOwnerId())
                .orElseThrow(() -> new OrganisationNotFound("Organisation not found."));

        if (dto.getDescription() != null) {
            organisation.setDescription(dto.getDescription());
        }

        if (dto.getImage() != null) {
            organisation.setImage(dto.getImage());
        }

       return organisation;
    }

    public void deactivateOrganisation(Integer orgId, Integer ownerId) {
        Organisation organisation = organisationRepository.findByIdAndOwner_Id(orgId, ownerId)
                .orElseThrow(() -> new OrganisationNotFound("Organisation not found."));

        organisation.setDeactivated(true);
        organisation.getMembers().clear();

        for (Repository repo : organisation.getRepositories()) {
            repo.setDeleted(true);
        }

        organisationRepository.save(organisation);
    }
}