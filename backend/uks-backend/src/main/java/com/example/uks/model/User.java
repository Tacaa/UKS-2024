package com.example.uks.model;

import com.example.uks.enumeration.Role;
import com.example.uks.enumeration.UserBadge;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users") 
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "joined_date", nullable = false)
    private Date joinedDate;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "password_changed")
    private Boolean passwordChanged;

    @Column(name = "user_badge")
    @Enumerated(EnumType.STRING)
    private UserBadge userBadge;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Repository> repositories = new HashSet<Repository>();

    public void addRepository(Repository repository) {
        repositories.add(repository);
        repository.setOwner(this);
    }

    public void removeRepository(Repository repository) {
        repositories.remove(repository);
        repository.setOwner(null);
    }

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Organisation> organisations = new HashSet<Organisation>();

    public void addOrganisation(Organisation organisation) {
        organisations.add(organisation);
        organisation.setOwner(this);
    }

    public void removeOrganisation(Organisation organisation) {
        organisations.remove(organisation);
        organisation.setOwner(null);
    }

    @ManyToMany(mappedBy = "members", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    private Set<Organisation> organisationsMember = new HashSet<Organisation>();

    @ManyToMany(mappedBy = "members", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    private Set<Organisation> teamMember = new HashSet<Organisation>();

}
