package model;

import java.io.Serializable;

public class Ticket implements Serializable {

  private int id;
  private double cost;
  private int idOfMatch;
  private boolean isAvailable;
  private int numberOfSeat;

  public Ticket(int idOfMatch, boolean isAvailable, int numberOfSeat) {
    id = (int)(Math.random()*Math.random()*1000000);
    this.idOfMatch = idOfMatch;
    this.isAvailable = isAvailable;
    this.numberOfSeat = numberOfSeat;
    cost = 10.59;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getIdOfMatch() {
    return idOfMatch;
  }

  public void setIdOfMatch(int idOfMatch) {
    this.idOfMatch = idOfMatch;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean available) {
    isAvailable = available;
  }

  public int getNumberOfSeat() {
    return numberOfSeat;
  }

  public void setNumberOfSeat(int numberOfSeat) {
    this.numberOfSeat = numberOfSeat;
  }

  public double getCost() {
    return cost;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Ticket{");
    sb.append("id=").append(id);
    sb.append(", idOfMatch=").append(idOfMatch);
    sb.append(", isAvailable=").append(isAvailable);
    sb.append(", numberOfSeat=").append(numberOfSeat);
    sb.append('}');
    return sb.toString();
  }
}
