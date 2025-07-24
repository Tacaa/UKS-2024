package com.example.uks.dto.tag;

import com.example.uks.model.Tag;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagDTO {
    private Integer id;
    private String name;
    private String dockerPullCommand;
    private String pulled;
    private String pushed;
    private String author;
    private Integer repositoryId;

    public static TagDTO from(Tag tag) {
        return TagDTO.builder()
                .id(tag.getId())
                .name(tag.getName())
                .dockerPullCommand(tag.getDockerPullCommand())
                .pulled(tag.getPulled())
                .pushed(tag.getPushed())
                .author(tag.getAuthor())
                .repositoryId(tag.getRepository() != null ? tag.getRepository().getId() : null)
                .build();
    }
}

