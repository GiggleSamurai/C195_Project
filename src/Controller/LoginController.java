package Controller;

import DAO.UserDaoImpl;
import Model.All_Appointments;
import Model.Appointment;
import Model.User;
import Utility.UserLanguage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    public TextField UsernameTextField;
    public TextField PasswordTextField;
    public Label TimeZone;
    public Label UsernameLabel;
    public Label PasswordLabel;
    public Button LoginLabel;
    public static Timestamp loginUTCTime;
    public static LocalDateTime loginLocalTime;

    public void LoginButton(ActionEvent actionEvent) throws Exception, IOException {

        User LoginUser = new User(0000000, UsernameTextField.getText(), PasswordTextField.getText());
        boolean checkLogin = UserDaoImpl.SqlCheckUser(LoginUser);
        if (checkLogin == true){

            System.out.println("You can login");
            loginUTCTime = Timestamp.from(Instant.now());
            loginLocalTime = LocalDateTime.now();

            FileWriter textFile = new FileWriter("login_activity.txt", true);
            textFile.write("\nUser: "+ UsernameTextField.getText() + "    UTC_Date&Time_Stamp: "+loginUTCTime+"    Attempt: Success");
            textFile.close();

            Parent root = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
           // Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setX(450);
            stage.setY(150);
            Scene scene = new Scene(root);
            //scene.set
            stage.setScene(scene);
            stage.show();

            try {
                if (All_Appointments.upcoming15minAppointments(loginLocalTime)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Appointment");
                    alert.setHeaderText("Upcoming Appointment");
                    String upcomingAppointmentID = Integer.toString(All_Appointments.upcomingAppointment.getAppointment_ID());
                    String upcomingAppointmentDateTime = All_Appointments.upcomingAppointment.getStart_Datetime().toString();
                    alert.setContentText("Appointment ID: " + upcomingAppointmentID + "\nAppointment Start Date & Time: " + upcomingAppointmentDateTime);
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Appointment");
                    alert.setHeaderText("Upcoming Appointment");
                    alert.setContentText("There is no upcoming appointment.");
                    alert.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else {
            System.out.println("No Login");
            FileWriter textFile = new FileWriter("login_activity.txt", true);
            textFile.write("\nUser: "+ UsernameTextField.getText() + "    UTC_Date&Time_Stamp: "+loginUTCTime+"    Attempt: Fail");
            textFile.close();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(UserLanguage.rb.getString("login") + " " + UserLanguage.rb.getString("error"));
            alert.setHeaderText(UserLanguage.rb.getString("incorrect") + " " +
                                UserLanguage.rb.getString("username") + "/ " +
                                UserLanguage.rb.getString("password"));

            alert.setContentText(UserLanguage.rb.getString("please") + " " +
                                 UserLanguage.rb.getString("try") + " " +
                                 UserLanguage.rb.getString("again") + ".");
            alert.showAndWait();
        }
    }
    public void initialize(URL location, ResourceBundle resources){

        TimeZone.setText(UserLanguage.rb.getString(ZoneId.systemDefault().toString()));
        UsernameLabel.setText(UserLanguage.rb.getString("username"));
        PasswordLabel.setText(UserLanguage.rb.getString("password"));
        LoginLabel.setText(UserLanguage.rb.getString("login"));

    }

    public void PasswordEnter(ActionEvent actionEvent) throws Exception {
        LoginButton(actionEvent);
    }

    /*public static Instant getLoginUTCTime(){
        return loginUTCTime;
    }*/
}
