package com.db.tycoonmusicfestivalus.serviceinterface;

import com.db.tycoonmusicfestivalus.entity.Label;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LabelServiceInterface {

    List<Label> getLabel(String name);
    Label getLabelById(Long labelId);
    Label addNewLabel(Label label);
    Label updateLabel(Long labelId, Label labelUpdatedDetails);
    void deleteLabel(Long labelId);

}
