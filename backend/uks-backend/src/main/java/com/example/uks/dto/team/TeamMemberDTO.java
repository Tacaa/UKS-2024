package com.example.uks.dto.team;

import com.example.uks.model.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamMemberDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;

    public static TeamMemberDTO from(User user) {
        return TeamMemberDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}