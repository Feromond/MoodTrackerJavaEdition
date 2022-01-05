import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FirstWelcomeViewController {

    /******************************************
    *
    *   Labels and attributes for the welcome screen controller.
    *
     ******************************************/
    @FXML
    private Label welcomeText;
    @FXML
    private Label lastSeenAwhileAgo;

    private final MoodLogger logger = new MoodLogger();


    // JAVAFX initializer
    @FXML
    public void initialize() throws FileNotFoundException {
        /*
        *   This initializes UI elements which require backend or non static values. Prepares
        *       the initial view of the scene to appear as desired.
         */
        if (logger.loggerExists()){
            logger.getOldLog();

            String name = logger.getName();
            welcomeText.setText("Welcome, " + name + " to MoodTrackerV3!");
            if (logger.lastAccessDayOld()){
                lastSeenAwhileAgo.setText("Its wonderful to see you back! Try to make this a routine, its very important to spend some time on yourself!");
            }
        }
        else{
            welcomeText.setText("Welcome to MoodTracker V3!");
        }
    }

    /******************************************
    *
    *   All handlers for button presses or different UI interactions.
    *
     ******************************************/

    public void continueButtonPushed(ActionEvent event) throws IOException {
        /*
        *   Handles the continue button being pushed and changes to the next scene. The choice of
        *       which scene to change to is decided by whether it is the first time running the application or not
         */
        if (logger.loggerExists()) {
            Parent moodInputViewParent = FXMLLoader.load(getClass().getResource("MoodInputView.fxml"));
            Scene moodInputViewScene = new Scene(moodInputViewParent,800,600);

            // This gets the stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setResizable(false);
            window.setTitle("Mood Selections");
            window.setScene(moodInputViewScene);
            window.show();
        }
        else{
            Parent userNameEntryViewParent = FXMLLoader.load(getClass().getResource("GetUserNameView.fxml"));
            Scene userNameEntryViewScene = new Scene(userNameEntryViewParent,800,600);

            // This gets the stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setResizable(false);
            window.setTitle("User Name Entry");
            window.setScene(userNameEntryViewScene);
            window.show();
        }
    }
}
