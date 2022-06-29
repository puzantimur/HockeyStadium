package model;

import java.io.Serializable;
import java.util.List;

public class HockeyStadium implements Serializable {

  private double bank;
  private List<User> users;
  private List<Match> matches;

  public HockeyStadium(int bank, List<User> users, List<Match> matches) {
    this.bank = bank;
    this.users = users;
    this.matches = matches;
  }

  public HockeyStadium() {
  }

  public double getBank() {
    return bank;
  }

  public void setBank(double bank) {
    this.bank = bank;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public List<Match> getMatches() {
    return matches;
  }

  public void setMatches(List<Match> matches) {
    this.matches = matches;
  }

}
