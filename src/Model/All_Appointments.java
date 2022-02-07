/**
 * @class All_Appointments.java
 * @author Louis Wong
 */

package Model;

import DAO.UserDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;

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

    /**
     *
     * @param customer_id the customer ID
     * @param setStartDateTime the start date time
     * @param appointment_id the appointment ID
     * @return boolean statement if appointment is overlap
     * @throws Exception
     */
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

    /**
     *
     * @param loginLocalTime the user login time
     * @return boolean statement if there is upcoming appointment in 15 min after login time
     * @throws Exception
     */
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
        String resultString = "-------------------- Total Numbers of Customer Appointment By Type --------------------";
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

    private static ObservableList<String> nonUniqueListContact = FXCollections.observableArrayList();
    private static ObservableList<String> UniqueListContact = FXCollections.observableArrayList();

    public static String getContactReport(){
        nonUniqueListContact.clear();
        UniqueListContact.clear();

        for(Appointment thisAppointment : allAppointments){
            nonUniqueListContact.add(thisAppointment.getContact_Name());
        }
        String resultString = "-------------------- The Schedule for Each Contact -------------------- ";
        for(Appointment checkThisAppointment : allAppointments){
            //int count = 0;
            if (UniqueListContact.contains(checkThisAppointment.getContact_Name())){
                //Skip this Duplicate Element
            } else {
                UniqueListContact.add(checkThisAppointment.getContact_Name());
                }
        }
        for (String thisContact : UniqueListContact) {
            resultString += "\nContact Name: " + thisContact;
            for (Appointment thisAppointment : allAppointments){
                if (thisAppointment.getContact_Name().equals(thisContact)){
                    resultString +=
                            "\n\tAppointment ID: "+ Integer.toString(thisAppointment.getAppointment_ID())+
                            "\tTitle: "+ thisAppointment.getTitle() +
                            "\tType: "+ thisAppointment.getType() +
                            "\tStart: "+ thisAppointment.getStart_Datetime().toString() +
                            "\tEnd: "+ thisAppointment.getEnd_Datetime().toString() +
                            "\tCustomer ID: "+ Integer.toString(thisAppointment.getCustomer_Id());
                }
            }
        }


        return resultString;
    }
}
