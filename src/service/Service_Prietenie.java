package service;

import domain.Entity;
import domain.Friendship;
import domain.User;
import domain.validators.ValidationException;
import repository.Repository0;

import java.util.UUID;

public interface Service_Prietenie<ID> {
    boolean createPrietenie(String username1, String username2);

    Entity<ID> deletePrietenie(String username1, String username2);
}


