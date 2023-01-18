package ui;


import domain.Friendship;
import domain.User;
import service.Service0;
import service.Service0_Prietenie;
import service.Service_Prietenie;

import java.util.Scanner;

public class UI0 implements UI{
    private Service0 s;
    private Service0_Prietenie sp;
    private Scanner keyboard;
    public UI0(Service0 service, Service0_Prietenie service0_prietenie){
        this.s =service;
        this.sp = service0_prietenie;
        keyboard = new Scanner(System.in);
    }

    private void showmenu(){
        System.out.println("1. Adauga user:");
        System.out.println("2. Sterge user: ");
        System.out.println("3. Afiseaza useri:");
        System.out.println("4. Adauga friendship: ");
        System.out.println("5. Sterge friendship: ");
        System.out.println("6. Afiseaza friendships: ");
        System.out.println("0. Exit");
    }

    private void in_memory(){
        User u1 = new User("Cristiano", "Ronaldo", "cr7");
        User u2 = new User("Neymar", "Jr", "neyney10");
        User u3 = new User("Leo", "Messi", "lionel_messi");
        User u4 = new User("Mihnea", "Neagu", "mihneaneagu709");
        this.s.addUtilizator(u1);
        this.s.addUtilizator(u2);
        this.s.addUtilizator(u3);
        this.s.addUtilizator(u4);

        this.sp.createPrietenie(u1.getusername(), u2.getusername());
        this.sp.createPrietenie(u1.getusername(), u3.getusername());
        this.sp.createPrietenie(u1.getusername(), u4.getusername());
        this.sp.createPrietenie(u3.getusername(), u2.getusername());
    }

    public void runUI(){
        in_memory();
        keyboard = new Scanner(System.in);
        while(true){

            showmenu();
            System.out.println("Introduceti optiunea dorita: ");
            int opt = keyboard.nextInt();
            try{
            switch (opt){
                case 0 :
                    keyboard.close();
                    return;

                case 1 :
                    User u = read_user();
                    s.addUtilizator(u);
                    break;

                case 2:
                    String username = read_username();
                    s.deleteUtilizator(username);
                    break;

                case 3:
                    Iterable<User> users = s.getAllUtilizatori();
                    users.forEach(System.out::println);
                    break;

                case 4:
                    String username1 = read_username();
                    String username2 = read_username();
                    sp.createPrietenie(username1, username2);
                    break;

                case 5:
                    username1 = read_username();
                    username2 = read_username();
                    sp.deletePrietenie(username1, username2);
                    break;

                case 6:
                    Iterable<Friendship> itf = s.getAllPrietenii();
                    itf.forEach(System.out::println);
                    break;

                default:
                    System.out.println("Optiune incorecta!");

            }
        }catch(Exception e){
                System.out.println(e.toString());}
    }}

    public User read_user() {
        System.out.println("Introduceti prenumele utilizatorului: ");
        String firstname = keyboard.next();
        System.out.println("Introduceti numele de familie al utilizatorului: ");
        String lastname = keyboard.next();
        System.out.println("Introduceti username-ul utilizatorului: ");
        String username = keyboard.next();
        User user = new User(firstname, lastname, username);
        return user;
    }

    public String read_username() {
        System.out.print("Introduceti username-ul dorit: ");
        String username = keyboard.next();
        return username;
    }
}

