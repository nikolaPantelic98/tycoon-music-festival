package com.db.tycoonmusicfestivalus.controller;

import com.db.tycoonmusicfestivalus.entity.Collective;
import com.db.tycoonmusicfestivalus.service.CollectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/collectives")
public class CollectiveController {

    private final CollectiveService collectiveService;

    @Autowired
    public CollectiveController(CollectiveService collectiveService) {
        this.collectiveService = collectiveService;
    }


    @GetMapping
    public List<Collective> getCollective(@RequestParam(value = "name", required = false) String name) {
        return collectiveService.getCollective(name);
    }

    @GetMapping("{collectiveId}")
    public ResponseEntity<Collective> getCollectiveById(@PathVariable Long collectiveId) {
        Collective collective = collectiveService.getCollectiveById(collectiveId);
        return ResponseEntity.ok(collective);
    }

    @PostMapping
    public Collective addNewCollective(@RequestBody Collective collective) {
        return collectiveService.addNewCollective(collective);
    }

    @PutMapping("{collectiveId}")
    public ResponseEntity<Collective> updateCollective(@PathVariable Long collectiveId, @RequestBody Collective collectiveUpdatedDetails) {
        Collective collective = collectiveService.updateCollective(collectiveId, collectiveUpdatedDetails);
        return ResponseEntity.ok(collective);
    }

    @DeleteMapping("{collectiveId}")
    public ResponseEntity<Collective> deleteCollective(@PathVariable Long collectiveId) {
        collectiveService.deleteCollective(collectiveId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
