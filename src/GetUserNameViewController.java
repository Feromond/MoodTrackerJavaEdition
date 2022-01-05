import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GetUserNameViewController {

    /******************************************
     *
     *   Section to control the GetUserName Properties / Attributes
     *
     ******************************************/
    private String userNameString;

    @FXML
    private TextField userNameField = new TextField();
    @FXML
    private Label userNameResponseLabel = new Label();


    /******************************************
     *
     *   Controls for the GetUserName View
     *
     ******************************************/
    public void saveNameButtonPushed(ActionEvent event) throws IOException {
        /*
        *   This handler ensures the user enters a valid username and will continue to the next scene
        *       once a valid username is entered and this save name button is pushed.
         */
        if (userNameField.getText() != null && (!userNameField.getText().isEmpty()) && !Objects.requireNonNull(userNameField.getText()).isBlank()){
            this.userNameString = (userNameField.getText());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MoodInputView.fxml"));
            Parent root = fxmlLoader.load();
            MoodInputViewController controller = fxmlLoader.<MoodInputViewController>getController();
            // Passing the selected username string to the next controller for it to manage and have access to it.
            controller.setUserNameString(this.userNameString.replaceAll("( +)"," ").trim());
            Scene MoodInputViewScene = new Scene(root,800,600);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            stage.setScene(MoodInputViewScene);
            stage.setTitle("Mood Selections");
            stage.show();
        }
        else{
            userNameResponseLabel.setText("You did not enter your name.");
        }
    }
}