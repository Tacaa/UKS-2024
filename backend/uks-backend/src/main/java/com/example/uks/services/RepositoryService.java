package com.example.uks.services;

import com.example.uks.dto.repository.CreateRepositoryDTO;
import com.example.uks.exceptions.OrganisationNullException;
import com.example.uks.exceptions.OwnerNullException;
import com.example.uks.exceptions.RepositoryNotFoundException;
import com.example.uks.model.Organisation;
import com.example.uks.model.Repository;
import com.example.uks.model.User;
import com.example.uks.repositories.OrganisationRepository;
import com.example.uks.repositories.RepositoryRepository;
import com.example.uks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RepositoryService {

    @Autowired
    private RepositoryRepository repositoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganisationRepository organisationRepository;


    public Repository findRepositoryById(Integer id){
        return repositoryRepository.findById(id).orElse(null);
    }

    public List<Repository> findAllRepositories(){
        return repositoryRepository.findAll();
    }

    public Page<Repository> findAllRepositories(Pageable page) {
        return repositoryRepository.findAll(page);
    }

    public Repository createRepository(CreateRepositoryDTO createRepositoryDTO){
        Repository repository = new Repository();
        repository.setName(createRepositoryDTO.getName());
        repository.setNamespace(createRepositoryDTO.getNamespace());
        repository.setDescription(createRepositoryDTO.getDescription());
        repository.setCategory(createRepositoryDTO.getCategory());
        repository.setVisibility(createRepositoryDTO.getVisibility());
        repository.setPersonal(createRepositoryDTO.getPersonal());

        repository.setCreated(new Date());
        repository.setStar(0);
        repository.setDeleted(false);

        if(createRepositoryDTO.getOwnerId() != null){
            User user = userRepository.findById(createRepositoryDTO.getOwnerId()).orElse(null);
            if(user != null){
                repository.setOwner(user);
            }else{
                throw new OwnerNullException("Owner with ID " + createRepositoryDTO.getOwnerId() + " does not exist");
            }
        }else{
            throw new OwnerNullException("Owner could not have id with value null.");
        }

        if(createRepositoryDTO.getOrganisationId() != null){
            Organisation organisation = organisationRepository.findById(createRepositoryDTO.getOrganisationId()).orElse(null);
            if(organisation != null){
                repository.setOrganisation(organisation);
            }else{
                throw new OrganisationNullException("Organisation with ID " + createRepositoryDTO.getOrganisationId() + " does not exist");
            }
        }else{
            repository.setOrganisation(null);
        }

        return repositoryRepository.save(repository);
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
