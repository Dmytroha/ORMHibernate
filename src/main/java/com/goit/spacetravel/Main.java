package com.goit.spacetravel;

import org.apache.log4j.BasicConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

   private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        BasicConfigurator.configure();
        logger.info("Application started");
        HibernateUtil hu = HibernateUtil.getInstance();
        SessionFactory sessionFactory = hu.getSessionFactory();
        }
    }
