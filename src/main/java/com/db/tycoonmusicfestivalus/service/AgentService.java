package com.db.tycoonmusicfestivalus.service;

import com.db.tycoonmusicfestivalus.entity.Agent;
import com.db.tycoonmusicfestivalus.exception.ResourceNotFoundException;
import com.db.tycoonmusicfestivalus.repository.AgentRepository;
import com.db.tycoonmusicfestivalus.serviceinterface.AgentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService implements AgentServiceInterface {

    private final AgentRepository agentRepository;

    @Autowired
    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }


    @Override
    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    @Override
    public Agent getAgentById(Long agentId) {
        return agentRepository.findById(agentId)
                .orElseThrow(()-> new ResourceNotFoundException("The agent with an id " + agentId + " doesn't exist."));
    }

    @Override
    public Agent addNewAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    @Override
    public Agent updateAgent(Long agentId, Agent agentUpdatedDetails) {
        Agent updatedAgent = agentRepository.findById(agentId)
                .orElseThrow(()-> new ResourceNotFoundException("The agent with an id " + agentId + " doesn't exist."));
        updatedAgent.setFirstName(agentUpdatedDetails.getFirstName());
        updatedAgent.setLastName(agentUpdatedDetails.getLastName());
        updatedAgent.setDateOfBirth(agentUpdatedDetails.getDateOfBirth());
        updatedAgent.setPlaceOfBirth(agentUpdatedDetails.getPlaceOfBirth());
        updatedAgent.setYearsActive(agentUpdatedDetails.getYearsActive());
        return agentRepository.save(updatedAgent);
    }

    @Override
    public void deleteAgent(Long agentId) {
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(()-> new ResourceNotFoundException("The agent with an id " + agentId + " doesn't exist."));
        agentRepository.delete(agent);
    }
}
