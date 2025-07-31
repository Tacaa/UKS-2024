package com.example.uks.dto.team;

import com.example.uks.model.Team;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamDTO {
    private Integer id;
    private String name;
    private String description;
    private String teamPersmission;
    private List<TeamMemberDTO> members;

    public static TeamDTO from(Team team) {
        List<TeamMemberDTO> memberDTOs = team.getMembers().stream()
                .map(TeamMemberDTO::from)
                .collect(Collectors.toList());

        return TeamDTO.builder()
                .id(team.getId())
                .name(team.getName())
                .description(team.getDescription())
                .teamPersmission(team.getTeamPersmission().name())
                .members(memberDTOs)
                .build();
    }
}
