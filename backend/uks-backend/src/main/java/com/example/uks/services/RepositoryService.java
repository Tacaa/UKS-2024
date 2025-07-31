package com.example.uks.services;

import com.example.uks.dto.repository.CreateOfficialRepositoryDTO;
import com.example.uks.dto.repository.CreateRepositoryDTO;
import com.example.uks.dto.repository.OfficialRepositoryDTO;
import com.example.uks.dto.repository.UpdateRepositoryDTO;
import com.example.uks.dto.util.PagedResponse;
import com.example.uks.enumeration.Category;
import com.example.uks.exceptions.AttributeNullException;
import com.example.uks.exceptions.OrganisationNullException;
import com.example.uks.exceptions.OwnerNullException;
import com.example.uks.exceptions.RepositoryNotFoundException;
import com.example.uks.model.OfficialRepository;
import com.example.uks.model.Organisation;
import com.example.uks.model.Repository;
import com.example.uks.model.User;
import com.example.uks.repositories.OfficialRepositoryRepository;
import com.example.uks.repositories.OrganisationRepository;
import com.example.uks.repositories.RepositoryRepository;
import com.example.uks.repositories.UserRepository;
import com.example.uks.util.specification.OfficialRepositorySpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class RepositoryService {

    @Autowired
    private RepositoryRepository repositoryRepository;

    @Autowired
    private OfficialRepositoryRepository officialRepositoryRepository;

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

    public Page<Repository> findRepositoriesByField(String category, String name, Integer ownerId, Pageable pageable) {
        return repositoryRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (category != null) {
                predicates.add(criteriaBuilder.equal(root.get("category"), Category.valueOf(category)));
            }
            if (name != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            if (ownerId != null) {
                predicates.add(criteriaBuilder.equal(root.get("owner").get("id"), ownerId));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }

    public Repository createRepository(CreateRepositoryDTO createRepositoryDTO){
        Repository repository = new Repository();

        if(createRepositoryDTO.getName() != null){
            //Repository repo = repositoryRepository.findByName(createRepositoryDTO.getName());
            if(repositoryRepository.findByName(createRepositoryDTO.getName()) ==null){
                repository.setName(createRepositoryDTO.getName());
            }else{
                throw new AttributeNullException("Repository with name " + createRepositoryDTO.getName() + " already exists!");
            }
        }else{
            throw new AttributeNullException("Name could not have value null.");
        }

        repository.setNamespace(createRepositoryDTO.getNamespace());
        repository.setDescription(createRepositoryDTO.getDescription());

        if(createRepositoryDTO.getCategory() != null){
            repository.setCategory(createRepositoryDTO.getCategory());
        }else{
            throw new AttributeNullException("Category could not have value null.");
        }


        repository.setVisibility(createRepositoryDTO.getVisibility());

        if(createRepositoryDTO.getPersonal() != null){
            repository.setPersonal(createRepositoryDTO.getPersonal());
        }else{
            throw new AttributeNullException("Personal could not have value null.");
        }


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

    public Repository updateRepository(Integer id, UpdateRepositoryDTO updateRepositoryDTO){
        Repository repository = repositoryRepository.findById(id).orElse(null);

        if(repository == null){
            throw new RepositoryNotFoundException("Repository with ID " + id + " does not exist");
        }

        repository.setNamespace(updateRepositoryDTO.getNamespace());
        repository.setDescription(updateRepositoryDTO.getDescription());
        repository.setVisibility(updateRepositoryDTO.getVisibility());

        if(updateRepositoryDTO.getCategory() != null){
            repository.setCategory(updateRepositoryDTO.getCategory());
        }else{
            throw new AttributeNullException("Category could not have value null.");
        }

        if(updateRepositoryDTO.getPersonal() != null){
            repository.setPersonal(updateRepositoryDTO.getPersonal());
        }else{
            throw new AttributeNullException("Personal could not have value null.");
        }

        repository.setUpdated(new Date());
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


    public OfficialRepository findOfficialRepositoryById(Integer id){
        return repositoryRepository.findOfficialRepositoryById(id).orElse(null);
    }

    public List<OfficialRepository> findAllOfficialRepositories(){
        return officialRepositoryRepository.findAll();
    }

    public Page<OfficialRepository> findAllOfficialRepositories(Pageable page) {
        return officialRepositoryRepository.findAll(page);
    }

    public PagedResponse<OfficialRepository> findOfficialRepositoriesByField(
            String category, String name, Integer ownerId, Pageable pageable) {
        Page<Repository> repositories = repositoryRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (category != null) {
                predicates.add(criteriaBuilder.equal(root.get("category"), Category.valueOf(category)));
            }
            if (name != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            if (ownerId != null) {
                predicates.add(criteriaBuilder.equal(root.get("owner").get("id"), ownerId));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        List<OfficialRepository> officialRepositories = new ArrayList<>() {
        };
        for(Repository repository : repositories){
            if (repository instanceof OfficialRepository) {
                officialRepositories.add(this.findOfficialRepositoryById(repository.getId()));
            }
        }

        return new PagedResponse<>(
                officialRepositories,
                officialRepositories.size()/ pageable.getPageSize() + 1,
                officialRepositories.size()
        );

    }

    public OfficialRepository createOfficialRepository(CreateOfficialRepositoryDTO createOfficialRepositoryDTO){
        OfficialRepository newOfficialRepository = new OfficialRepository();

        if(createOfficialRepositoryDTO.getPrefix() != null){
            OfficialRepository repository = repositoryRepository.findOfficialRepositoryByPrefix(createOfficialRepositoryDTO.getPrefix()).orElse(null);
            if(repository == null){
                newOfficialRepository.setPrefix(createOfficialRepositoryDTO.getPrefix());
            }else{
                throw new AttributeNullException("Repository with prefix " + createOfficialRepositoryDTO.getPrefix() + " already exists!");
            }
        }else{
            throw new AttributeNullException("Prefix could not have value null.");
        }

        if(createOfficialRepositoryDTO.getCreateRepositoryDTO().getName() != null){
            if(repositoryRepository.findByName(createOfficialRepositoryDTO.getCreateRepositoryDTO().getName()) == null){
                newOfficialRepository.setName(createOfficialRepositoryDTO.getCreateRepositoryDTO().getName());
            }else{
                throw new AttributeNullException("Repository with name " + createOfficialRepositoryDTO.getCreateRepositoryDTO().getName() + " already exists!");
            }
        }else{
            throw new AttributeNullException("Name could not have value null.");
        }

        newOfficialRepository.setNamespace(createOfficialRepositoryDTO.getCreateRepositoryDTO().getNamespace());
        newOfficialRepository.setDescription(createOfficialRepositoryDTO.getCreateRepositoryDTO().getDescription());

        if(createOfficialRepositoryDTO.getCreateRepositoryDTO().getCategory() != null){
            newOfficialRepository.setCategory(createOfficialRepositoryDTO.getCreateRepositoryDTO().getCategory());
        }else{
            throw new AttributeNullException("Category could not have value null.");
        }


        newOfficialRepository.setVisibility(createOfficialRepositoryDTO.getCreateRepositoryDTO().getVisibility());

        if(createOfficialRepositoryDTO.getCreateRepositoryDTO().getPersonal() != null){
            newOfficialRepository.setPersonal(createOfficialRepositoryDTO.getCreateRepositoryDTO().getPersonal());
        }else{
            throw new AttributeNullException("Personal could not have value null.");
        }


        newOfficialRepository.setCreated(new Date());
        newOfficialRepository.setStar(0);
        newOfficialRepository.setDeleted(false);

        if(createOfficialRepositoryDTO.getCreateRepositoryDTO().getOwnerId() != null){
            User user = userRepository.findById(createOfficialRepositoryDTO.getCreateRepositoryDTO().getOwnerId()).orElse(null);
            if(user != null){
                newOfficialRepository.setOwner(user);
            }else{
                throw new OwnerNullException("Owner with ID " + createOfficialRepositoryDTO.getCreateRepositoryDTO().getOwnerId() + " does not exist");
            }
        }else{
            throw new OwnerNullException("Owner could not have id with value null.");
        }

        if(createOfficialRepositoryDTO.getCreateRepositoryDTO().getOrganisationId() != null){
            Organisation organisation = organisationRepository.findById(createOfficialRepositoryDTO.getCreateRepositoryDTO().getOrganisationId()).orElse(null);
            if(organisation != null){
                newOfficialRepository.setOrganisation(organisation);
            }else{
                throw new OrganisationNullException("Organisation with ID " + createOfficialRepositoryDTO.getCreateRepositoryDTO().getOrganisationId() + " does not exist");
            }
        }else{
            newOfficialRepository.setOrganisation(null);
        }

        return officialRepositoryRepository.save(newOfficialRepository);
    }

}
