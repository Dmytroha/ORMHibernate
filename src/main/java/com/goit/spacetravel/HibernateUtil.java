package com.goit.spacetravel;

import com.goit.spacetravel.entities.Client;
import com.goit.spacetravel.entities.Planet;
import com.goit.spacetravel.entities.Ticket;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
        private static final HibernateUtil INSTANCE;

        @Getter
        private SessionFactory sessionFactory;

        static {
            INSTANCE = new HibernateUtil();
        }

        private HibernateUtil() {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(Client.class)
                    .addAnnotatedClass(Planet.class)
                    .addAnnotatedClass(Ticket.class)
                    .buildSessionFactory();
        }

        public static HibernateUtil getInstance() {
            return INSTANCE;
        }

        public void close() {
            sessionFactory.close();
        }

}
