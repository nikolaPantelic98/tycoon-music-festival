package com.db.tycoonmusicfestivalus.controller;

import com.db.tycoonmusicfestivalus.entity.Album;
import com.db.tycoonmusicfestivalus.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }


    @GetMapping
    public List<Album> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    @GetMapping("{albumId}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long albumId) {
        Album album = albumService.getAlbumById(albumId);
        return ResponseEntity.ok(album);
    }

    @PostMapping
    public Album addNewAlbum(@RequestBody Album album) {
        return albumService.addNewAlbum(album);
    }

    @PutMapping("{albumId}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long albumId, @RequestBody Album albumUpdatedDetails) {
        Album album = albumService.updateAlbum(albumId, albumUpdatedDetails);
        return ResponseEntity.ok(album);
    }

    @DeleteMapping("{albumId}")
    public ResponseEntity<Album> deleteAlbum(@PathVariable Long albumId) {
        albumService.deleteAlbum(albumId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{albumId}/artists/{artistId}")
    public Album assignAlbumToArtist(@PathVariable Long albumId, @PathVariable Long artistId) {
        return albumService.assignAlbumToArtist(albumId, artistId);
    }
}