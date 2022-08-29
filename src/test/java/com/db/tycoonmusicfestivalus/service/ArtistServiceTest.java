package com.db.tycoonmusicfestivalus.service;

import com.db.tycoonmusicfestivalus.entity.Artist;
import com.db.tycoonmusicfestivalus.exception.ResourceNotFoundException;
import com.db.tycoonmusicfestivalus.repository.ArtistRepository;
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
class ArtistServiceTest {

    @Mock
    private ArtistRepository artistRepository;

    @InjectMocks
    private ArtistService artistService;

    @Test
    void shouldGetArtist() {
        List<Artist> artists = new ArrayList<>();
        artists.add(new Artist());

        given(artistRepository.findAll()).willReturn(artists);

        List<Artist> expectedArtist = artistService.getArtist(null);

        Assertions.assertEquals(expectedArtist, artists);
        verify(artistRepository).findAll();
    }

    @Test
    void whenGivenId_shouldReturnArtist_ifFound() {
        Artist artist =
                Artist.builder()
                        .artistId(1L)
                        .firstName("Marshall")
                        .lastName("Mathers")
                        .build();

        when(artistRepository.findById(artist.getArtistId())).thenReturn(Optional.of(artist));

        Artist expectedArtist = artistService.getArtistById(artist.getArtistId());

        assertThat(expectedArtist).isSameAs(artist);
        verify(artistRepository).findById(artist.getArtistId());
    }

    @Test
    void shouldThrowException_whenArtistDoesntExist_whileReturningArtist() {
        Artist artist =
                Artist.builder()
                        .artistId(1L)
                        .firstName("Marshall")
                        .lastName("Mathers")
                        .build();

        given(artistRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                artistService.getArtistById(artist.getArtistId()));
    }

    @Test
    void whenSaveArtist_shouldReturnArtist() {
        Artist artist =
                Artist.builder()
                        .firstName("Marshall")
                        .lastName("Mathers")
                        .build();

        when(artistRepository.save(ArgumentMatchers.any(Artist.class))).thenReturn(artist);

        Artist createdArtist = artistService.registerNewArtist(artist);

        assertThat(createdArtist.getFirstName()).isSameAs(artist.getFirstName());
        assertThat(createdArtist.getLastName()).isSameAs(artist.getLastName());
        verify(artistRepository).save(artist);
    }

    @Test
    void whenGivenId_shouldUpdateArtist_ifFound() {
        Artist artist =
                Artist.builder()
                        .artistId(10L)
                        .firstName("Marshall")
                        .lastName("Mathers")
                        .build();

        Artist newArtist =
                Artist.builder()
                        .firstName("Tupac")
                        .lastName("Shakur")
                        .build();

        given(artistRepository.findById(artist.getArtistId())).willReturn(Optional.of(artist));
        artistService.updateArtist(artist.getArtistId(), newArtist);

        verify(artistRepository).save(artist);
        verify(artistRepository).findById(artist.getArtistId());
    }

    @Test
    void shouldThrowException_whenArtistDoesntExist_whileUpdatingArtist() {
        Artist artist =
                Artist.builder()
                        .artistId(10L)
                        .firstName("Marshall")
                        .lastName("Mathers")
                        .build();

        Artist newArtist =
                Artist.builder()
                        .artistId(11L)
                        .firstName("Tupac")
                        .lastName("Shakur")
                        .build();

        given(artistRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                artistService.updateArtist(artist.getArtistId(), newArtist));
    }

    @Test
    void whenGivenId_shouldDeleteArtist_ifFound() {
        Artist artist =
                Artist.builder()
                        .artistId(20L)
                        .firstName("Marshall")
                        .lastName("Mathers")
                        .build();

        when(artistRepository.findById(artist.getArtistId())).thenReturn(Optional.of(artist));

        artistService.deleteArtist(artist.getArtistId());
        verify(artistRepository).delete(artist);
    }

    @Test
    void shouldThrowException_whenArtistDoesntExist_whileDeletingArtist() {
        Artist artist =
                Artist.builder()
                        .artistId(21L)
                        .firstName("Marshall")
                        .lastName("Mathers")
                        .build();

        given(artistRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                artistService.deleteArtist(artist.getArtistId()));
    }
}