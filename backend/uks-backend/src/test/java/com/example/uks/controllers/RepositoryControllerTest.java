package com.example.uks.controllers;

import com.example.uks.dto.repository.*;
import com.example.uks.dto.util.PagedResponse;
import com.example.uks.enumeration.Category;
import com.example.uks.exceptions.AttributeNotUniqueException;
import com.example.uks.exceptions.AttributeNullException;
import com.example.uks.exceptions.RepositoryNotFoundException;
import com.example.uks.model.OfficialRepository;
import com.example.uks.model.Repository;
import com.example.uks.services.RepositoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RepositoryControllerTest {

    @InjectMocks
    private RepositoryController repositoryController;

    @Mock
    private RepositoryService repositoryService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRepository_whenRepositoryExists_shouldReturnRepository() {
        Repository repository = new Repository();
        repository.setId(1);

        when(repositoryService.findRepositoryById(1)).thenReturn(repository);

        ResponseEntity<RepositoryDTO> response = repositoryController.getRepository(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getId());
    }

    @Test
    void getRepository_whenRepositoryNotExist_shouldReturnNotFound(){
        when(repositoryService.findRepositoryById(1)).thenReturn(null);

        ResponseEntity<RepositoryDTO> response = repositoryController.getRepository(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getAllRepositories_shouldReturnAllRepositories() {
        Repository repository = new Repository();
        repository.setId(1);

        List<Repository> repositories = new ArrayList<>();
        repositories.add(repository);

        when(repositoryService.findAllRepositories()).thenReturn(repositories);

        ResponseEntity<List<RepositoryDTO>> response = repositoryController.getAllRepositories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getRepositoriesPage() {
        Repository repository = new Repository();
        repository.setId(1);

        List<Repository> repositories = new ArrayList<>();
        repositories.add(repository);

        Page<Repository> repositoryPage = new PageImpl<>(repositories);

        when(repositoryService.findAllRepositories(any(Pageable.class))).thenReturn(repositoryPage);

        ResponseEntity<PagedResponse<RepositoryDTO>> response = repositoryController.getRepositoriesPage(Pageable.unpaged());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getContent().size());
    }
    @Test
    void filterRepositories_ifSuccess(){
        String category = "API_MANAGEMENT";
        String name = "WeatherAPI";
        Integer ownerId = 1;
        Pageable pageable = PageRequest.of(0,10);

        Repository repository1 = new Repository();
        repository1.setId(1);
        repository1.setName("WeatherAPI");
        repository1.setCategory(Category.API_MANAGMENT);

        Repository repository2 = new Repository();
        repository2.setId(2);
        repository2.setName("ForecastAPI");
        repository2.setCategory(Category.API_MANAGMENT);

        List<Repository> repositories = List.of(repository1,repository2);
        Page<Repository> repositoryPage = new PageImpl<>(repositories, pageable, repositories.size());

        when(repositoryService.findRepositoriesByField(category, name, ownerId, pageable))
                .thenReturn(repositoryPage);

        ResponseEntity<PagedResponse<RepositoryDTO>> response =
                repositoryController.filterRepositories(category, name, ownerId, pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().getContent().size());
        assertEquals(1, response.getBody().getContent().get(0).getId());
        assertEquals(2, response.getBody().getContent().get(1).getId());
        assertEquals("WeatherAPI", response.getBody().getContent().get(0).getName());
        assertEquals("ForecastAPI", response.getBody().getContent().get(1).getName());
        assertEquals(Category.API_MANAGMENT, response.getBody().getContent().get(0).getCategory());
        assertEquals(2, response.getBody().getTotalElements());
        assertEquals(1, response.getBody().getTotalPages());
    }

    @Test
    void filterRepositories_ifEmptyResult(){
        String category = "NAN";
        String name = null;
        Integer ownerId = null;
        Pageable pageable = PageRequest.of(0,10);

        Page<Repository> emptyPage = Page.empty(pageable);

        when(repositoryService.findRepositoriesByField(category, name, ownerId, pageable))
                .thenReturn(emptyPage);

        ResponseEntity<PagedResponse<RepositoryDTO>> response =
                repositoryController.filterRepositories(category, name, ownerId, pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().isEmpty());
        assertEquals(0, response.getBody().getTotalElements());
        assertEquals(0, response.getBody().getTotalPages());
    }

    @Test
    void filterRepositories_ifNullFilters(){
        String category = null;
        String name = null;
        Integer ownerId = null;
        Pageable pageable = PageRequest.of(0,10);

        Repository repository = new Repository();
        repository.setId(1);
        repository.setName("WeatherAPI");
        repository.setCategory(Category.API_MANAGMENT);

        List<Repository> repositories = List.of(repository);
        Page<Repository> repositoryPage = new PageImpl<>(repositories, pageable, repositories.size());

        when(repositoryService.findRepositoriesByField(category, name, ownerId, pageable))
                .thenReturn(repositoryPage);

        ResponseEntity<PagedResponse<RepositoryDTO>> response =
                repositoryController.filterRepositories(category, name, ownerId, pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getContent().size());
        assertEquals(1, response.getBody().getContent().get(0).getId());
        assertEquals("WeatherAPI", response.getBody().getContent().get(0).getName());
        assertEquals(Category.API_MANAGMENT, response.getBody().getContent().get(0).getCategory());
    }

    @Test
    void createRepository_ifSuccess_createRepository() {
        Repository repository = new Repository();
        repository.setId(1);

        when(repositoryService.createRepository(any(CreateRepositoryDTO.class))).thenReturn(repository);

        CreateRepositoryDTO repositoryDTO = new CreateRepositoryDTO();
        ResponseEntity<Map<String, Object>> response = repositoryController.createRepository(repositoryDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void createRepository_throwsException() {
        when(repositoryService.createRepository(any(CreateRepositoryDTO.class)))
                .thenThrow(new AttributeNotUniqueException("Attribute not unique"));

        CreateRepositoryDTO repositoryDTO = new CreateRepositoryDTO();
        ResponseEntity<Map<String, Object>> response = repositoryController.createRepository(repositoryDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Attribute not unique", response.getBody().get("message"));
    }

    @Test
    void updateRepository_ifSuccess() {
        Repository repository = new Repository();
        repository.setId(1);

        when(repositoryService.updateRepository(eq(1), any(UpdateRepositoryDTO.class)))
                .thenReturn(repository);

        UpdateRepositoryDTO repositoryDTO = new UpdateRepositoryDTO();
        ResponseEntity<Map<String, Object>> response = repositoryController.updateRepository(1, repositoryDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void updateRepository_ifNotFound() {
        when(repositoryService.updateRepository(eq(1), any(UpdateRepositoryDTO.class)))
                .thenThrow(new RepositoryNotFoundException("Repository not found"));

        UpdateRepositoryDTO repositoryDTO = new UpdateRepositoryDTO();
        ResponseEntity<Map<String, Object>> response = repositoryController.updateRepository(1, repositoryDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Repository not found", response.getBody().get("message"));
    }

    @Test
    void deleteRepository_ifSuccess() {
        Repository repository = new Repository();
        repository.setId(1);

        doNothing().when(repositoryService).deleteRepository(1);

        ResponseEntity<String> response = repositoryController.deleteRepository(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Repository deleted successfully!", response.getBody());
    }

    @Test
    void deleteRepository_ifNotFound() {

        doThrow(new RepositoryNotFoundException("Repository not found"))
                .when(repositoryService).deleteRepository(1);

        ResponseEntity<String> response = repositoryController.deleteRepository(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Repository not found", response.getBody());
    }

    @Test
    void deleteLogicallyRepository_ifSuccess() {
        Repository repository = new Repository();
        repository.setId(1);

        doNothing().when(repositoryService).deleteLogicallyRepository(1);

        ResponseEntity<String> response = repositoryController.deleteLogicallyRepository(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Repository deleted successfully!", response.getBody());
    }

    @Test
    void deleteLogicallyRepository_ifNotFound() {

        doThrow(new RepositoryNotFoundException("Repository not found!"))
                .when(repositoryService).deleteLogicallyRepository(1);

        ResponseEntity<String> response = repositoryController.deleteLogicallyRepository(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Repository not found!", response.getBody());
    }

    @Test
    void getOfficialRepository_whenOfficialRepositoryExists_shouldReturnOfficialRepository() {
        OfficialRepository officialRepository = new OfficialRepository();
        officialRepository.setId(1);

        when(repositoryService.findOfficialRepositoryById(1)).thenReturn(officialRepository);

        ResponseEntity<OfficialRepositoryDTO> response = repositoryController.getOfficialRepository(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getRepositoryDTO().getId());
    }

    @Test
    void getOfficialRepository_whenOfficialRepositoryNotExists_shouldReturnNotFound() {
        when(repositoryService.findOfficialRepositoryById(1)).thenReturn(null);

        ResponseEntity<OfficialRepositoryDTO> response = repositoryController.getOfficialRepository(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllOfficialRepositories() {
        OfficialRepository officialRepository = new OfficialRepository();
        officialRepository.setId(1);

        List<OfficialRepository> officialRepositories = new ArrayList<>();
        officialRepositories.add(officialRepository);

        when(repositoryService.findAllOfficialRepositories()).thenReturn(officialRepositories);

        ResponseEntity<List<OfficialRepositoryDTO>> response = repositoryController.getAllOfficialRepositories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getOfficialRepositoriesPage() {
        OfficialRepository officialRepository = new OfficialRepository();
        officialRepository.setId(1);

        List<OfficialRepository> officialRepositories = new ArrayList<>();
        officialRepositories.add(officialRepository);

        Page<OfficialRepository> officialRepositoryPage = new PageImpl<>(officialRepositories);

        when(repositoryService.findAllOfficialRepositories(any(Pageable.class)))
                .thenReturn(officialRepositoryPage);

        ResponseEntity<PagedResponse<OfficialRepositoryDTO>> response =
                repositoryController.getOfficialRepositoriesPage(Pageable.unpaged());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getContent().size());
    }

    @Test
    void filterOfficialRepositories() {
        Pageable pageable = PageRequest.of(0, 10);
        OfficialRepository officialRepository = new OfficialRepository();
        officialRepository.setId(1);
        officialRepository.setName("Repo");

        PagedResponse<OfficialRepository> pagedResponse = new PagedResponse<>(
                List.of(officialRepository),
                1,
                1
        );

        when(repositoryService.findOfficialRepositoriesByField(
                "API_MANAGEMENT",
                "Repo",
                1,
                pageable
        )).thenReturn(pagedResponse);

        ResponseEntity<PagedResponse<OfficialRepositoryDTO>> response =
                repositoryController.filterOfficialRepositories(
                        "API_MANAGEMENT",
                        "Repo",
                        1,
                        pageable
                );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTotalPages());
        assertEquals(1, response.getBody().getTotalElements());
        assertEquals("Repo", response.getBody().getContent().get(0).getRepositoryDTO().getName());
    }

    @Test
    void createOfficialRepository_ifSuccess_createOfficialRepository() {
        OfficialRepository officialRepository = new OfficialRepository();
        officialRepository.setId(1);

        when(repositoryService.createOfficialRepository(any(CreateOfficialRepositoryDTO.class)))
                .thenReturn(officialRepository);

        CreateOfficialRepositoryDTO officialRepositoryDTO = new CreateOfficialRepositoryDTO();
        ResponseEntity<Map<String, Object>> response = repositoryController.createOfficialRepository(officialRepositoryDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void createOfficialRepository_IfFails_throwBadRequest(){
        when(repositoryService.createOfficialRepository(any(CreateOfficialRepositoryDTO.class)))
                .thenThrow(new AttributeNotUniqueException("Attribute not unique"));

        CreateOfficialRepositoryDTO officialRepositoryDTO = new CreateOfficialRepositoryDTO();
        ResponseEntity<Map<String, Object>> response = repositoryController.createOfficialRepository(officialRepositoryDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Attribute not unique", response.getBody().get("message"));
    }

    @Test
    void updateOfficialRepository_ifSuccess() {
      UpdateRepositoryDTO updateRepositoryDTO = new UpdateRepositoryDTO();
      updateRepositoryDTO.setNamespace("Updated Repo");

      Repository repository = new Repository();
      repository.setId(1);
      repository.setNamespace("Updated Repo");

      when(repositoryService.updateRepository(1, updateRepositoryDTO))
              .thenReturn(repository);

      ResponseEntity<Map<String, Object>> response =
              repositoryController.updateofficialRepository(1, updateRepositoryDTO);

      assertEquals(HttpStatus.CREATED, response.getStatusCode());
      assertNotNull(response.getBody());
      assertNull(response.getBody().get("message"));
      assertNotNull(response.getBody().get("data"));
      RepositoryDTO repositoryDTO = (RepositoryDTO) response.getBody().get("data");
      assertEquals("Updated Repo", repositoryDTO.getNamespace());
    }

    @Test
    void updateOfficialRepository_ifNotFound(){
        UpdateRepositoryDTO updateRepositoryDTO = new UpdateRepositoryDTO();
        updateRepositoryDTO.setNamespace("NAN");

        when(repositoryService.updateRepository(1, updateRepositoryDTO))
                .thenThrow(new RepositoryNotFoundException("Repository not found"));

        ResponseEntity<Map<String, Object>> response =
                repositoryController.updateofficialRepository(1, updateRepositoryDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get("message"));
        assertEquals("Repository not found", response.getBody().get("message"));
        assertNull(response.getBody().get("data"));
    }

    @Test
    void updateOfficialRepository_ifBadRequest_attributeNotUnique(){
        UpdateRepositoryDTO updateRepositoryDTO = new UpdateRepositoryDTO();
        updateRepositoryDTO.setNamespace("Duplicate Name");

        when(repositoryService.updateRepository(1, updateRepositoryDTO))
                .thenThrow(new AttributeNotUniqueException("Name must be unique"));

        ResponseEntity<Map<String, Object>> response =
                repositoryController.updateofficialRepository(1, updateRepositoryDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get("message"));
        assertEquals("Name must be unique", response.getBody().get("message"));
        assertNull(response.getBody().get("data"));
    }

    @Test
    void updateOfficialRepository_ifBadRequest_attributeNull(){
        UpdateRepositoryDTO updateRepositoryDTO = new UpdateRepositoryDTO();
        updateRepositoryDTO.setNamespace(null);

        when(repositoryService.updateRepository(1, updateRepositoryDTO))
                .thenThrow(new AttributeNullException("Name cannot be null"));

        ResponseEntity<Map<String, Object>> response =
                repositoryController.updateofficialRepository(1, updateRepositoryDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().get("message"));
        assertEquals("Name cannot be null", response.getBody().get("message"));
        assertNull(response.getBody().get("data"));
    }
}