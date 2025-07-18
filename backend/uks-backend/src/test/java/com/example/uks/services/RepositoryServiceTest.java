package com.example.uks.services;

import com.example.uks.dto.repository.CreateOfficialRepositoryDTO;
import com.example.uks.dto.repository.CreateRepositoryDTO;
import com.example.uks.dto.repository.UpdateRepositoryDTO;
import com.example.uks.dto.team.CreateTeamDTO;
import com.example.uks.enumeration.Category;
import com.example.uks.enumeration.Visibility;
import com.example.uks.exceptions.AttributeNullException;
import com.example.uks.exceptions.OwnerNullException;
import com.example.uks.model.OfficialRepository;
import com.example.uks.model.Organisation;
import com.example.uks.model.Repository;
import com.example.uks.model.User;
import com.example.uks.repositories.OfficialRepositoryRepository;
import com.example.uks.repositories.OrganisationRepository;
import com.example.uks.repositories.RepositoryRepository;
import com.example.uks.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RepositoryServiceTest {

    @Mock
    private RepositoryRepository repositoryRepository;

    @Mock
    private OfficialRepositoryRepository officialRepositoryRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrganisationRepository organisationRepository;

    @InjectMocks
    private RepositoryService repositoryService;

    private CreateRepositoryDTO createRepositoryDTO;

    private User owner;
    private Organisation organisation;
    private Repository repository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        createRepositoryDTO = new CreateRepositoryDTO();
        createRepositoryDTO.setName("Repo");
        createRepositoryDTO.setNamespace("namespace");
        createRepositoryDTO.setDescription("desc");
        createRepositoryDTO.setCategory(Category.DEVOPS);
        createRepositoryDTO.setVisibility(Visibility.PUBLIC);
        createRepositoryDTO.setPersonal(true);
        createRepositoryDTO.setOwnerId(1);
        createRepositoryDTO.setOrganisationId(1);

        owner = new User();
        owner.setId(1);

        organisation = new Organisation();
        organisation.setId(1);

        repository = new Repository();
        repository.setId(1);
        repository.setName("Repo");
    }

    @Test
    void findRepositoryById_returnRepo(){
        when(repositoryRepository.findById(1)).thenReturn(Optional.of(repository));
        Repository returned = repositoryService.findRepositoryById(1);
        assertEquals(1, returned.getId());
    }

    @Test
    void findRepositoryById_NullNotFound(){
        when(repositoryRepository.findById(1)).thenReturn(Optional.empty());
        Repository returned = repositoryService.findRepositoryById(1);
        assertNull(returned);
    }

    @Test
    void createRepository_returnSavedRepo() {
        when(repositoryRepository.findByName("Repo")).thenReturn(null);
        when(userRepository.findById(1)).thenReturn(Optional.of(owner));
        when(organisationRepository.findById(1)).thenReturn(Optional.of(organisation));
        when(repositoryRepository.save(any())).thenReturn(repository);

        Repository returned = repositoryService.createRepository(createRepositoryDTO);

        assertEquals("Repo", returned.getName());
        verify(repositoryRepository).save(any(Repository.class));
    }

    @Test
    void createRepository_nameIsNull() {
        createRepositoryDTO.setName(null);
        Exception e = assertThrows(AttributeNullException.class, ()-> repositoryService.createRepository(createRepositoryDTO));
        assertEquals("Name could not have value null.", e.getMessage());
    }

    @Test
    void createRepository_nameAlreadyExists() {
        when(repositoryRepository.findByName("Repo")).thenReturn(new Repository());
        Exception e = assertThrows(AttributeNullException.class, () -> repositoryService.createRepository(createRepositoryDTO));
        assertEquals("Repository with name Repo already exists!", e.getMessage());
    }

    @Test
    void createRepository_ownerNotFound() {
        when(repositoryRepository.findByName("Repo")).thenReturn(null);
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        Exception e = assertThrows(OwnerNullException.class, () -> repositoryService.createRepository(createRepositoryDTO));
        assertEquals("Owner with ID 1 does not exist", e.getMessage());
    }

    @Test
    void updateRepository_success() {
        UpdateRepositoryDTO updatedRepo = new UpdateRepositoryDTO();
        updatedRepo.setNamespace("Updated Repo");
        updatedRepo.setCategory(Category.DEVOPS);
        updatedRepo.setPersonal(true);

        when(repositoryRepository.findById(1)).thenReturn(Optional.of(repository));
        when(repositoryRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Repository returned = repositoryService.updateRepository(1, updatedRepo);
        assertEquals("Updated Repo", returned.getNamespace());
        assertEquals(Category.DEVOPS, returned.getCategory());
        assertEquals(true, returned.getPersonal());
    }

    @Test
    void deleteRepository_success() {
        when(repositoryRepository.findById(1)).thenReturn(Optional.of(repository));
        doNothing().when(repositoryRepository).delete(repository);

        repositoryService.deleteRepository(1);

        verify(repositoryRepository).findById(1);
        verify(repositoryRepository).delete(repository);
    }

    @Test
    void deleteLogicallyRepository_setDeactivated() {
        repository.setDeleted(false);
        when(repositoryRepository.findById(1)).thenReturn(Optional.of(repository));
        when(repositoryRepository.save(any())).thenReturn(repository);

        repositoryService.deleteLogicallyRepository(1);
        assertTrue(repository.getDeleted());
        verify(repositoryRepository).save(repository);
    }

    @Test
    void createOfficialRepository() {
        CreateRepositoryDTO dto = new CreateRepositoryDTO();
        dto.setName("Repo");
        dto.setNamespace("test/ns");
        dto.setDescription("desc");
        dto.setCategory(Category.DEVOPS);
        dto.setVisibility(Visibility.PUBLIC);
        dto.setPersonal(true);
        dto.setOwnerId(1);
        dto.setOrganisationId(1);

        CreateOfficialRepositoryDTO officialDTO = new CreateOfficialRepositoryDTO();
        officialDTO.setPrefix("ukg");
        officialDTO.setCreateRepositoryDTO(dto);

        when(repositoryRepository.findOfficialRepositoryByPrefix("ukg")).thenReturn(Optional.empty());
        when(repositoryRepository.findByName("Repo")).thenReturn(null);
        when(userRepository.findById(1)).thenReturn(Optional.of(owner));
        when(organisationRepository.findById(1)).thenReturn(Optional.of(organisation));
        when(officialRepositoryRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        OfficialRepository saved = repositoryService.createOfficialRepository(officialDTO);

        assertEquals("Repo", saved.getName());
        assertEquals("ukg", saved.getPrefix());
        assertEquals(owner, saved.getOwner());
        assertEquals(organisation, saved.getOrganisation());
        assertFalse(saved.getDeleted());
        assertEquals(0, saved.getStar());
        assertNotNull(saved.getCreated());
        verify(officialRepositoryRepository).save(any());
    }
}