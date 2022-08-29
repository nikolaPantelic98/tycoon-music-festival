package com.db.tycoonmusicfestivalus.service;

import com.db.tycoonmusicfestivalus.entity.Album;
import com.db.tycoonmusicfestivalus.entity.Producer;
import com.db.tycoonmusicfestivalus.entity.Song;
import com.db.tycoonmusicfestivalus.exception.ResourceNotFoundException;
import com.db.tycoonmusicfestivalus.repository.AlbumRepository;
import com.db.tycoonmusicfestivalus.repository.ProducerRepository;
import com.db.tycoonmusicfestivalus.repository.SongRepository;
import com.db.tycoonmusicfestivalus.serviceinterface.SongServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService implements SongServiceInterface {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final ProducerRepository producerRepository;

    @Autowired
    public SongService(SongRepository songRepository, AlbumRepository albumRepository,
                       ProducerRepository producerRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.producerRepository = producerRepository;
    }


    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @Override
    public Song getSongById(Long songId) {
        return songRepository.findById(songId)
                .orElseThrow(()-> new ResourceNotFoundException("Song with an id " + songId + " doesn't exist."));
    }

    @Override
    public Song addNewSong(Song song) {
        return songRepository.save(song);
    }

    @Override
    public Song updateSong(Long songId, Song songUpdatedDetails) {
        Song updatedSong = songRepository.findById(songId)
                .orElseThrow(()-> new ResourceNotFoundException("Song with an id " + songId + " doesn't exist."));
        updatedSong.setName(songUpdatedDetails.getName());
        updatedSong.setLength(songUpdatedDetails.getLength());
        updatedSong.setGenre(songUpdatedDetails.getGenre());
        return songRepository.save(updatedSong);
    }

    @Override
    public void deleteSong(Long songId) {
        Song song = songRepository.findById(songId)
                .orElseThrow(()-> new ResourceNotFoundException("Song with an id " + songId + " doesn't exist."));
        songRepository.delete(song);
    }

    @Override
    public Song assignSongToAlbum(Long songId, Long albumId) {
        Song song = songRepository.findById(songId)
                .orElseThrow(()-> new ResourceNotFoundException("Artist with an id " + songId + " doesn't exist."));
        Album album = albumRepository.findById(albumId)
                .orElseThrow(()-> new ResourceNotFoundException("Label with an id " + albumId + " doesn't exist."));
        song.assignAlbum(album);
        return songRepository.save(song);
    }

    @Override
    public Song assignSongToProducer(Long songId, Long producerId) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new ResourceNotFoundException("Artist with an id " + songId + " doesn't exist."));
        Producer producer = producerRepository.findById(producerId)
                .orElseThrow(() -> new ResourceNotFoundException("Label with an id " + producerId + " doesn't exist."));
        song.assignProducer(producer);
        return songRepository.save(song);
    }
}
