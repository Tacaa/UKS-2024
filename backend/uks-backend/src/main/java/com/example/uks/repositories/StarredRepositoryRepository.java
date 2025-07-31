package com.example.uks.repositories;

import com.example.uks.model.StarredRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StarredRepositoryRepository extends JpaRepository<StarredRepository, Integer> {

    boolean existsByUserIdAndRepositoryId(Integer userId, Integer repositoryId);

    List<StarredRepository> findByUserId(Integer userId);

    Integer countByRepositoryId(Integer repositoryId);

    Optional<StarredRepository> findByUserIdAndRepositoryId(Integer userId, Integer repositoryId);
}

