package com.khdour;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.core.Jdbi;

import com.google.inject.Inject;

public class ToDoDaoImpl implements TodoDAO1 {
    private Jdbi jdbi;

    @Inject
    public ToDoDaoImpl(Jdbi jdbi) {
        this.jdbi = jdbi;
    }
    @Override
    public void insertTodo(ToDo todo) {
        jdbi.withHandle(handle -> 
            handle.createUpdate("INSERT INTO todo (id, title, description, done, created_on, updated_on, isbn) VALUES (:id, :title, :description, :done, :createdOn, :updatedOn, :ISBN)")
                .bindBean(todo)
                .execute()
        );
    }

    @Override
    public void updateTodo(ToDo todo) {
        jdbi.withHandle(handle ->
            handle.createUpdate("UPDATE todo SET title = :title, description = :description, done = :done, updated_on = :updatedOn, isbn = :ISBN WHERE id = :id")
                .bindBean(todo)
                .execute()
        );
    }

    @Override
    public Optional<ToDo> getTodo(String id) {
        return jdbi.withHandle(handle ->
            handle.createQuery("SELECT * FROM todo WHERE id = :id")
                .bind("id", id)
                .mapToBean(ToDo.class)
                .findFirst()
        );
    }

    @Override
    public void deleteTodo(String id) {
        jdbi.withHandle(handle ->
            handle.createUpdate("DELETE FROM todo WHERE id = :id")
                .bind("id", id)
                .execute()
        );
    }

    @Override
    public List<ToDo> getAllTodos() {
        return jdbi.withHandle(handle ->
            handle.createQuery("SELECT * FROM todo")
                .mapToBean(ToDo.class)
                .list()
        );
    }

    @Override
    public void updateTitle(String id, String title) {
        jdbi.withHandle(handle ->
            handle.createUpdate("UPDATE todo SET title = :title WHERE id = :id")
                .bind("title", title)
                .bind("id", id)
                .execute()
        );
    }
}

