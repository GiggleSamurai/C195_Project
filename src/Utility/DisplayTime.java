/**
 * @class DisplayTime.java
 * @author Louis Wong
 */

package Utility;

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

    /**
     *
     * @return list of all the hours in 12 hour system
     */
    public static ObservableList<String> getAllHours() {
        allHours.clear();
        allHours.addAll( "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11","12");
        return allHours;
    }

    /**
     *
     * @return list of all the minutes available for appointment
     */
    public static ObservableList<String> getAllMinutes() {
        allMinutes.clear();
        allMinutes.addAll("00", "15", "30", "45");
        return allMinutes;
    }

    /**
     *
     * @return list of just AM & PM
     */
    public static ObservableList<String> getAMorPM() {
        theAMorPM.clear();
        theAMorPM.addAll( "AM", "PM");
        return theAMorPM;
    }

    public static Integer getMinuteInt(String minute) {
        Integer minValue = Integer.parseInt(minute);
        return minValue;
    }

    /**
     *
     * @param hour hour to convert
     * @param AmOrPm if AM or PM to convert
     * @return the 12 hour formatted integer
     */
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

        }
    }

    /**
     *
     * @param userTime user time
     * @return converted UTC time
     */
    public static LocalDateTime userTime2UTC(LocalDateTime userTime) {
        ZonedDateTime userZoneTime = ZonedDateTime.of(userTime, ZoneId.systemDefault());
        ZonedDateTime utcZoneTime = userZoneTime.withZoneSameInstant(ZoneOffset.UTC);
        LocalDateTime utcTime = utcZoneTime.toLocalDateTime();
        return utcTime;
    }

    /**
     *
     * @param StartUTCDateTime the appointment start UTC time
     * @param EndUTCDateTime the appointment end UTC time
     * @return boolean statement if is in business hour or not
     */
    public static boolean inBusinessHours(LocalDateTime StartUTCDateTime, LocalDateTime EndUTCDateTime) {
        int StartHour = StartUTCDateTime.getHour();
        int EndHour = EndUTCDateTime.getHour();
        int OpenESTHour = 8 + 0; //0=AM
        int CloseESTHour = 10 + 12; //12=PM
        //EST = UTC-5:00
        int OpenUTCHour = OpenESTHour + 5;
        int CloseUTCHour = CloseESTHour + 5;

        if (StartHour>=OpenUTCHour && StartHour<=CloseUTCHour && EndHour>=OpenUTCHour && EndHour<=CloseUTCHour) {
            //In Business Hour
            return true;
        } else {
            return false;
        }
    }
}
