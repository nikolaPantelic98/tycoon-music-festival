package com.db.tycoonmusicfestivalus.service;

import com.db.tycoonmusicfestivalus.entity.Album;
import com.db.tycoonmusicfestivalus.entity.Artist;
import com.db.tycoonmusicfestivalus.exception.ResourceNotFoundException;
import com.db.tycoonmusicfestivalus.repository.AlbumRepository;
import com.db.tycoonmusicfestivalus.repository.ArtistRepository;
import com.db.tycoonmusicfestivalus.serviceinterface.AlbumServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService implements AlbumServiceInterface {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }


    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @Override
    public Album getAlbumById(Long albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(()-> new ResourceNotFoundException("Album with an id " + albumId + " doesn't exist."));
    }

    @Override
    public Album addNewAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Album updateAlbum(Long albumId, Album albumUpdatedDetails) {
        Album updatedAlbum = albumRepository.findById(albumId)
                .orElseThrow(()-> new ResourceNotFoundException("Album with an id " + albumId + " doesn't exist."));
        updatedAlbum.setName(albumUpdatedDetails.getName());
        updatedAlbum.setDateOfRelease(albumUpdatedDetails.getDateOfRelease());
        updatedAlbum.setNumberOfSongs(albumUpdatedDetails.getNumberOfSongs());
        updatedAlbum.setLength(albumUpdatedDetails.getLength());
        updatedAlbum.setReviewScore(albumUpdatedDetails.getReviewScore());
        return albumRepository.save(updatedAlbum);
    }

    @Override
    public void deleteAlbum(Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(()-> new ResourceNotFoundException("Album with an id " + albumId + " doesn't exist."));
        albumRepository.delete(album);
    }

    @Override
    public Album assignAlbumToArtist(Long albumId, Long artistId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(()-> new ResourceNotFoundException("Artist with an id " + albumId + " doesn't exist."));
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(()-> new ResourceNotFoundException("Label with an id " + artistId + " doesn't exist."));
        album.assignArtist(artist);
        return albumRepository.save(album);
    }
}
