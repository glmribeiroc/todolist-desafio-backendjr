package br.com.guilhermecosta.desafiotodomanager.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "todo_list")
public class TodoList {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NotEmpty
  @Column(nullable = false)
  private String title;

  @OneToMany(mappedBy = "todoList", cascade = CascadeType.ALL)
  private List<Todo> todosList;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  public TodoList() {
  }

  public TodoList(String title) {
    this.title = title;
  }

  public TodoList(String title, LocalDateTime createdAt) {
    this.title = title;
    this.todosList = new ArrayList<>();
    this.createdAt = createdAt;
  }

  public TodoList(Long id, String title, List<Todo> todosList, LocalDateTime createdAt) {
    this.id = id;
    this.title = title;
    this.todosList = todosList;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Todo> getTodosList() {
    return todosList;
  }

  public void setTodosList(List<Todo> todosList) {
    this.todosList = todosList;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(obj, this);
  }

}
