package com.example.uks.dto.repository;

import com.example.uks.enumeration.Category;
import com.example.uks.enumeration.Visibility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRepositoryDTO {
    private String namespace;
    private String description;
    private Visibility visibility;
    private Boolean personal;
    private Category category;
}
