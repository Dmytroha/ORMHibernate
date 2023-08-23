package com.goit.spacetravel.servicies;

import java.util.List;
import java.util.Optional;

public interface CrudService <T>{
    void create(T t);
    List<T> findAll();
   <I extends Number > Optional<T> getById(I id);
    <S extends  CharSequence> Optional<T> getById(S id);
}
