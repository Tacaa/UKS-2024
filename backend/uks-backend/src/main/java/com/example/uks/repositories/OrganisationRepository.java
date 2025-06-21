package com.example.uks.repositories;

import com.example.uks.model.Organisation;
import com.example.uks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrganisationRepository extends JpaRepository<Organisation, Integer> {
    List<Organisation> findByOwner(User owner);

    List<Organisation> findByMembersContains(User user);

    Optional<Organisation> findById(Integer id);
}
