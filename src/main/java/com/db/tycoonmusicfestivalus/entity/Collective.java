package com.db.tycoonmusicfestivalus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(
        name = "collective",
        uniqueConstraints = @UniqueConstraint(
                name = "name_unique",
                columnNames = "name"
        )
)
public class Collective {

    @Id
    @SequenceGenerator(
            name = "collective_sequence",
            sequenceName = "collective_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "collective_sequence"
    )
    private Long collectiveId;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "origin",
            nullable = false
    )
    private String origin;
    @Column(
            name = "years_active",
            nullable = false
    )
    private String yearsActive;
    @Column(
            name = "website"
    )
    private String website;

    @JsonIgnore
    @OneToMany(mappedBy = "collective")
    @ToString.Exclude
    private List<Artist> artists;
}
