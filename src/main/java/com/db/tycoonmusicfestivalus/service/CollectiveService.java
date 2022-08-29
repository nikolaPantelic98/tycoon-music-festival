package com.db.tycoonmusicfestivalus.service;

import com.db.tycoonmusicfestivalus.entity.Collective;
import com.db.tycoonmusicfestivalus.exception.ResourceNotFoundException;
import com.db.tycoonmusicfestivalus.repository.CollectiveRepository;
import com.db.tycoonmusicfestivalus.serviceinterface.CollectiveServiceInterface;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectiveService implements CollectiveServiceInterface {

    private final CollectiveRepository collectiveRepository;

    @Autowired
    public CollectiveService(CollectiveRepository collectiveRepository) {
        this.collectiveRepository = collectiveRepository;
    }


    @Override
    public List<Collective> getCollective(String name) {
        if (Strings.isEmpty(name)) {
            return collectiveRepository.findAll();
        } else {
            return collectiveRepository.findCollectiveByName(name);
        }
    }

    @Override
    public Collective getCollectiveById(Long collectiveId) {
        return collectiveRepository.findById(collectiveId)
                .orElseThrow(()-> new ResourceNotFoundException("The collective with an id " + collectiveId + " doesn't exist."));
    }

    public Collective addNewCollective(Collective collective) {
        return collectiveRepository.save(collective);
    }

    @Override
    public Collective updateCollective(Long collectiveId, Collective collectiveUpdatedDetails) {
        Collective updatedCollective = collectiveRepository.findById(collectiveId)
                .orElseThrow(()-> new ResourceNotFoundException("The collective with an id " + collectiveId + " doesn't exist."));
        updatedCollective.setName(collectiveUpdatedDetails.getName());
        updatedCollective.setOrigin(collectiveUpdatedDetails.getOrigin());
        updatedCollective.setYearsActive(collectiveUpdatedDetails.getYearsActive());
        updatedCollective.setWebsite(collectiveUpdatedDetails.getWebsite());
        return collectiveRepository.save(updatedCollective);
    }

    @Override
    public void deleteCollective(Long collectiveId) {
        Collective collective = collectiveRepository.findById(collectiveId)
                .orElseThrow(()-> new ResourceNotFoundException("The collective with an id " + collectiveId + " doesn't exist."));
        collectiveRepository.delete(collective);
    }
}
