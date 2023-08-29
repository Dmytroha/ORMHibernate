package com.goit.spacetravel.servicies;

import com.goit.spacetravel.HibernateUtil;
import com.goit.spacetravel.entities.Planet;
import jakarta.persistence.EntityTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class PlanetService implements  PlanetCrudService{
    private final SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    @Override
    public void create(Planet planet) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction() ;
            try{
                session.persist(planet);
                transaction.commit();
            }catch(Exception e){
                transaction.rollback();
            }

        }
    }

    @Override
    public List<Planet> findAll() {
        List<Planet> planetList;
        try (Session session = sessionFactory.openSession()){
            planetList = session.createQuery("from Planet", Planet.class).list();
        }
        return planetList;

    }
    @Override
    public  Optional<Planet> getById(String id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Planet.class, id));
        }
    }


    @Override
    public  void deleteById(String id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Optional<Planet> planetOptional = Optional.ofNullable(session.get(Planet.class, id));
            session.remove(planetOptional.orElseThrow(() -> new NoSuchElementException(NO_SUCH_ID_MSG)));
            transaction.commit();
        } catch (NoSuchElementException e) {
            Optional.ofNullable(transaction).ifPresent(EntityTransaction::rollback);
            throw new NoSuchElementException(NO_SUCH_ID_MSG);
        }

    }

    @Override
    public void update(Planet planet) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(planet);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}
