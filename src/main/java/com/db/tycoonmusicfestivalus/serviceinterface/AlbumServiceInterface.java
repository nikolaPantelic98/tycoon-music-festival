package com.db.tycoonmusicfestivalus.serviceinterface;

import com.db.tycoonmusicfestivalus.entity.Album;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AlbumServiceInterface {

    List<Album> getAllAlbums();
    Album getAlbumById(Long albumId);
    Album addNewAlbum(Album album);
    Album updateAlbum(Long albumId, Album albumUpdatedDetails);
    void deleteAlbum(Long albumId);
    Album assignAlbumToArtist(Long albumId, Long artistId);
}
