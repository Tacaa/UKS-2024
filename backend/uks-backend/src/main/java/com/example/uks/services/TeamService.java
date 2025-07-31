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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getTeamsForMember(Integer orgId, Integer userId) {
        Organisation organisation = organisationRepository.findByIdAndDeactivatedFalse(orgId)
                .orElseThrow(() -> new OrganisationNotFound("Organisation not found"));

        boolean isMember = organisation.getMembers().stream()
                .anyMatch(user -> user.getId().equals(userId));
        boolean isOwner = organisation.getOwner().getId().equals(userId);

        if (!isMember && !isOwner) {
            throw new AccessDeniedException("User is not a member of this organisation.");
        }

        return organisation.getTeams().stream().toList();
    }


    public Team createTeam(CreateTeamDTO dto) {
        Organisation organisation = organisationRepository.findByIdAndDeactivatedFalse(dto.getOrganisationId())
                .orElseThrow(() -> new OrganisationNotFound("Organisation not found"));

        if (!organisation.getOwner().getId().equals(dto.getOwnerId())) {
            throw new AccessDeniedException("Only the organisation owner can create teams.");
        }

        Team team = new Team();
        team.setName(dto.getName());
        team.setDescription(dto.getDescription());
        team.setTeamPermission(dto.getTeamPermission());
        team.setOrganisation(organisation);

        return teamRepository.save(team);
    }

    public void addMemberToTeam(Integer teamId, Integer memberId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException("Team not found"));

        User member = userRepository.findById(memberId)
                .orElseThrow(() -> new UserNotFound("User not found"));

        team.getMembers().add(member);
        teamRepository.save(team);
    }

    public void updateTeam(Integer teamId, UpdateTeamDTO dto) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException("Team not found"));

        if (dto.getName() != null) {
            team.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            team.setDescription(dto.getDescription());
        }

        teamRepository.save(team);
    }

}
