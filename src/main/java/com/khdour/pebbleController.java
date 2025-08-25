package com.khdour;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.server.handler.ContextHandler.Context;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.loader.ClasspathLoader;
import io.pebbletemplates.pebble.template.PebbleTemplate;

public class pebbleController {
    Map<String, Object> context = new HashMap<>();

    PebbleEngine engine = new PebbleEngine.Builder().loader(new ClasspathLoader()).build();
    PebbleTemplate compiledTemplate;
    Writer writer;
    String template;

    @Inject
    public pebbleController(@Named("pebble") String template) {
            this.template = template;
    }

    public String render(Map<String, Object> context, String template) {
        compiledTemplate = engine.getTemplate(template);
        writer = new StringWriter();
        try {
            compiledTemplate.evaluate(writer, context);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String output = writer.toString();
        return output;

    }
}
