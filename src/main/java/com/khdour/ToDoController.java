package com.khdour;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class ToDoController {
    private final ToDoService todoService;
    private final pebbleController pebbleController;
    String template;

    @Inject
    public ToDoController(ToDoService todoService, pebbleController pebbleController,
            @Named("pebble") String template) {
        this.todoService = todoService;
        this.pebbleController = pebbleController;
        this.template = template;
    }

    public void registerRoute() throws IOException {

        // configure static file
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/Public", Location.CLASSPATH);
        });

        app.get("/todos", ctx -> {
            Map<String, Object> context = new HashMap<>();
            context.put("todos", todoService.getAllTodos().toArray());
            String output = pebbleController.render(context, template);
            ctx.status(200);
            ctx.html(output);
        });

        app.post("/todo", ctx -> {
            ToDo todo = new ToDo(
                    UUID.randomUUID().toString(),
                    ctx.formParam("title"),
                    ctx.formParam("description"),
                    false,
                    ZonedDateTime.now(),
                    null);
            todoService.createTodo(todo);
            ctx.status(201);
            ctx.html("Todo created successfully!");
        });

        app.put("/todo/{id}", ctx -> {
            String id = ctx.pathParam("id");
            ToDo todo = todoService.getTodo(id).orElseThrow(() -> new RuntimeException("Todo not found"));
            String title = ctx.formParam("title");
            if (title != null && title.length() > 4) {
                todo.setTitle(title);
            }
            String description = ctx.formParam("description");
            if (description != null && description.length() > 4) {
                todo.setDescription(description);
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
                ctx.html("Todo deleted successfully!");

            } else {
                ctx.status(404);
            }
        });

        app.get("/todo/{id}", ctx -> {
            String id = ctx.pathParam("id");
            ToDo todo = todoService.getTodo(id).orElse(null);
            if (todo == null) {
                ctx.status(404);
                ctx.html("Todo not found");
                return;
            }
            ctx.status(200);
            ctx.html(todo.toString());
        });

        app.start(8080);
    }
}
