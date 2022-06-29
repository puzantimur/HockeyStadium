package service;

import java.time.LocalDateTime;
import java.util.Scanner;
import model.Match;
import model.Ticket;
import model.User;
import view.Main;


public class ManagerService {

  public static User currentUser;

  public static void managerMenu(User user) {
    System.out.println("Hello " + user.getLogin());
    System.out.println("Press 1 to check matches\nPress 2 to add match\nPress 3 to check balance"
        + " of user \nPress 4 to buy a ticket for user\nPress 5 to return user's ticket \nPress"
        + " 6 to get back in main menu");
    Scanner scanner = new Scanner(System.in);
    int button = scanner.nextInt();
    switch (button) {
      case 1:
        checkMatches(user);
        break;
      case 2:
        addMatch(user);
        break;
      case 3:
        checkBalanceOfUser(user);
        break;
      case 4:
        purchaseTicketForUser(user);
        break;
      case 5:
        returnTicket(user);
      case 6:
        LogService.mainMenu();
        break;
      default:
        System.out.println("Can you just aim?");
        managerMenu(user);
    }
  }

  public static void checkMatches(User user) {
    System.out.println(Main.stadium.getMatches());
    System.out.println("Press 1 to get back");
    Scanner scanner = new Scanner(System.in);
    int button = scanner.nextInt();
    switch (button) {
      case 1:
        managerMenu(user);
        break;
      default:
        System.out.println("Can you just aim?");
        managerMenu(user);
    }
  }

  public static void addMatch(User user) {
    System.out.println("Please, enter matchup");
    Scanner scanner = new Scanner(System.in);
    String matchUp = scanner.nextLine();
    System.out.println("Please, enter the date of match: Year - ");
    int year = scanner.nextInt();
    System.out.println("Month - ");
    int month = scanner.nextInt();
    System.out.println("Day - ");
    int day = scanner.nextInt();
    System.out.println("Hours -");
    int hours = scanner.nextInt();
    System.out.println("Minutes - ");
    int minutes = scanner.nextInt();
    Match match = new Match(matchUp, year, month, day, hours, minutes);
    Main.stadium.getMatches().add(match);
    System.out.println("Match successfully was added");
    managerMenu(user);
  }

  public static void checkBalanceOfUser(User user) {
    System.out.println("Please, enter the login of user");
    Scanner scanner = new Scanner(System.in);
    String login = scanner.nextLine();
    System.out.println("Please, enter the password of user");
    String password = scanner.nextLine();
    for (User callingUser : Main.stadium.getUsers()) {
      if ((callingUser.getLogin().equals(login)) && (callingUser.getPassword().equals(password))) {
        currentUser = callingUser;
        break;
      }
    }
    if (!Main.stadium.getUsers().contains(currentUser)) {
      System.out.println("There is no user like this");
      System.out.println("Press 1 to exit back to menu");
      int button = scanner.nextInt();
      switch (button) {
        case 1:
          managerMenu(user);
          break;
        default:
          System.out.println("Can you just aim?");
          managerMenu(user);
      }
    } else {
      System.out.println("Balance of " + currentUser.getLogin()+ " " + currentUser.getMoney() + " $");
      System.out.println("Press 1 to exit back to menu");
      int button = scanner.nextInt();
      switch (button) {
        case 1:
          managerMenu(user);
          break;
        default:
          System.out.println("Can you just aim?");
          managerMenu(user);
      }
    }
  }

  public static void purchaseTicketForUser(User user) {
    boolean switcher = false;
    System.out.println("Please, enter the login of user");
    Scanner scanner = new Scanner(System.in);
    String login = scanner.nextLine();
    System.out.println("Please, enter the password of user");
    String password = scanner.nextLine();
    for (User callingUser : Main.stadium.getUsers()) {
      if ((callingUser.getLogin().equals(login)) && (callingUser.getPassword().equals(password))) {
        currentUser = callingUser;
        break;
      }
    }
    if (!Main.stadium.getUsers().contains(currentUser)) {
      System.out.println("There is no user like this");
      System.out.println("Press 1 to exit back to menu");
      int button = scanner.nextInt();
      switch (button) {
        case 1:
          managerMenu(user);
          break;
        default:
          System.out.println("Can you just aim?");
          managerMenu(user);
      }
    } else {
      System.out.println("Enter id of match");
      int idOfMatch = scanner.nextInt();
      for (Match match : Main.stadium.getMatches()) {
        if (match.getId() == idOfMatch) {
          System.out.println("Please, enter seat number (from 1 to 100)");
          Scanner sc = new Scanner(System.in);
          int seatNumber = sc.nextInt();
          for (Ticket ticket : match.getTickets()) {
            if (ticket.getNumberOfSeat() == seatNumber) {
                if (ticket.isAvailable()) {
                  if (currentUser.getMoney() > ticket.getCost()) {
                    currentUser.setMoney(currentUser.getMoney() - ticket.getCost());
                    Main.stadium.setBank(Main.stadium.getBank() + ticket.getCost());
                    ticket.setAvailable(false);
                    currentUser.getPurchasedTickets().add(ticket);
                    System.out.println("Succsess");
                    switcher = true;
                  } else {
                    System.out.println("No money");
                  }
                  managerMenu(user);
                  return;
                } else {
                  System.out.println(
                      "This seat was bought by another person");
                } break;
              }

            }break;
          }
        }
      }
    System.out.println("Enter 1 to exit back to menu");
    int button = scanner.nextInt();
    switch (button) {
      case 1:
        managerMenu(user);
        break;
      default:
        System.out.println("Can you just aim?");
        managerMenu(user);
    }
  }

  public static void returnTicket(User user) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please, enter the login of user");
    String login = scanner.nextLine();
    System.out.println("Please, enter the password of user");
    String password = scanner.nextLine();
    for (User callingUser : Main.stadium.getUsers()) {
      if ((callingUser.getLogin().equals(login)) && (callingUser.getPassword().equals(password))) {
        currentUser = callingUser;
        break;
      }
    }
    if (!Main.stadium.getUsers().contains(currentUser)) {
      System.out.println("There is no user like this");
      System.out.println("Press 1 to exit back to menu");
      int button = scanner.nextInt();
      switch (button) {
        case 1:
          managerMenu(user);
        default:
          System.out.println("Can you just aim?");
          managerMenu(user);
      }
    } else {
      System.out.println("Enter id of match");
      int idOfMatch = scanner.nextInt();
      for (Match match : Main.stadium.getMatches()) {
        if (match.getId() == idOfMatch) {
          if (match.getDateOfMatchNotFormatted().isBefore(LocalDateTime.now())) {
            System.out.println("Please, enter id of ticket");
            Scanner sc = new Scanner(System.in);
            int idOfTicket = sc.nextInt();
            for (Ticket ticket : match.getTickets()) {
              if (ticket.getId() == idOfTicket) {
                if (currentUser.getPurchasedTickets().contains(ticket)) {
                  currentUser.getPurchasedTickets().remove(ticket);
                  ticket.setAvailable(true);
                  currentUser.setMoney(currentUser.getMoney() + ticket.getCost());
                  Main.stadium.setBank(Main.stadium.getBank() - ticket.getCost());
                  System.out.println("Success");
                  managerMenu(user);
                  return;
                }
                break;
              }

            }

          } else {
            System.out.println("The match has passed");
            managerMenu(user);
            return;
          }
          break;
        }

      }
      System.out.println("There is no match like this");
    }
    System.out.println("Enter 1 to exit back to menu");
    int button = scanner.nextInt();
    switch (button) {
      case 1:
        managerMenu(user);
        break;
      default:
        System.out.println("Can you just aim?");
        managerMenu(user);
    }

  }
}
