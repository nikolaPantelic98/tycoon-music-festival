package com.db.tycoonmusicfestivalus.serviceinterface;

import com.db.tycoonmusicfestivalus.entity.Song;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SongServiceInterface {

    List<Song> getAllSongs();
    Song getSongById(Long songId);
    Song addNewSong(Song song);
    Song updateSong(Long songId, Song songUpdatedDetails);
    void deleteSong(Long songId);
    Song assignSongToAlbum(Long songId, Long albumId);
    Song assignSongToProducer(Long songId, Long producerId);
}
