package com.example.uks.dto.user;

import com.example.uks.enumeration.Role;
import com.example.uks.enumeration.UserBadge;
import com.example.uks.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;

    public UpdateUserDTO(User user){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
    }
}
