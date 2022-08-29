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
        name = "label",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "name_unique",
                        columnNames = "name"
                ),
                @UniqueConstraint(
                        name = "website_unique",
                        columnNames = "website"
                )
        }
)
public class Label {

    @Id
    @SequenceGenerator(
            name = "label_sequence",
            sequenceName = "label_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "label_sequence"
    )
    private Long labelId;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "country_of_origin",
            nullable = false
    )
    private String countryOfOrigin;
    @Column(
            name = "location",
            nullable = false
    )
    private String location;
    @Column(
            name = "website"
    )
    private String website;

    @JsonIgnore
    @OneToMany(mappedBy = "label")
    @ToString.Exclude
    private List<Artist> artists;

    @JsonIgnore
    @OneToMany(mappedBy = "label")
    @ToString.Exclude
    private List<Producer> producers;
}
