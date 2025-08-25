package com.khdour;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.inject.Inject;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.loader.ClasspathLoader;
import io.pebbletemplates.pebble.template.PebbleTemplate;

public class ToDoController {
    private final ToDoService todoService;

    @Inject
    public ToDoController(ToDoService todoService) {
        this.todoService = todoService;
    }

    public void registerRoute() throws IOException{

        PebbleEngine engine = new PebbleEngine.Builder().loader(new ClasspathLoader()).build();
        PebbleTemplate compiledTemplate = engine.getTemplate("pebble/viewTodos.html.peb");
        Writer writer = new StringWriter();

   Map<String, Object> context = new HashMap<>();
    context.put("todos", todoService.getAllTodos().toArray());

    compiledTemplate.evaluate(writer, context);

    String output = writer.toString();
               // configure static file
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/Public", Location.CLASSPATH);
        });

        app.get("/todos", ctx -> {
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
            ToDo todo = todoService.getTodo(id).orElse(null);
            if(todo == null){
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
