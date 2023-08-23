package com.goit.spacetravel;

import com.goit.spacetravel.entities.Client;
import com.goit.spacetravel.entities.Planet;
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
    public static void main(String[] args) {

        BasicConfigurator.configure();
        logger.info("Application started");
        HibernateUtil hu = HibernateUtil.getInstance();
        SessionFactory sessionFactory = hu.getSessionFactory();
        Session session = sessionFactory.openSession();



        ClientService clientService = new ClientService();
        clientService.findAll().forEach(client -> logger.info(client.getName()));

        Optional<Client> clientOptional = clientService.getById(4L); // may we should catch NumberFormatException
        clientOptional.ifPresent(client -> logger.info(client.getName()));

        PlanetService planetService = new PlanetService();
        planetService.findAll().forEach(planet -> logger.info(planet.getName()));

        Optional<Planet> planetOptional = planetService.getById("JUP");
        planetOptional.ifPresent(planet -> logger.info(planet.getName()));

        session.close();//close session

        hu.close(); //close session factory

        logger.info("Application finished");
        }
    }
