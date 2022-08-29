package com.db.tycoonmusicfestivalus.controller;

import com.db.tycoonmusicfestivalus.entity.Songwriter;
import com.db.tycoonmusicfestivalus.service.SongwriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/songwriters")
public class SongwriterController {

    private final SongwriterService songwriterService;

    @Autowired
    public SongwriterController(SongwriterService songwriterService) {
        this.songwriterService = songwriterService;
    }

    @GetMapping
    public List<Songwriter> getAllSongwriters() {
        return songwriterService.getAllSongwriters();
    }

    @GetMapping("{songwriterId}")
    public ResponseEntity<Songwriter> getSongwriterById(@PathVariable Long songwriterId) {
        Songwriter songwriter = songwriterService.getSongwriterById(songwriterId);
        return ResponseEntity.ok(songwriter);
    }

    @PostMapping
    public Songwriter addNewSongWriter(@RequestBody Songwriter songwriter) {
        return songwriterService.addNewSongwriter(songwriter);
    }

    @PutMapping("{songwriterId}")
    public ResponseEntity<Songwriter> updateSongwriter(@PathVariable Long songwriterId, @RequestBody Songwriter songwriterUpdatedDetails) {
        Songwriter songwriter = songwriterService.updateSongwriter(songwriterId, songwriterUpdatedDetails);
        return ResponseEntity.ok(songwriter);
    }

    @DeleteMapping("{songwriterId}")
    public ResponseEntity<Songwriter> deleteSongwriter(@PathVariable Long songwriterId) {
        songwriterService.deleteSongwriter(songwriterId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{songwriterId}/songs/{songId}")
    public Songwriter connectSongwriterToSong(@PathVariable Long songwriterId, @PathVariable Long songId) {
        return songwriterService.connectSongwriterToSong(songwriterId, songId);
    }
}
