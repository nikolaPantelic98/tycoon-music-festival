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
        name = "producer",
        uniqueConstraints = @UniqueConstraint(
                name = "nickname_unique",
                columnNames = "nickname"
        )
)
public class Producer {

    @Id
    @SequenceGenerator(
            name = "producer_sequence",
            sequenceName = "producer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "producer_sequence"
    )
    private Long producerId;
    @Column(
            name = "first_name"
    )
    private String firstName;
    @Column(
            name = "last_name"
    )
    private String lastName;
    @Column(
            name = "nickname"
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "label_id",
            referencedColumnName = "labelId"
    )
    private Label label;

    @JsonIgnore
    @OneToMany(mappedBy = "producer")
    @ToString.Exclude
    private List<Song> songs;

    public void assignLabel(Label label) {
        this.label = label;
    }
}
