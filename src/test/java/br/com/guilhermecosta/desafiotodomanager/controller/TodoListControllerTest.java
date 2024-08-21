package br.com.guilhermecosta.desafiotodomanager.controller;

import static br.com.guilhermecosta.desafiotodomanager.common.TodoListConstants.TODO_LIST;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.guilhermecosta.desafiotodomanager.dto.TodoListCreateRequest;
import br.com.guilhermecosta.desafiotodomanager.service.TodoListService;

@WebMvcTest(TodoListController.class)
public class TodoListControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TodoListService todoListService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void createTodoList_WithValidData_ReturnsTodoList() throws Exception {
    when(todoListService.create(new TodoListCreateRequest("NEW TODO LIST TITLE"))).thenReturn(TODO_LIST);

    mockMvc
        .perform(MockMvcRequestBuilders.post("/todo-list").content(objectMapper.writeValueAsString(TODO_LIST))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  public void createTodoList_WithEmptyData_ReturnsBadRequest() throws Exception {
    var emptyTodoList = new TodoListCreateRequest(null);

    mockMvc
        .perform(MockMvcRequestBuilders.post("/todo-list").content(objectMapper.writeValueAsString(emptyTodoList))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
  }

  @Test
  public void createTodoList_WithInvalidData_ReturnsBadRequest() throws Exception {
    var invalidTodoList = new TodoListCreateRequest("");

    mockMvc
        .perform(MockMvcRequestBuilders.post("/todo-list").content(objectMapper.writeValueAsString(invalidTodoList))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
  }
}
