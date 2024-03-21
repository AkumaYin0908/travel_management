package gov.coateam1.util;


import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeConverter {

    private DateTimeConverter(){}
    private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
    private final static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");


    public static String convertToString(LocalDate date){
        return dateFormatter.format(date);
    }

    public static LocalDate convertToLocalDate(String dateString){
        return LocalDate.parse(dateString,dateFormatter);
    }

    public static String convertToString(LocalTime time){
        return timeFormatter.format(time);
    }

    public static LocalTime convertToLocalTime(String timeString){
        return LocalTime.parse(timeString,timeFormatter);
    }
}
