import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage StartStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/Main.fxml"));
        StartStage.setTitle("Hello World");
        StartStage.setScene(new Scene(root));
        StartStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
