package com.db.tycoonmusicfestivalus.serviceinterface;

import com.db.tycoonmusicfestivalus.entity.Songwriter;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SongwriterServiceInterface {

    List<Songwriter> getAllSongwriters();
    Songwriter getSongwriterById(Long songwriterId);
    Songwriter addNewSongwriter(Songwriter songwriter);
    Songwriter updateSongwriter(Long songwriterId, Songwriter songwriterUpdatedDetails);
    void deleteSongwriter(Long songwriterId);
    Songwriter connectSongwriterToSong(Long songwriterId, Long songId);
}
