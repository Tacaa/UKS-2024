package com.example.uks.services;

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

@Service
public class OrganisationService {

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private UserRepository userRepository;

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
}
