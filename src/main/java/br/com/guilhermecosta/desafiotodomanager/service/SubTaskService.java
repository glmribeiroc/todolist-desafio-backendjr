package br.com.guilhermecosta.desafiotodomanager.service;

import org.springframework.stereotype.Service;

import br.com.guilhermecosta.desafiotodomanager.dto.SubTaskCreateRequest;
import br.com.guilhermecosta.desafiotodomanager.entity.SubTask;
import br.com.guilhermecosta.desafiotodomanager.entity.Todo;
import br.com.guilhermecosta.desafiotodomanager.repository.SubTaskRepository;
import br.com.guilhermecosta.desafiotodomanager.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SubTaskService {

  private final SubTaskRepository subTaskRepository;
  private final TodoRepository todoRepository;

  public SubTaskService(SubTaskRepository subTaskRepository, TodoRepository todoRepository) {
    this.subTaskRepository = subTaskRepository;
    this.todoRepository = todoRepository;
  }

  public void add(SubTaskCreateRequest subTaskCreateRequest) {
    Todo todo = todoRepository.findById(subTaskCreateRequest.todoId())
        .orElseThrow(() -> new EntityNotFoundException("Todo not found."));

    SubTask subTask = new SubTask(subTaskCreateRequest.name(), todo);
    subTaskRepository.save(subTask);
  }
}
