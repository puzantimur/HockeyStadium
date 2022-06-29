package model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Match implements Serializable {

  private int id;
  private String matchUp;
  private LocalDateTime dateOfMatch;
  private List<Ticket> tickets;

  //create defalut list of tickets
  public List<Ticket> makeListTickets () {
    for (int i = 0; i < 100; i++) {
      Ticket ticket = new Ticket(id, true, i+1);
      tickets.add(ticket);
    }
    return tickets;
  }

  public Match(String matchUp, int year, int month, int day, int hour, int min) {
    id = (int)(Math.random()*Math.random()*1000000);
    this.matchUp = matchUp;
    dateOfMatch = LocalDateTime.of(year, month, day, hour, min);
    tickets = new ArrayList<>();
    tickets = makeListTickets();
  }


  public int getId() {
    return id;
  }

  public String getMatchUp() {
    return matchUp;
  }

  public void setMatchUp(String matchUp) {
    this.matchUp = matchUp;
  }

  public String getDateOfMatch() {
    return UtilDate.getFormattedDate(dateOfMatch);
  }

  public LocalDateTime getDateOfMatchNotFormatted() {
    return dateOfMatch;
  }


  public void setDateOfMatch(int year, int month, int day, int hour, int min) {
    dateOfMatch = LocalDateTime.of(year, month, day, hour, min);
  }

  public List<Ticket> getTickets() {
    return tickets;
  }

  public void setTickets(List<Ticket> tickets) {
    this.tickets = tickets;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Match{");
    sb.append("id=").append(id);
    sb.append(", matchUp='").append(matchUp).append('\'');
    sb.append(", dateOfMatch=").append(dateOfMatch);
    sb.append('}');
    return sb.toString();
  }
}

