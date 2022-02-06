package Model;

import DAO.UserDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class All_Appointments {
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> customerAppointments = FXCollections.observableArrayList();

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

    /**
     *
     * @return refresh the observable list of all appointments
     */
    public static void refreshAllAppointments() throws Exception {
        clearAllAppointments();
        UserDaoImpl.SqlAllAppointments();
    }

    /**
     *
     * @return observable list of customer appointments
     */
    public static ObservableList<Appointment> getCustomerAppointments(int customer_id) throws Exception {
        customerAppointments.clear();
        for(Appointment foundObj : allAppointments){
            if (foundObj.getCustomer_Id() == customer_id) {
                customerAppointments.add(foundObj);
            }
        }
        return customerAppointments;
    }

    /**
     *
     * @return check all customer appointments overlap
     */
    public static Boolean checkCustomerAppointments(int customer_id, LocalDateTime setStartDateTime) throws Exception {
        ObservableList<Appointment> thisCustomerAppointment = getCustomerAppointments(customer_id);
        for(Appointment thisAppointment : thisCustomerAppointment){
            if (thisAppointment.isOverlap(setStartDateTime) == true) {
                return false;
            }
        }
        return true;
    }
}
