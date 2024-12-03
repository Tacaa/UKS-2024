package com.example.uks.services;

import com.example.uks.model.Repository;
import com.example.uks.repositories.RepositoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepositoryService {

    @Autowired
    private RepositoryRepository repositoryRepository;



    public Repository findRepositoryById(Integer id){
        return repositoryRepository.findById(id).orElse(null);
    }

}
