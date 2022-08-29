package com.db.tycoonmusicfestivalus.service;

import com.db.tycoonmusicfestivalus.entity.Agent;
import com.db.tycoonmusicfestivalus.entity.Artist;
import com.db.tycoonmusicfestivalus.entity.Collective;
import com.db.tycoonmusicfestivalus.entity.Label;
import com.db.tycoonmusicfestivalus.exception.ResourceNotFoundException;
import com.db.tycoonmusicfestivalus.repository.AgentRepository;
import com.db.tycoonmusicfestivalus.repository.ArtistRepository;
import com.db.tycoonmusicfestivalus.repository.CollectiveRepository;
import com.db.tycoonmusicfestivalus.repository.LabelRepository;
import com.db.tycoonmusicfestivalus.serviceinterface.ArtistServiceInterface;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService implements ArtistServiceInterface {

    private final ArtistRepository artistRepository;
    private final LabelRepository labelRepository;
    private final AgentRepository agentRepository;
    private final CollectiveRepository collectiveRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository, LabelRepository labelRepository,
                         AgentRepository agentRepository, CollectiveRepository collectiveRepository) {
        this.artistRepository = artistRepository;
        this.labelRepository = labelRepository;
        this.agentRepository = agentRepository;
        this.collectiveRepository = collectiveRepository;
    }


    @Override
    public List<Artist> getArtist(String nickname) {
        if (Strings.isEmpty(nickname)) {
            return artistRepository.findAll();
        } else {
            return artistRepository.findArtistByNickname(nickname);
        }
    }

    @Override
    public Artist getArtistById(Long artistId) {
        return artistRepository.findById(artistId)
                .orElseThrow(() -> new ResourceNotFoundException("Artist with an id " + artistId + " doesn't exist."));
    }

    @Override
    public Artist registerNewArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public Artist updateArtist(Long artistId, Artist artistUpdatedDetails) {
        Artist updatedArtist = artistRepository.findById(artistId)
                .orElseThrow(() -> new ResourceNotFoundException("Artist with an id " + artistId + " doesn't exist."));
        updatedArtist.setFirstName(artistUpdatedDetails.getFirstName());
        updatedArtist.setLastName(artistUpdatedDetails.getLastName());
        updatedArtist.setNickname(artistUpdatedDetails.getNickname());
        updatedArtist.setDateOfBirth(artistUpdatedDetails.getDateOfBirth());
        updatedArtist.setPlaceOfBirth(artistUpdatedDetails.getPlaceOfBirth());
        updatedArtist.setYearsActive(artistUpdatedDetails.getYearsActive());
        updatedArtist.setWebsite(artistUpdatedDetails.getWebsite());
        return artistRepository.save(updatedArtist);
    }

    @Override
    public void deleteArtist(Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new ResourceNotFoundException("Artist with an id " + artistId + "doesn't exist."));
        artistRepository.delete(artist);
    }

    @Override
    public Artist assignArtistToLabel(Long artistId, Long labelId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(()-> new ResourceNotFoundException("Artist with an id " + artistId + " doesn't exist."));
        Label label = labelRepository.findById(labelId)
                .orElseThrow(()-> new ResourceNotFoundException("Label with an id " + labelId + " doesn't exist."));
        artist.assignLabel(label);
        return artistRepository.save(artist);
    }

    @Override
    public Artist assignArtistToAgent(Long artistId, Long agentId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(()-> new ResourceNotFoundException("The artist with an id " + artistId + " doesn't exist."));
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(()-> new ResourceNotFoundException("The agent with an id " + agentId + " doesn't exist."));
        artist.assignAgent(agent);
        return artistRepository.save(artist);
    }

    @Override
    public Artist assignArtistToCollective(Long artistId, Long collectiveId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(()-> new ResourceNotFoundException("The artist with an id " + artistId + " doesn't exist."));
        Collective collective = collectiveRepository.findById(collectiveId)
                .orElseThrow(()-> new ResourceNotFoundException("The collective with an id " + collectiveId + " doesn't exist."));
        artist.assignCollective(collective);
        return artistRepository.save(artist);
    }
}
