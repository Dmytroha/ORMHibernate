package com.goit.spacetravel.servicies;

import com.goit.spacetravel.entities.Planet;

import java.util.List;
import java.util.Optional;

public interface PlanetCrudService {
    String  NO_SUCH_ID_MSG = "Planet with such id does not exist";
    void create(Planet planet);
    void update(Planet planet);
    List<Planet> findAll();

    Optional<Planet> getById(String id);
    void deleteById(String id);
}
