package br.com.guilhermecosta.desafiotodomanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.guilhermecosta.desafiotodomanager.entity.Todo;
import jakarta.transaction.Transactional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
  @Modifying
  @Transactional
  @Query("DELETE FROM Todo t WHERE t.todoList.id = :todoListId AND t.status = 'COMPLETED'")
  void deleteCompletedByTodoListId(Long todoListId);
}
