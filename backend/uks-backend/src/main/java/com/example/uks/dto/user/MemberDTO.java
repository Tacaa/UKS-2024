package com.example.uks.dto.user;

import com.example.uks.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Boolean isOwner;

    public MemberDTO(User user, boolean isOwner){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.isOwner = isOwner;
    }
}
