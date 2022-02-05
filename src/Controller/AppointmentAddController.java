package Controller;

import DAO.UserDaoImpl;
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
import java.time.ZoneId;
import java.util.ResourceBundle;

public class AppointmentAddController implements Initializable {
    public TextField TitleInput;
    public TextField DescriptionInput;
    public TextField LocationInput;
    public ComboBox ContactComboBox;
    public DatePicker StartDatePicker;
    public ComboBox StartHourComboBox;
    public ComboBox StartMinComboBox;
    public ComboBox StartAmPmComboBox;
    public DatePicker EndDatePicker;
    public ComboBox EndHourComboBox;
    public ComboBox EndMinComboBox;
    public ComboBox EndAmPmComboBox;
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

    public void SaveButton(ActionEvent actionEvent) throws IOException {
        selectedContact = (Contact) ContactComboBox.getSelectionModel().getSelectedItem();
        // System.out.println(selectedContact.getDivision_Id());
    /*    UserDaoImpl.SqlInsertAppointment( CustomerNameInput.getText(),
                AddressInput.getText(),
                PostalCodeInput.getText(),
                PhoneInput.getText(),
                selectedDivision.getDivision_Id());*/

        Parent root = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        stage.setX(450);
        stage.setY(150);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
