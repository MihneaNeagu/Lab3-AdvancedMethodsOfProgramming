package repository;

import domain.Entity;
import repository.Repository0;
import domain.validators.Validator;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository0<ID, E extends Entity<ID>> implements Repository0<ID,E> {

    private Validator<E> validator;
    Map<ID,E> entities;

    public InMemoryRepository0(Validator<E> validator) {
        this.validator = validator;
        entities=new HashMap<ID,E>();
    }

    @Override
    public E findOne(ID id){
        if (id==null)
            throw new IllegalArgumentException("id must be not null");
        return entities.get(id);
    }

    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    @Override
    public E save(E entity) {
        if (entity==null)
            throw new IllegalArgumentException("entity must be not null");
        validator.validate(entity);
        if(entities.get(entity.getId()) != null) {
            return entity;
        }
        else entities.put(entity.getId(),entity);
        return null;
    }

    @Override
    public E delete(ID id) {
        if (id == null)
            throw new IllegalArgumentException("entity must be not null!");

        E entity = entities.get(id);
        if (entity == null)
            return null;

        return entities.remove(entity.getId());
    }

    @Override
    public E update(E entity) {

        if(entity == null)
            throw new IllegalArgumentException("entity must be not null!");
        validator.validate(entity);

        entities.put(entity.getId(),entity);

        if(entities.get(entity.getId()) != null) {
            entities.put(entity.getId(),entity);
            return null;
        }
        return entity;
    }

}