package com.goit.spacetravel;

import com.goit.spacetravel.entities.Client;
import com.goit.spacetravel.entities.Planet;
import com.goit.spacetravel.entities.Ticket;
import com.goit.spacetravel.exeptions.CantFindPropertiesException;
import com.goit.spacetravel.migratiron.FlywayUtils;
import com.goit.spacetravel.servicies.ClientService;
import com.goit.spacetravel.servicies.PlanetService;
import com.goit.spacetravel.servicies.TicketService;
import org.apache.log4j.BasicConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.time.ZonedDateTime;
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
            flywayUtils.migrate();
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

        logger.info("------------------Add new valid Ticket-------------------");
        TicketService ticketService = new TicketService();
        Ticket ticket = new Ticket();
        ticket.setClientId(clientService.getById(8L).get());
        ticket.setCreatedAt(ZonedDateTime.now(ZoneId.of("UTC")));
        ticket.setFromPlanetId(planetService.getById("NEP").get());
        ticket.setToPlanetId(planetService.getById("VEN").get());
        ticketService.create(ticket);
        Long idToDelete = ticket.getId();
        logger.info("------------------Add new  1 invalid Tickets-------------------");
        ticket.setClientId(null);
        ticket.setCreatedAt(ZonedDateTime.now(ZoneId.of("UTC")));
        ticket.setFromPlanetId(planetService.getById("NEP").get());
        ticket.setToPlanetId(planetService.getById("VEN").get());
        ticketService.create(ticket);
        logger.info("------------------Add new  2 invalid Tickets-------------------");
        ticket.setClientId(clientService.getById(5L).get());
        ticket.setCreatedAt(ZonedDateTime.now(ZoneId.of("UTC")));
        ticket.setFromPlanetId(null);
        ticket.setToPlanetId(planetService.getById("VEN").get());
        ticketService.create(ticket);
        logger.info("------------------Add new 3 invalid Tickets-------------------");
        ticket.setClientId(clientService.getById(6L).get());
        ticket.setCreatedAt(ZonedDateTime.now(ZoneId.of("UTC")));
        ticket.setFromPlanetId(planetService.getById("NEP").get());
        ticket.setToPlanetId(null);
        ticketService.create(ticket);

        logger.info("------------------Update ticket 4 with not existed planets-------------------");
        Planet fromPlanet = new Planet();
        Planet toPlanet = new Planet();
        ticket = ticketService.getById(4L).get();
        fromPlanet.setId("REG24");
        fromPlanet.setName("REGULA");
        toPlanet.setId("PIG");
        toPlanet.setName("PIGGY");
        ticket.setFromPlanetId(fromPlanet);
        ticket.setToPlanetId(toPlanet);

        try {
            ticketService.update(ticket);
        } catch(Exception e){
            e.printStackTrace();
            logger.error("**********************Unable update ticket information*****************");
        }
        logger.info("------------List all tickets---->");

        ticketService.findAll().forEach(t -> logger.info("Ticket ID {}  Client ID [{}]  Name {} ctreated at {} from->{} to-->{}"
                ,t.getId(),t.getClientId().getId(),t.getClientId().getName(),t.getCreatedAt(),
                t.getFromPlanetId().getName(),t.getToPlanetId().getName()));

        logger.info("------------delete ticket ---->");
        ticketService.deleteById(idToDelete);


        logger.info("------------------Close all-------------------");

        session.close();//close session
        hu.close(); //close session factory
        logger.info("------------------Application finished-------------------");
        }
    }
