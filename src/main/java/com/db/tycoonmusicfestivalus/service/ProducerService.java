package com.db.tycoonmusicfestivalus.service;

import com.db.tycoonmusicfestivalus.entity.Label;
import com.db.tycoonmusicfestivalus.entity.Producer;
import com.db.tycoonmusicfestivalus.exception.ResourceNotFoundException;
import com.db.tycoonmusicfestivalus.repository.LabelRepository;
import com.db.tycoonmusicfestivalus.repository.ProducerRepository;
import com.db.tycoonmusicfestivalus.serviceinterface.ProducerServiceInterface;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerService implements ProducerServiceInterface {

    private final ProducerRepository producerRepository;
    private final LabelRepository labelRepository;

    @Autowired
    public ProducerService(ProducerRepository producerRepository, LabelRepository labelRepository) {
        this.producerRepository = producerRepository;
        this.labelRepository = labelRepository;
    }


    public List<Producer> getProducer(String nickname) {
        if (Strings.isEmpty(nickname)) {
            return producerRepository.findAll();
        } else {
            return producerRepository.findProducerByNickname(nickname);
        }
    }

    @Override
    public Producer getProducerById(Long producerId) {
        return producerRepository.findById(producerId)
                .orElseThrow(() -> new ResourceNotFoundException("Producer with an id " + producerId + " doesn't exist."));
    }

    @Override
    public Producer registerNewProducer(Producer producer) {
        return producerRepository.save(producer);
    }

    @Override
    public Producer updateProducer(Long producerId, Producer producerUpdatedDetails) {
        Producer updatedProducer = producerRepository.findById(producerId)
                .orElseThrow(()-> new ResourceNotFoundException("Producer with an id " + producerId + " doesn't exist."));
        updatedProducer.setFirstName(producerUpdatedDetails.getFirstName());
        updatedProducer.setLastName(producerUpdatedDetails.getLastName());
        updatedProducer.setNickname(producerUpdatedDetails.getNickname());
        updatedProducer.setDateOfBirth(producerUpdatedDetails.getDateOfBirth());
        updatedProducer.setPlaceOfBirth(producerUpdatedDetails.getPlaceOfBirth());
        return producerRepository.save(updatedProducer);
    }

    @Override
    public void deleteProducer(Long producerId) {
        Producer producer = producerRepository.findById(producerId)
                .orElseThrow(()-> new ResourceNotFoundException("Producer with an id " + producerId + " doesn't exist."));
        producerRepository.delete(producer);
    }

    @Override
    public Producer assignProducerToLabel(Long producerId, Long labelId) {
        Producer producer = producerRepository.findById(producerId)
                .orElseThrow(()-> new ResourceNotFoundException("Artist with an id " + producerId + " doesn't exist."));
        Label label = labelRepository.findById(labelId)
                .orElseThrow(()-> new ResourceNotFoundException("Label with an id " + labelId + " doesn't exist."));
        producer.assignLabel(label);
        return producerRepository.save(producer);
    }
}
