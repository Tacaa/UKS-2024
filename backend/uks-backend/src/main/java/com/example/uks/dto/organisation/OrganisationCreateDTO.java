package com.example.uks.dto.organisation;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganisationCreateDTO {
    //all fields must be filled
    private String name;
    private String description;
    private String image;
    private Integer ownerId;
}
