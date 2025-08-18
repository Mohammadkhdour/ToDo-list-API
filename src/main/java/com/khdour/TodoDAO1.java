package com.khdour;

import java.util.List;
import java.util.Optional;

public interface TodoDAO1 {
    void insertTodo(ToDo todo);

    void updateTodo( ToDo todo);

    Optional<ToDo> getTodo( String id);

    void deleteTodo( String id);

    List<ToDo> getAllTodos();

    void updateTitle( String id, String title);
}
