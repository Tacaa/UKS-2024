package com.example.uks.model;


import com.example.uks.enumeration.Badge;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("Official")
public class OfficialRepository extends Repository {

    @Column(name = "prefix", nullable = false, unique = true)
    private String prefix;

    @Column(name = "badge")
    @Enumerated(EnumType.STRING)
    private Badge badge = Badge.DOCKER_OFFICIAL_IMAGE;

}
