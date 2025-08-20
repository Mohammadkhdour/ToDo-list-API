package com.khdour;

import java.time.ZonedDateTime;
import java.util.UUID;

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

        app.get("/todos", ctx -> {
            //ctx.json(todoService.getAllTodos().toArray());
            String todosHtml = todoService.getAllTodos().stream()
                .map(Object::toString)
                .reduce("", (acc, todo) -> acc + todo + "<br>");

            ctx.status(200);
            ctx.html(todosHtml);
        });

        app.post("/todo", ctx -> {
            ToDo todo = new ToDo(
                UUID.randomUUID().toString(),
                ctx.formParam("title"),
                ctx.formParam("description"),
                false,
                ZonedDateTime.now(),
                null
            );
            todoService.createTodo(todo);
            ctx.status(201);
            ctx.html("Todo created successfully!");
        });

        app.put("/todo/{id}", ctx -> {
            String id = ctx.pathParam("id");
            ToDo todo = todoService.getTodo(id).orElseThrow(() -> new RuntimeException("Todo not found"));
            if (ctx.formParam("title").length() > 0) {
                todo.setTitle(ctx.formParam("title"));
            }
            if (ctx.formParam("description").length() > 0) {
                todo.setDescription(ctx.formParam("description"));
            }
            todo.setDone(Boolean.parseBoolean(ctx.formParam("done")));
            todo.setUpdatedOn(ZonedDateTime.now());
            todoService.updateTodo(todo);
            ctx.status(200);
            ctx.html("Todo updated successfully!");
        });

        app.delete("/todo/{id}", ctx -> {
            String id = ctx.pathParam("id");
            if (todoService.deleteTodo(id) > 0) {
                ctx.status(200);
            } else {
                ctx.status(404);
            }
            ctx.html("Todo deleted successfully!");
        });

        app.get("/todo/{id}", ctx -> {
            String id = ctx.pathParam("id");
            ToDo todo = todoService.getTodo(id).orElseThrow(() -> new RuntimeException("Todo not found"));
            ctx.status(200);
            ctx.html(todo.toString());
        });

        app.start(8080);
    }
}
