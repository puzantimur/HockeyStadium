package service;

import java.util.Scanner;
import java.util.UUID;
import model.Match;
import model.User;
import model.UserType;
import view.Main;

public class AdminService {

  public static void adminMenu(User user) {
    System.out.println("Hello " + user.getLogin());
    System.out.println(
        """
            Press 0 to check all users
            Press 1 to change user's login
            Press 2 to change user's password
            Press 3 to change user's money
            Press 4 to change user's type
            Press 5 to check matches
            Press 6 to delete the match
            Press 7 to change date and time of match
            Press 8 to change match-up
            Press 9 to delete user
            Press 10 to exit in main menu in main menu""");
    Scanner scanner = new Scanner(System.in);
    int button = scanner.nextInt();
    switch (button) {
      case 0 -> {
        getAllUsers();
        adminMenu(user);
      }
      case 1 -> changeUserLogin(user);
      case 2 -> changeUserPassword(user);
      case 3 -> changeUserMoney(user);
      case 4 -> changeUserType(user);
      case 5 -> checkMatches(user);
      case 6 -> deleteMatch(user);
      case 7 -> changeDateOfMatch(user);
      case 8 -> changeMatchup(user);
      case 9 -> deleteUser(user);
      case 10 -> LogService.mainMenu();
      default -> {
        System.out.println("Can you just aim?");
        adminMenu(user);
      }
    }
  }

  public static void getAllUsers() {
    System.out.println(Main.stadium.getUsers());
  }

  public static void changeUserLogin(User user) {
    System.out.println("Enter user's id");
    Scanner scanner = new Scanner(System.in);
    String idOfUser = scanner.nextLine();
    UUID currentUUID = UUID.fromString(idOfUser);
    for (User user1 : Main.stadium.getUsers()) {
      if (user1.getId().equals(currentUUID)) {
        System.out.println("Enter the login you want to change to");
        Scanner sc = new Scanner(System.in);
        String newLogin = sc.nextLine();
        user1.setLogin(newLogin);
        System.out.println("Success");
        adminMenu(user);
        return;
      }
    }
    System.out.println("There is no id like this");
    adminMenu(user);
  }

  public static void changeUserPassword(User user) {
    System.out.println("Enter user's id");
    Scanner scanner = new Scanner(System.in);
    String idOfUser = scanner.nextLine();
    UUID currentUUID = UUID.fromString(idOfUser);
    for (User user1 : Main.stadium.getUsers()) {
      if (user1.getId().equals(currentUUID)) {
        System.out.println("Enter the password you want to change to");
        Scanner sc = new Scanner(System.in);
        String newPassword = sc.nextLine();
        user1.setPassword(newPassword);
        System.out.println("Success");
        adminMenu(user);
        return;
      }
    }
    System.out.println("There is no id like this");
    adminMenu(user);
  }

  public static void changeUserMoney(User user) {
    System.out.println("Enter user's id");
    Scanner scanner = new Scanner(System.in);
    String idOfUser = scanner.nextLine();
    UUID currentUUID = UUID.fromString(idOfUser);
    for (User user1 : Main.stadium.getUsers()) {
      if (user1.getId().equals(currentUUID)) {
        System.out.println("Enter how much money to add");
        Scanner sc = new Scanner(System.in);
        double money = sc.nextDouble();
        user1.setMoney(user.getMoney() + money);
        System.out.println("Success");
        adminMenu(user);
        return;
      }
    }
    System.out.println("There is no id like this");
    adminMenu(user);
  }

  public static void changeUserType(User user) {
    System.out.println("Enter user's id");
    String idOfUser;
    try (Scanner scanner = new Scanner(System.in)) {
      idOfUser = scanner.nextLine();
    }
    UUID currentUUID = UUID.fromString(idOfUser);
    for (User user1 : Main.stadium.getUsers()) {
      if (user1.getId().equals(currentUUID)) {
        System.out.println("Enter 1 to make user\nEnter 2 to make manager\nEnter 3 to make admin");
        Scanner sc = new Scanner(System.in);
        int button = sc.nextInt();
        switch (button) {
          case 1:
            user1.setUserType(UserType.USER);
            System.out.println("Success");
            break;
          case 2:
            user1.setUserType(UserType.MANAGER);
            System.out.println("Success");
            break;
          case 3:
            user1.setUserType(UserType.ADMIN);
            System.out.println("Success");
            break;
          default:
            System.out.println("Can you just aim?");
            changeUserType(user);
        }
        adminMenu(user);
        return;
      }
    }
    System.out.println("There is no id like this");
    adminMenu(user);
  }

  public static void checkMatches (User user) {
    System.out.println(Main.stadium.getMatches());
    System.out.println("Press 1 to get back");
    Scanner scanner = new Scanner(System.in);
    int button = scanner.nextInt();
    if (button == 1) {
      adminMenu(user);
    } else {
      System.out.println("Can you just aim?");
      adminMenu(user);
    }
  }

  public static void deleteMatch (User user) {
    System.out.println("Enter id of match");
    Scanner scanner = new Scanner(System.in);
    int idOfMatch = scanner.nextInt();
    for (Match match : Main.stadium.getMatches()) {
      if (match.getId() == idOfMatch) {
        Main.stadium.getMatches().remove(match);
        System.out.println("Success!");
        adminMenu(user);
        return;
      }
    }
    System.out.println("There is no match like this");
    adminMenu(user);
  }

  public static void changeDateOfMatch (User user) {
    System.out.println("Enter id of match");
    Scanner scanner = new Scanner(System.in);
    int idOfMatch = scanner.nextInt();
    for (Match match : Main.stadium.getMatches()) {
      if (match.getId()==idOfMatch) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please, enter the date of match: Year - ");
        int year = sc.nextInt();
        System.out.println("Month - ");
        int month = sc.nextInt();
        System.out.println("Day - ");
        int day = sc.nextInt();
        System.out.println("Hours -");
        int hours = sc.nextInt();
        System.out.println("Minutes - ");
        int minutes = sc.nextInt();
        match.setDateOfMatch(year, month, day, hours, minutes);
        System.out.println("Success");
        adminMenu(user);
        return;
      }
    }
    System.out.println("There is no match like this");
    adminMenu(user);
  }

  public static void changeMatchup (User user) {
    System.out.println("Enter id of match");
    Scanner scanner = new Scanner(System.in);
    int idOfMatch = scanner.nextInt();
    for (Match match : Main.stadium.getMatches()) {
      if (match.getId()==idOfMatch) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please, enter matchup");
        String matchup = sc.nextLine();
        match.setMatchUp(matchup);
        System.out.println("Success");
        adminMenu(user);
        return;
      }
    }
    System.out.println("There is no match like this");
    adminMenu(user);
  }

  public static void deleteUser (User user) {
    System.out.println("Enter user's id");
    Scanner scanner = new Scanner(System.in);
    String idOfUser = scanner.nextLine();
    UUID currentUUID = UUID.fromString(idOfUser);
    for (User user1 : Main.stadium.getUsers()) {
      if (user1.getId().equals(currentUUID)) {
        Main.stadium.getUsers().remove(user1);
        System.out.println("Success");
        adminMenu(user);
        return;
      }
    }
    System.out.println("There is no id like this");
    adminMenu(user);
  }

  }





