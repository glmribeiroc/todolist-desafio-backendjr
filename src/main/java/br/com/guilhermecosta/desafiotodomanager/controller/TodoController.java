package br.com.guilhermecosta.desafiotodomanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.guilhermecosta.desafiotodomanager.dto.TodoCreateRequest;
import br.com.guilhermecosta.desafiotodomanager.dto.TodoResponse;
import br.com.guilhermecosta.desafiotodomanager.dto.TodoUpdateIsFavoriteRequest;
import br.com.guilhermecosta.desafiotodomanager.dto.TodoUpdateRequest;
import br.com.guilhermecosta.desafiotodomanager.dto.TodoUpdateStatusRequest;
import br.com.guilhermecosta.desafiotodomanager.service.TodoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("todo")
public class TodoController {

  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @PostMapping
  public ResponseEntity<?> add(@Valid @RequestBody TodoCreateRequest todoCreateRequest) {
    todoService.add(todoCreateRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }

  @PutMapping("{id}")
  public ResponseEntity<TodoResponse> update(
      @PathVariable Long id,
      @RequestBody TodoUpdateRequest todoUpdateRequest) {
    return ResponseEntity.ok(todoService.update(id, todoUpdateRequest));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    todoService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PatchMapping("{id}/update-status")
  public ResponseEntity<TodoResponse> updateStatus(
      @PathVariable Long id,
      @RequestBody TodoUpdateStatusRequest status) {
    return ResponseEntity.ok(todoService.updateStatus(id, status));
  }

  @PatchMapping("{id}/update-favorite")
  public ResponseEntity<TodoResponse> updateFavorite(
      @PathVariable Long id,
      @RequestBody TodoUpdateIsFavoriteRequest isFavoriteRequest) {
    return ResponseEntity.ok(todoService.updateIsFavorite(id, isFavoriteRequest));
  }

}
