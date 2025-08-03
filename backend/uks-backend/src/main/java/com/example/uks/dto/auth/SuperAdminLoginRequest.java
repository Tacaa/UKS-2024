package com.example.uks.dto.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuperAdminLoginRequest {
    private String username;
    private String password;
    private String newPassword;
}
