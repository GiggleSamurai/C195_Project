import DAO.UserDaoImpl;
import Model.User;
import Utility.UserLanguage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;



public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
      //  UserDaoImpl.SqlInsertCustomer( "louis", "address", "postal_code", "phone", 60);

        if (Locale.getDefault().equals("fr")){
            UserLanguage.setLanguage(Locale.FRENCH);
        } else {
            System.out.println("its english");
            UserLanguage.setLanguage(Locale.ENGLISH);
        }
        // UserLanguage.setLanguage(Locale.FRENCH);

        Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        /*
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root));
        stage.setScene(scene);
        stage.show();
*/     // Locale userLocale = new Locale("fr","FR");
        //Locale enLocale = Locale.ENGLISH;
        //ResourceBundle rb = ResourceBundle.getBundle("Utility/Nat", enLocale);
      //  Locale.getDefault();
        //System.out.println(rb.getString("hello"));
        //System.out.println("hello");
        stage.setTitle(UserLanguage.rb.getString("appointment")+" "+ UserLanguage.rb.getString("system"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
        //UserDaoImpl.getQuery();
       // System.out.println("'");
     //   User LoginUser = new User(123, "admin", "admin1");
      //  UserDaoImpl.checkUser(LoginUser);




    }


    public static void main(String[] args) {
        launch(args);
    }
}
