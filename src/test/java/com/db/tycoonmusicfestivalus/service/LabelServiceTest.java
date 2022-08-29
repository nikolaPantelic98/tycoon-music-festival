package com.db.tycoonmusicfestivalus.service;

import com.db.tycoonmusicfestivalus.entity.Label;
import com.db.tycoonmusicfestivalus.exception.ResourceNotFoundException;
import com.db.tycoonmusicfestivalus.repository.LabelRepository;
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
class LabelServiceTest {

    @Mock
    private LabelRepository labelRepository;

    @InjectMocks
    private LabelService labelService;

    @Test
    void shouldGetLabel() {
        List<Label> labels = new ArrayList<>();
        labels.add(new Label());

        given(labelRepository.findAll()).willReturn(labels);

        List<Label> expectedLabels = labelService.getLabel(null);

        Assertions.assertEquals(expectedLabels, labels);
        verify(labelRepository).findAll();
    }

    @Test
    void whenGivenId_shouldReturnLabel_ifFound() {
        Label label =
                Label.builder()
                        .labelId(1L)
                        .name("Deathrow Records")
                        .build();

        when(labelRepository.findById(label.getLabelId())).thenReturn(Optional.of(label));

        Label expectedLabel = labelService.getLabelById(label.getLabelId());

        assertThat(expectedLabel).isSameAs(label);
        verify(labelRepository).findById(label.getLabelId());
    }

    @Test
    void shouldThrowException_whenLabelDoesntExist_whileReturningLabel() {
        Label label =
                Label.builder()
                        .labelId(1L)
                        .name("Deathrow Records")
                        .build();

        given(labelRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                labelService.getLabelById(label.getLabelId()));
    }

    @Test
    void whenSaveLabel_shouldReturnLabel() {
        Label label =
                Label.builder()
                        .name("Atlantic Records")
                        .build();

        when(labelRepository.save(ArgumentMatchers.any(Label.class))).thenReturn(label);

        Label createdLabel = labelService.addNewLabel(label);

        assertThat(createdLabel.getName()).isSameAs(label.getName());
        verify(labelRepository).save(label);
    }

    @Test
    void whenGivenId_shouldUpdateLabel_ifFound() {
        Label label =
                Label.builder()
                        .labelId(10L)
                        .name("Deathrow Records")
                        .build();

        Label newLabel =
                Label.builder()
                        .name("Atlantic Records")
                        .build();

        given(labelRepository.findById(label.getLabelId())).willReturn(Optional.of(label));
        labelService.updateLabel(label.getLabelId(), newLabel);

        verify(labelRepository).save(label);
        verify(labelRepository).findById(label.getLabelId());
    }

    @Test
    void shouldThrowException_whenLabelDoesntExist_whileUpdatingLabel() {
        Label label =
                Label.builder()
                        .labelId(10L)
                        .name("Deathrow Records")
                        .build();

        Label newLabel =
                Label.builder()
                        .labelId(11L)
                        .name("Atlantic Records")
                        .build();

        given(labelRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                labelService.updateLabel(label.getLabelId(), newLabel));
    }

    @Test
    void whenGivenId_shouldDeleteLabel_ifFound() {
        Label label =
                Label.builder()
                        .labelId(20L)
                        .name("Deathrow Records")
                        .build();

        when(labelRepository.findById(label.getLabelId())).thenReturn(Optional.of(label));

        labelService.deleteLabel(label.getLabelId());
        verify(labelRepository).delete(label);
    }

    @Test
    void shouldThrowException_whenLabelDoesntExist_whileDeletingLabel() {
        Label label =
                Label.builder()
                        .labelId(21L)
                        .name("Deathrow Records")
                        .build();

        given(labelRepository.findById(anyLong())).willReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () ->
                labelService.deleteLabel(label.getLabelId()));
    }
}