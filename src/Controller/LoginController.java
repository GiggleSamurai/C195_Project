package Controller;

import DAO.UserDaoImpl;
import Model.User;
import Utility.UserLanguage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;


public class LoginController implements Initializable {
    public TextField UsernameTextField;
    public TextField PasswordTextField;
    public Label TimeZone;
    public Label UsernameLabel;
    public Label PasswordLabel;
    public Button LoginLabel;

    public void LoginButton(ActionEvent actionEvent) throws Exception, IOException {

        User LoginUser = new User(0000000, UsernameTextField.getText(), PasswordTextField.getText());
        boolean checkLogin = UserDaoImpl.checkUser(LoginUser);
        if (checkLogin == true){

            System.out.println("You can login");
            Parent root = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            System.out.println("No Login");
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
}
