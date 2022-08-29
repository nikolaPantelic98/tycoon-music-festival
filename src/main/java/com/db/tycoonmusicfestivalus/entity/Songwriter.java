package com.db.tycoonmusicfestivalus.entity;

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
        name = "songwriter"
)
public class Songwriter {

    @Id
    @SequenceGenerator(
            name = "songwriter_sequence",
            sequenceName = "songwriter_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "songwriter_sequence"
    )
    private Long songwriterId;
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "songwriter_song_mapped",
            joinColumns = @JoinColumn(
                    name = "songwriter_id",
                    referencedColumnName = "songwriterId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "song_id",
                    referencedColumnName = "songId"
            )
    )
    private List<Song> songs;

    public void connectSongwriterToSong(Song song) {
        songs.add(song);
    }
}
