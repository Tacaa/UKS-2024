package com.example.uks.dto.repository;


import com.example.uks.enumeration.Category;
import com.example.uks.enumeration.Visibility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRepositoryDTO {
    private String name;
    private String namespace;
    private String description;
    private Visibility visibility;
    private Boolean personal;
    private Integer ownerId;
    private Integer organisationId;
    private Category category;
}
