import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// This is the class which executes my MoodTracker, Version #3, Program.

public class MoodTrackerFinalGUI extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FirstWelcomeView.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Welcome Window");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


