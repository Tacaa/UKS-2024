package com.example.uks.controllers;


import com.example.uks.dto.repository.RepositoryDTO;
import com.example.uks.dto.util.PagedResponse;
import com.example.uks.exceptions.RepositoryNotFoundException;
import com.example.uks.model.Repository;
import com.example.uks.services.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/repositories")
public class RepositoryController {

    @Autowired
    private RepositoryService repositoryService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<RepositoryDTO> getRepository(@PathVariable Integer id) {
        Repository repository = repositoryService.findRepositoryById(id);

        if (repository == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new RepositoryDTO(repository), HttpStatus.OK);
    }


    @GetMapping(value = "/all")
    public ResponseEntity<List<RepositoryDTO>> getAllRepositories() {
        List<Repository> repositories = repositoryService.findAllRepositories();

        List<RepositoryDTO> repositoriesDTO = new ArrayList<>();
        for (Repository repository : repositories) {
            repositoriesDTO.add(new RepositoryDTO(repository));
        }

        return new ResponseEntity<>(repositoriesDTO, HttpStatus.OK);
    }


    // /api/repositories?page=0&size=5&sort=name,DESC
    @GetMapping
    public ResponseEntity<PagedResponse<RepositoryDTO>> getRepositoriesPage(Pageable page) {
        Page<Repository> repositories = repositoryService.findAllRepositories(page);

        List<RepositoryDTO> repositoriesContent = new ArrayList<>();
        for (Repository repository : repositories) {
            repositoriesContent.add(new RepositoryDTO(repository));
        }

        PagedResponse<RepositoryDTO> response = new PagedResponse<>(
                repositoriesContent,
                repositories.getTotalPages(),
                repositories.getTotalElements()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteRepository(@PathVariable Integer id) {
        try {
            repositoryService.deleteRepository(id);
            return ResponseEntity.ok("Repository deleted successfully!");
        } catch (RepositoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "soft/{id}")
    public ResponseEntity<String> deleteLogicallyRepository(@PathVariable Integer id) {
        try {
            repositoryService.deleteLogicallyRepository(id);
            return ResponseEntity.ok("Repository deleted successfully!");
        } catch (RepositoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
