package com.example.uks.repositories;

import com.example.uks.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    List<Tag> findAllByRepositoryId(Long repositoryId);
}
