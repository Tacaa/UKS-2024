package com.example.uks.dto.organisation;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddMemberDTO {
    private Integer memberId;
    private Integer ownerId;
}
