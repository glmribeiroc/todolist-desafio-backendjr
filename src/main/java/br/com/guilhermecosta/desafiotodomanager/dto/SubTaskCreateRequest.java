package br.com.guilhermecosta.desafiotodomanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SubTaskCreateRequest(@NotBlank @Size(min = 5) String name, Long todoId) {

}
