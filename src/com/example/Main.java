package com.example;

import com.example.adapter.*;
import com.example.decorator.*;
import com.example.factory.*;
import com.example.observer.PayObserver;
import com.example.payment.*;
import com.example.registration.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to FilmPicker!!!" +
                    "\n[1] Register" +
                    "\n[2] Login" +
                    "\n[3] About Us" +
                    "\n[4] Exit");

            int start = sc.nextInt();
            switch (start) {
                case 1:
                    DatabaseManager db = new DatabaseManager();
                    UserRegistration userRegistration = new UserRegistration(db);
                    userRegistration.registerUserManually();
                    break;
                case 2:
                    LogIn log = new LogIn();

                    System.out.println("Enter first name:");
                    String fname = sc.next();

                    System.out.println("Enter last name:");
                    String lname = sc.next();

                    System.out.println("Enter age:");
                    int age = sc.nextInt();

                    System.out.println("Enter password:");
                    String password = sc.next();

                    boolean userExists = log.userExists(fname, lname, age, password);

                    if (userExists) {
                        System.out.println("User exists!");
                    } else {
                        System.out.println("User does not exist!");
                    }

                    while(true) {
                        System.out.println("[1] All Films" +
                                "\n[2] All Films (FULL INFO)" +
                                "\n[3] Methods" +
                                "\n[4] Add Film" +
                                "\n[5] Detele Film" +
                                "\n[6] Choose by Genre" +
                                "\n[7] Add Reaction" +
                                "\n[8] Exit");

                        MainMethods methods = new MainMethods();
                        PayObserver observer = new PayObserver();

                        int choiseAction = sc.nextInt();
                        switch (choiseAction) {
                            case 1:
                                methods.getAllFilms();
                                break;
                            case 2:
                                UserPayment payUser = new UserPayment();
                                System.out.println("All Films with full info (5000tg); Do you want? [Y]/[N]");
                                String full = sc.next();
                                if (full.equals("Y")) {
                                    System.out.println("Select a Payment Method: " + "\n [1] Kaspi" + "\n [2] Halyk");
                                    int payChoice = sc.nextInt();
                                    switch (payChoice) {
                                        case 1:
                                            payUser.setPaymentStrategy(new KaspiPay());
                                            break;
                                        case 2:
                                            payUser.setPaymentStrategy(new HalykPay());
                                            break;
                                        default:
                                            System.out.println("Invalid payment method choice.");
                                            return;
                                    }
                                    System.out.println("Enter amount: ");
                                    int amount = sc.nextInt();
                                    payUser.makePayment(amount);

                                    methods.getAllFilmsFull();

                                    observer.notifyObserver();
                                    break;
                                } else {
                                    break;
                                }
                            case 3:
                                System.out.println("[1] Random Film" +
                                        "\n[2] Film by Name" +
                                        "\n[3] Film by Rating" +
                                        "\n[4] Film by Year");

                                int choiceMethod = sc.nextInt();
                                switch (choiceMethod) {
                                    case 1:
                                        try {
                                            methods.getRandomFilm();
                                        } catch (Exception e) {
                                            System.out.println("Error getting random film");
                                            throw new RuntimeException(e);
                                        }
                                        return;
                                    case 2:
                                        System.out.println("Enter name of the film");
                                        String nameChoice = sc.next();
                                        try {
                                            methods.getFilmByName(nameChoice);
                                        } catch (Exception e) {
                                            System.out.println("Error getting film by name");
                                            throw new RuntimeException(e);
                                        }
                                        return;
                                    case 3:
                                        System.out.println("Enter rating of the film");
                                        String ratingChoice = sc.next();
                                        try {
                                            methods.getFilmByRating(ratingChoice);
                                        } catch (Exception e) {
                                            System.out.println("Error getting film by name");
                                            throw new RuntimeException(e);
                                        }
                                        return;
                                    case 4:
                                        System.out.println("Enter year of the film");
                                        int yearChoice = sc.nextInt();
                                        try {
                                            methods.getFilmByYear(yearChoice);
                                        } catch (Exception e) {
                                            System.out.println("Error getting film by name");
                                            throw new RuntimeException(e);
                                        }
                                        return;
                                }
                            case 4:
                                methods.addFilm();
                                break;
                            case 5:
                                methods.deleteFilm();
                                break;
                            case 6:
                                System.out.println("Choose a film genre: " +
                                        "\n[1] drama" +
                                        "\n[2] action" +
                                        "\n[3] crime" +
                                        "\n[4] comedy");

                                FilmGenre filmGenre = new FilmGenre();

                                int choiceGenre = sc.nextInt();
                                switch (choiceGenre) {
                                    case 1:
                                        Genres drama = filmGenre.chooseGenre("drama");
                                        drama.getFilmByGenre();
                                        break;
                                    case 2:
                                        Genres action = filmGenre.chooseGenre("action");
                                        action.getFilmByGenre();
                                        break;
                                    case 3:
                                        Genres crime = filmGenre.chooseGenre("crime");
                                        crime.getFilmByGenre();
                                        break;
                                    case 4:
                                        Genres comedy = filmGenre.chooseGenre("comedy");
                                        comedy.getFilmByGenre();
                                        break;
                                    default:
                                        System.out.println("Invalid choice for film");
                                        return;
                                }
                                break;
                            case 7:
                                Reaction reaction = new Reaction();
                                reaction.addReaction();
                                break;
                            case 8:
                                System.exit(1);
                            default:
                                System.out.println("Invalid choice");
                                break;
                        }
                    }
                case 3:
                    System.out.println("Our product was created in 2023 to help those who need clarity and speed in searching for films.");
                case 4:
                    System.exit(1);
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}