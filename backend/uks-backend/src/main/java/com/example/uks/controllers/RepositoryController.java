package com.example.uks.controllers;

import com.example.uks.dto.repository.*;
import com.example.uks.dto.util.PagedResponse;
import com.example.uks.exceptions.*;
import com.example.uks.model.OfficialRepository;
import com.example.uks.model.Repository;
import com.example.uks.services.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // /api/repositories/search?page=0&size=10&sort=name,DESC&category=API_MANAGMENT&name=WeatherAPI&ownerId=1
    @GetMapping(value = "/search")
    public ResponseEntity<PagedResponse<RepositoryDTO>> filterRepositories(@RequestParam(required = false) String category,
                                                                           @RequestParam(required = false) String name,
                                                                           @RequestParam(required = false) Integer ownerId,
                                                                           Pageable page)
    {
        Page<Repository> repositories = repositoryService.findRepositoriesByField(category, name, ownerId, page);

        List<RepositoryDTO> repositoriesContent = repositories.stream()
                .map(RepositoryDTO::new)
                .toList();

        PagedResponse<RepositoryDTO> response = new PagedResponse<>(
                repositoriesContent,
                repositories.getTotalPages(),
                repositories.getTotalElements()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Map<String, Object>> createRepository(@RequestBody CreateRepositoryDTO createRepositoryDTO) {
        try {
            Repository repository = repositoryService.createRepository(createRepositoryDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("message", null);
            response.put("data", new RepositoryDTO(repository));
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (OwnerNullException | OrganisationNullException | AttributeNotUniqueException | AttributeNullException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> updateRepository(@PathVariable Integer id, @RequestBody UpdateRepositoryDTO updateRepositoryDTO) {
        try {
            Repository repository = repositoryService.updateRepository(id, updateRepositoryDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("message", null);
            response.put("data", new RepositoryDTO(repository));
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (RepositoryNotFoundException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (AttributeNotUniqueException | AttributeNullException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

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


    @GetMapping(value = "official/{id}")
    public ResponseEntity<OfficialRepositoryDTO> getOfficialRepository(@PathVariable Integer id) {
        OfficialRepository repository = repositoryService.findOfficialRepositoryById(id);

        if (repository == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new OfficialRepositoryDTO(repository), HttpStatus.OK);
    }


    @GetMapping(value = "official/all")
    public ResponseEntity<List<OfficialRepositoryDTO>> getAllOfficialRepositories() {
        List<OfficialRepository> repositories = repositoryService.findAllOfficialRepositories();

        List<OfficialRepositoryDTO> repositoriesDTO = new ArrayList<>();
        for (OfficialRepository repository : repositories) {
            repositoriesDTO.add(new OfficialRepositoryDTO(repository));
        }

        return new ResponseEntity<>(repositoriesDTO, HttpStatus.OK);
    }


    // /api/repositories/official?page=0&size=5&sort=name,DESC
    @GetMapping(value = "official")
    public ResponseEntity<PagedResponse<OfficialRepositoryDTO>> getOfficialRepositoriesPage(Pageable page) {
        Page<OfficialRepository> repositories = repositoryService.findAllOfficialRepositories(page);

        List<OfficialRepositoryDTO> repositoriesContent = new ArrayList<>();
        for (OfficialRepository repository : repositories) {
            repositoriesContent.add(new OfficialRepositoryDTO(repository));
        }

        PagedResponse<OfficialRepositoryDTO> response = new PagedResponse<>(
                repositoriesContent,
                repositories.getTotalPages(),
                repositories.getTotalElements()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // /api/repositories/official/search?page=0&size=10&sort=name,DESC&category=API_MANAGMENT&name=WeatherAPI&ownerId=1
    @GetMapping(value = "official/search")
    public ResponseEntity<PagedResponse<OfficialRepositoryDTO>> filterOfficialRepositories(@RequestParam(required = false) String category,
                                                                           @RequestParam(required = false) String name,
                                                                           @RequestParam(required = false) Integer ownerId,
                                                                           Pageable page)
    {
        PagedResponse<OfficialRepository> repositories = repositoryService.findOfficialRepositoriesByField(category, name, ownerId, page);

        List<OfficialRepositoryDTO> repositoriesContent = repositories.getContent().stream()
                .map(OfficialRepositoryDTO::new)
                .toList();

        PagedResponse<OfficialRepositoryDTO> response = new PagedResponse<>(
                repositoriesContent,
                repositories.getTotalPages(),
                repositories.getTotalElements()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/official")
    public ResponseEntity<Map<String, Object>> createOfficialRepository(@RequestBody CreateOfficialRepositoryDTO createOfficialRepositoryDTO) {
        try {
            OfficialRepository repository = repositoryService.createOfficialRepository(createOfficialRepositoryDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("message", null);
            response.put("data", new OfficialRepositoryDTO(repository));
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (OwnerNullException | OrganisationNullException | AttributeNotUniqueException | AttributeNullException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "official/{id}")
    public ResponseEntity<Map<String, Object>> updateofficialRepository(@PathVariable Integer id, @RequestBody UpdateRepositoryDTO updateRepositoryDTO) {
        try {
            Repository repository = repositoryService.updateRepository(id, updateRepositoryDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("message", null);
            response.put("data", new RepositoryDTO(repository));
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        }catch (RepositoryNotFoundException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } catch (AttributeNotUniqueException | AttributeNullException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }


}
