package com.example.uks.services;

import com.example.uks.dto.organisation.UpdateTeamDTO;
import com.example.uks.dto.team.CreateTeamDTO;
import com.example.uks.exceptions.AccessDeniedException;
import com.example.uks.exceptions.OrganisationNotFound;
import com.example.uks.exceptions.TeamNotFoundException;
import com.example.uks.exceptions.UserNotFound;
import com.example.uks.model.Organisation;
import com.example.uks.model.Team;
import com.example.uks.model.User;
import com.example.uks.repositories.OrganisationRepository;
import com.example.uks.repositories.TeamRepository;
import com.example.uks.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeamServiceTest {

    @Mock
    private OrganisationRepository organisationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    private Organisation organisation;
    private User user;
    private Team team;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1);

        organisation = new Organisation();
        organisation.setId(100);
        organisation.setOwner(user);
        organisation.setDeactivated(false);
        organisation.setMembers(Set.of(user));
        organisation.setTeams(Set.of());

        team = new Team();
        team.setId(200);
        team.setName("Dev Team");
        team.setDescription("Development Team");
        team.setOrganisation(organisation);
        team.setMembers(new HashSet<>());
    }

    @Test
    void getTeamsForMember_returnTeams_userIsOwner() {

        when(organisationRepository.findByIdAndDeactivatedFalse(100)).thenReturn(Optional.of(organisation));

        List<Team> returned = teamService.getTeamsForMember(100, 1);

        assertNotNull(returned);
        verify(organisationRepository).findByIdAndDeactivatedFalse(100);
    }

    @Test
    void getTeamsForMember_returnTeams_userIsNotMemberOrOwner() {

        organisation.setMembers(Set.of());
        when(organisationRepository.findByIdAndDeactivatedFalse(100)).thenReturn(Optional.of(organisation));

        assertThrows(AccessDeniedException.class, ()-> teamService.getTeamsForMember(100,2));
    }

    @Test
    void getTeamsForMember_returnTeams_organisationDoesNotExist() {
        when(organisationRepository.findByIdAndDeactivatedFalse(999)).thenReturn(Optional.empty());

        assertThrows(OrganisationNotFound.class, ()-> teamService.getTeamsForMember(999,1));
    }

    @Test
    void createTeam_userIsOwner() {
        CreateTeamDTO dto = new CreateTeamDTO();
        dto.setOrganisationId(100);
        dto.setOwnerId(1);
        dto.setName("Dev Team");
        dto.setDescription("Development Team");

        when(organisationRepository.findByIdAndDeactivatedFalse(100)).thenReturn(Optional.of(organisation));
        when(teamRepository.save(any(Team.class))).thenReturn(team);

        Team returned = teamService.createTeam(dto);

        assertEquals("Dev Team",returned.getName());
        verify(teamRepository).save(any(Team.class));
    }

    @Test
    void createTeam_accessDenied_userIsNotOwner() {
        CreateTeamDTO dto = new CreateTeamDTO();
        dto.setOrganisationId(100);
        dto.setOwnerId(99);

        when(organisationRepository.findByIdAndDeactivatedFalse(100)).thenReturn(Optional.of(organisation));

        assertThrows(AccessDeniedException.class, ()-> teamService.createTeam(dto));
    }

    @Test
    void createTeam_organisationNotFound() {
        CreateTeamDTO dto = new CreateTeamDTO();
        dto.setOrganisationId(100);
        dto.setOwnerId(1);

        when(organisationRepository.findByIdAndDeactivatedFalse(100)).thenReturn(Optional.empty());

        assertThrows(OrganisationNotFound.class, ()-> teamService.createTeam(dto));
    }

    @Test
    void addMemberToTeam_success() {
        User member = new User();
        member.setId(2);

        when(teamRepository.findById(200)).thenReturn(Optional.of(team));
        when(userRepository.findById(2)).thenReturn(Optional.of(member));

        teamService.addMemberToTeam(200, 2);

        assertTrue(team.getMembers().contains(member));
        verify(teamRepository).save(team);
    }

    @Test
    void addMemberToTeam_teamNotFound(){
        when(teamRepository.findById(123)).thenReturn(Optional.empty());

        assertThrows(TeamNotFoundException.class, ()-> teamService.addMemberToTeam(123,1));
    }

    @Test
    void addMemberToTeam_userNotFound(){
        when(teamRepository.findById(200)).thenReturn(Optional.of(team));
        when(userRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(UserNotFound.class, ()-> teamService.addMemberToTeam(200,99));
    }

    @Test
    void updateTeam_updateNameAndDesc(){
        UpdateTeamDTO dto = new UpdateTeamDTO();
        dto.setName("Updated Name");
        dto.setDescription("Updated Desc");

        when(teamRepository.findById(200)).thenReturn(Optional.of(team));

        teamService.updateTeam(200, dto);

        assertEquals("Updated Name", team.getName());
        assertEquals("Updated Desc", team.getDescription());
        verify(teamRepository).save(team);
    }

    @Test
    void updateTeam_teamNotFound() {
        UpdateTeamDTO dto = new UpdateTeamDTO();
        when(teamRepository.findById(404)).thenReturn(Optional.empty());

        assertThrows(TeamNotFoundException.class, () -> teamService.updateTeam(404, dto));
    }
}