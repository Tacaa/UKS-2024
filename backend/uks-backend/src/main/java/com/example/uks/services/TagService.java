package com.example.uks.services;

import com.example.uks.dto.tag.CreateTagDTO;
import com.example.uks.exceptions.RepositoryNotFoundException;
import com.example.uks.model.Repository;
import com.example.uks.model.Tag;
import com.example.uks.repositories.RepositoryRepository;
import com.example.uks.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private RepositoryRepository repositoryRepository;

    public Tag createTag(CreateTagDTO dto) {
        Repository repository = repositoryRepository.findById(dto.getRepositoryId())
                .orElseThrow(() -> new RepositoryNotFoundException("Repository not found"));

        Tag tag = new Tag();
        tag.setName(dto.getName());
        tag.setDockerPullCommand(dto.getDockerPullCommand());
        tag.setPulled("");
        tag.setPushed("now");
        tag.setAuthor(dto.getAuthor());
        tag.setRepository(repository);

        return tagRepository.save(tag);
    }

    public List<Tag> getAllTagsByRepository(Long repositoryId) {
        return tagRepository.findAllByRepositoryId(repositoryId);
    }
}
