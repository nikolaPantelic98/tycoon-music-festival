package com.db.tycoonmusicfestivalus.service;

import com.db.tycoonmusicfestivalus.entity.Songwriter;
import com.db.tycoonmusicfestivalus.exception.ResourceNotFoundException;
import com.db.tycoonmusicfestivalus.repository.SongwriterRepository;
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
class SongwriterServiceTest {

    @Mock
    private SongwriterRepository songwriterRepository;

    @InjectMocks
    private SongwriterService songwriterService;

    @Test
    void shouldGetAllSongwriters() {
        List<Songwriter> songwriters = new ArrayList<>();
        songwriters.add(new Songwriter());

        given(songwriterRepository.findAll()).willReturn(songwriters);

        List<Songwriter> expectedSongwriters = songwriterService.getAllSongwriters();

        Assertions.assertEquals(expectedSongwriters, songwriters);
        verify(songwriterRepository).findAll();
    }

    @Test
    void whenGivenId_shouldReturnSongwriter_ifFound() {
        Songwriter songwriter =
                Songwriter.builder()
                        .songwriterId(1L)
                        .firstName("John")
                        .lastName("Hill")
                        .build();

        when(songwriterRepository.findById(songwriter.getSongwriterId())).thenReturn(Optional.of(songwriter));

        Songwriter expectedSongwriter = songwriterService.getSongwriterById(songwriter.getSongwriterId());

        assertThat(expectedSongwriter).isSameAs(songwriter);
        verify(songwriterRepository).findById(songwriter.getSongwriterId());
    }

    @Test
    void shouldThrowException_whenSongwriterDoesntExist_whileReturningSongwriter() {
        Songwriter songwriter =
                Songwriter.builder()
                        .songwriterId(1L)
                        .firstName("John")
                        .lastName("Hill")
                        .build();

        given(songwriterRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                songwriterService.getSongwriterById(songwriter.getSongwriterId()));
    }

    @Test
    void whenSaveSongwriter_shouldReturnSongwriter() {
        Songwriter songwriter =
                Songwriter.builder()
                        .firstName("John")
                        .lastName("Hill")
                        .build();

        when(songwriterRepository.save(ArgumentMatchers.any(Songwriter.class))).thenReturn(songwriter);

        Songwriter createdSongwriter = songwriterService.addNewSongwriter(songwriter);

        assertThat(createdSongwriter.getFirstName()).isSameAs(songwriter.getFirstName());
        assertThat(createdSongwriter.getLastName()).isSameAs(songwriter.getLastName());
        verify(songwriterRepository).save(songwriter);
    }

    @Test
    void whenGivenId_shouldUpdateSongwriter_ifFound() {
        Songwriter songwriter =
                Songwriter.builder()
                        .songwriterId(1L)
                        .firstName("John")
                        .lastName("Hill")
                        .build();

        Songwriter newSongwriter =
                Songwriter.builder()
                        .firstName("James")
                        .lastName("Miller")
                        .build();

        given(songwriterRepository.findById(songwriter.getSongwriterId())).willReturn(Optional.of(songwriter));
        songwriterService.updateSongwriter(songwriter.getSongwriterId(), newSongwriter);

        verify(songwriterRepository).save(songwriter);
        verify(songwriterRepository).findById(songwriter.getSongwriterId());
    }

    @Test
    void shouldThrowException_whenSongwriterDoesntExist_whileUpdatingSongwriter() {
        Songwriter songwriter =
                Songwriter.builder()
                        .songwriterId(10L)
                        .firstName("John")
                        .lastName("Hill")
                        .build();

        Songwriter newSongwriter =
                Songwriter.builder()
                        .songwriterId(11L)
                        .firstName("James")
                        .lastName("Miller")
                        .build();

        given(songwriterRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                songwriterService.updateSongwriter(songwriter.getSongwriterId(), newSongwriter));
    }

    @Test
    void whenGivenId_shouldDeleteSongwriter_ifFound() {
        Songwriter songwriter =
                Songwriter.builder()
                        .songwriterId(20L)
                        .firstName("James")
                        .lastName("Miller")
                        .build();

        when(songwriterRepository.findById(songwriter.getSongwriterId())).thenReturn(Optional.of(songwriter));

        songwriterService.deleteSongwriter(songwriter.getSongwriterId());
        verify(songwriterRepository).delete(songwriter);
    }

    @Test
    void shouldThrowException_whenSongwriterDoesntExist_whileDeletingSongwriter() {
        Songwriter songwriter =
                Songwriter.builder()
                        .songwriterId(21L)
                        .firstName("John")
                        .lastName("Hill")
                        .build();

        given(songwriterRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                songwriterService.deleteSongwriter(songwriter.getSongwriterId()));
    }
}