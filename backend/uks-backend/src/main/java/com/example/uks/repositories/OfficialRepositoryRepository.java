package com.example.uks.repositories;

import com.example.uks.model.OfficialRepository;
import com.example.uks.model.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OfficialRepositoryRepository extends JpaRepository<OfficialRepository, Integer>, JpaSpecificationExecutor<OfficialRepository> {

    Page<OfficialRepository> findAll(Pageable pageable);

}
