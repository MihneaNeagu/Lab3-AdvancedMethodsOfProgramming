import domain.User;
import domain.Friendship;
import domain.validators.FriendshipValidator;
import domain.validators.UserValidator;
import repository.InMemoryRepository0;
import service.Service0;
import service.Service0_Prietenie;
import ui.UI0;

import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        InMemoryRepository0<UUID, User> utilizatorRepo = new InMemoryRepository0<>(new UserValidator());
        InMemoryRepository0<UUID, Friendship> prietenieRepo = new InMemoryRepository0<>(new FriendshipValidator());

        Service0 service = new Service0(utilizatorRepo,prietenieRepo);
        Service0_Prietenie service_prietenie = new Service0_Prietenie(utilizatorRepo, prietenieRepo);
        UI0 ui = new UI0(service, service_prietenie);


        ui.runUI();
    }
}