package com.example.uks.controllers;

import com.example.uks.dto.organisation.UpdateTeamDTO;
import com.example.uks.dto.team.AddTeamMemberDTO;
import com.example.uks.dto.team.CreateTeamDTO;
import com.example.uks.dto.team.TeamDTO;
import com.example.uks.exceptions.AccessDeniedException;
import com.example.uks.exceptions.OrganisationNotFound;
import com.example.uks.model.Team;
import com.example.uks.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/{orgId}")
    public ResponseEntity<Map<String, Object>> getOrganisationTeams(
            @PathVariable Integer orgId,
            @RequestParam Integer userId) {

        Map<String, Object> response = new HashMap<>();

        try {
            List<Team> teams = teamService.getTeamsForMember(orgId, userId);
            List<TeamDTO> dtoList = teams.stream()
                    .map(TeamDTO::from)
                    .collect(Collectors.toList());

            response.put("message", "");
            response.put("data", dtoList);
            return ResponseEntity.ok(response);

        } catch (OrganisationNotFound e) {
            response.put("message", "Organisation not found");
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (AccessDeniedException e) {
            response.put("message", e.getMessage());
            response.put("data", null);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);

        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createTeam(
            @RequestBody CreateTeamDTO dto) {

        Map<String, Object> response = new HashMap<>();
        try {
            Team team = teamService.createTeam(dto);
            response.put("message", "Team successfully created");
            response.put("data", TeamDTO.from(team));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (OrganisationNotFound e) {
            response.put("message", "Organisation not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        } catch (AccessDeniedException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);

        } catch (IllegalArgumentException e) {
            response.put("message", "Invalid team permission: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        } catch (Exception e) {
            response.put("message", "Unexpected server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/add_member")
    public ResponseEntity<Map<String, Object>> addMemberToTeam(
            @RequestBody AddTeamMemberDTO dto) {

        teamService.addMemberToTeam(dto.getTeamId(), dto.getMemberId());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Member added to team");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<Map<String, Object>> updateTeam(
            @PathVariable Integer teamId,
            @RequestBody UpdateTeamDTO dto) {

        teamService.updateTeam(teamId, dto);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Team updated successfully");
        return ResponseEntity.ok(response);
    }
}
