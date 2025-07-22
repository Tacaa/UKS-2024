package com.example.uks.unit.controllers;

import com.example.uks.controllers.TeamController;
import com.example.uks.dto.organisation.UpdateTeamDTO;
import com.example.uks.dto.team.AddTeamMemberDTO;
import com.example.uks.dto.team.CreateTeamDTO;
import com.example.uks.enumeration.TeamPersmission;
import com.example.uks.exceptions.AccessDeniedException;
import com.example.uks.exceptions.OrganisationNotFound;
import com.example.uks.model.Team;
import com.example.uks.services.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TeamControllerTest {

    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamController teamController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getOrganisationTeams_success() {

        Team team = new Team();
        team.setId(1);
        team.setTeamPersmission(TeamPersmission.READ_WRITE);
        List<Team> teams = List.of(team);
        when(teamService.getTeamsForMember(1, 100)).thenReturn(teams);

        ResponseEntity<Map<String, Object>> response = teamController.getOrganisationTeams(1, 100);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().get("data") instanceof List);
        verify(teamService).getTeamsForMember(1, 100);
    }

    @Test
    void getOrganisationTeams_notFound(){
        when(teamService.getTeamsForMember(1,100)).thenThrow(new OrganisationNotFound("Organisation not found"));

        ResponseEntity<Map<String,Object>> response = teamController.getOrganisationTeams(1, 100);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Organisation not found", response.getBody().get("message"));
        assertNull(response.getBody().get("data"));
        verify(teamService).getTeamsForMember(1,100);
    }

    @Test
    void getOrganisationTeams_accessDenied(){
        when(teamService.getTeamsForMember(1,100)).thenThrow(new AccessDeniedException("Access Denied"));

        ResponseEntity<Map<String,Object>> response = teamController.getOrganisationTeams(1, 100);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals("Access Denied", response.getBody().get("message"));
        assertNull(response.getBody().get("data"));
        verify(teamService).getTeamsForMember(1, 100);
    }

    @Test
    void createTeam_success() {
        CreateTeamDTO dto = new CreateTeamDTO();

        Team team = new Team();
        team.setId(1);
        team.setTeamPersmission(TeamPersmission.READ_WRITE);

        when(teamService.createTeam(dto)).thenReturn(team);

        ResponseEntity<Map<String, Object>> response = teamController.createTeam(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Team successfully created", response.getBody().get("message"));
        verify(teamService).createTeam(dto);
    }

    @Test
    void createTeam_organisationNotFound(){
        CreateTeamDTO dto = new CreateTeamDTO();

        when(teamService.createTeam(dto)).thenThrow(new OrganisationNotFound("Organisation not found"));
        ResponseEntity<Map<String, Object>> response = teamController.createTeam(dto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Organisation not found", response.getBody().get("message"));
        assertNull(response.getBody().get("data"));
        verify(teamService).createTeam(dto);
    }

    @Test
    void createTeam_invalidPermission(){
        CreateTeamDTO dto = new CreateTeamDTO();

        when(teamService.createTeam(dto)).thenThrow(new IllegalArgumentException("ADMIN_ONLY"));

        ResponseEntity<Map<String, Object>> response = teamController.createTeam(dto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid team permission: ADMIN_ONLY", response.getBody().get("message"));
    }

    @Test
    void addMemberToTeam_success() {
        AddTeamMemberDTO dto = new AddTeamMemberDTO();
        dto.setTeamId(5);
        dto.setMemberId(10);

        ResponseEntity<Map<String, Object>> response = teamController.addMemberToTeam(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Member added to team", response.getBody().get("message"));
        verify(teamService).addMemberToTeam(5,10);
    }

    @Test
    void updateTeam_success() {
        UpdateTeamDTO dto = new UpdateTeamDTO();

        ResponseEntity<Map<String, Object>> response = teamController.updateTeam(7, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Team updated successfully", response.getBody().get("message"));
        verify(teamService).updateTeam(7, dto);
    }
}