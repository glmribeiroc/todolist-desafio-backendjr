package br.com.guilhermecosta.desafiotodomanager.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.guilhermecosta.desafiotodomanager.entity.Todo;
import br.com.guilhermecosta.desafiotodomanager.entity.TodoStatus;

public record TodoResponse(Long id, String title, String description, boolean isFavorite, LocalDateTime completionDate,
    LocalDateTime expectedCompletionDate, TodoStatus status, LocalDateTime createdAt, LocalDateTime updatedAt,
    List<SubTaskResponse> subTasks) {
  public TodoResponse(Todo todo) {
    this(
        todo.getId(),
        todo.getTitle(),
        todo.getDescription(),
        todo.isFavorite(),
        todo.getCompletionDate(),
        todo.getExpectedCompletionDate(),
        todo.getStatus(),
        todo.getCreatedAt(),
        todo.getUpdatedAt(),
        todo.getSubTasks().stream()
            .map(SubTaskResponse::new)
            .collect(Collectors.toList()));
  }
}
