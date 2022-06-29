package service;

import java.time.LocalDateTime;
import java.util.Scanner;
import model.Match;
import model.Ticket;
import model.User;
import view.Main;

public class UserService {

  public static Match currentMatch;

  public static void userMenu(User user) {
    System.out.println("Hello " + user.getLogin());
    System.out.println("Press 1 to check matches\nPress 2 to check your balance$\nPress 3 to buy a"
        + " ticket\nPress 4 to check your current tickets\nPress 5 to return the ticket \nPress 6"
        + " to get back in main menu");
    Scanner scanner = new Scanner(System.in);
    int button = scanner.nextInt();
    switch (button) {
      case 1:
        checkMatches(user);
        break;
      case 2:
        checkBalance(user);
        break;
      case 3:
        buyTicket(user);
        break;
      case 4:
        checkTickets(user);
        break;
      case 5:
        returnTicket(user);
        break;
      case 6:
        LogService.mainMenu();
        break;
      default:
        System.out.println("Can you just aim?");
        userMenu(user);
    }
  }

  public static void checkMatches(User user) {
    System.out.println(Main.stadium.getMatches());
    System.out.println("Press 1 to get back");
    Scanner scanner = new Scanner(System.in);
    int button = scanner.nextInt();
    switch (button) {
      case 1:
        userMenu(user);
        break;
      default:
        System.out.println("Can you just aim?");
        userMenu(user);
    }
  }

  public static void checkBalance(User user) {
    Scanner scanner = new Scanner(System.in);
    System.out.println(
        "To check balance press 1\nTo add some money to your balance, press 2\nTo exit press 3");
    int buttonm = scanner.nextInt();
    switch (buttonm) {
      case 1:
        System.out.println("Your balance " + user.getMoney());
        checkBalance(user);
        break;
      case 2:
        System.out.println("How much to add $: ");
        Scanner scannerBalance = new Scanner(System.in);
        double balance = scannerBalance.nextDouble();
        user.setMoney(user.getMoney() + balance);
        System.out.println("Your current balance is: " + (user.getMoney() +
            "$"));
        checkBalance(user);
        break;
      case 3:
        userMenu(user);
        break;
      default:
        System.out.println("Can you just aim?");
        userMenu(user);
    }
  }

  public static void buyTicket(User user) {
    System.out.println("Enter id of match");
    Scanner scanner = new Scanner(System.in);
    int idOfMatch = scanner.nextInt();
    for (Match match : Main.stadium.getMatches()) {
      if (match.getId() == idOfMatch) {
        currentMatch = match;
        System.out.println("Please, enter seat number (from 1 to 100)");
        Scanner sc = new Scanner(System.in);
        int seatNumber = sc.nextInt();
        for (Ticket ticket : match.getTickets()) {
          if (ticket.getNumberOfSeat() == seatNumber) {
            do {
              if (ticket.isAvailable()) {
                if (user.getMoney() > ticket.getCost()) {
                  user.setMoney(user.getMoney() - ticket.getCost());
                  Main.stadium.setBank(Main.stadium.getBank() + ticket.getCost());
                  user.getPurchasedTickets().add(ticket);
                  ticket.setAvailable(false);
                  System.out.println("Success");
                  userMenu(user);
                  return;
                }
              } else {
                System.out.println(
                    "This seat was bought by another person. Enter another seat number");
              }
            } while (ticket.isAvailable());
            break;
          }
        }
        break;
      }
    }
    System.out.println("Wrong id of match");
    System.out.println("Enter 1 to exit back to menu");
    int button = scanner.nextInt();
    switch (button) {
      case 1:
        userMenu(user);
        break;
      default:
        System.out.println("Can you just aim?");
        userMenu(user);
    }
  }


  public static void checkTickets(User user) {
    System.out.println(user.getPurchasedTickets());
    System.out.println("Press 1 to get back");
    Scanner scanner = new Scanner(System.in);
    int button = scanner.nextInt();
    switch (button) {
      case 1:
        userMenu(user);
        break;
      default:
        System.out.println("Can you just aim?");
        userMenu(user);
    }
  }

  public static void returnTicket(User user) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter id of match");
    int idOfMatch = scanner.nextInt();
    for (Match match : Main.stadium.getMatches()) {
      if (match.getId() == idOfMatch) {
        if (match.getDateOfMatchNotFormatted().isAfter(LocalDateTime.now())) {
          System.out.println("Please, enter id of ticket");
          Scanner sc = new Scanner(System.in);
          int idOfTicket = sc.nextInt();
          for (Ticket ticket : match.getTickets()) {
            if (ticket.getId() == idOfTicket) {
              if (user.getPurchasedTickets().contains(ticket)) {
                user.getPurchasedTickets().remove(ticket);
                ticket.setAvailable(true);
                user.setMoney(user.getMoney() + ticket.getCost());
                Main.stadium.setBank(Main.stadium.getBank() - ticket.getCost());
                System.out.println("Successfully returned");
                userMenu(user);
                return;
              }
              break;
            }
          }
        } else {
          System.out.println("The match has passed");
          returnTicket(user);
          return;
        }
        break;
      }
    }
    System.out.println("There is no match like this");
    System.out.println("Enter 1 to exit back to menu");
    int button = scanner.nextInt();
    switch (button) {
    case 1:
      userMenu(user);
      break;
    default:
      System.out.println("Can you just aim?");
      userMenu(user);
  }
 }
}
