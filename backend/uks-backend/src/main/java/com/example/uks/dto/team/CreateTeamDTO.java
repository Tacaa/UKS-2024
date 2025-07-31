package com.example.uks.dto.team;

import com.example.uks.enumeration.TeamPersmission;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTeamDTO {
    private String name;
    private String description;
    private TeamPersmission teamPersmission;
    private Integer ownerId;
    private Integer organisationId;
}