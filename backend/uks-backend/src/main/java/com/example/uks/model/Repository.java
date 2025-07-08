package com.example.uks.model;

import com.example.uks.enumeration.Category;
import com.example.uks.enumeration.Visibility;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.SoftDelete;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.DiscriminatorType.STRING;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "repositories")
@Inheritance(strategy=SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=STRING)
public class Repository {

    @Id
    //@SequenceGenerator(name = "repositorySequence", sequenceName = "repository_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "namespace")
    private String namespace;

    @Column(name = "description")
    private String description;

    @Column(name = "visibility", nullable = false)
    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

    @Column(name = "star")
    private Integer star;

    @Column(name = "personal", nullable = false)
    private Boolean personal;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;
}
