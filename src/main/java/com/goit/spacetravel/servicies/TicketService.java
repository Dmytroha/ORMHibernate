package com.goit.spacetravel.servicies;

import com.goit.spacetravel.HibernateUtil;
import com.goit.spacetravel.entities.Client;
import com.goit.spacetravel.entities.Ticket;
import jakarta.persistence.EntityTransaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class TicketService implements TicketCrudService{
    private final SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    @Override
    public void create(Ticket ticket) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction() ;
            try{
                session.persist(ticket);
                transaction.commit();
            }catch(Exception e){
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Ticket ticket) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(ticket);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public List<Ticket> findAll() {
        List<Ticket> ticketList;
        try (Session session = sessionFactory.openSession()){
            ticketList = session.createQuery("from Ticket", Ticket.class).list();
        }
        return ticketList;
    }

    @Override
    public Optional<Ticket> getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Ticket.class, id));
        }

    }

    @Override
    public void deleteById(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Optional<Ticket> ticketOptional = Optional.ofNullable(session.get(Ticket.class, id));
            session.remove(ticketOptional.orElseThrow(() -> new NoSuchElementException(NO_SUCH_ID_MSG)));
            transaction.commit();
        } catch (NoSuchElementException e) {
            Optional.ofNullable(transaction).ifPresent(EntityTransaction::rollback);
            throw new NoSuchElementException(NO_SUCH_ID_MSG);
        }


    }
}
