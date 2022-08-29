package com.db.tycoonmusicfestivalus.controller;

import com.db.tycoonmusicfestivalus.entity.Producer;
import com.db.tycoonmusicfestivalus.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/producers")
public class ProducerController {

    private final ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }


    @GetMapping
    public List<Producer> getProducer(@RequestParam(value = "nickname", required = false) String nickname) {
        return producerService.getProducer(nickname);
    }

    @GetMapping("{producerId}")
    public ResponseEntity<Producer> getProducerById(@PathVariable Long producerId) {
        Producer producer = producerService.getProducerById(producerId);
        return ResponseEntity.ok(producer);
    }

    @PostMapping
    public Producer registerNewProducer(@RequestBody Producer producer) {
        return producerService.registerNewProducer(producer);
    }

    @PutMapping("{producerId}")
    public ResponseEntity<Producer> updateProducer(@PathVariable Long producerId, @RequestBody Producer producerUpdatedDetails) {
        Producer producer = producerService.updateProducer(producerId, producerUpdatedDetails);
        return ResponseEntity.ok(producer);
    }

    @DeleteMapping("{producerId}")
    public ResponseEntity<Producer> deleteProducer(@PathVariable Long producerId) {
        producerService.deleteProducer(producerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{producerId}/labels/{labelId}")
    public Producer assignProducerToLabel(@PathVariable Long producerId, @PathVariable Long labelId) {
        return producerService.assignProducerToLabel(producerId, labelId);
    }
}
