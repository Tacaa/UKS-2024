package com.example.uks.dto.user;

import com.example.uks.enumeration.UserBadge;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BadgeDTO {
    private UserBadge userBadge;

    public BadgeDTO(UserBadge userBadge){
        this.userBadge = userBadge;
    }
}
