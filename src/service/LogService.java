package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import model.Match;
import model.User;
import model.UserType;
import view.Main;

public class LogService {


  public static void mainMenu() {
    System.out.println("Hello, user.\nIf u wanna login, press 1\nIf u wanna registry, "
        + "press 2\nIf u wanna quit, press 3");
    Scanner scanner = new Scanner(System.in);
    int numberToLog = scanner.nextInt();
    switch (numberToLog) {
      case 1:
        LogService.loginUser();
        break;
      case 2:
        LogService.registry();
        break;
      case 3:
        System.out.println("Good bye");
        saveProgress();
        break;
      default:
        System.out.println("Wrong number!!! Bye!!!");
    }
  }

  public static void loginUser() {
    User currentUser = null;
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter your login ");
    String nick = sc.nextLine();
    System.out.println("Enter your password: ");
    String pass = sc.nextLine();
    for (User user : Main.stadium.getUsers()) {
      if ((user.getLogin().equals(nick)) && (user.getPassword().equals(pass))) {
        currentUser = user;
        if (currentUser.getUserType().equals(UserType.ADMIN)) {
          AdminService.adminMenu(currentUser);
        }
        else if (currentUser.getUserType().equals(UserType.MANAGER)) {
          ManagerService.managerMenu(currentUser);
        }
        else if (currentUser.getUserType().equals(UserType.USER)) {
          UserService.userMenu(currentUser);
        } break;
      }

    }
      if (currentUser == null) {
        System.out.println("Wrong login or password");
        System.out.println("Try again - 1 \nBack to main menu - 2");
        int oneMoreTry = sc.nextInt();
        switch (oneMoreTry) {
          case 1:
            loginUser();
            break;
          case 2:
            mainMenu();
            break;
          default:
            System.out.println("Can you just aim?");
            loginUser();
        }
      }
    }


  public static void registry() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter your login: ");
    String nick = scanner.nextLine();
    System.out.println(
        "Enter your password: \n(We remind you that when registering - the password must"
            + " contains at least one number and uppercase letters, as well as 8 or more characters");
    String pass = scanner.nextLine();
    for (User test : Main.stadium.getUsers()) {
      if ((test.getLogin().equals(nick))) {
        System.out.println(
            "Unfortunately there is already a user with this nickname");
        System.out.println("Enter 1 to try again. \nEnter 2 to exit back");
        int remake = scanner.nextInt();
        switch (remake) {
          case 1:
            registry();
            break;
          case 2:
            LogService.mainMenu();
            break;
          default:
            System.out.println("Can you just aim?");
            registry();
        }
      } break;
    }
    if (pass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$") &&
        nick.matches("[A-Za-z_0-9].{6,20}")) {
      User user = new User (nick, pass);
      Main.stadium.getUsers().add(user);
      System.out.println(
          "Congratulations on your successful registration! \nYour "
              + "login - " + nick + " Your password - " + pass);
      UserService.userMenu(user);
      } else {
      System.out.println("Incorrect login or password, try again");
      System.out.println(
          "Enter 1 to try again. \nEnter 2 to exit back");
      Scanner scanner1 = new Scanner(System.in);
      int attempt = scanner1.nextInt();
      switch (attempt) {
        case 1:
          registry();
          break;
        case 2:
          mainMenu();
          break;
        default:
          System.out.println("Can you just aim?");
          registry();
      }
    }
  }

  public static void saveProgress() {
    FileOutputStream fileOutputStream = null;
    try {
      fileOutputStream = new FileOutputStream(new File("saveProgress.txt"));
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(Main.stadium);
      objectOutputStream.flush();
      objectOutputStream.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

