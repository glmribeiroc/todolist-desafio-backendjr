package br.com.guilhermecosta.desafiotodomanager.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "todo")
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NotEmpty
  @Column(nullable = false)
  private String title;

  @NotEmpty
  @Column(nullable = false)
  private String description;

  @Column(name = "completion_date")
  private LocalDateTime completionDate;

  @Column(name = "expected_completion_date")
  private LocalDateTime expectedCompletionDate;

  @Enumerated(EnumType.STRING)
  private TodoStatus status;

  @Column(name = "is_favorite")
  private boolean isFavorite;

  @ManyToOne
  @JoinColumn(name = "todo_list_id")
  private TodoList todoList;

  @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL)
  private List<SubTask> subTasks;

  @CreationTimestamp
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public Todo() {
  }

  public Todo(String title, String description, LocalDateTime expectedcCompletionDate, boolean isFavorite,
      TodoList todoList) {
    this.title = title;
    this.description = description;
    this.expectedCompletionDate = expectedcCompletionDate;
    this.isFavorite = isFavorite;
    this.todoList = todoList;
    this.status = TodoStatus.PENDING;
  }

  public Todo(String title, String description, LocalDateTime expectedCompletionDate, boolean isFavorite,
      TodoList todoList,
      List<SubTask> subTasks) {
    this.title = title;
    this.description = description;
    this.expectedCompletionDate = expectedCompletionDate;
    this.isFavorite = isFavorite;
    this.todoList = todoList;
    this.subTasks = subTasks;
    this.status = TodoStatus.PENDING;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getCompletionDate() {
    return completionDate;
  }

  public void setCompletionDate(LocalDateTime completionDate) {
    this.completionDate = completionDate;
  }

  public LocalDateTime getExpectedCompletionDate() {
    return expectedCompletionDate;
  }

  public void setExpectedCompletionDate(LocalDateTime expectedcCompletionDate) {
    this.expectedCompletionDate = expectedcCompletionDate;
  }

  public TodoStatus getStatus() {
    return status;
  }

  public void setStatus(TodoStatus status) {
    this.status = status;
  }

  public TodoList getTodoList() {
    return todoList;
  }

  public void setTodoList(TodoList todoList) {
    this.todoList = todoList;
  }

  public List<SubTask> getSubTasks() {
    return subTasks;
  }

  public void setSubTasks(List<SubTask> subTasks) {
    this.subTasks = subTasks;
  }

  public boolean isFavorite() {
    return isFavorite;
  }

  public void setFavorite(boolean isFavorite) {
    this.isFavorite = isFavorite;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

}
