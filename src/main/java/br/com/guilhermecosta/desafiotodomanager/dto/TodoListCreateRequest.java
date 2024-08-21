package br.com.guilhermecosta.desafiotodomanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TodoListCreateRequest(@NotBlank @Size(min = 5) String title) {
}
