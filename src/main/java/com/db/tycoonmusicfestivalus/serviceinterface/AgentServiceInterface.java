package com.db.tycoonmusicfestivalus.serviceinterface;

import com.db.tycoonmusicfestivalus.entity.Agent;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AgentServiceInterface {

    List<Agent> getAllAgents();
    Agent getAgentById(Long agentId);
    Agent addNewAgent(Agent agent);
    Agent updateAgent(Long agentId, Agent agentUpdatedDetails);
    void deleteAgent(Long agentId);
}
