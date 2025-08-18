package com.khdour;

import javax.sql.DataSource;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

public class ToDoModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(DataSource.class).toInstance(DataSourceConfig.getDataSource("jdbc:mysql://localhost:3306/todo", "root", "mohammad1234"));

        bind(TodoDAO1.class).to(ToDoDaoImpl.class);

         bind(String.class)
         .annotatedWith(Names.named("JDBC"))
         .toInstance("jdbc:mysql://localhost:3306/todo");

         bind(Jdbi.class).toInstance(Jdbi.create("jdbc:mysql://localhost:3306/todo", "root", "mohammad1234").installPlugin(new SqlObjectPlugin()));

        // bind(Jdbi.class).in(Singleton.class);

        bind(ToDoService.class);


    }

        @Provides 
        @Singleton
        public TodoDAO2 provideTodoDAO2(Jdbi jdbi) {
            return jdbi.onDemand(TodoDAO2.class);
        }
}
