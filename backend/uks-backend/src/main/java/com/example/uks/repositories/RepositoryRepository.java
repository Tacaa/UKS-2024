package com.example.uks.repositories;

import com.example.uks.model.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositoryRepository extends JpaRepository<Repository, Integer> {

    public Page<Repository> findAll(Pageable pageable);
}
