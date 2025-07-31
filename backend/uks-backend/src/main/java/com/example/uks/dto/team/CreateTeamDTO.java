package com.example.uks.dto.team;

import com.example.uks.enumeration.TeamPermission;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTeamDTO {
    private String name;
    private String description;
    private TeamPermission teamPermission;
    private Integer ownerId;
    private Integer organisationId;
}