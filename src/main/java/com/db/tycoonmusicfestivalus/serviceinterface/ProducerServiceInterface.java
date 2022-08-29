package com.db.tycoonmusicfestivalus.serviceinterface;

import com.db.tycoonmusicfestivalus.entity.Producer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProducerServiceInterface {

    List<Producer> getProducer(String nickname);
    Producer getProducerById(Long producerId);
    Producer registerNewProducer(Producer producer);
    Producer updateProducer(Long producerId, Producer producerUpdatedDetails);
    void deleteProducer(Long producerId);
    Producer assignProducerToLabel(Long producerId, Long labelId);
}
