package com.db.tycoonmusicfestivalus.serviceinterface;

import com.db.tycoonmusicfestivalus.entity.Collective;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CollectiveServiceInterface {

    List<Collective> getCollective(String name);
    Collective getCollectiveById(Long collectiveId);
    Collective addNewCollective(Collective collective);
    Collective updateCollective(Long collectiveId, Collective collectiveUpdatedDetails);
    void deleteCollective(Long collectiveId);
}
