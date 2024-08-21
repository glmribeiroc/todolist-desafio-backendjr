package br.com.guilhermecosta.desafiotodomanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.guilhermecosta.desafiotodomanager.dto.TodoListCreateRequest;
import br.com.guilhermecosta.desafiotodomanager.dto.TodoListResponse;
import br.com.guilhermecosta.desafiotodomanager.entity.TodoList;
import br.com.guilhermecosta.desafiotodomanager.entity.TodoStatus;
import br.com.guilhermecosta.desafiotodomanager.repository.TodoListRepository;
import br.com.guilhermecosta.desafiotodomanager.repository.TodoRepository;

@Service
public class TodoListService {

  private final TodoListRepository todoListRepository;
  private final TodoRepository todoRepository;

  public TodoListService(TodoListRepository todoListRepository, TodoRepository todoRepository) {
    this.todoListRepository = todoListRepository;
    this.todoRepository = todoRepository;
  }

  public TodoList create(TodoListCreateRequest todoListCreateRequest) {
    var todoList = new TodoList(todoListCreateRequest.title());
    return todoListRepository.save(todoList);
  }

  public List<TodoListResponse> listAll(Boolean isFavorite, TodoStatus status) {
    List<TodoList> todoLists = todoListRepository.findAllWithSortedTodos(isFavorite, status);
    return todoLists.stream()
        .map(TodoListResponse::new)
        .collect(Collectors.toList());
  }

  public void deleteCompletedTasks(Long id) {
    todoRepository.deleteCompletedByTodoListId(id);
  }

}
