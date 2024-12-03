package com.example.uks.services;

import com.example.uks.model.Repository;
import com.example.uks.repositories.RepositoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepositoryService {

    @Autowired
    private RepositoryRepository repositoryRepository;


    public Repository findRepositoryById(Integer id){
        return repositoryRepository.findById(id).orElse(null);
    }

    public List<Repository> findAllRepositories(){
        return repositoryRepository.findAll();
    }

    public Page<Repository> findAllRepositories(Pageable page) {
        return repositoryRepository.findAll(page);
    }


}
