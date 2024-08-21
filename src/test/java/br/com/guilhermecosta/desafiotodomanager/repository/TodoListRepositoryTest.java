package br.com.guilhermecosta.desafiotodomanager.repository;

import static br.com.guilhermecosta.desafiotodomanager.common.TodoListConstants.TODO_LIST;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import br.com.guilhermecosta.desafiotodomanager.entity.TodoList;

@DataJpaTest
public class TodoListRepositoryTest {

  @Autowired
  private TodoListRepository todoListRepository;

  @Autowired
  private TestEntityManager testEntityManager;

  @Test
  public void createTodoList_withValidData_ReturnsTodoList() {
    TodoList todoList = todoListRepository.save(TODO_LIST);

    testEntityManager.flush();

    TodoList sut = testEntityManager.find(TodoList.class, todoList.getId());

    assertThat(sut).isNotNull();
    assertThat(sut.getTitle()).isEqualTo(TODO_LIST.getTitle());
    assertThat(sut.getCreatedAt()).isNotNull();
  }

  @Test
  public void createTodoList_WithEmptyData_ThrowsException() {
    TodoList emptyTodoList = new TodoList();

    assertThatThrownBy(() -> {
      todoListRepository.save(emptyTodoList);
      testEntityManager.flush();
    }).isInstanceOf(RuntimeException.class);
  }

  @Test
  public void createTodoList_WithInvalidData_ThrowsException() {
    TodoList invalidTodoList = new TodoList("");

    assertThatThrownBy(() -> {
      todoListRepository.save(invalidTodoList);
      testEntityManager.flush();
    }).isInstanceOf(RuntimeException.class);
  }
}
