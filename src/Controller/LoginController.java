package Controller;

import DAO.UserDaoImpl;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField UsernameTextField;
    public TextField PasswordTextField;

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
        else{System.out.println("No Login");}
    }
    public void initialize(URL location, ResourceBundle resources){

    }
}
