/**
 * @class AppointmentAddController.java
 * @author Louis Wong
 */

package Controller;

import DAO.UserDaoImpl;
import Model.All_Appointments;
import Model.All_Contacts;
import Model.Contact;
import Utility.DisplayTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class AppointmentAddController implements Initializable {
    public TextField TitleInput;
    public TextField DescriptionInput;
    public TextField LocationInput;
    public ComboBox ContactComboBox;
    public TextField TypeInput;
    public DatePicker StartDatePicker;
    public ComboBox StartHourComboBox;
    public ComboBox StartMinComboBox;
    public ComboBox StartAmPmComboBox;
    public DatePicker EndDatePicker;
    public ComboBox EndHourComboBox;
    public ComboBox EndMinComboBox;
    public ComboBox EndAmPmComboBox;
    public TextField CustomerIdInput;
    public TextField UserIdInput;
    public Contact selectedContact;

    /**
     * Initialize elements when this FXML form is load
     */
    public void initialize(URL location, ResourceBundle resources){
        StartHourComboBox.setItems(DisplayTime.getAllHours());
        EndHourComboBox.setItems(DisplayTime.getAllHours());
        StartMinComboBox.setItems(DisplayTime.getAllMinutes());
        EndMinComboBox.setItems(DisplayTime.getAllMinutes());
        StartAmPmComboBox.setItems(DisplayTime.getAMorPM());
        EndAmPmComboBox.setItems(DisplayTime.getAMorPM());
        StartHourComboBox.getSelectionModel().selectFirst();
        EndHourComboBox.getSelectionModel().selectFirst();
        StartMinComboBox.getSelectionModel().selectFirst();
        EndMinComboBox.getSelectionModel().selectFirst();
        StartAmPmComboBox.getSelectionModel().selectFirst();
        EndAmPmComboBox.getSelectionModel().selectFirst();
        StartDatePicker.setValue(LocalDate.now(ZoneId.systemDefault()));
        EndDatePicker.setValue(LocalDate.now(ZoneId.systemDefault()));

        try {
            UserDaoImpl.SqlAllContact();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ContactComboBox.setItems(All_Contacts.getAllContact());
        ContactComboBox.getSelectionModel().selectFirst();
    }

    /**
     *
     * @param actionEvent save the appointment to SQL & load main scene if no error
     * @throws Exception
     */
    public void SaveButton(ActionEvent actionEvent) throws Exception {
        try {
            selectedContact = (Contact) ContactComboBox.getSelectionModel().getSelectedItem();

            String title = TitleInput.getText();
            String description = DescriptionInput.getText();
            String location = LocationInput.getText();
            String type = TypeInput.getText();

            LocalDate StartDate = StartDatePicker.getValue();
            String StartHour = StartHourComboBox.getSelectionModel().getSelectedItem().toString();
            String StartAMorPM = StartAmPmComboBox.getSelectionModel().getSelectedItem().toString();
            String StartMinute = StartMinComboBox.getSelectionModel().getSelectedItem().toString();
            //User input
            LocalDateTime userSelectStartTime = LocalDateTime.of(StartDate.getYear(), StartDate.getMonthValue(), StartDate.getDayOfMonth(), DisplayTime.getHourInt(StartHour, StartAMorPM), DisplayTime.getMinuteInt(StartMinute));
            LocalDateTime start_utcDatetime = DisplayTime.userTime2UTC(userSelectStartTime);

            LocalDate EndDate = EndDatePicker.getValue();
            String EndHour = EndHourComboBox.getSelectionModel().getSelectedItem().toString();
            String EndAMorPM = EndAmPmComboBox.getSelectionModel().getSelectedItem().toString();
            String EndMinute = EndMinComboBox.getSelectionModel().getSelectedItem().toString();
            LocalDateTime userSelectEndTime = LocalDateTime.of(EndDate.getYear(), EndDate.getMonthValue(), EndDate.getDayOfMonth(), DisplayTime.getHourInt(EndHour, EndAMorPM), DisplayTime.getMinuteInt(EndMinute));
            LocalDateTime end_utcDatetime = DisplayTime.userTime2UTC(userSelectEndTime);

            int contact_id = ((Contact) ContactComboBox.getSelectionModel().getSelectedItem()).getContact_Id();
            int customer_id = Integer.parseInt(CustomerIdInput.getText());
            int user_id = Integer.parseInt(UserIdInput.getText());

            if(InputErrorCheck(customer_id, user_id, userSelectEndTime, userSelectStartTime, start_utcDatetime, end_utcDatetime)){
                UserDaoImpl.SqlInsertAppointment(title,
                        description,
                        location,
                        type,
                        start_utcDatetime,
                        end_utcDatetime,
                        customer_id,
                        user_id,
                        contact_id);

                Parent root = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                stage.setX(450);
                stage.setY(150);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Input Error");
            alert.setContentText("Please fill the form. \nCustomer ID & User ID must be integer.");
            alert.showAndWait();
        }

    }

    /**
     *
     * @param actionEvent load main scene
     * @throws Exception
     */
    public void CancelButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setX(450);
        stage.setY(150);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * RUNTIME ERROR when save method is called, this method will check each text field by using try and catch blocks, and if else statements, append all result in a single string, then set it on a label.
     * display text field to user on error message output label with text wrap
     * @return boolean of input text field error
     */
    public boolean InputErrorCheck(int customer_id, int user_id, LocalDateTime userSelectEndTime, LocalDateTime userSelectStartTime, LocalDateTime start_utcDatetime, LocalDateTime end_utcDatetime) throws Exception {
        String errorMessage ="";

        // Customer ID check
        try {
            if (UserDaoImpl.SqlCheckCustomer_ID(customer_id) == false){
                String message = "Customer ID is Invalid.";
                errorMessage += "\n"+message;
            }
        } catch(Exception e){
            String message = "Customer ID is Invalid.";
            errorMessage += "\n"+message;
        }

        // User ID check
        try {
            if (UserDaoImpl.SqlCheckUser_ID(user_id) == false){
                String message = "User ID is invalid.";
                errorMessage += "\n"+message;
                System.out.println("User return false");
            }
        } catch(Exception e){
            String message = "User ID is invalid.";
            errorMessage += "\n"+message;
        }

        // Check if Time Greater
        if (userSelectEndTime.isAfter(userSelectStartTime)){
            // It's good
        } else {
            String message = "End time must be greater than start time.";
            errorMessage += "\n"+message;
        }

        // Check if Overlap
        if (All_Appointments.checkCustomerAppointments(customer_id,userSelectStartTime) == true) {
            // It's good
        }
        else {
            String message = "The appointment is overlap with other appointment.";
            errorMessage += "\n"+message;
            }

        // Check if in Business Hours
        if (DisplayTime.inBusinessHours(start_utcDatetime,end_utcDatetime)==true){
            // It's good
        } else {
            String message = "The appointment is not in business hours (EST. 8AM - 10PM ).";
            errorMessage += "\n"+message;
        }

        if (errorMessage.isEmpty()){
            return true;
        } else {
            //Pop an alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Input Error");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
