package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class UserDaoImpl {
    static boolean act;
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
            int userid=result.getInt("User_ID");
            String userNameG=result.getString("User_Name");
            String password=result.getString("Password");
            System.out.println(userid);
            System.out.println(userNameG);
            System.out.println(password);
        }
        DBConnection.closeConnection();
    }

    public static boolean checkUser(User checkThisUser) throws SQLException, Exception{

        DBConnection.makeConnection();
        String checkThisUserID = checkThisUser.getUserName();
        String checkThisPassword = checkThisUser.getPassword();
        String sqlStatement="select * from users where User_Name =" +"'"+ checkThisUserID +"'";
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

        if (userNameList.contains(checkThisUserID)) {
            System.out.println("VALID USER");
            System.out.println(userNameList.indexOf(checkThisUserID));
            int listIndex = userNameList.indexOf(checkThisUserID);
            if (passwordList.get(listIndex).equals(checkThisPassword)){
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
}

