package com.khdour;

import java.time.ZonedDateTime;
import java.util.logging.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main( String[] args )
    {


        Injector injector = Guice.createInjector(new ToDoModule());
        flywayMigration flywayMigrationInstance = injector.getInstance(flywayMigration.class);
        flywayMigrationInstance.migrateDatabase();
        ToDoService todoService = injector.getInstance(ToDoService.class);
        todoService.getAllTodos().forEach(e -> logger.info(e.toString()));
        todoService.createTodo(new ToDo("5", "New Task", "This is a new task", false, ZonedDateTime.now(), ZonedDateTime.now()));
        todoService.updateTitle("1", "Updated Task");
        todoService.getAllTodos().forEach(e -> logger.info(e.toString()));

        ToDoController todoController = injector.getInstance(ToDoController.class);
        todoController.registerRoute();
    }
}
