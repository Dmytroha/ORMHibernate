package com.goit.spacetravel;

import com.goit.spacetravel.entities.Client;
import com.goit.spacetravel.entities.Planet;
import com.goit.spacetravel.exeptions.CantFindPropertiesException;
import com.goit.spacetravel.migratiron.FlywayUtils;
import com.goit.spacetravel.servicies.ClientService;
import com.goit.spacetravel.servicies.PlanetService;
import org.apache.log4j.BasicConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

   private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args)  {

        BasicConfigurator.configure();
        logger.info("--------------------Application started-------------------------");

        FlywayUtils flywayUtils = FlywayUtils.getInstance();
        try {
            flywayUtils.init();
        } catch (CantFindPropertiesException e) {
            logger.error(e.getMessage());
        }


        HibernateUtil hu = HibernateUtil.getInstance();
        SessionFactory sessionFactory = hu.getSessionFactory();
        Session session = sessionFactory.openSession();

        // use Client CRUD serveries
        ClientService clientService = new ClientService();
        Client newClient = new Client();
        newClient.setName("Dude");
        clientService.create(newClient);
        Long newClientId = newClient.getId();
        logger.info("----------------->Read new client ID {}",newClientId);
        clientService.findAll().forEach(client -> logger.info("--------------------------->{}", client.getName()));

        Optional<Client> clientOptional = clientService.getById(newClientId);
        clientOptional.ifPresent(client -> logger.info("Last added Client ----> id = {}, name = {}",client.getId(),client.getName()));
        clientService.deleteById(newClientId);


        Planet newPlanet = new Planet();
        newPlanet.setId("237"); newPlanet.setName("SUN237");
        PlanetService planetService = new PlanetService();
        planetService.findAll().forEach(planet -> logger.info("------------------------->{}",planet.getName()));

        Optional<Planet> planetOptional = planetService.getById("JUP");
        planetOptional.ifPresent(planet -> logger.info("------------------------>{}",planet.getName()));

        planetService.create(newPlanet);
        planetService.findAll().forEach(planet -> logger.info("------------------------->{}",planet.getName()));
        String newPlanetId = newPlanet.getId();

        planetOptional = planetService.getById(newPlanetId);
        planetOptional.ifPresent(planet ->logger.info("Last added Planet ----------->Id = {},  name = {}",
                planet.getId(),planet.getName()));
        planetService.deleteById(newPlanetId);

        session.close();//close session
        hu.close(); //close session factory
        logger.info("------------------Application finished-------------------");
        }
    }
