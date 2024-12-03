package com.example.uks.repositories;

import com.example.uks.model.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryRepository extends JpaRepository<Repository, Integer> {

    //public Optional<Repository> findById(Long id);
}
