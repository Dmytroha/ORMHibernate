package com.goit.spacetravel.servicies;

import com.goit.spacetravel.HibernateUtil;
import com.goit.spacetravel.entities.Client;
import jakarta.persistence.EntityTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


public class ClientService implements  ClientCrudService{
    private final SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();


    @Override
    public void create(Client client) {

        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction() ;
            try{
                session.persist(client);
                transaction.commit();
            }catch(Exception e){
                transaction.rollback();
            }

        }

    }

    @Override
    public List<Client> findAll() {
        List<Client> clientList;
        try (Session session = sessionFactory.openSession()){
            clientList = session.createQuery("from Client", Client.class).list();
        }
        return clientList;
    }

    @Override
    public  Optional<Client> getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Client.class, id));
        }

    }

    @Override
    public  void deleteById(Long id) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Optional<Client> clientOptional = Optional.ofNullable(session.get(Client.class, id));
            session.remove(clientOptional.orElseThrow(() -> new NoSuchElementException(NO_SUCH_ID_MSG)));
            transaction.commit();
        } catch (NoSuchElementException e) {
            Optional.ofNullable(transaction).ifPresent(EntityTransaction::rollback);
            throw new NoSuchElementException(NO_SUCH_ID_MSG);
        }

    }

    @Override
    public void update(Client client) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(client);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}
