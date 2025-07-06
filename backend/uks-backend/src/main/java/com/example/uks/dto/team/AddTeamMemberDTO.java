package com.example.uks.dto.team;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddTeamMemberDTO {
    private Integer memberId;
    private Integer teamId;
}