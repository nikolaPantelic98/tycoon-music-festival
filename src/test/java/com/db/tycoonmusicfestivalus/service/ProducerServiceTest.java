package com.db.tycoonmusicfestivalus.service;

import com.db.tycoonmusicfestivalus.entity.Producer;
import com.db.tycoonmusicfestivalus.exception.ResourceNotFoundException;
import com.db.tycoonmusicfestivalus.repository.ProducerRepository;
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
class ProducerServiceTest {

    @Mock
    private ProducerRepository producerRepository;

    @InjectMocks
    private ProducerService producerService;

    @Test
    void shouldGetProducer() {
        List<Producer> producers = new ArrayList<>();
        producers.add(new Producer());

        given(producerRepository.findAll()).willReturn(producers);

        List<Producer> expectedProducers = producerService.getProducer(null);

        Assertions.assertEquals(expectedProducers, producers);
        verify(producerRepository).findAll();
    }

    @Test
    void whenGivenId_shouldReturnProducer_ifFound() {
        Producer producer =
                Producer.builder()
                        .producerId(1L)
                        .firstName("Mark")
                        .lastName("Wayne")
                        .build();

        when(producerRepository.findById(producer.getProducerId())).thenReturn(Optional.of(producer));

        Producer expectedProducer = producerService.getProducerById(producer.getProducerId());

        assertThat(expectedProducer).isSameAs(producer);
        verify(producerRepository).findById(producer.getProducerId());
    }

    @Test
    void shouldThrowException_whenProducerDoesntExist_whileReturningProducer() {
        Producer producer =
                Producer.builder()
                        .producerId(1L)
                        .firstName("Mark")
                        .lastName("Wayne")
                        .build();

        given(producerRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                producerService.getProducerById(producer.getProducerId()));
    }

    @Test
    void whenSaveProducer_shouldReturnProducer() {
        Producer producer =
                Producer.builder()
                        .firstName("Mark")
                        .lastName("Wayne")
                        .build();

        when(producerRepository.save(ArgumentMatchers.any(Producer.class))).thenReturn(producer);

        Producer createdProducer = producerService.registerNewProducer(producer);

        assertThat(createdProducer.getFirstName()).isSameAs(producer.getFirstName());
        assertThat(createdProducer.getLastName()).isSameAs(producer.getLastName());
        verify(producerRepository).save(producer);
    }

    @Test
    void whenGivenId_shouldUpdateProducer_ifFound() {
        Producer producer =
                Producer.builder()
                        .producerId(10L)
                        .firstName("Mark")
                        .lastName("Wayne")
                        .build();

        Producer newProducer =
                Producer.builder()
                        .firstName("John")
                        .lastName("Biggy")
                        .build();

        given(producerRepository.findById(producer.getProducerId())).willReturn(Optional.of(producer));
        producerService.updateProducer(producer.getProducerId(), newProducer);

        verify(producerRepository).save(producer);
        verify(producerRepository).findById(producer.getProducerId());
    }

    @Test
    void shouldThrowException_whenProducerDoesntExist_whileUpdatingProducer() {
        Producer producer =
                Producer.builder()
                        .producerId(10L)
                        .firstName("Mark")
                        .lastName("Wayne")
                        .build();

        Producer newProducer =
                Producer.builder()
                        .producerId(11L)
                        .firstName("John")
                        .lastName("Biggy")
                        .build();

        given(producerRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                producerService.updateProducer(producer.getProducerId(), newProducer));
    }

    @Test
    void whenGivenId_shouldDeleteProducer_ifFound() {
        Producer producer =
                Producer.builder()
                        .producerId(20L)
                        .firstName("Mark")
                        .lastName("Wayne")
                        .build();

        when(producerRepository.findById(producer.getProducerId())).thenReturn(Optional.of(producer));

        producerService.deleteProducer(producer.getProducerId());
        verify(producerRepository).delete(producer);
    }

    @Test
    void shouldThrowException_whenProducerDoesntExist_whileDeletingProducer() {
        Producer producer =
                Producer.builder()
                        .producerId(21L)
                        .firstName("Mark")
                        .lastName("Wayne")
                        .build();

        given(producerRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                producerService.deleteProducer(producer.getProducerId()));
    }
}