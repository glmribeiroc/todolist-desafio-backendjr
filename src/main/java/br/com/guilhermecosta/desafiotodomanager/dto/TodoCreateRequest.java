package br.com.guilhermecosta.desafiotodomanager.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TodoCreateRequest(
        @NotBlank @Size(min = 5) String title,
        @NotBlank String description,
        LocalDateTime expectedCompletionDate,
        boolean isFavorite, Long todoListId) {
}
