package com.example.uks.services;

import com.example.uks.exceptions.RepositoryNotFoundException;
import com.example.uks.model.Repository;
import com.example.uks.repositories.RepositoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void deleteRepository(Integer id){
        Repository repository = repositoryRepository.findById(id).orElse(null);

        if(repository == null){
            throw new RepositoryNotFoundException("Repository with ID " + id + " does not exist");
        }

        repositoryRepository.delete(repository);
    }

    public void deleteLogicallyRepository(Integer id){
        Repository repository = repositoryRepository.findById(id).orElse(null);

        if(repository == null){
            throw new RepositoryNotFoundException("Repository with ID " + id + " does not exist");
        }

        repository.setDeleted(true);
        repositoryRepository.save(repository);
    }


}
