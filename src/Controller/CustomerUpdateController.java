/**
 * @class CustomerUpdateController.java
 * @author Louis Wong
 */

package Controller;

import Model.*;
import DAO.UserDaoImpl;
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
import java.util.ResourceBundle;

public class CustomerUpdateController implements Initializable {
    public TextField Customer_IdTextField;
    public TextField CustomerNameInput;
    public TextField AddressInput;
    public TextField PostalCodeInput;
    public TextField PhoneInput;
    public ComboBox DivisionIdComboBox;
    public ComboBox CountryComboBox;
    public First_Division selectedDivision;
    public Countries selectedCountry;

    /**
     *
     * @param actionEvent save the customer to SQL & load main scene if no error
     * @throws Exception
     */
    public void SaveButton(ActionEvent actionEvent) throws Exception {
        selectedDivision = (First_Division) DivisionIdComboBox.getSelectionModel().getSelectedItem();
        System.out.println(selectedDivision.getDivision_Id());
        UserDaoImpl.SqlUpdateCustomer( Customer_IdTextField.getText(),
                CustomerNameInput.getText(),
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
     * @param actionEvent load main scene
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
    public void initialize(URL location, ResourceBundle resources) {
        int selectedDivision_Id = MainController.selectedCustomer.getDivision_Id();
        int selectedCountry_Id = 0;
        int First_DivisionArrayIndex = -1;

        ObservableList<First_Division> thisCountryFirst_Division = FXCollections.observableArrayList();
        try {
            UserDaoImpl.SqlAllCountries();
            selectedCountry_Id = UserDaoImpl.SqlLookUpCountryID(selectedDivision_Id);
            thisCountryFirst_Division = UserDaoImpl.SqlFirst_DivisionByCountry(selectedCountry_Id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CountryComboBox.setItems(All_Countries.getAll_Countries());
        CountryComboBox.getSelectionModel().select(All_Countries.lookup_Country(selectedCountry_Id));
        DivisionIdComboBox.setItems(thisCountryFirst_Division);

        Customer_IdTextField.setText(Integer.toString(MainController.selectedCustomer.getCustomer_Id()));
        CustomerNameInput.setText(MainController.selectedCustomer.getCustomer_Name());
        AddressInput.setText(MainController.selectedCustomer.getAddress());
        PostalCodeInput.setText(MainController.selectedCustomer.getPostal_Code());
        PhoneInput.setText(MainController.selectedCustomer.getPhone());


        for(First_Division foundObj : thisCountryFirst_Division){
            First_DivisionArrayIndex += 1;
            if (foundObj.getDivision_Id() == selectedDivision_Id) {

                DivisionIdComboBox.getSelectionModel().select(First_DivisionArrayIndex);
            }
        }

    }

    /**
     *  sql to get country list with the country is selected
     * @param actionEvent trigger when country combo box is switched
     * @throws Exception
     */
    public void CountrySwitchTrigger(ActionEvent actionEvent) throws Exception {
        if (CountryComboBox.getSelectionModel() == null || selectedCountry == null ){}
        else {
            selectedCountry = (Countries) CountryComboBox.getSelectionModel().getSelectedItem();
            DivisionIdComboBox.setItems(UserDaoImpl.SqlFirst_DivisionByCountry(selectedCountry.getCountry_Id()));
            DivisionIdComboBox.getSelectionModel().selectFirst();
        }

    }


}
