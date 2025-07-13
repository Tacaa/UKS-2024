package com.example.uks.dto.auth;

import com.example.uks.enumeration.RoleEnum;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private RoleEnum roleEnum;
}
