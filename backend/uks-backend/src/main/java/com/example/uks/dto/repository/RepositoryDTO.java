package com.example.uks.dto.repository;

import com.example.uks.dto.organisation.RepositoryOrganisationDTO;
import com.example.uks.dto.user.OwnerDTO;
import com.example.uks.enumeration.Category;
import com.example.uks.enumeration.Visibility;
import com.example.uks.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.example.uks.model.Repository;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryDTO {
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
    private OwnerDTO owner;
    private RepositoryOrganisationDTO organisation;


    public RepositoryDTO(Repository repository) {
        this.id = repository.getId();
        this.name = repository.getName();
        this.namespace = repository.getNamespace();
        this.description = repository.getDescription();
        this.visibility = repository.getVisibility();
        this.created = repository.getCreated();
        this.updated = repository.getUpdated();
        this.star = repository.getStar();
        this.personal = repository.getPersonal();
        this.deleted = repository.getDeleted();
        this.category = repository.getCategory();

        if (repository.getCategory() != null) {
            this.categoryString = repository.getCategory().getFormattedName();
        }

        if (repository.getOwner() != null) {
            User user = repository.getOwner();
            this.owner = new OwnerDTO(user.getId(), user.getFirstName(), user.getLastName());
        } else {
            this.owner = null;
        }

        if (repository.getOrganisation() != null){
            this.organisation = new RepositoryOrganisationDTO(repository.getOrganisation().getId(), repository.getOrganisation().getName());
        }else{
           this.organisation = null;
        }
    }

}
