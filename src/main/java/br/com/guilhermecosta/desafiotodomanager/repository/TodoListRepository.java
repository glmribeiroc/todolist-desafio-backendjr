package br.com.guilhermecosta.desafiotodomanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.guilhermecosta.desafiotodomanager.entity.TodoList;
import br.com.guilhermecosta.desafiotodomanager.entity.TodoStatus;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
  @Query("SELECT tl FROM TodoList tl LEFT JOIN FETCH tl.todosList t WHERE "
      + "(:status IS NULL OR t.status = :status) AND"
      + "(:isFavorite IS NULL OR t.isFavorite = :isFavorite) "
      + "ORDER BY t.isFavorite DESC, t.createdAt ASC")
  List<TodoList> findAllWithSortedTodos(Boolean isFavorite, TodoStatus status);
}
