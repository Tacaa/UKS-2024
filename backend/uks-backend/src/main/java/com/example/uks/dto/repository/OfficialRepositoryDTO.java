package com.example.uks.dto.repository;

import com.example.uks.dto.organisation.RepositoryOrganisationDTO;
import com.example.uks.dto.user.OwnerDTO;
import com.example.uks.enumeration.Badge;
import com.example.uks.model.OfficialRepository;
import com.example.uks.model.Repository;
import com.example.uks.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfficialRepositoryDTO {
    private RepositoryDTO repositoryDTO;
    private String prefix;
    private Badge badge;
    private String badgeString;

    public OfficialRepositoryDTO(OfficialRepository repository) {
        this.repositoryDTO = new RepositoryDTO(repository);
        this.prefix = repository.getPrefix();
        this.badge = repository.getBadge();
        this.badgeString = repository.getBadge().getFormattedName();
    }
}
