package com.khdour;

import java.time.ZonedDateTime;

import com.google.inject.Inject;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class ToDoController {
    private final ToDoService todoService;

    @Inject
    public ToDoController(ToDoService todoService) {
        this.todoService = todoService;
    }

    public void registerRoute(){
               // configure static file
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/Public", Location.CLASSPATH);
        });

        // app.get("/", ctx -> {
        //     ctx.result("Welcome to the ToDo Application!");
        // });

        app.get("/todos", ctx -> {
            //ctx.json(todoService.getAllTodos().toArray());
            String todosHtml = todoService.getAllTodos().stream()
                .map(Object::toString)
                .reduce("", (acc, todo) -> acc + todo + "<br>");
            ctx.html(todosHtml);
        });

        app.post("/todo", ctx -> {
            ToDo todo = new ToDo(
                ctx.formParam("id"),
                ctx.formParam("title"),
                ctx.formParam("description"),
                false,
                ZonedDateTime.now(),
                ZonedDateTime.now()
            );
            todoService.createTodo(todo);
            ctx.status(201);
            ctx.html("Todo created successfully!");
        });

        app.put("/todo/{id}", ctx -> {
            String id = ctx.pathParam("id");
            ToDo todo = todoService.getTodo(id).orElseThrow(() -> new RuntimeException("Todo not found"));
            todo.setTitle(ctx.formParam("title"));
            todo.setDescription(ctx.formParam("description"));
            todo.setUpdatedOn(ZonedDateTime.now());
            todoService.updateTodo(todo);
            ctx.html("Todo updated successfully!");
        });

        app.delete("/todo/{id}", ctx -> {
            String id = ctx.pathParam("id");
            todoService.deleteTodo(id);
            ctx.html("Todo deleted successfully!");
        });

        app.get("/todo/{id}", ctx -> {
            String id = ctx.pathParam("id");
            ToDo todo = todoService.getTodo(id).orElseThrow(() -> new RuntimeException("Todo not found"));
            ctx.html(todo.toString());
        });

        app.start(8080);
    }
}
