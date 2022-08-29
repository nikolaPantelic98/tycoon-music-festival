package com.db.tycoonmusicfestivalus.service;

import com.db.tycoonmusicfestivalus.entity.Song;
import com.db.tycoonmusicfestivalus.entity.Songwriter;
import com.db.tycoonmusicfestivalus.exception.ResourceNotFoundException;
import com.db.tycoonmusicfestivalus.repository.SongRepository;
import com.db.tycoonmusicfestivalus.repository.SongwriterRepository;
import com.db.tycoonmusicfestivalus.serviceinterface.SongwriterServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongwriterService implements SongwriterServiceInterface {

    private final SongwriterRepository songwriterRepository;
    private final SongRepository songRepository;

    @Autowired
    public SongwriterService(SongwriterRepository songwriterRepository, SongRepository songRepository) {
        this.songwriterRepository = songwriterRepository;
        this.songRepository = songRepository;
    }


    @Override
    public List<Songwriter> getAllSongwriters() {
        return songwriterRepository.findAll();
    }

    @Override
    public Songwriter getSongwriterById(Long songwriterId) {
        return songwriterRepository.findById(songwriterId)
                .orElseThrow(()-> new ResourceNotFoundException("The songwriter with an id " + songwriterId + " doesn't exist."));
    }

    @Override
    public Songwriter addNewSongwriter(Songwriter songwriter) {
        return songwriterRepository.save(songwriter);
    }

    @Override
    public Songwriter updateSongwriter(Long songwriterId, Songwriter songwriterUpdatedDetails) {
        Songwriter updatedSongwriter = songwriterRepository.findById(songwriterId)
                .orElseThrow(()-> new ResourceNotFoundException("The songwriter with an id " + songwriterId + " doesn't exist."));
        updatedSongwriter.setFirstName(songwriterUpdatedDetails.getFirstName());
        updatedSongwriter.setLastName(songwriterUpdatedDetails.getLastName());
        updatedSongwriter.setDateOfBirth(songwriterUpdatedDetails.getDateOfBirth());
        updatedSongwriter.setPlaceOfBirth(songwriterUpdatedDetails.getPlaceOfBirth());
        return songwriterRepository.save(updatedSongwriter);
    }

    @Override
    public void deleteSongwriter(Long songwriterId) {
        Songwriter songwriter = songwriterRepository.findById(songwriterId)
                .orElseThrow(()-> new ResourceNotFoundException("The songwriter with an id " + songwriterId + " doesn't exist."));
        songwriterRepository.delete(songwriter);
    }

    @Override
    public Songwriter connectSongwriterToSong(Long songwriterId, Long songId) {
        Songwriter songwriter = songwriterRepository.findById(songwriterId)
                .orElseThrow(()-> new ResourceNotFoundException("The songwriter with an id " + songwriterId + "doesn't exist."));
        Song song = songRepository.findById(songId)
                .orElseThrow(()-> new ResourceNotFoundException("The song with an id " + songId + " doesn't exist."));
        songwriter.connectSongwriterToSong(song);
        return songwriterRepository.save(songwriter);
    }
}
