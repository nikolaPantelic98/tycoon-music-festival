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
        name = "song"
)
public class Song {

    @Id
    @SequenceGenerator(
            name = "song_sequence",
            sequenceName = "song_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "song_sequence"
    )
    private Long songId;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "length",
            nullable = false
    )
    private String length;

    @Embedded
    private Genre genre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "album_id",
            referencedColumnName = "albumId"
    )
    private Album album;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "producer_id",
            referencedColumnName = "producerId"
    )
    private Producer producer;

    @JsonIgnore
    @ManyToMany(mappedBy = "songs")
    @ToString.Exclude
    private List<Songwriter> songwriters;

    public void assignAlbum(Album album) {
        this.album = album;
    }

    public void assignProducer(Producer producer) {
        this.producer = producer;
    }

}
