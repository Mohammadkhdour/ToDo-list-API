package com.khdour;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;

public class flywayMigration {
     public static void migrateDatabase(DataSource dataSource) {

            System.out.println("Starting Flyway migration...");
            System.out.println("Migration location: classpath:db/migration");


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
            
            System.out.println("Flyway migration completed successfully.");
    }
}
