package com.khdour;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(ToDo.class)
public interface TodoDAO2 {
    @SqlUpdate("INSERT INTO todo (id, title, description, done, created_on, updated_on, isbn) VALUES (:id, :title, :description, :done, :createdOn, :updatedOn, :ISBN)")
    void insertTodo(@BindBean ToDo todo);

    @SqlUpdate("UPDATE todo SET title = :title, description = :description, done = :done, updated_on = :updatedOn, isbn = :ISBN WHERE id = :id")
    void updateTodo(@BindBean ToDo todo);

    @SqlQuery("SELECT * FROM todo WHERE id = :id")
    Optional<ToDo> getTodo(@Bind("id") String id);

    @SqlUpdate("DELETE FROM todo WHERE id = :id")
    void deleteTodo(@Bind("id") String id);

    @SqlQuery("SELECT * FROM todo")
    List<ToDo> getAllTodos();

    @SqlUpdate("UPDATE todo SET title = :title WHERE id = :id")
    void updateTitle(@Bind("id") String id, @Bind("title") String title);
}
