package br.com.guilhermecosta.desafiotodomanager.service;

import static br.com.guilhermecosta.desafiotodomanager.common.TodoConstans.TODO;
import static br.com.guilhermecosta.desafiotodomanager.common.TodoConstans.TODO_LIST;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.guilhermecosta.desafiotodomanager.dto.TodoCreateRequest;
import br.com.guilhermecosta.desafiotodomanager.dto.TodoResponse;
import br.com.guilhermecosta.desafiotodomanager.dto.TodoUpdateIsFavoriteRequest;
import br.com.guilhermecosta.desafiotodomanager.dto.TodoUpdateRequest;
import br.com.guilhermecosta.desafiotodomanager.dto.TodoUpdateStatusRequest;
import br.com.guilhermecosta.desafiotodomanager.entity.Todo;
import br.com.guilhermecosta.desafiotodomanager.entity.TodoStatus;
import br.com.guilhermecosta.desafiotodomanager.repository.TodoListRepository;
import br.com.guilhermecosta.desafiotodomanager.repository.TodoRepository;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {
  @InjectMocks
  private TodoService todoService;

  @Mock
  private TodoRepository todoRepository;

  @Mock
  private TodoListRepository todoListRepository;

  @Test
  public void addTodo_WithValidData_shouldSaveTodoAndAddToTodoList() {
    when(todoListRepository.findById(1L)).thenReturn(Optional.of(TODO_LIST));

    when(todoRepository.save(any(Todo.class))).thenReturn(TODO);

    todoService.add(new TodoCreateRequest(TODO.getTitle(), TODO.getDescription(),
        TODO.getExpectedCompletionDate(), false, 1L));

    verify(todoRepository).save(any(Todo.class));
    verify(todoListRepository).save(TODO_LIST);
    assertThat(TODO_LIST.getTodosList()).contains(TODO);
  }

  @Test
  public void addTodo_WithInvalidData_ThrowsException() {
    when(todoListRepository.findById(1L)).thenReturn(Optional.of(TODO_LIST));

    when(todoRepository.save(any(Todo.class))).thenThrow(RuntimeException.class);

    assertThatThrownBy(() -> todoService.add(new TodoCreateRequest("", TODO.getDescription(),
        TODO.getExpectedCompletionDate(), false, 1L))).isInstanceOf(RuntimeException.class);
  }

  @Test
  public void updateTodo_WithValidData_ShouldUpdateTodoAndReturnTodoResponse() {
    TodoUpdateRequest updateRequest = new TodoUpdateRequest("New Title", "New Description", LocalDateTime.now());

    when(todoRepository.findById(1L)).thenReturn(Optional.of(TODO));
    when(todoRepository.save(TODO)).thenReturn(TODO);

    TodoResponse sut = todoService.update(1L, updateRequest);

    verify(todoRepository).findById(1L);
    verify(todoRepository).save(TODO);

    assertThat(sut.title()).isEqualTo(updateRequest.title());
    assertThat(sut.description()).isEqualTo(updateRequest.description());
    assertThat(sut.expectedcCompletionDate()).isEqualTo(updateRequest.expectedCompletionDate());
  }

  @Test
  public void updateTodoStatus_WithValidData_ShouldUpdateTodoStatusAndReturnTodoResponse() {
    when(todoRepository.findById(1L)).thenReturn(Optional.of(TODO));
    when(todoRepository.save(TODO)).thenReturn(TODO);

    TodoResponse sut = todoService.updateStatus(1L, new TodoUpdateStatusRequest(TodoStatus.COMPLETED));

    verify(todoRepository).findById(1L);
    verify(todoRepository).save(TODO);

    assertThat(sut.status()).isEqualTo(TodoStatus.COMPLETED);
    assertThat(TODO.getCompletionDate()).isNotNull();
  }

  @Test
  public void updateTodoIsFavorite_WithValidData_ShouldUpdateFavoriteStatus() {
    TodoUpdateIsFavoriteRequest favoriteRequest = new TodoUpdateIsFavoriteRequest(true);

    when(todoRepository.findById(1L)).thenReturn(Optional.of(TODO));
    when(todoRepository.save(TODO)).thenReturn(TODO);

    TodoResponse response = todoService.updateIsFavorite(1L, favoriteRequest);

    verify(todoRepository).findById(1L);
    verify(todoRepository).save(TODO);
    assertThat(response.isFavorite()).isTrue();
  }

  @Test
  public void deleteTodo_WithValidId_ShouldDeleteTodo() {
    when(todoRepository.findById(1L)).thenReturn(Optional.of(TODO));

    todoService.delete(1L);

    verify(todoRepository).findById(1L);
    verify(todoRepository).delete(TODO);
  }

}
