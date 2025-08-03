package com.example.uks.dto.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTokenState {
    private String accessToken;
    private Integer expiresIn;
}