package com.example.uks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "organisations")
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "deactivated", nullable = false)
    private Boolean deactivated;

    @Column(name = "image", nullable = true)
    private String image;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "organisations_members", joinColumns = @JoinColumn(name = "organisation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "member_id", referencedColumnName = "id"))
    private Set<User> members = new HashSet<User>();

    @OneToMany(mappedBy = "organisation", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Team> teams = new HashSet<Team>();

    public void addTeam(Team team) {
        teams.add(team);
        team.setOrganisation(this);
    }

    public void removeTeam(Team team) {
        teams.remove(team);
        team.setOrganisation(null);
    }

    @OneToMany(mappedBy = "organisation", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Repository> repositories = new HashSet<Repository>();

    public void addRepository(Repository repository) {
        repositories.add(repository);
        repository.setOrganisation(this);
    }

    public void removeRepository(Repository repository) {
        repositories.remove(repository);
        repository.setOrganisation(null);
    }

}
