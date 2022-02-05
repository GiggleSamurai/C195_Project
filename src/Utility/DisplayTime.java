package Utility;

import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class DisplayTime {
    private static ObservableList<String> allHours = FXCollections.observableArrayList();
    private static ObservableList<String> allMinutes = FXCollections.observableArrayList();
    private static ObservableList<String> theAMorPM = FXCollections.observableArrayList();

    //  hours.addAll("0", "1", "2", "3", "04", "05", "06", "07", "08", "9", "10", "11",
      //          "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
    public static ObservableList<String> getAllHours() {
        allHours.addAll( "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11","12");
        return allHours;
    }

    public static ObservableList<String> getAllMinutes() {
        allMinutes.addAll("00", "15", "30", "45");
        return allMinutes;
    }

    public static ObservableList<String> getAMorPM() {
        theAMorPM.addAll( "AM", "PM");
        return theAMorPM;
    }

    public static Integer getMinuteInt(String minute) {
        Integer minValue = Integer.parseInt(minute);
        return minValue;
    }

    public static Integer getHourInt(String hour, String AmOrPm) {
        if (AmOrPm.equals("AM")){
            return Integer.parseInt(hour);
        } else {
            Integer thisHour = Integer.parseInt(hour) + 12;
            if (thisHour == 24) {
                return 0;
            } else {
                return thisHour;
            }

           /* LocalDateTime ldt = LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), Integer.parseInt(hour), Integer.parseInt(minute));
            ZonedDateTime locZdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
            ZonedDateTime utcZdt = locZdt.withZoneSameInstant(ZoneOffset.UTC);*/
        }
    }
}
