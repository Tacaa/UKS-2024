package com.example.uks.dto.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtAuthenticationRequest {
    private String username;
    private String password;
}
