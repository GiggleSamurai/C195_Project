/**
 * @class UserDaoImpl.java
 * @author Louis Wong
 */

package DAO;

import Model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;



public class UserDaoImpl {
    private static String currentApplicationUser;

    /**
     * @param checkThisUser login user
     * @return boolean if user & password is correct
     */
    public static boolean SqlCheckUser(User checkThisUser) throws SQLException, Exception{

        DBConnection.makeConnection();
        String checkThisUserName = checkThisUser.getUserName();
        String checkThisPassword = checkThisUser.getPassword();
        String sqlStatement="select * from users where User_Name =" +"'"+ checkThisUserName +"'";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();

        ArrayList<String> userNameList = new ArrayList<String>();
        ArrayList<String> passwordList = new ArrayList<String>();
        while(result.next()) {
            String userName = result.getString("User_Name");
            String password = result.getString("Password");
            userNameList.add(userName);
            passwordList.add(password);

            System.out.println(userName);
            System.out.println(password);
        }
        DBConnection.closeConnection();

        if (userNameList.contains(checkThisUserName)) {
            System.out.println("VALID USER");
            System.out.println(userNameList.indexOf(checkThisUserName));
            int listIndex = userNameList.indexOf(checkThisUserName);
            if (passwordList.get(listIndex).equals(checkThisPassword)){
                currentApplicationUser = checkThisUserName;
                System.out.println("CORRECT PASSWORD");
                return true;
            } else{
                System.out.println("INCORRECT PASSWORD");
                return false;
            }

        } else{
            System.out.println("INVALID USER");
            return false;
        }

    }

    /**
     * SQL query all customers
     */
    public static void SqlAllCustomers() throws SQLException, Exception{

        DBConnection.makeConnection();
        String sqlStatement="select * from customers";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();

        All_Customers.clearAllCustomers();

        while(result.next()) {

            int customer_id = result.getInt("Customer_ID");
            String customer_name = result.getString("Customer_Name");
            String address = result.getString("Address");
            String postal_code = result.getString("Postal_Code");
            String phone = result.getString("Phone");
            int division_id = result.getInt("Division_ID");

            Customer thisCustomer = new Customer(customer_id, customer_name, address, postal_code, phone, division_id);
            All_Customers.addCustomer(thisCustomer);

        }
        DBConnection.closeConnection();

    }

    /**
     * SQL query all appointments & convert to user local time
     */
    public static void SqlAllAppointments() throws SQLException, Exception{

        DBConnection.makeConnection();

        String sqlStatement= "SELECT * FROM appointments JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID;";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();

        All_Appointments.clearAllAppointments();

        while(result.next()) {

            int appointment_id = result.getInt("Appointment_ID");
            String title = result.getString("Title");
            String description = result.getString("Description");
            String location = result.getString("Location");
            String contact_name = result.getString("Contact_Name");
            String type = result.getString("Type");
            LocalDateTime start_datetime = result.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end_datetime = result.getTimestamp("End").toLocalDateTime();
            int customer_id = result.getInt("Customer_ID");
            int user_id = result.getInt("User_ID");

            //To User Local Time Conversion
            ZonedDateTime utcZoneTime_start_datetime = start_datetime.atZone(ZoneId.of("UTC"));
            ZonedDateTime userZoneTime_start_datetime = utcZoneTime_start_datetime.withZoneSameInstant(ZoneId.systemDefault());
            LocalDateTime userTime_start_datetime = userZoneTime_start_datetime.toLocalDateTime();

            ZonedDateTime utcZoneTime_end_datetime = end_datetime.atZone(ZoneId.of("UTC"));
            ZonedDateTime userZoneTime_end_datetime = utcZoneTime_end_datetime.withZoneSameInstant(ZoneId.systemDefault());
            LocalDateTime userTime_end_datetime = userZoneTime_end_datetime.toLocalDateTime();

            Appointment thisAppointment = new Appointment(appointment_id, title, description, location, contact_name, type, userTime_start_datetime, userTime_end_datetime, customer_id, user_id);
            All_Appointments.addAppointment(thisAppointment);

        }
        DBConnection.closeConnection();

    }


    /**
     *  SQL insert new customer to database table & refresh the UI table
     * @param customer_name the customer name
     * @param address the customer address
     * @param postal_code the customer postal code
     * @param phone the customer phone number
     * @param division_id the first division ID
     * @throws Exception
     */
    public static void SqlInsertCustomer( String customer_name, String address, String postal_code, String phone, int division_id) throws Exception {
        DBConnection.makeConnection();
        String sqlStatement = "SELECT MAX(Customer_ID) FROM customers";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        int maxUserId = 0;
        while (result.next()) {
            maxUserId = result.getInt("MAX(Customer_ID)");
        }

        sqlStatement = "INSERT INTO customers VALUES(" +
                        String.valueOf(maxUserId + 1) +
                        ", '" + customer_name + "'" +
                        ", '" + address + "'" +
                        ", '" + postal_code + "'" +
                        ", '" + phone + "'" +
                        ", NOW()" +
                        ", '" + currentApplicationUser + "'" +
                        ", NOW()" +
                        ", '" + currentApplicationUser + "'" +
                        ", " + String.valueOf(division_id) +
                        ")";
        Query.makeQuery(sqlStatement);
        All_Customers.refreshAllCustomers();

    }

    /**
     *  SQL query all the first division ID & location name
     * @throws Exception
     */
    public static void SqlAllFirst_Division() throws Exception {
        DBConnection.makeConnection();
        String sqlStatement="select * from first_level_divisions";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        All_First_Division.clearAllFirst_Division();

        while(result.next()) {

            int division_id = result.getInt("Division_ID");
            String division = result.getString("Division");
            First_Division thisFirst_Division = new First_Division(division_id,division);

            All_First_Division.addFirst_Division(thisFirst_Division);

        }
        DBConnection.closeConnection();

    }

    /**
     *
     * @param selectedCustomer customer to delete on SQL
     * @throws Exception
     */
    public static void SqlDeleteCustomer(Customer selectedCustomer) throws Exception {
        DBConnection.makeConnection();
        String sqlStatement = "delete from customers where Customer_Id = " + Integer.toString(selectedCustomer.getCustomer_Id());
        Query.makeQuery(sqlStatement);
        All_Customers.refreshAllCustomers();
        DBConnection.closeConnection();
    }


    /**
     *
     * @param customer_name the customer name
     * @param address the customer address
     * @param postal_code the customer postal code
     * @param phone the customer phone number
     * @param division_id the first division ID
     * @throws Exception
     */
    public static void SqlUpdateCustomer( String customer_id,String customer_name, String address, String postal_code, String phone, int division_id) throws Exception {
        DBConnection.makeConnection();

        String sqlStatement =
                "UPDATE Customers SET Customer_Name = '"+ customer_name + "'" +
                ", Address ='" + address + "'" +
                ", Postal_Code ='" + postal_code + "'" +
                ", Phone ='" + phone + "'" +
                ", Division_ID =" + division_id  +
                ", Last_Update = NOW()" +
                ", Last_Updated_By = '" + currentApplicationUser +"'"+
                " WHERE Customer_ID = " + customer_id;

        Query.makeQuery(sqlStatement);
        All_Customers.refreshAllCustomers();
    }


    /**
     *  SQL query all the contact from contacts table
     * @throws Exception
     */
    public static void SqlAllContact() throws Exception {
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * FROM contacts";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();

        All_First_Division.clearAllFirst_Division();

        while (result.next()) {

            int contact_id = result.getInt("Contact_ID");
            String contact_name = result.getString("Contact_Name");
            Contact thisContact = new Contact(contact_id, contact_name);

            All_Contacts.addContact(thisContact);

        }
        DBConnection.closeConnection();
    }

    /**
     *
     * @param title the appointment title
     * @param description the appointment description
     * @param location the appointment location
     * @param type the appointment type
     * @param start_datetime the appointment start time
     * @param end_datetime the appointment end time
     * @param customer_id the appointment customer ID
     * @param user_id the appointment user ID
     * @param contact_id the appointment contact ID
     * @throws Exception
     */
    public static void SqlInsertAppointment(String title,
                                            String description,
                                            String location,
                                            String type,
                                            LocalDateTime start_datetime,
                                            LocalDateTime end_datetime,
                                            int customer_id,
                                            int user_id,
                                            int contact_id
                                                            ) throws Exception {
        DBConnection.makeConnection();
        String sqlStatement = "SELECT MAX(Appointment_ID) FROM appointments";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        int maxAppointmentId = 0;
        while (result.next()) {
            maxAppointmentId = result.getInt("MAX(Appointment_ID)");
        }
        System.out.println(maxAppointmentId);
        sqlStatement = "INSERT INTO appointments VALUES(" +
                String.valueOf(maxAppointmentId + 1) +
                ", '" + title + "'" +
                ", '" + description + "'" +
                ", '" + location + "'" +
                ", '" + type + "'" +
                ", '" + Timestamp.valueOf(start_datetime) + "'" +
                ", '" + Timestamp.valueOf(end_datetime) + "'" +
                ", NOW()" +
                ", '" + currentApplicationUser + "'" +
                ", NOW()" +
                ", '" + currentApplicationUser + "'" +
                ", " + customer_id +
                ", " + user_id +
                ", " + contact_id +
                ")";

        System.out.println(sqlStatement);
        Query.makeQuery(sqlStatement);


        All_Appointments.refreshAllAppointments();

    }

    /**
     *
     * @param selectedAppointment the appointment to delete
     * @throws Exception
     */
    public static void SqlDeleteAppointment(Appointment selectedAppointment) throws Exception {
        DBConnection.makeConnection();
        String sqlStatement = "DELETE FROM appointments WHERE Appointment_Id = " + Integer.toString(selectedAppointment.getAppointment_ID());
        Query.makeQuery(sqlStatement);
        All_Customers.refreshAllCustomers();
        DBConnection.closeConnection();
    }

    /**
     *
     * @param customer_id the customer ID to check
     * @return boolean statement if customer ID is in the database
     * @throws Exception
     */
    public static boolean SqlCheckCustomer_ID(Integer customer_id) throws Exception {
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * FROM customers WHERE Customer_ID = " + Integer.toString(customer_id);
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();

        int thisId =0;
        while (result.next()) {
            thisId = result.getInt("Customer_ID");
        }
        DBConnection.closeConnection();
        if (thisId == customer_id) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param user_id the user ID
     * @return boolean statement if user ID is in the database
     * @throws Exception
     */
    public static boolean SqlCheckUser_ID(Integer user_id) throws Exception {
        DBConnection.makeConnection();
        String sqlStatement = "SELECT * FROM users WHERE User_ID = " + Integer.toString(user_id);
        Query.makeQuery(sqlStatement);

        ResultSet result = Query.getResult();

        int thisId =0;
        while (result.next()) {
            thisId = result.getInt("User_ID");
        }

        DBConnection.closeConnection();
        if (thisId == user_id) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param title the appointment title
     * @param description the appointment description
     * @param location the appointment location
     * @param type the appointment type
     * @param start_utcDatetime the appointment start time
     * @param end_utcDatetime the appointment end time
     * @param customer_id the appointment customer ID
     * @param user_id the appointment user ID
     * @param contact_id the appointment contact ID
     * @throws Exception
     */
    public static void SqlUpdateAppointment(int appointment_id, String title, String description, String location, String type, LocalDateTime start_utcDatetime, LocalDateTime end_utcDatetime, int customer_id, int user_id, int contact_id) throws Exception {

        DBConnection.makeConnection();

        String sqlStatement =
                "UPDATE appointments SET "+
                        " Title = '" + title + "'" +
                        ", Description = '" + description + "'" +
                        ", Location ='" + location + "'" +
                        ", Type = '" + type + "'" +
                        ", Start = '" + Timestamp.valueOf(start_utcDatetime) + "'" +
                        ", End = '" + Timestamp.valueOf(end_utcDatetime) + "'" +
                        ", Last_Update = NOW()" +
                        ", Last_Updated_By = '" + currentApplicationUser + "'" +
                        ", Customer_ID = " + customer_id +
                        ", User_ID = " + user_id +
                        ", Contact_ID = " + contact_id +
                        " WHERE Appointment_ID = " + appointment_id ;

        Query.makeQuery(sqlStatement);
        All_Appointments.refreshAllAppointments();
    }

}

