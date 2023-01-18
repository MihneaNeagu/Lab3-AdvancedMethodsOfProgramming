package service;

import domain.Entity;
import domain.Friendship;
import domain.User;
import domain.validators.ValidationException;

public interface Service<ID> {
    boolean addUtilizator(User user);

    Entity<ID> deleteUtilizator(String username);

    Iterable<User> getAllUtilizatori();

    Iterable<Friendship> getAllPrietenii();

    Entity<ID> deleteCascada(String username);
}

