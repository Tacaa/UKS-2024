package com.example.uks.repositories;

import com.example.uks.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    List<Tag> findAllByRepositoryIdAndDeletedFalse(Long repositoryId);

    @Query("SELECT t FROM Tag t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :query, '%')) AND t.deleted = false")
    List<Tag> searchByNameContaining(@Param("query") String query);

    Optional<Tag> findByIdAndDeletedFalse(Integer id);

}
