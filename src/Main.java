/**
 * @class Main.java
 * @author Louis Wong
 */

import Utility.UserLanguage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Locale;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        if (Locale.getDefault().getLanguage().equals("fr")){
            UserLanguage.setLanguage(Locale.FRENCH);
        } else {
            UserLanguage.setLanguage(Locale.ENGLISH);
        }

        Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        stage.setTitle(UserLanguage.rb.getString("appointment")+" "+ UserLanguage.rb.getString("system"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
