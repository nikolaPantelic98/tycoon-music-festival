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
        name = "artist",
        uniqueConstraints = @UniqueConstraint(
                name = "nickname_unique",
                columnNames = "nickname"
        )
)
public class Artist {

    @Id
    @SequenceGenerator(
            name = "artist_sequence",
            sequenceName = "artist_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "artist_sequence"
    )
    private Long artistId;
    @Column(
            name = "first_name"
    )
    private String firstName;
    @Column(
            name = "last_name"
    )
    private String lastName;
    @Column(
            name = "nickname",
            nullable = false
    )
    private String nickname;
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
    @Column(
            name = "website"
    )
    private String website;

    @JsonIgnore
    @OneToMany(mappedBy = "artist")
    @ToString.Exclude
    private List<Album> albums;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "label_id",
            referencedColumnName = "labelId"
    )
    private Label label;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "agent_id",
            referencedColumnName = "agentId"
    )
    private Agent agent;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "collective_id",
            referencedColumnName = "collectiveId"
    )
    private Collective collective;


    public void assignLabel(Label label) {
        this.label = label;
    }

    public void assignAgent(Agent agent) {
        this.agent = agent;
    }

    public void assignCollective(Collective collective) {
        this.collective = collective;
    }
}
