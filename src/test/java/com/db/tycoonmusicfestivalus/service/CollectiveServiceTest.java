package com.db.tycoonmusicfestivalus.service;

import com.db.tycoonmusicfestivalus.entity.Collective;
import com.db.tycoonmusicfestivalus.exception.ResourceNotFoundException;
import com.db.tycoonmusicfestivalus.repository.CollectiveRepository;
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
class CollectiveServiceTest {

    @Mock
    private CollectiveRepository collectiveRepository;

    @InjectMocks
    private CollectiveService collectiveService;

    @Test
    void shouldGetCollective() {
        List<Collective> collectives = new ArrayList<>();
        collectives.add(new Collective());

        given(collectiveRepository.findAll()).willReturn(collectives);

        List<Collective> expectedCollectives = collectiveService.getCollective(null);

        Assertions.assertEquals(expectedCollectives, collectives);
        verify(collectiveRepository).findAll();
    }

    @Test
    void whenGivenId_shouldReturnCollective_ifFound() {
        Collective collective =
                Collective.builder()
                        .collectiveId(1L)
                        .name("KQR")
                        .build();

        when(collectiveRepository.findById(collective.getCollectiveId())).thenReturn(Optional.of(collective));

        Collective expectedCollective = collectiveService.getCollectiveById(collective.getCollectiveId());

        assertThat(expectedCollective).isSameAs(collective);
        verify(collectiveRepository).findById(collective.getCollectiveId());
    }

    @Test
    void shouldThrowException_whenCollectiveDoesntExist_whileReturningCollective() {
        Collective collective =
                Collective.builder()
                        .collectiveId(1L)
                        .name("KQR")
                        .build();

        given(collectiveRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                collectiveService.getCollectiveById(collective.getCollectiveId()));
    }

    @Test
    void whenSaveCollective_shouldReturnCollective() {
        Collective collective =
                Collective.builder()
                        .name("PJR")
                        .build();

        when(collectiveRepository.save(ArgumentMatchers.any(Collective.class))).thenReturn(collective);

        Collective createdCollective = collectiveService.addNewCollective(collective);

        assertThat(createdCollective.getName()).isSameAs(collective.getName());
        verify(collectiveRepository).save(collective);
    }

    @Test
    void whenGivenId_shouldUpdateCollective_ifFound() {
        Collective collective =
                Collective.builder()
                        .collectiveId(10L)
                        .name("KQR")
                        .build();

        Collective newCollective =
                Collective.builder()
                        .name("PJR")
                        .build();

        given(collectiveRepository.findById(collective.getCollectiveId())).willReturn(Optional.of(collective));
        collectiveService.updateCollective(collective.getCollectiveId(), newCollective);

        verify(collectiveRepository).save(collective);
        verify(collectiveRepository).findById(collective.getCollectiveId());
    }

    @Test
    void shouldThrowException_whenCollectiveDoesntExist_whileUpdatingCollective() {
        Collective collective =
                Collective.builder()
                        .collectiveId(10L)
                        .name("KQR")
                        .build();

        Collective newCollective =
                Collective.builder()
                        .collectiveId(11L)
                        .name("PJR")
                        .build();

        given(collectiveRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                collectiveService.updateCollective(collective.getCollectiveId(), newCollective));
    }

    @Test
    void whenGivenId_shouldDeleteCollective_ifFound() {
        Collective collective =
                Collective.builder()
                        .collectiveId(20L)
                        .name("KQR")
                        .build();

        when(collectiveRepository.findById(collective.getCollectiveId())).thenReturn(Optional.of(collective));

        collectiveService.deleteCollective(collective.getCollectiveId());
        verify(collectiveRepository).delete(collective);
    }

    @Test
    void shouldThrowException_whenCollectiveDoesntExist_whileDeletingCollective() {
        Collective collective =
                Collective.builder()
                        .collectiveId(21L)
                        .name("KQR")
                        .build();

        given(collectiveRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                collectiveService.deleteCollective(collective.getCollectiveId()));
    }
}