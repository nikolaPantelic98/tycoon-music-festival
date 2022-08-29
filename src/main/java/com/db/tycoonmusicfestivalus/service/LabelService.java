package com.db.tycoonmusicfestivalus.service;

import com.db.tycoonmusicfestivalus.entity.Label;
import com.db.tycoonmusicfestivalus.exception.ResourceNotFoundException;
import com.db.tycoonmusicfestivalus.repository.LabelRepository;
import com.db.tycoonmusicfestivalus.serviceinterface.LabelServiceInterface;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelService implements LabelServiceInterface {

    private final LabelRepository labelRepository;

    @Autowired
    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }


    @Override
    public List<Label> getLabel(String name) {
        if (Strings.isEmpty(name)) {
            return labelRepository.findAll();
        } else {
            return labelRepository.findLabelByName(name);
        }
    }

    @Override
    public Label getLabelById(Long labelId) {
        return labelRepository.findById(labelId)
                .orElseThrow(()-> new ResourceNotFoundException("Label with an id " + labelId + " doesn't exist."));
    }

    @Override
    public Label addNewLabel(Label label) {
        return labelRepository.save(label);
    }

    @Override
    public Label updateLabel(Long labelId, Label labelUpdatedDetails) {
        Label updatedLabel = labelRepository.findById(labelId)
                .orElseThrow(()-> new ResourceNotFoundException("Label with an id " + labelId + " doesn't exist."));
        updatedLabel.setName(labelUpdatedDetails.getName());
        updatedLabel.setCountryOfOrigin(labelUpdatedDetails.getCountryOfOrigin());
        updatedLabel.setLocation(labelUpdatedDetails.getLocation());
        updatedLabel.setWebsite(labelUpdatedDetails.getWebsite());
        return labelRepository.save(updatedLabel);
    }

    @Override
    public void deleteLabel(Long labelId) {
        Label label = labelRepository.findById(labelId)
                .orElseThrow(()-> new ResourceNotFoundException("Label with an id " + labelId + " doesn't exist."));
        labelRepository.delete(label);
    }
}
