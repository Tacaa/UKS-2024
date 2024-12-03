package com.example.uks.controllers;


import com.example.uks.dto.repository.RepositoryDTO;
import com.example.uks.model.Repository;
import com.example.uks.services.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/repositories")
public class RepositoryController {

    @Autowired
    private RepositoryService repositoryService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<RepositoryDTO> getStudent(@PathVariable Integer id) {

        Repository repository = repositoryService.findRepositoryById(id);

        if (repository == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new RepositoryDTO(repository), HttpStatus.OK);
    }

}
