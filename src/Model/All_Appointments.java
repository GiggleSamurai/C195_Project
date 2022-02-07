package Model;

import DAO.UserDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

import static Model.MonthObj.*;

public class All_Appointments {
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> customerAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment>  thisMonthAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment>  thisWeekdayAppointments = FXCollections.observableArrayList();
    public static Appointment upcomingAppointment;

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
        ObservableList<Appointment> thisCustomerAppointments = getCustomerAppointments(customer_id);
        for(Appointment thisAppointment : thisCustomerAppointments){
            if (thisAppointment.isOverlap(setStartDateTime) == true) {
                return false;
            }
        }
        return true;
    }

    public static Boolean checkCustomerAppointmentsWithoutThis(int customer_id, LocalDateTime setStartDateTime, int appointment_id) throws Exception {
        ObservableList<Appointment> thisCustomerAppointments = getCustomerAppointments(customer_id);
        for(Appointment thisAppointment : thisCustomerAppointments){
            if (thisAppointment.getAppointment_ID() == appointment_id){
                //Skip
            } else {
                if (thisAppointment.isOverlap(setStartDateTime) == true) {
                    return false;
                }
            }
        }
        return true;
    }

    public static Boolean upcoming15minAppointments(LocalDateTime loginLocalTime) throws Exception {

        for(Appointment thisAppointment : allAppointments){
            if (thisAppointment.isOverlap(loginLocalTime) || thisAppointment.isOverlap(loginLocalTime.plusMinutes(15)) ) {
                upcomingAppointment = thisAppointment;
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return sadasdasdasdsadasdasdddddddddddddddddddddddddddddddddddddddddd
     */
    public static ObservableList<Appointment> getThisMonthAppointments(int month) throws Exception {
        thisMonthAppointments.clear();
        for(Appointment thisAppointment : allAppointments){
            if (thisAppointment.getStart_Datetime().getMonth().getValue() == month) {
                thisMonthAppointments.add(thisAppointment);
            }
        }
        return thisMonthAppointments;
    }

    /**
     *
     * @return sadasdasdasdsadasdasdddddddddddddddddddddddddddddddddddddddddd
     */
    public static ObservableList<Appointment> getThisWeekdayAppointments(int weekday) throws Exception {
        thisWeekdayAppointments.clear();
        for(Appointment thisAppointment : allAppointments){
            if (thisAppointment.getStart_Datetime().getDayOfWeek().getValue() == weekday) {
                thisWeekdayAppointments.add(thisAppointment);
            }
        }
        return thisWeekdayAppointments;
    }

    public static String getMonthReport(){
        MonthObj.clearCount();
        for(int i = 1; i < 13; i++){
            System.out.println(i);
            for(Appointment thisAppointment : allAppointments){
                if (thisAppointment.getStart_Datetime().getMonth().getValue() == i) {
                    MonthObj.add1to(i);
                }
            }
        }

        return MonthObj.getResult();
    }

    private static ObservableList<String> nonUniqueListType = FXCollections.observableArrayList();
    private static ObservableList<String> UniqueListType = FXCollections.observableArrayList();

    public static String getTypeReport(){
        nonUniqueListType.clear();
        UniqueListType.clear();

        for(Appointment thisAppointment : allAppointments){
            nonUniqueListType.add(thisAppointment.getType());
        }
        String resultString = "Total Numbers of Customer Appointment By Type:";
        for(Appointment checkThisAppointment : allAppointments){
            int count = 0;
            if (UniqueListType.contains(checkThisAppointment.getType())){
                //Skip this Duplicate Element
            } else {
                UniqueListType.add(checkThisAppointment.getType());
                for (Appointment thisAppointment : allAppointments) {

                    if (checkThisAppointment.getType().equals(thisAppointment.getType())) {
                        count += 1;
                    }
                }
                resultString += "\n" + checkThisAppointment.getType() + ": " + Integer.toString(count);
            }
        }

        return resultString;
    }
}
