package com.example.uks.dto.organisation;

import com.example.uks.model.Organisation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryOrganisationDTO {
    private Integer id;
    private String name;

    public RepositoryOrganisationDTO(Organisation organisation){
        this.id = organisation.getId();
        this.name = organisation.getName();
    }
}
