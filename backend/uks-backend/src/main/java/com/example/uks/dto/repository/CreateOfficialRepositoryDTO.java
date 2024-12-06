package com.example.uks.dto.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOfficialRepositoryDTO {
    private CreateRepositoryDTO createRepositoryDTO;
    private String prefix;
}
