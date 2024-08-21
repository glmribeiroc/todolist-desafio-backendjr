package br.com.guilhermecosta.desafiotodomanager.service;

import static br.com.guilhermecosta.desafiotodomanager.common.TodoListConstants.TODO_LIST;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import br.com.guilhermecosta.desafiotodomanager.dto.TodoListCreateRequest;
import br.com.guilhermecosta.desafiotodomanager.dto.TodoListResponse;
import br.com.guilhermecosta.desafiotodomanager.entity.TodoList;
import br.com.guilhermecosta.desafiotodomanager.entity.TodoStatus;
import br.com.guilhermecosta.desafiotodomanager.repository.TodoListRepository;

@ExtendWith(MockitoExtension.class)
public class TodoListServiceTest {
  @InjectMocks
  private TodoListService todoListService;

  @Mock
  private TodoListRepository todoListRepository;

  @Test
  public void createTodoList_WithValidData_ReturnsTodoList() {
    when(todoListRepository.save(TODO_LIST)).thenReturn(TODO_LIST);

    TodoList sut = todoListService.create(new TodoListCreateRequest("NEW TODO LIST TITLE"));

    assertThat(sut).isEqualTo(TODO_LIST);
  }

  @Test
  public void createTodoList_WithInvalidData_ThrowsException() {
    when(todoListRepository.save(new TodoList(""))).thenThrow(RuntimeException.class);

    assertThatThrownBy(() -> todoListService.create(new TodoListCreateRequest("")))
        .isInstanceOf(RuntimeException.class);
  }

  @Test
  public void getAllTodoList_NoFilter_ReturnsTodoList() {
    var todoList = List.of(new TodoList("Title", LocalDateTime.now()));
    when(todoListRepository.findAllWithSortedTodos(null,
        null)).thenReturn(todoList);

    List<TodoListResponse> sut = todoListService.listAll(null, null);

    assertThat(sut).isNotEmpty();
    assertThat(sut).isEqualTo(todoList.stream()
        .map(TodoListResponse::new).collect(Collectors.toList()));
  }

  @Test
  public void getAllTodoList_WithFilter_ReturnsTodoList() {
    var todoList = List.of(new TodoList("Title", LocalDateTime.now()));
    when(todoListRepository.findAllWithSortedTodos(Boolean.TRUE,
        TodoStatus.COMPLETED)).thenReturn(todoList);

    List<TodoListResponse> sut = todoListService.listAll(Boolean.TRUE,
        TodoStatus.COMPLETED);

    assertThat(sut).isNotEmpty();
    assertThat(sut).isEqualTo(todoList.stream()
        .map(TodoListResponse::new).collect(Collectors.toList()));
  }
}
