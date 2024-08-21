package br.com.guilhermecosta.desafiotodomanager.repository;

import static br.com.guilhermecosta.desafiotodomanager.common.TodoConstans.TODO;
import static br.com.guilhermecosta.desafiotodomanager.common.TodoListConstants.TODO_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import br.com.guilhermecosta.desafiotodomanager.entity.Todo;
import br.com.guilhermecosta.desafiotodomanager.entity.TodoList;

@DataJpaTest
public class TodoRepositoryTest {

  @Autowired
  private TodoRepository todoRepository;

  @Autowired
  private TodoListRepository todoListRepository;

  @Autowired
  private TestEntityManager testEntityManager;

  @Test
  public void addTodo_WithValidData_shouldSaveTodoAndAddToTodoList() {
    TodoList todoList = todoListRepository.saveAndFlush(TODO_LIST);

    TODO.setTodoList(todoList);
    Todo todo = todoRepository.save(TODO);

    testEntityManager.flush();

    Todo sut = testEntityManager.find(Todo.class, todo.getId());

    assertThat(sut).isNotNull();
    assertThat(sut.getTitle()).isEqualTo(TODO.getTitle());
    assertThat(sut.getDescription()).isEqualTo(TODO.getDescription());
    assertThat(sut.getCompletionDate()).isEqualTo(TODO.getCompletionDate());
    assertThat(sut.getStatus()).isEqualTo(TODO.getStatus());
    assertThat(sut.isFavorite()).isEqualTo(TODO.isFavorite());
    assertThat(sut.getTodoList()).isEqualTo(todoList);
    assertThat(sut.getCreatedAt()).isNotNull();
    assertThat(sut.getUpdatedAt()).isNotNull();
  }

  @Test
  public void addTodo_WithEmptyTitle_shouldSaveTodoAndAddToTodoList() {
    TodoList todoList = todoListRepository.saveAndFlush(TODO_LIST);
    TODO.setTodoList(todoList);

    Todo emptyTodo = new Todo(null, "Description", LocalDateTime.now(), false, todoList);

    assertThatThrownBy(() -> {
      todoRepository.save(emptyTodo);
      testEntityManager.flush();
    }).isInstanceOf(RuntimeException.class);
  }

  @Test
  public void addTodo_WithInvalidTitle_shouldSaveTodoAndAddToTodoList() {
    TodoList todoList = todoListRepository.saveAndFlush(TODO_LIST);
    TODO.setTodoList(todoList);

    Todo invalidTodo = new Todo("", "Description", LocalDateTime.now(), false, todoList);

    assertThatThrownBy(() -> {
      todoRepository.save(invalidTodo);
      testEntityManager.flush();
    }).isInstanceOf(RuntimeException.class);
  }

  @Test
  public void addTodo_WithEmptyDescription_shouldSaveTodoAndAddToTodoList() {
    TodoList todoList = todoListRepository.saveAndFlush(TODO_LIST);
    TODO.setTodoList(todoList);

    Todo emptyTodo = new Todo("Title", null, LocalDateTime.now(), false, todoList);

    assertThatThrownBy(() -> {
      todoRepository.save(emptyTodo);
      testEntityManager.flush();
    }).isInstanceOf(RuntimeException.class);
  }

  @Test
  public void addTodo_WithInvalidDescription_shouldSaveTodoAndAddToTodoList() {
    TodoList todoList = todoListRepository.saveAndFlush(TODO_LIST);
    TODO.setTodoList(todoList);

    Todo emptyTodo = new Todo("Title", "", LocalDateTime.now(), false, todoList);

    assertThatThrownBy(() -> {
      todoRepository.save(emptyTodo);
      testEntityManager.flush();
    }).isInstanceOf(RuntimeException.class);
  }

}
