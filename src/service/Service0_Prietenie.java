package service;

import domain.Entity;
import domain.Friendship;
import domain.User;
import domain.validators.ValidationException;
import repository.Repository0;


import java.util.UUID;

public class Service0_Prietenie implements Service_Prietenie<UUID>{

    private Repository0<UUID, User> utilizatorRepo;
    private Repository0<UUID, Friendship> prietenieRepo;

    public Service0_Prietenie(Repository0<UUID, User> utilizatorRepo, Repository0<UUID, Friendship> prietenieRepo) {
        this.utilizatorRepo = utilizatorRepo;
        this.prietenieRepo = prietenieRepo;
    }
    public User getUtilizatorByusername(String username) {
        Iterable<User> it = utilizatorRepo.findAll();
        for (User u : it)
            if (u.getusername().equals(username))
                return u;
        return null;
    }
    @Override
    public boolean createPrietenie(String username1, String username2) {

        Entity<UUID> f = null;
        User u1, u2;
        if (username1 == null || username2 == null)
            throw new IllegalArgumentException("usernameul trebuie sa nu fie null!");

        u1 = getUtilizatorByusername(username1);
        u2 = getUtilizatorByusername(username2);
        if (u1 == null || u2 == null || u1.equals(u2))
            throw new ValidationException("Nu exista cei doi utilizatori!");
        for(User friend:u1.getFriends())
        {
            if(friend == u2)
                throw new ValidationException("Deja prieteni");
        }
        f = prietenieRepo.save(new Friendship(u1, u2));


        if (f != null) {
            throw new ValidationException("Utilizatorii acestia sunt deja prieteni!");
        }

        u1.addFriend(u2);
        u2.addFriend(u1);
        return true;
    }

    @Override
    public Entity<UUID> deletePrietenie(String username1, String username2) {
        Entity<UUID> f = null;
        User u1, u2;
        if (username1 == null || username2 == null)
            throw new IllegalArgumentException("username-urile trebuie sa fie diferite de null!");
        u1 = getUtilizatorByusername(username1);
        u2 = getUtilizatorByusername(username2);

        if (u1 == null || u2 == null || u1.equals(u2))
            throw new ValidationException("Nu exista cei doi utilizatori!");

        Iterable<Friendship> l = prietenieRepo.findAll();
        for (Friendship elem : l)
            if (
                    (elem.getUtilizator1().getId().equals(u1.getId()) && elem.getUtilizator2().getId().equals(u2.getId()))
                            || (elem.getUtilizator1().getId().equals(u2.getId()) && elem.getUtilizator2().getId().equals(u1.getId()))
            ) {
                f = prietenieRepo.delete(elem.getId());
                break;
            }


        if (f == null) {
            throw new ValidationException("Cei doi utilizatori nu sunt prieteni!");
        }

        u1.removeFriend(u2);
        u2.removeFriend(u1);
        return f;
    }
}
