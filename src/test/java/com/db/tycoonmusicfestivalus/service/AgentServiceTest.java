package com.db.tycoonmusicfestivalus.service;

import com.db.tycoonmusicfestivalus.entity.Agent;
import com.db.tycoonmusicfestivalus.exception.ResourceNotFoundException;
import com.db.tycoonmusicfestivalus.repository.AgentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgentServiceTest {

    @Mock
    private AgentRepository agentRepository;

    @InjectMocks
    private AgentService agentService;

    @Test
    void shouldGetAllAgents() {
        List<Agent> agents = new ArrayList<>();
        agents.add(new Agent());

        given(agentRepository.findAll()).willReturn(agents);

        List<Agent> expectedAgents = agentService.getAllAgents();

        Assertions.assertEquals(expectedAgents, agents);
        verify(agentRepository).findAll();
    }

    @Test
    void whenGivenId_shouldReturnAgent_ifFound() {
        Agent agent =
                Agent.builder()
                        .agentId(1L)
                        .firstName("Mark")
                        .lastName("White")
                        .build();

        when(agentRepository.findById(agent.getAgentId())).thenReturn(Optional.of(agent));

        Agent expectedAgent = agentService.getAgentById(agent.getAgentId());

        assertThat(expectedAgent).isSameAs(agent);
        verify(agentRepository).findById(agent.getAgentId());
    }

    @Test
    void shouldThrowException_whenAgentDoesntExist_whileReturningAgent() {
        Agent agent =
                Agent.builder()
                        .agentId(1L)
                        .firstName("Mark")
                        .lastName("White")
                        .build();

        given(agentRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                agentService.getAgentById(agent.getAgentId()));
    }

    @Test
    void whenSaveAgent_shouldReturnAgent() {
        Agent agent =
                Agent.builder()
                        .firstName("Nicolas")
                        .lastName("White")
                        .build();

        when(agentRepository.save(ArgumentMatchers.any(Agent.class))).thenReturn(agent);

        Agent createdAgent = agentService.addNewAgent(agent);

        assertThat(createdAgent.getFirstName()).isSameAs(agent.getFirstName());
        assertThat(createdAgent.getLastName()).isSameAs(agent.getLastName());
        verify(agentRepository).save(agent);
    }

    @Test
    void whenGivenId_shouldUpdateAgent_ifFound() {
        Agent agent =
                Agent.builder()
                        .agentId(10L)
                        .firstName("Mark")
                        .lastName("White")
                        .build();

        Agent newAgent =
                Agent.builder()
                        .firstName("Nicolas")
                        .lastName("Black")
                        .build();

        given(agentRepository.findById(agent.getAgentId())).willReturn(Optional.of(agent));
        agentService.updateAgent(agent.getAgentId(), newAgent);

        verify(agentRepository).save(agent);
        verify(agentRepository).findById(agent.getAgentId());
    }

    @Test
    void shouldThrowException_whenAgentDoesntExist_whileUpdatingAgent() {
        Agent agent =
                Agent.builder()
                        .agentId(10L)
                        .firstName("Mark")
                        .lastName("White")
                        .build();

        Agent newAgent =
                Agent.builder()
                        .agentId(11L)
                        .firstName("Nicolas")
                        .lastName("Black")
                        .build();

        given(agentRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                agentService.updateAgent(agent.getAgentId(), newAgent));
    }

    @Test
    void whenGivenId_shouldDeleteAgent_ifFound() {
        Agent agent =
                Agent.builder()
                        .agentId(20L)
                        .firstName("Mark")
                        .lastName("Black")
                        .build();

        when(agentRepository.findById(agent.getAgentId())).thenReturn(Optional.of(agent));

        agentService.deleteAgent(agent.getAgentId());
        verify(agentRepository).delete(agent);
    }

    @Test
    void shouldThrowException_whenAgentDoesntExist_whileDeletingAgent() {
        Agent agent =
                Agent.builder()
                        .agentId(21L)
                        .firstName("Mark")
                        .lastName("Black")
                        .build();

        given(agentRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                agentService.deleteAgent(agent.getAgentId()));
    }
}