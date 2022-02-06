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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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

    public void SaveButton(ActionEvent actionEvent) throws Exception {
        selectedContact = (Contact) ContactComboBox.getSelectionModel().getSelectedItem();

        String title = TitleInput.getText();
        String description = TitleInput.getText();
        String location = TitleInput.getText();
        String type = TitleInput.getText();

        LocalDate StartDate = StartDatePicker.getValue();
        String StartHour = StartHourComboBox.getSelectionModel().getSelectedItem().toString();
        String StartAMorPM = StartAmPmComboBox.getSelectionModel().getSelectedItem().toString();
        String StartMinute = StartMinComboBox.getSelectionModel().getSelectedItem().toString();
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

        if (userSelectEndTime.isAfter(userSelectStartTime)){
            if (All_Appointments.checkCustomerAppointments(customer_id,userSelectStartTime) == true) {
                if (DisplayTime.inBusinessHours(start_utcDatetime,end_utcDatetime)==true){
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
                } else {
                    System.out.println("NOT IN BUSINESS HOUR!");
                }
            }
            else { System.out.println("BAD APPOINTMENT OVERLAP!!");}
        } else {
            System.out.println("End Time Must be Greater than Start Time");
        }

    }

    public void CancelButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setX(450);
        stage.setY(150);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
