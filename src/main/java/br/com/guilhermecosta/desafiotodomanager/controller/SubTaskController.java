package br.com.guilhermecosta.desafiotodomanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.guilhermecosta.desafiotodomanager.dto.SubTaskCreateRequest;
import br.com.guilhermecosta.desafiotodomanager.service.SubTaskService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("subtask")
public class SubTaskController {

  private final SubTaskService subTaskService;

  public SubTaskController(SubTaskService subTaskService) {
    this.subTaskService = subTaskService;
  }

  @PostMapping
  public ResponseEntity<?> add(@Valid @RequestBody SubTaskCreateRequest subTaskCreateRequest) {
    subTaskService.add(subTaskCreateRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(null);
  }
}
