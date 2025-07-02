package com.example.uks.services;

import com.example.uks.dto.organisation.OrganisationUpdateDTO;
import com.example.uks.exceptions.AccessDeniedException;
import com.example.uks.model.Organisation;
import com.example.uks.model.Repository;
import com.example.uks.dto.organisation.OrganisationCreateDTO;
import com.example.uks.dto.user.MemberDTO;
import com.example.uks.exceptions.AttributeNotUniqueException;
import com.example.uks.exceptions.OrganisationNotFound;
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
import java.util.stream.Collectors;

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

    public Organisation createOrganisation(OrganisationCreateDTO dto) {
        if (organisationRepository.existsByName(dto.getName())) {
            throw new AttributeNotUniqueException("Organisation with this name already exists.");
        }

        User owner = userRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new UserNotFound("Owner not found"));

        Organisation organisation = new Organisation();
        organisation.setName(dto.getName());
        organisation.setDescription(dto.getDescription());
        organisation.setImage(dto.getImage());
        organisation.setOwner(owner);
        organisation.setDeactivated(false);
        organisation.getMembers().add(owner);

        return organisationRepository.save(organisation);
    }
  
    public Organisation getOrganisationById(Integer id) {
        return organisationRepository.findByIdAndDeactivatedFalse(id)
                .orElseThrow(() -> new OrganisationNotFound("Organisation not found"));
    }

    public List<Organisation> getUserOrganisations(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound("User not found"));

        List<Organisation> owned = organisationRepository.findByOwnerAndDeactivatedFalse(user);
        List<Organisation> member = organisationRepository.findByMembersContainsAndDeactivatedFalse(user);

        Set<Organisation> allOrgs = new HashSet<>();
        allOrgs.addAll(owned);
        allOrgs.addAll(member);

        return new ArrayList<>(allOrgs);
    }

    public void addMember(Integer orgId, Integer ownerId, Integer userIdToAdd) {
        Organisation organisation = organisationRepository.findByIdAndDeactivatedFalse(orgId)
                .orElseThrow(() -> new OrganisationNotFound("Organisation not found"));

        if (!organisation.getOwner().getId().equals(ownerId)) {
            throw new AccessDeniedException("Only the owner can add members.");
        }

        User userToAdd = userRepository.findById(userIdToAdd)
                .orElseThrow(() -> new UserNotFound("User to add not found"));

        if (organisation.getMembers().contains(userToAdd)) {
            throw new IllegalArgumentException("User is already a member.");
        }

        organisation.getMembers().add(userToAdd);
        organisationRepository.save(organisation);
    }


    public List<MemberDTO> getOrganisationMembers(Integer orgId, Integer userId) {
        Organisation organisation = organisationRepository.findByIdAndDeactivatedFalse(orgId)
                .orElseThrow(() -> new OrganisationNotFound("Organisation not found"));

        boolean isMember = organisation.getMembers().stream()
                .anyMatch(user -> user.getId().equals(userId));
        boolean isOwner = organisation.getOwner().getId().equals(userId);

        if (!isMember && !isOwner) {
            throw new AccessDeniedException("User is not a member of this organisation.");
        }

        Set<User> allMembers = new HashSet<>(organisation.getMembers());
        allMembers.add(organisation.getOwner());

        return allMembers.stream()
                .map(user -> new MemberDTO(user, user.getId().equals(organisation.getOwner().getId())))
                .collect(Collectors.toList());

    }
}
