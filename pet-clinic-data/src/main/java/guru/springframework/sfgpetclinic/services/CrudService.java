package guru.springframework.sfgpetclinic.services;

import java.util.Set;

//takes a type and an ID
public interface CrudService<T, ID> {
//using java generics so we can specify them when we implement this interface

    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}
