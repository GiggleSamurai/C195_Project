import DAO.UserDaoImpl;
import Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
        /*
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root));
        stage.setScene(scene);
        stage.show();
*/
        stage.setTitle("Hello World");
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
