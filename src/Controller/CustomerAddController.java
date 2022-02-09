/**
 * @class CustomerAddController.java
 * @author Louis Wong
 */

package Controller;

import Model.*;
import DAO.UserDaoImpl;
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
import java.util.ResourceBundle;

public class CustomerAddController implements Initializable {
    public TextField CustomerNameInput;
    public TextField AddressInput;
    public TextField PostalCodeInput;
    public TextField PhoneInput;
    public ComboBox DivisionIdComboBox;
    public First_Division selectedDivision;
    public Countries selectedCountry;
    public ComboBox CountryComboBox;

    /**
     *
     * @param actionEvent save the customer to SQL & load main scene if no error
     * @throws Exception
     */
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

    /**
     *
     * @param actionEvent Load main scene
     * @throws IOException
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
     * Initialize elements when this FXML form is load
     */
    public void initialize(URL location, ResourceBundle resources){
        try {
            UserDaoImpl.SqlAllCountries();
        } catch (Exception e) {
            e.printStackTrace();
        }

        CountryComboBox.setItems(All_Countries.getAll_Countries());

    }

    /**
     *  sql to get country list with the country is selected
     * @param actionEvent trigger when country combo box is switched
     * @throws Exception
     */
    public void CountrySwitchTrigger(ActionEvent actionEvent) throws Exception {
        selectedCountry = (Countries) CountryComboBox.getSelectionModel().getSelectedItem();
        DivisionIdComboBox.setItems(UserDaoImpl.SqlFirst_DivisionByCountry(selectedCountry.getCountry_Id()));
        DivisionIdComboBox.getSelectionModel().selectFirst();

    }
}
