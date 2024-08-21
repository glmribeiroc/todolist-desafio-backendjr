package br.com.guilhermecosta.desafiotodomanager.common;

import java.time.LocalDateTime;
import java.util.ArrayList;

import br.com.guilhermecosta.desafiotodomanager.entity.Todo;
import br.com.guilhermecosta.desafiotodomanager.entity.TodoList;

public class TodoConstans {
  public final static TodoList TODO_LIST = new TodoList(1L, "My List", new ArrayList<>(), LocalDateTime.now());
  public final static Todo TODO = new Todo("New todo", "Todo description", LocalDateTime.now(), false, TODO_LIST,
      new ArrayList<>());
}
