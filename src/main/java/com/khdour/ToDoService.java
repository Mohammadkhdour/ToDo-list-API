package com.khdour;

import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;
    
public class ToDoService {
    private TodoDAO todoDAO;


    @Inject
    public ToDoService(TodoDAO todoDAO) {
        this.todoDAO = todoDAO;
    }

    public void createTodo(ToDo todo) {
        todoDAO.insertTodo(todo);
    }

    public void updateTodo(ToDo todo) {
        todoDAO.updateTodo(todo);
    }

    public Optional<ToDo> getTodo(String id) {
        return todoDAO.getTodo(id);
    }

    public void deleteTodo(String id) {
        todoDAO.deleteTodo(id);
    }

    public List<ToDo> getAllTodos() {
        return todoDAO.getAllTodos();
    }

    public void updateTitle(String id, String title) {
        todoDAO.updateTitle(id, title);
    }
}
