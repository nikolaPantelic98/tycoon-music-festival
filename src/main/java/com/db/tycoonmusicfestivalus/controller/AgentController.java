package com.db.tycoonmusicfestivalus.controller;

import com.db.tycoonmusicfestivalus.entity.Agent;
import com.db.tycoonmusicfestivalus.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agents")
public class AgentController {

    private final AgentService agentService;

    @Autowired
    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping
    public List<Agent> getAgents() {
        return agentService.getAllAgents();
    }

    @GetMapping("{agentId}")
    public ResponseEntity<Agent> getAgentById(@PathVariable Long agentId) {
        Agent agent = agentService.getAgentById(agentId);
        return ResponseEntity.ok(agent);
    }

    @PostMapping
    public Agent addNewAgent(@RequestBody Agent agent) {
        return agentService.addNewAgent(agent);
    }

    @PutMapping("{agentId}")
    public ResponseEntity<Agent> updateAgent(@PathVariable Long agentId, @RequestBody Agent agentUpdatedDetails) {
        Agent agent = agentService.updateAgent(agentId, agentUpdatedDetails);
        return ResponseEntity.ok(agent);
    }

    @DeleteMapping("{agentId}")
    public ResponseEntity<Agent> deleteAgent(@PathVariable Long agentId) {
        agentService.deleteAgent(agentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
