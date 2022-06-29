package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class UtilDate {

  public static final String DATE_FORMAT_PATTER = "dd-MM-yyyy HH:mm";
  public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTER);

  public static String getFormattedDate(LocalDateTime date) {
    return DATE_FORMAT.format(date);
  }

}
