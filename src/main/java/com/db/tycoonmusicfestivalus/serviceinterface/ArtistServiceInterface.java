package com.db.tycoonmusicfestivalus.serviceinterface;

import com.db.tycoonmusicfestivalus.entity.Artist;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArtistServiceInterface {

    List<Artist> getArtist(String nickname);
    Artist getArtistById(Long artistId);
    Artist registerNewArtist(Artist artist);
    Artist updateArtist(Long artistId, Artist artistUpdatedDetails);
    void deleteArtist(Long artistId);
    Artist assignArtistToLabel(Long artistId, Long labelId);
    Artist assignArtistToAgent(Long artistId, Long agentId);
    Artist assignArtistToCollective(Long artistId, Long collectiveId);
}
