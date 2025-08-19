package com.khdour;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;

import com.google.inject.Inject;

public class flywayMigration {
    @Inject
    DataSource dataSource;

    public void migrateDatabase() {


            Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .validateOnMigrate(true)
                .cleanDisabled(false)
                .load();

            flyway.clean();

            // Run migration
            flyway.migrate();
            
    }
}
