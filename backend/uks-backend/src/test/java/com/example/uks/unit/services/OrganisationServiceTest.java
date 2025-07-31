package com.example.uks.unit.services;

import com.example.uks.dto.organisation.OrganisationCreateDTO;
import com.example.uks.dto.organisation.OrganisationUpdateDTO;
import com.example.uks.dto.user.MemberDTO;
import com.example.uks.model.Organisation;
import com.example.uks.model.Repository;
import com.example.uks.model.User;
import com.example.uks.repositories.OrganisationRepository;
import com.example.uks.repositories.TeamRepository;
import com.example.uks.repositories.UserRepository;
import com.example.uks.services.OrganisationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrganisationServiceTest {

    @InjectMocks
    private OrganisationService organisationService;

    @Mock
    private OrganisationRepository organisationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TeamRepository teamRepository;

    private User owner;
    private Organisation organisation;

    @BeforeEach
    void setUp(){
        owner = new User();
        owner.setId(1);

        organisation = new Organisation();
        organisation.setId(1);
        organisation.setName("Org");
        organisation.setOwner(owner);
        organisation.setMembers(new HashSet<>(List.of(owner)));
        organisation.setRepositories(new HashSet<>());
        organisation.setDeactivated(false);
    }

//    @Test
//    void updateOrganisation() {
//        OrganisationUpdateDTO dto = new OrganisationUpdateDTO();
//        dto.setOwnerId(1);
//        dto.setDescription("Desc");
//        dto.setImage("image.png");
//
//        when(organisationRepository.findByIdAndOwner_Id(1, 1)).thenReturn(Optional.of(organisation));
//
//        Organisation retuned = organisationService.updateOrganisation(1, dto);
//
//        assertEquals("Desc", retuned.getDescription());
//        assertEquals("image.png", retuned.getImage());
//    }

    @Test
    void deactivateOrganisation() {
        Repository repository = new Repository();
        repository.setDeleted(false);
        organisation.getRepositories().add(repository);

        when(organisationRepository.findByIdAndOwner_Id(1,1)).thenReturn(Optional.of(organisation));

        organisationService.deactivateOrganisation(1, 1);

        assertTrue(organisation.getDeactivated());
        assertTrue(organisation.getMembers().isEmpty());
        assertTrue(repository.getDeleted());
        verify(organisationRepository).save(organisation);
    }

    @Test
    void createOrganisation() {
        OrganisationCreateDTO dto = new OrganisationCreateDTO();
        dto.setOwnerId(1);
        dto.setName("Org");
        dto.setDescription("Desc");
        dto.setImage("image.png");

        when(organisationRepository.existsByName("Org")).thenReturn(false);
        when(userRepository.findById(1)).thenReturn(Optional.of(owner));
        when(organisationRepository.save(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        Organisation returned = organisationService.createOrganisation(dto);

        assertEquals("Org", returned.getName());
        assertEquals(owner, returned.getOwner());
        assertFalse(returned.getDeactivated());
        assertTrue(returned.getMembers().contains(owner));
    }

    @Test
    void getOrganisationById() {
        when(organisationRepository.findByIdAndDeactivatedFalse(1)).thenReturn(Optional.ofNullable(organisation));

        Organisation returned = organisationService.getOrganisationById(1);
        assertEquals(1, returned.getId());
    }

    @Test
    void getUserOrganisations() {
        when(userRepository.findById(1)).thenReturn(Optional.of(owner));
        when(organisationRepository.findByOwnerAndDeactivatedFalse(owner)).thenReturn(List.of(organisation));
        when(organisationRepository.findByMembersContainsAndDeactivatedFalse(owner)).thenReturn(Collections.emptyList());

        List<Organisation> returned = organisationService.getUserOrganisations(1);
        assertEquals(1, returned.size());
    }

    @Test
    void addMember() {
        User newUser = new User();
        newUser.setId(2);

        when(organisationRepository.findByIdAndDeactivatedFalse(1)).thenReturn(Optional.of(organisation));
        when(userRepository.findById(2)).thenReturn(Optional.of(newUser));

        organisationService.addMember(1, 1, 2);

        assertTrue(organisation.getMembers().contains(newUser));
        verify(organisationRepository).save(organisation);
    }

    @Test
    void getOrganisationMembers() {
        User member = new User();
        member.setId(2);
        organisation.getMembers().add(member);

        when(organisationRepository.findByIdAndDeactivatedFalse(1)).thenReturn(Optional.of(organisation));

        List<MemberDTO> returned = organisationService.getOrganisationMembers(1, 2);

        assertEquals(2, returned.size());
        assertTrue(returned.stream().anyMatch(dto -> dto.getIsOwner()));
    }
}