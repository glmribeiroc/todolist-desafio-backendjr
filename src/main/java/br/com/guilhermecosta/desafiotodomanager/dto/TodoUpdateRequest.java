package br.com.guilhermecosta.desafiotodomanager.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Size;

public record TodoUpdateRequest(@Size(min = 5) String title, String description, LocalDateTime expectedCompletionDate) {

}
