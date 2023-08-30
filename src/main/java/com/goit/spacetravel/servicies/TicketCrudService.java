package com.goit.spacetravel.servicies;

import com.goit.spacetravel.entities.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketCrudService {
    String  NO_SUCH_ID_MSG = "Ticket with such id does not exist";
    void create(Ticket ticket);
    void update(Ticket ticket);
    List<Ticket> findAll();

    Optional<Ticket> getById(Long id);
    void deleteById(Long id);
}
