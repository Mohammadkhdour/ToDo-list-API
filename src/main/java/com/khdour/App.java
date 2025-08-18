package com.khdour;

import java.time.ZonedDateTime;

import javax.sql.DataSource;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        DataSource dataSource = DataSourceConfig.getDataSource("jdbc:mysql://localhost:3306/todo", "root", "mohammad1234");
        flywayMigration.migrateDatabase(dataSource);

        Jdbi jdbi = Jdbi.create(dataSource);
        jdbi.installPlugin(new SqlObjectPlugin());


        ToDoDao todoDao = new ToDoDao(jdbi);
        TodoDAO2 todoDao2 = jdbi.onDemand(TodoDAO2.class);

        todoDao2.getAllTodos().forEach(e -> System.out.println(e.toString()));

        todoDao2.updateTitle("1", "New Title");
        ToDo newToDo = ToDo.builder().id("4").title("New Task").description("This is a new task").done(false).createdOn(ZonedDateTime.now()).updatedOn(ZonedDateTime.now()).ISBN("978-3-16-148410-3").build();
        todoDao.insertTodo(newToDo);
        todoDao2.getAllTodos().forEach(e -> System.out.println(e.toString()));
    }
}
