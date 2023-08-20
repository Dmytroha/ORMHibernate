package com.goit.spacetravel.migratiron;

import org.flywaydb.core.Flyway;


public class MigrationService {

    public static void main(String[] args) {
        //configure flyway
        Flyway flyway = Flyway.configure().cleanDisabled(false)
            .dataSource("jdbc:h2:file:./SpaceTravel", "sa", null)
            .load();
        //clean database
        flyway.clean();

        // Start the migration
        flyway.migrate();
    }

}

