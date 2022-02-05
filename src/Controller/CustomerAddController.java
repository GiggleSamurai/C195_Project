package Controller;

import DAO.UserDaoImpl;
import Model.All_First_Division;
import Model.Appointment;
import Model.Customer;
import Model.First_Division;
import Utility.UserLanguage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class CustomerAddController implements Initializable {
    public TextField CustomerNameInput;
    public TextField AddressInput;
    public TextField PostalCodeInput;
    public TextField PhoneInput;
    public ComboBox DivisionIdComboBox;
    public First_Division selectedDivision;

    public void SaveButton(ActionEvent actionEvent) throws Exception {
        selectedDivision = (First_Division) DivisionIdComboBox.getSelectionModel().getSelectedItem();
        System.out.println(selectedDivision.getDivision_Id());
        UserDaoImpl.SqlInsertCustomer( CustomerNameInput.getText(),
                                       AddressInput.getText(),
                                       PostalCodeInput.getText(),
                                       PhoneInput.getText(),
                                       selectedDivision.getDivision_Id());

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

    public void initialize(URL location, ResourceBundle resources){
        try {
            UserDaoImpl.SqlAllFirst_Division();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DivisionIdComboBox.setItems(All_First_Division.getAllFirst_Division());

    }
}
