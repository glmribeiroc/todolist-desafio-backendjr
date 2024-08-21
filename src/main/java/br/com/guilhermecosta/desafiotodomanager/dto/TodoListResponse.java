package br.com.guilhermecosta.desafiotodomanager.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.guilhermecosta.desafiotodomanager.entity.TodoList;

public record TodoListResponse(Long id, String title, List<TodoResponse> todoList) {
  public TodoListResponse(TodoList todoList) {
    this(todoList.getId(),
        todoList.getTitle(),
        todoList.getTodosList().stream()
            .map(TodoResponse::new)
            .collect(Collectors.toList()));
  }
}
