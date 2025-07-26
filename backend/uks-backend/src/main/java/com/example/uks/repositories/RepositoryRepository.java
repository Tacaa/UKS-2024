package com.example.uks.repositories;

import com.example.uks.model.OfficialRepository;
import com.example.uks.model.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface RepositoryRepository extends JpaRepository<Repository, Integer>, JpaSpecificationExecutor<Repository> {

    Page<Repository> findAll(Pageable pageable);

    Repository findByName(String name);

    @Query("SELECT o FROM Repository o WHERE o.id = :id ")
    Optional<OfficialRepository> findOfficialRepositoryById(@Param("id") Integer id);

    @Query("SELECT o FROM Repository o WHERE o.prefix = :prefix ")
    Optional<OfficialRepository> findOfficialRepositoryByPrefix(@Param("prefix") String prefix);

    List<Repository> findByOrganisationId(Integer organisationId);

}
