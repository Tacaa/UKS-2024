package com.example.uks.dto.organisation;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganisationUpdateDTO {
    private String description;
    private String image;
    private Integer ownerId;
}
