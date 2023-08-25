package com.goit.spacetravel.servicies;

import java.util.List;
import java.util.Optional;

public interface CrudService <T>{

    String  NO_SUCH_ID_MSG = "No element with such id";
    void create(T t);
    void update(T t);
    List<T> findAll();
   <I extends Number > Optional<T> getById(I id);
    <S extends  CharSequence> Optional<T> getById(S id);

    <I extends Number > void deleteById(I id);
    <S extends  CharSequence> void deleteById(S id);

}
