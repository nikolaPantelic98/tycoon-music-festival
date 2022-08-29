package com.db.tycoonmusicfestivalus.controller;

import com.db.tycoonmusicfestivalus.entity.Song;
import com.db.tycoonmusicfestivalus.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/songs")
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }


    @GetMapping
    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    @GetMapping("{songId}")
    public ResponseEntity<Song> getSongById(@PathVariable Long songId) {
        Song song = songService.getSongById(songId);
        return ResponseEntity.ok(song);
    }

    @PostMapping
    public Song addNewSong(@RequestBody Song song) {
        return songService.addNewSong(song);
    }

    @PutMapping("{songId}")
    public ResponseEntity<Song> updateSong(@PathVariable Long songId, @RequestBody Song songUpdatedDetails) {
        Song song = songService.updateSong(songId, songUpdatedDetails);
        return ResponseEntity.ok(song);
    }

    @DeleteMapping("{songId}")
    public ResponseEntity<Song> deleteSong(@PathVariable Long songId) {
        songService.deleteSong(songId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{songId}/albums/{albumId}")
    public Song assignSongToAlbum(@PathVariable Long songId, @PathVariable Long albumId) {
        return songService.assignSongToAlbum(songId, albumId);
    }

    @PutMapping("{songId}/producers/{producerId}")
    public Song assignSongToProducer(@PathVariable Long songId, @PathVariable Long producerId) {
        return songService.assignSongToProducer(songId, producerId);
    }
}
