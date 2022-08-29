package com.db.tycoonmusicfestivalus.service;

import com.db.tycoonmusicfestivalus.entity.Song;
import com.db.tycoonmusicfestivalus.exception.ResourceNotFoundException;
import com.db.tycoonmusicfestivalus.repository.SongRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SongServiceTest {

    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private SongService songService;

    @Test
    void shouldGtAllSongs() {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song());

        given(songRepository.findAll()).willReturn(songs);

        List<Song> expectedSongs = songService.getAllSongs();

        Assertions.assertEquals(expectedSongs, songs);
        verify(songRepository).findAll();
    }

    @Test
    void whenGivenId_shouldReturnSong_ifFound() {
        Song song =
                Song.builder()
                        .songId(1L)
                        .name("Lose yourself")
                        .build();

        when(songRepository.findById(song.getSongId())).thenReturn(Optional.of(song));

        Song expectedSong = songService.getSongById(song.getSongId());

        assertThat(expectedSong).isSameAs(song);
        verify(songRepository).findById(song.getSongId());
    }

    @Test
    void shouldThrowException_whenSongDoesntExist_whileReturningSong() {
        Song song =
                Song.builder()
                        .songId(1L)
                        .name("Lose yourself")
                        .build();

        given(songRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                songService.getSongById(song.getSongId()));
    }

    @Test
    void whenSaveSong_shouldReturnSong() {
        Song song =
                Song.builder()
                        .name("Lose yourself")
                        .build();

        when(songRepository.save(ArgumentMatchers.any(Song.class))).thenReturn(song);

        Song createdSong = songService.addNewSong(song);

        assertThat(createdSong.getName()).isSameAs(song.getName());
        verify(songRepository).save(song);
    }

    @Test
    void whenGivenId_shouldUpdateSong_ifFound() {
        Song song =
                Song.builder()
                        .songId(1L)
                        .name("Lose yourself")
                        .build();

        Song newSong =
                Song.builder()
                        .name("Who Am I")
                        .build();

        given(songRepository.findById(song.getSongId())).willReturn(Optional.of(song));
        songService.updateSong(song.getSongId(), newSong);

        verify(songRepository).save(song);
        verify(songRepository).findById(song.getSongId());
    }

    @Test
    void shouldThrowException_whenSongDoesntExist_whileUpdatingSong() {
        Song song =
                Song.builder()
                        .songId(10L)
                        .name("Lose yourself")
                        .build();

        Song newSong =
                Song.builder()
                        .songId(11L)
                        .name("Who Am I")
                        .build();

        given(songRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                songService.updateSong(song.getSongId(), newSong));
    }

    @Test
    void whenGivenId_shouldDeleteAgent_ifFound() {
        Song song =
                Song.builder()
                        .songId(20L)
                        .name("Who Am I")
                        .build();

        when(songRepository.findById(song.getSongId())).thenReturn(Optional.of(song));

        songService.deleteSong(song.getSongId());
        verify(songRepository).delete(song);
    }

    @Test
    void shouldThrowException_whenSongDoesntExist_whileDeletingSong() {
        Song song =
                Song.builder()
                        .songId(21L)
                        .name("Who Am I")
                        .build();

        given(songRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                songService.deleteSong(song.getSongId()));
    }
}