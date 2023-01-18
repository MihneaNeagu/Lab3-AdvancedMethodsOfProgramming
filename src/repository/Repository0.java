package repository;

import domain.Entity;
import domain.validators.ValidationException;

public interface Repository0<ID, E extends Entity<ID>> {

    E findOne(ID id);

    Iterable<E> findAll();

    E save(E entity);

    E delete(ID id);

    E update(E entity);

}

