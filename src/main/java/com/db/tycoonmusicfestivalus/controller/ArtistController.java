package com.db.tycoonmusicfestivalus.controller;

import com.db.tycoonmusicfestivalus.entity.Artist;
import com.db.tycoonmusicfestivalus.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artists")
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }


    @GetMapping
    public List<Artist> getArtist(@RequestParam(value = "nickname", required = false) String nickname) {
        return artistService.getArtist(nickname);
    }

    @GetMapping("{artistId}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long artistId) {
        Artist artist = artistService.getArtistById(artistId);
        return ResponseEntity.ok(artist);
    }

    @PostMapping
    public Artist registerNewArtist(@RequestBody Artist artist) {
        return artistService.registerNewArtist(artist);
    }

    @PutMapping("{artistId}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Long artistId, @RequestBody Artist artistUpdatedDetails) {
        Artist artist = artistService.updateArtist(artistId, artistUpdatedDetails);
        return ResponseEntity.ok(artist);
    }

    @DeleteMapping("{artistId}")
    public ResponseEntity<Artist> deleteArtist(@PathVariable Long artistId) {
        artistService.deleteArtist(artistId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{artistId}/labels/{labelId}")
    public Artist assignArtistToLabel(@PathVariable Long artistId, @PathVariable Long labelId) {
        return artistService.assignArtistToLabel(artistId, labelId);
    }

    @PutMapping("{artistId}/agents/{agentId}")
    public Artist assignArtistToAgent(@PathVariable Long artistId, @PathVariable Long agentId) {
        return artistService.assignArtistToAgent(artistId, agentId);
    }

    @PutMapping("{artistId}/collectives/{collectiveId}")
    public Artist assignArtistToCollective(@PathVariable Long artistId, @PathVariable Long collectiveId) {
        return artistService.assignArtistToCollective(artistId, collectiveId);
    }
}
