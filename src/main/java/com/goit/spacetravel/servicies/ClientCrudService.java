package com.goit.spacetravel.servicies;

import com.goit.spacetravel.entities.Client;

import java.util.List;
import java.util.Optional;

public interface ClientCrudService  {
    String  NO_SUCH_ID_MSG = "Client with such id does not exist";
    void create(Client client);
    void update(Client client);
    List<Client> findAll();
   Optional<Client> getById(Long id);
   void deleteById(Long id);

}
