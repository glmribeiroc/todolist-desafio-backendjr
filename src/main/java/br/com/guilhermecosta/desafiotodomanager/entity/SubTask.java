package br.com.guilhermecosta.desafiotodomanager.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "subtask")
public class SubTask {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "todo_id", nullable = false)
  private Todo todo;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  public SubTask() {
  }

  public SubTask(String name, Todo todo) {
    this.name = name;
    this.todo = todo;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String title) {
    this.name = title;
  }

  public Todo getTodo() {
    return todo;
  }

  public void setTodo(Todo todo) {
    this.todo = todo;
  }

}
