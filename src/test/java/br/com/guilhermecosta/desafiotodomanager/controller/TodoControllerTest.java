package br.com.guilhermecosta.desafiotodomanager.controller;

import static br.com.guilhermecosta.desafiotodomanager.common.TodoConstans.TODO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.guilhermecosta.desafiotodomanager.dto.TodoCreateRequest;
import br.com.guilhermecosta.desafiotodomanager.service.TodoService;

@WebMvcTest(TodoController.class)
public class TodoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TodoService todoService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void createTodo_WithValidData_ReturnsTodoList() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.post("/todo")
            .content(objectMapper.writeValueAsString(new TodoCreateRequest(TODO.getTitle(), TODO.getDescription(),
                TODO.getExpectedCompletionDate(), false, 1L)))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  public void createTodoList_WithEmptyTitle_ReturnsBadRequest() throws Exception {
    var emptyTitle = new TodoCreateRequest(null, TODO.getDescription(),
        TODO.getExpectedCompletionDate(), false, 1L);

    mockMvc
        .perform(MockMvcRequestBuilders.post("/todo").content(objectMapper.writeValueAsString(emptyTitle))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
  }

  @Test
  public void createTodoList_WithInvaidTitle_ReturnsBadRequest() throws Exception {
    var emptyTitle = new TodoCreateRequest("", TODO.getDescription(),
        TODO.getExpectedCompletionDate(), false, 1L);

    mockMvc
        .perform(MockMvcRequestBuilders.post("/todo").content(objectMapper.writeValueAsString(emptyTitle))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
  }

  @Test
  public void createTodoList_WithEmptyDescription_ReturnsBadRequest() throws Exception {
    var emptyTitle = new TodoCreateRequest(TODO.getTitle(), null,
        TODO.getExpectedCompletionDate(), false, 1L);

    mockMvc
        .perform(MockMvcRequestBuilders.post("/todo").content(objectMapper.writeValueAsString(emptyTitle))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
  }

  @Test
  public void createTodoList_WithInvalidDescription_ReturnsBadRequest() throws Exception {
    var emptyTitle = new TodoCreateRequest(TODO.getTitle(), "",
        TODO.getExpectedCompletionDate(), false, 1L);

    mockMvc
        .perform(MockMvcRequestBuilders.post("/todo").content(objectMapper.writeValueAsString(emptyTitle))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
  }
}
