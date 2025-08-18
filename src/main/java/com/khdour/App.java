package com.khdour;

import java.time.ZonedDateTime;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        // DataSource dataSource = DataSourceConfig.getDataSource("jdbc:mysql://localhost:3306/todo", "root", "mohammad1234");
        

        // Jdbi jdbi = Jdbi.create(dataSource);
        // jdbi.installPlugin(new SqlObjectPlugin());


        // ToDoDaoImpl todoDao = new ToDoDaoImpl(jdbi);
        // TodoDAO2 todoDao2 = jdbi.onDemand(TodoDAO2.class);

        // todoDao2.getAllTodos().forEach(e -> System.out.println(e.toString()));

        // todoDao2.updateTitle("1", "New Title");
        // ToDo newToDo = ToDo.builder().id("4").title("New Task").description("This is a new task").done(false).createdOn(ZonedDateTime.now()).updatedOn(ZonedDateTime.now()).ISBN("978-3-16-148410-3").build();
        // todoDao.insertTodo(newToDo);
        // todoDao2.getAllTodos().forEach(e -> System.out.println(e.toString()));

        Injector injector = Guice.createInjector(new ToDoModule());
        flywayMigration flywayMigrationInstance = injector.getInstance(flywayMigration.class);
        flywayMigrationInstance.migrateDatabase();
        ToDoService todoService = injector.getInstance(ToDoService.class);
        todoService.getAllTodos().forEach(e -> System.out.println(e.toString()));
        todoService.createTodo(new ToDo("5", "New Task", "This is a new task", false, ZonedDateTime.now(), ZonedDateTime.now(),"978-3-16-148410-3"));
        todoService.getAllTodos().forEach(e -> System.out.println(e.toString()));

        ToDoController todoController = injector.getInstance(ToDoController.class);
        todoController.registerRoute();
    }
}
