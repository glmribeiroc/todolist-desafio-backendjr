package br.com.guilhermecosta.desafiotodomanager.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.guilhermecosta.desafiotodomanager.dto.TodoCreateRequest;
import br.com.guilhermecosta.desafiotodomanager.dto.TodoResponse;
import br.com.guilhermecosta.desafiotodomanager.dto.TodoUpdateIsFavoriteRequest;
import br.com.guilhermecosta.desafiotodomanager.dto.TodoUpdateRequest;
import br.com.guilhermecosta.desafiotodomanager.dto.TodoUpdateStatusRequest;
import br.com.guilhermecosta.desafiotodomanager.entity.Todo;
import br.com.guilhermecosta.desafiotodomanager.entity.TodoList;
import br.com.guilhermecosta.desafiotodomanager.entity.TodoStatus;
import br.com.guilhermecosta.desafiotodomanager.repository.TodoListRepository;
import br.com.guilhermecosta.desafiotodomanager.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TodoService {

  private final TodoListRepository todoListRepository;
  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository, TodoListRepository todoListRepository) {
    this.todoListRepository = todoListRepository;
    this.todoRepository = todoRepository;
  }

  public void add(TodoCreateRequest todoCreateRequest) {
    TodoList todoList = todoListRepository.findById(todoCreateRequest.todoListId())
        .orElseThrow(() -> new IllegalArgumentException("Todo List not found."));

    Todo todo = new Todo(todoCreateRequest.title(), todoCreateRequest.description(),
        todoCreateRequest.expectedCompletionDate(), todoCreateRequest.isFavorite(), todoList);

    var savedTodo = todoRepository.save(todo);

    todoList.getTodosList().add(savedTodo);
    todoListRepository.save(todoList);
  }

  public TodoResponse update(Long id, TodoUpdateRequest todoUpdateRequest) {
    Todo todo = todoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Todo not found."));
    if (todoUpdateRequest.title() != null) {
      todo.setTitle(todoUpdateRequest.title());
    }
    if (todoUpdateRequest.description() != null) {
      todo.setDescription(todoUpdateRequest.description());
    }
    if (todoUpdateRequest.expectedCompletionDate() != null) {
      todo.setExpectedCompletionDate(todoUpdateRequest.expectedCompletionDate());
    }

    var updatedTodo = todoRepository.save(todo);
    return new TodoResponse(updatedTodo);
  }

  public TodoResponse updateStatus(Long id, TodoUpdateStatusRequest updateStatusRequest) {
    Todo todo = todoRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Todo not found."));

    if (updateStatusRequest.status() != todo.getStatus()) {
      todo.setStatus(updateStatusRequest.status());
      todo.setCompletionDate(updateStatusRequest.status() == TodoStatus.COMPLETED ? LocalDateTime.now() : null);
    }

    Todo updatedTodo = todoRepository.save(todo);
    return new TodoResponse(updatedTodo);
  }

  public TodoResponse updateIsFavorite(Long id, TodoUpdateIsFavoriteRequest updateIsFavoriteRequest) {
    Todo todo = todoRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Todo not found."));

    todo.setFavorite(updateIsFavoriteRequest.isFavorite());

    Todo updatedTodo = todoRepository.save(todo);
    return new TodoResponse(updatedTodo);
  }

  public void delete(Long id) {
    Todo todo = todoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Todo not found."));
    todoRepository.delete(todo);
  }
}
