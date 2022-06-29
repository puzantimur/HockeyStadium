package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User implements Serializable {

  private UUID id;
  private double money;
  private String login;
  private String password;
  private UserType userType;
  private List<Ticket> purchasedTickets;

  public User() {
  }

  public User(String login, String password) {
    money = 0;
    id = UUID.randomUUID();
    this.login = login;
    this.password = password;
    userType = UserType.USER;
    purchasedTickets = new ArrayList<>();
  }

  public User(String login, String password, UserType userType) {
    id = UUID.randomUUID();
    this.login = login;
    this.password = password;
    this.userType = userType;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }

  public List<Ticket> getPurchasedTickets() {
    return purchasedTickets;
  }

  public void setPurchasedTickets(List<Ticket> purchasedTickets) {
    this.purchasedTickets = purchasedTickets;
  }

  public double getMoney() {
    return money;
  }

  public void setMoney(double money) {
    this.money = money;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("User{");
    sb.append("id=").append(id);
    sb.append(", money=").append(money);
    sb.append(", login='").append(login).append('\'');
    sb.append(", password='").append(password).append('\'');
    sb.append(", userType=").append(userType);
    sb.append(", purchasedTickets=").append(purchasedTickets);
    sb.append('}');
    return sb.toString();
  }
}
