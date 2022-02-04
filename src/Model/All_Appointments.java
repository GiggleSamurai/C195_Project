package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class All_Appointments {
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    public static void SqlAllAppointments() {

    }

    /**
     *
     * @param Appointment the part add to all appointments array list
     */
    public static void addAppointment(Appointment Appointment) {
        allAppointments.add(Appointment);
    }

    /**
     *
     * @return the observable list of all appointments
     */
    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    }

    /**
     *
     * @return clear the observable list of all appointments
     */
    public static void clearAllAppointments() {
        allAppointments.clear();
    }

}
