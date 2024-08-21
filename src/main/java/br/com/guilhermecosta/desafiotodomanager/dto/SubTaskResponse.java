package br.com.guilhermecosta.desafiotodomanager.dto;

import br.com.guilhermecosta.desafiotodomanager.entity.SubTask;

public record SubTaskResponse(Long id, String name) {
  public SubTaskResponse(SubTask subTask) {
    this(subTask.getId(), subTask.getName());
  }
}
