package com.goit.spacetravel.servicies;

import com.goit.spacetravel.HibernateUtil;
import com.goit.spacetravel.entities.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class ClientService implements CrudService <Client>{
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
    public <I extends Number > Optional<Client> getById(I id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Client.class, id));
        }

    }

    @Override
    public <S extends CharSequence> Optional<Client> getById(S id) {
        return getById( Long.valueOf((String) id));

    }
}
