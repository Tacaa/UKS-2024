package com.example.uks.dto.star;

import com.example.uks.model.StarredRepository;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StarredRepositoryDTO {
    private Integer id;
    private Integer userId;
    private Integer repositoryId;
    private LocalDateTime starredAt;

    public static StarredRepositoryDTO from(StarredRepository repository) {
        return StarredRepositoryDTO.builder()
                .id(repository.getId())
                .userId(repository.getUser().getId())
                .repositoryId(repository.getRepository().getId())
                .starredAt(repository.getStarredAt())
                .build();
    }
}
