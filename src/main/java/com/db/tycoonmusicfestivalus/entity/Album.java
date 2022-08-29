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
        name = "album"
)
public class Album {

    @Id
    @SequenceGenerator(
            name = "album_sequence",
            sequenceName = "album_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "album_sequence"
    )
    private Long albumId;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "date_of_release",
            nullable = false
    )
    private LocalDate dateOfRelease;
    @Column(
            name = "number_of_songs",
            nullable = false
    )
    private Integer numberOfSongs;
    @Column(
            name = "length",
            nullable = false
    )
    private String length;
    @Column(
            name = "review_score",
            nullable = false
    )
    private String reviewScore;

    @JsonIgnore
    @OneToMany(mappedBy = "album")
    @ToString.Exclude
    private List<Song> songs;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "artist_id",
            referencedColumnName = "artistId"
    )
    private Artist artist;

    public void assignArtist(Artist artist) {
        this.artist = artist;
    }
}
