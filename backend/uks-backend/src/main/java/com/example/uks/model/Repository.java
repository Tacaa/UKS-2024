package com.example.uks.model;

import com.example.uks.enumeration.Visibility;
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
@Table(name = "repositories")
public class Repository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "namespace")
    private String namespace;

    @Column(name = "description")
    private String description;

    @Column(name = "visibility", nullable = false)
    private Visibility visibility;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

    @Column(name = "star")
    private Integer star;

    @Column(name = "personal", nullable = false)
    private Boolean personal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "repository", fetch = FetchType.EAGER,  cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Tag> tags = new HashSet<Tag>();

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.setRepository(this);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.setRepository(null);
    }

}
