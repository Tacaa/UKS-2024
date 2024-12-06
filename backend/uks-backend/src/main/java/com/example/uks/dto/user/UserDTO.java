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
public class UserDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Date joinedDate;
    private Role role;
    private UserBadge userBadge;

    public UserDTO(User user){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.username = user.getUsername();
        this.joinedDate = user.getJoinedDate();
        this.userBadge = user.getUserBadge();
    }
}
