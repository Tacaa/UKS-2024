package com.example.uks.dto.organisation;

import com.example.uks.dto.user.UserDTO;
import com.example.uks.model.Organisation;
import com.example.uks.model.Team;
import com.example.uks.model.User;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganisationDTO {
    private Integer id;
    private String name;
    private String description;
    private Boolean deactivated;
    private Integer ownerId;
    private String image;
    private List<Integer> members;
    private List<Integer> teams;

    public static OrganisationDTO from(Organisation organisation) {
        if (organisation == null) {
            return null;
        }

        List<Integer> memberIds = organisation.getMembers()
                .stream()
                .map(User::getId)
                .collect(Collectors.toList());

        List<Integer> teamIds = organisation.getTeams()
                .stream()
                .map(Team::getId)
                .collect(Collectors.toList());

        return OrganisationDTO.builder()
                .id(organisation.getId())
                .name(organisation.getName())
                .description(organisation.getDescription())
                .deactivated(organisation.getDeactivated())
                .image(organisation.getImage())
                .ownerId(organisation.getOwner().getId())
                .members(memberIds)
                .teams(teamIds)
                .build();
    }
}
