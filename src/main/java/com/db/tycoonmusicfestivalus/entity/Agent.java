package com.db.tycoonmusicfestivalus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(
        name = "agent"
)
public class Agent {

    @Id
    @SequenceGenerator(
            name = "agent_sequence",
            sequenceName = "agent_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "agent_sequence"
    )
    private Long agentId;
    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;
    @Column(
            name = "date_of_birth",
            nullable = false
    )
    private LocalDate dateOfBirth;
    @Column(
            name = "place_of_birth",
            nullable = false
    )
    private String placeOfBirth;
    @Column(
            name = "years_active",
            nullable = false
    )
    private String yearsActive;

    @JsonIgnore
    @OneToMany(mappedBy = "agent")
    @ToString.Exclude
    private List<Artist> artists;
}
