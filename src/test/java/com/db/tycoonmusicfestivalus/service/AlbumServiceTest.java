package com.db.tycoonmusicfestivalus.service;

import com.db.tycoonmusicfestivalus.entity.Album;
import com.db.tycoonmusicfestivalus.exception.ResourceNotFoundException;
import com.db.tycoonmusicfestivalus.repository.AlbumRepository;
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
class AlbumServiceTest {

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private AlbumService albumService;

    @Test
    void shouldGetAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albums.add(new Album());

        given(albumRepository.findAll()).willReturn(albums);

        List<Album> expectedAlbums = albumService.getAllAlbums();

        Assertions.assertEquals(expectedAlbums, albums);
        verify(albumRepository).findAll();
    }

    @Test
    void whenGivenId_shouldReturnAlbum_ifFound() {
        Album album =
                Album.builder()
                        .albumId(1L)
                        .name("8 Miles")
                        .build();

        when(albumRepository.findById(album.getAlbumId())).thenReturn(Optional.of(album));

        Album expectedAlbum = albumService.getAlbumById(album.getAlbumId());

        assertThat(expectedAlbum).isSameAs(album);
        verify(albumRepository).findById(album.getAlbumId());
    }

    @Test
    void shouldThrowException_whenAlbumDoesntExist_whileReturningAlbum() {
        Album album =
                Album.builder()
                        .albumId(1L)
                        .name("8 Miles")
                        .build();

        given(albumRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                albumService.getAlbumById(album.getAlbumId()));
    }

    @Test
    void whenSaveAlbum_shouldReturnAlbum() {
        Album album =
                Album.builder()
                        .name("8 Miles")
                        .build();

        when(albumRepository.save(ArgumentMatchers.any(Album.class))).thenReturn(album);

        Album createdAlbum = albumService.addNewAlbum(album);

        assertThat(createdAlbum.getName()).isSameAs(album.getName());
        verify(albumRepository).save(album);
    }

    @Test
    void whenGivenId_shouldUpdateAlbum_ifFound() {
        Album album =
                Album.builder()
                        .albumId(10L)
                        .name("8 Miles")
                        .build();

        Album newAlbum =
                Album.builder()
                        .name("Sunset of LA")
                        .build();

        given(albumRepository.findById(album.getAlbumId())).willReturn(Optional.of(album));
        albumService.updateAlbum(album.getAlbumId(), newAlbum);

        verify(albumRepository).save(album);
        verify(albumRepository).findById(album.getAlbumId());
    }

    @Test
    void shouldThrowException_whenAlbumDoesntExist_whileUpdatingAlbum() {
        Album album =
                Album.builder()
                        .albumId(10L)
                        .name("8 Miles")
                        .build();

        Album newAlbum =
                Album.builder()
                        .albumId(11L)
                        .name("Sunset of LA")
                        .build();

        given(albumRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                albumService.updateAlbum(album.getAlbumId(), newAlbum));
    }

    @Test
    void whenGivenId_shouldDeleteAlbum_ifFound() {
        Album album =
                Album.builder()
                        .albumId(20L)
                        .name("10 Miles")
                        .build();

        when(albumRepository.findById(album.getAlbumId())).thenReturn(Optional.of(album));

        albumService.deleteAlbum(album.getAlbumId());
        verify(albumRepository).delete(album);
    }

    @Test
    void shouldThrowException_whenAlbumDoesntExist_whileDeletingAlbum() {
        Album album =
                Album.builder()
                        .albumId(21L)
                        .name("10 Miles")
                        .build();

        given(albumRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                albumService.deleteAlbum(album.getAlbumId()));
    }
}