package com.example.uks.dto.repository;

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
    private String category;
    private OwnerDTO owner;


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

        if (repository.getCategory() != null) {
            this.category = repository.getCategory().getFormattedName();
        }

        if (repository.getOwner() != null) {
            User user = repository.getOwner();
            this.owner = new OwnerDTO(user.getId(), user.getFirstName(), user.getLastName());
        } else {
            this.owner = null;
        }
    }

}
