package com.example.uks.repositories;

import com.example.uks.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganisationRepository extends JpaRepository<Organisation, Integer> {
    Optional<Organisation> findByIdAndOwner_Id(Integer orgId, Integer ownerId);
}
