package com.example.uks.dto.repository;

import com.example.uks.dto.organisation.RepositoryOrganisationDTO;
import com.example.uks.dto.user.OwnerDTO;
import com.example.uks.enumeration.Category;
import com.example.uks.enumeration.Visibility;
import com.example.uks.model.OfficialRepository;
import com.example.uks.model.User;
import lombok.*;

import com.example.uks.model.Repository;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganisationRepositoryDTO {
    private Integer id;
    private String name;
    private String namespace;
    private String description;
    private Visibility visibility;
    private Date created;
    private Date updated;
    private Integer star;
    private Boolean personal;
    private String categoryString;
    private Category category;
    private Boolean deleted;
    private String prefix;
    private String badgeString;
    private OwnerDTO owner;
    private RepositoryOrganisationDTO organisation;


    public static OrganisationRepositoryDTO from(Repository repo) {
        OrganisationRepositoryDTO dto = OrganisationRepositoryDTO.builder()
                .id(repo.getId())
                .name(repo.getName())
                .namespace(repo.getNamespace())
                .description(repo.getDescription())
                .visibility(repo.getVisibility())
                .created(repo.getCreated())
                .updated(repo.getUpdated())
                .star(repo.getStar())
                .personal(repo.getPersonal())
                .category(repo.getCategory())
                .deleted(repo.getDeleted())
                .build();

        if (repo.getCategory() != null) {
            dto.categoryString = repo.getCategory().getFormattedName();
        }

        if (repo.getOwner() != null) {
            User user = repo.getOwner();
            dto.owner = new OwnerDTO(user.getId(), user.getFirstName(), user.getLastName());
        } else {
            dto.owner = null;
        }

        if (repo.getOrganisation() != null){
            dto.organisation = new RepositoryOrganisationDTO(repo.getOrganisation().getId(), repo.getOrganisation().getName());
        }else{
            dto.organisation = null;
        }

        // Ako je official, dodaj dodatne informacije
        if (repo instanceof OfficialRepository) {
            OfficialRepository official = (OfficialRepository) repo;
            dto.setPrefix(official.getPrefix());
            dto.setBadgeString(official.getBadge().getFormattedName());
        }

        return dto;
    }

}
