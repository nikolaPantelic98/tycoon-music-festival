package com.db.tycoonmusicfestivalus.controller;

import com.db.tycoonmusicfestivalus.entity.Label;
import com.db.tycoonmusicfestivalus.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/labels")
public class LabelController {

    private final LabelService labelService;

    @Autowired
    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }


    @GetMapping
    public List<Label> getLabel(@RequestParam(value = "name", required = false) String name) {
        return labelService.getLabel(name);
    }

    @GetMapping("{labelId}")
    public ResponseEntity<Label> getLabelById(@PathVariable Long labelId) {
        Label label = labelService.getLabelById(labelId);
        return ResponseEntity.ok(label);
    }

    @PostMapping
    public Label addNewLabel(@RequestBody Label label) {
        return labelService.addNewLabel(label);
    }

    @PutMapping("{labelId}")
    public ResponseEntity<Label> updateLabel(@PathVariable Long labelId, @RequestBody Label labelUpdatedDetails) {
        Label label = labelService.updateLabel(labelId, labelUpdatedDetails);
        return ResponseEntity.ok(label);
    }

    @DeleteMapping("{labelId}")
    public ResponseEntity<Label> deleteLabel(@PathVariable Long labelId) {
        labelService.deleteLabel(labelId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
