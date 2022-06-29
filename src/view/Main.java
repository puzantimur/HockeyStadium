package view;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.HockeyStadium;
import model.Match;
import model.User;
import model.UserType;
import service.LogService;

public class Main {

  public static HockeyStadium stadium;

  public static void main(String[] args) {

    //hardcoded data
    User admin = new User("Bfsword", "Babysiting454", UserType.ADMIN);
    User manager1 = new User("Bloodthirster1", "Perfectgirls123", UserType.MANAGER);
    User manager2 = new User("Windlow343", "Whynotthispassword222", UserType.MANAGER);

    //creation a list of user
    List<User> users = new ArrayList<>();
    users.add(manager1);
    users.add(manager2);
    users.add(admin);

    //creatoin a list of match
    List<Match> matches = new ArrayList<>();


    stadium = new HockeyStadium(20000, users,matches);

    {
      try {
        FileInputStream fileInputStream =new FileInputStream("saveProgress.txt");
        ObjectInputStream objectInputStream =new ObjectInputStream(fileInputStream);
        stadium = (HockeyStadium) objectInputStream.readObject();
      } catch (IOException | ClassNotFoundException e) {
        System.out.println("There is no progress here");
      }
    }
    //start the program
    LogService.mainMenu();


  }
}
