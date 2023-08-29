package com.goit.spacetravel.migratiron;

import com.goit.spacetravel.Main;
import com.goit.spacetravel.exeptions.CantFindPropertiesException;
import org.flywaydb.core.Flyway;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import static org.hibernate.cfg.AvailableSettings.*;

public class FlywayUtils {
    private static final FlywayUtils INSTANCE;

    static {
        INSTANCE = new FlywayUtils();
    }
    public void init() throws CantFindPropertiesException {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(Main.class.getClassLoader().getResource("hibernate.properties").getPath()));
            Flyway
                    .configure()
                    .dataSource(
                            (String) properties.get(URL),
                            (String) properties.get(USER),
                            (String) properties.get(PASS))
                    .baselineOnMigrate(true)
                    .failOnMissingLocations(true)
                    .locations("db/migration")
                    .load()
                    .migrate();
        }catch (IOException e){
            throw new CantFindPropertiesException("Can't find hibernate.properties in resources");
        }
    }
    public static FlywayUtils getInstance() {
        return INSTANCE;
    }

}
