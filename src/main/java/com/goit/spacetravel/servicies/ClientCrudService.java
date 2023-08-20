package com.goit.spacetravel.servicies;

import com.goit.spacetravel.entities.Client;

import java.util.List;

public interface ClientCrudService {
     void create(Client client);
     void deleteById(Client client);
     List<Client> findAll();

}
