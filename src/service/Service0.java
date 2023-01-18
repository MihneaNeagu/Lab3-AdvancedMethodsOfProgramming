package service;

import domain.Entity;
import domain.Friendship;
import domain.User;
import domain.validators.ValidationException;
import repository.Repository0;
import java.util.UUID;

public class Service0 implements Service<UUID> {
    private Repository0<UUID, User> utilizatorRepo;
    private Repository0<UUID, Friendship> prietenieRepo;

    public Service0(Repository0<UUID, User> utilizatorRepo, Repository0<UUID, Friendship> prietenieRepo) {
        this.utilizatorRepo = utilizatorRepo;
        this.prietenieRepo = prietenieRepo;
    }

    @Override
    public boolean addUtilizator(User user) {
        Entity<UUID> u = null;
            if (user.getFirstName() == null)
                throw new IllegalArgumentException("usernameul nu trebuie sa fie null!");
            else if (getUtilizatorByusername(user.getusername()) != null)
                throw new IllegalArgumentException("Exista deja un cont cu acest username!");
            u = utilizatorRepo.save(user);


        if (u != null) {
            throw new ValidationException("Exista un alt utilizator cu acest ID");
        }

        return true;
    }
    public Entity<UUID> deleteCascada(String username)
    {
        User u = null;
        u = getUtilizatorByusername(username);
        if (u == null) {
            throw new ValidationException("Nu exista niciun utilizator cu acest ID!");
        }

        for (User friend : u.getFriends()) {
            for (Friendship p : prietenieRepo.findAll())
                if ((p.getUtilizator1().equals(u) && p.getUtilizator2().equals(friend))
                        || (p.getUtilizator1().equals(friend) && p.getUtilizator2().equals(u))) {
                    prietenieRepo.delete(p.getId());
                    break;
                }
            friend.removeFriend(u);
        }
        return u;
    }

    @Override
    public Entity<UUID> deleteUtilizator(String username) {
        User u = null;
        u = getUtilizatorByusername(username);
        if (u == null) {
            throw new ValidationException("Nu exista niciun utilizator cu acest ID!");
        }
        deleteCascada(u.getusername());
        u = (User) utilizatorRepo.delete(u.getId());


        if (u == null) {
            throw new ValidationException("Nu exista niciun utilizator cu acest ID!");
        }

        return u;
    }

    @Override
    public Iterable<User> getAllUtilizatori() {
        return utilizatorRepo.findAll();
    }

    @Override
    public Iterable<Friendship> getAllPrietenii() {
        return prietenieRepo.findAll();
    }

    public User getUtilizatorByusername(String username) {
        Iterable<User> it = utilizatorRepo.findAll();
        for (User u : it)
            if (u.getusername().equals(username))
                return u;
        return null;
    }
}
