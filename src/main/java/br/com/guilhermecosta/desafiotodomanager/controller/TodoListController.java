package br.com.guilhermecosta.desafiotodomanager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.guilhermecosta.desafiotodomanager.dto.TodoListCreateRequest;
import br.com.guilhermecosta.desafiotodomanager.dto.TodoListResponse;
import br.com.guilhermecosta.desafiotodomanager.entity.TodoList;
import br.com.guilhermecosta.desafiotodomanager.entity.TodoStatus;
import br.com.guilhermecosta.desafiotodomanager.service.TodoListService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/todo-list")
public class TodoListController {

  private final TodoListService todoListService;

  public TodoListController(TodoListService todoListService) {
    this.todoListService = todoListService;
  }

  @PostMapping
  public ResponseEntity<TodoList> create(@Valid @RequestBody TodoListCreateRequest todoListCreateRequest) {
    var todoListCreated = todoListService.create(todoListCreateRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(todoListCreated);
  }

  @GetMapping
  public ResponseEntity<List<TodoListResponse>> getAll(
      @RequestParam(value = "isFavorite", required = false) Boolean isFavorite,
      @RequestParam(value = "status", required = false) TodoStatus status) {
    return ResponseEntity.status(HttpStatus.OK).body(todoListService.listAll(isFavorite, status));
  }

  @DeleteMapping("{id}/completed-todos")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    todoListService.deleteCompletedTasks(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}