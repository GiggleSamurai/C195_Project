package DAO;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;



public class UserDaoImpl {
    private static String currentApplicationUser;
    static boolean act;
    /*
    public static User getUser(String userName) throws SQLException, Exception{
        // type is name or phone, value is the name or the phone #
        DBConnection.makeConnection();
        String sqlStatement="select * FROM users WHERE User_Name  = '" + userName+ "'";
        //  String sqlStatement="select FROM address";
        Query.makeQuery(sqlStatement);
        User userResult;
        ResultSet result=Query.getResult();
        while(result.next()){
            int userid=result.getInt("User_ID");
            String userNameG=result.getString("User_Name");
            String password=result.getString("Password");
            userResult= new User(userid, userName, password);
            return userResult;
        }
        DBConnection.closeConnection();
        return null;
    }
    public static ObservableList<User> getAllUsers() throws SQLException, Exception{
        ObservableList<User> allUsers= FXCollections.observableArrayList();
        DBConnection.makeConnection();
        String sqlStatement="select * from users";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){
            int userid=result.getInt("User_ID");
            String userNameG=result.getString("User_Name");
            String password=result.getString("Password");
            User userResult= new User(userid, userNameG, password);
            allUsers.add(userResult);

        }
        DBConnection.closeConnection();
        return allUsers;
    }

    public static void getQuery() throws SQLException, Exception{

        DBConnection.makeConnection();
        String sqlStatement="select * from users";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        System.out.println(result.toString());
        while(result.next()){
            int userid = result.getInt("User_ID");
            String userNameG=result.getString("User_Name");
            String password=result.getString("Password");
            System.out.println(userid);
            System.out.println(userNameG);
            System.out.println(password);
        }
        DBConnection.closeConnection();
    }
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

    public static void SqlAllAppointments() throws SQLException, Exception{

        DBConnection.makeConnection();

        String sqlStatement= "SELECT * FROM appointments JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID;";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();

        //SELECT * FROM table1 appointments JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID;

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
    //SqlInsertCustomer( String customer_name, String address, String postal_code, String phone, int division_id)
    public static void SqlInsertCustomer( String customer_name, String address, String postal_code, String phone, int division_id) throws Exception {
        DBConnection.makeConnection();
        String sqlStatement = "SELECT MAX(Customer_ID) FROM customers";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();
        int maxUserId = 0;
        while (result.next()) {
            maxUserId = result.getInt("MAX(Customer_ID)");
        }
        System.out.println(String.valueOf(maxUserId + 1));
        //currentApplicationUser ="hihi";
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

        System.out.println(sqlStatement);
        All_Customers.refreshAllCustomers();
      //  Query.makeQuery(sqlStatement);
    }

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

    public static void SqlDeleteCustomer(Customer selectedCustomer) throws Exception {
        DBConnection.makeConnection();
        String sqlStatement = "delete from customers where Customer_Id = " + Integer.toString(selectedCustomer.getCustomer_Id());
        Query.makeQuery(sqlStatement);
        All_Customers.refreshAllCustomers();
        DBConnection.closeConnection();
    }



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


    public static void SqlAllContact() throws Exception {
        DBConnection.makeConnection();
        String sqlStatement="SELECT * FROM contacts";
        Query.makeQuery(sqlStatement);
        ResultSet result = Query.getResult();

        All_First_Division.clearAllFirst_Division();

        while(result.next()) {

            int contact_id = result.getInt("Contact_ID");
            String contact_name = result.getString("Contact_Name");
            Contact thisContact = new Contact(contact_id,contact_name);

            All_Contacts.addContact(thisContact);

        }
        DBConnection.closeConnection();

    }
}

