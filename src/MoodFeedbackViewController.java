import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MoodFeedbackViewController {


    private MoodFeedback feedback;
    private MoodLogger logger;

    /******************************************
     *
     *  Section to control the properties of the MoodFeedBack Controller
     *
     ******************************************/

    // All String values related to the title labels and response labels for mood feedback.
    private String moodScoreFeedbackString;
    private String variantOneFeedbackString;
    private String variantTwoFeedbackString;
    private String variantThreeFeedbackString;

    private String randomDogVideoString;
    private String randomMovieString;
    private String dogVideoURL;
    private String randomMovieURL;

    // All labels in the UI which are the responses to the user input
    @FXML
    private Label moodScoreFeedbackLabel = new Label();
    @FXML
    private Label variantOneFeedbackLabel = new Label();
    @FXML
    private Label variantTwoFeedbackLabel = new Label();
    @FXML
    private Label variantThreeFeedbackLabel = new Label();

    // The hyperlinks in the UI which are generated based on specific values of the users inputs
    @FXML
    private Hyperlink randomDogVideoHyperlink = new Hyperlink();
    @FXML
    private Hyperlink randomMovieHyperlink = new Hyperlink();


    /******************************************
     *
     * Initializer to prepare / update all necessary components of the view.
     *
     ******************************************/
    @FXML
    public void initialize(){
        // Runs delayed to allow for the controller to gather information from the previous controller classes.
        Platform.runLater(() -> {
            // Sets the label texts to the generated responses based on the users input.
            moodScoreFeedbackLabel.setText(moodScoreFeedbackString);
            variantOneFeedbackLabel.setText(variantOneFeedbackString);
            variantTwoFeedbackLabel.setText(variantTwoFeedbackString);
            variantThreeFeedbackLabel.setText(variantThreeFeedbackString);

            // Saves the logger in correct format based on whether it is the first time the user is using the program.
            if (this.logger.loggerExists()){
                try {
                    this.logger.getOldLog();
                    this.logger.saveLog();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    this.logger.firstSaveLog();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Sets the url text for dog videos to correspond with what the URL is representing.
            randomDogVideoHyperlink.setText(randomDogVideoString);
            randomDogVideoHyperlink.setBorder(Border.EMPTY);
            // Checks if the video URL is empty, as not all user entries will generate this output.
            if (!(dogVideoURL.length() == 0)){
                randomDogVideoHyperlink.setOnAction(e -> {
                    if(Desktop.isDesktopSupported())
                    {
                        try {
                            Desktop.getDesktop().browse(new URI(this.dogVideoURL));

                        } catch (IOException | URISyntaxException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
            }
            // Sets the url text to correspond with which movie the URL is representing.
            randomMovieHyperlink.setText(randomMovieString);
            randomMovieHyperlink.setBorder(Border.EMPTY);
            // Checks if the video URL is empty, as not all user entries will generate this output.
            if (!(randomMovieURL.length() == 0)){
                randomMovieHyperlink.setOnAction(e -> {
                    if(Desktop.isDesktopSupported())
                    {
                        try {
                            Desktop.getDesktop().browse(new URI(this.randomMovieURL));

                        } catch (IOException | URISyntaxException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    /******************************************
     *
     *   Controls / Handlers for the MoodFeedbackController View.
     *
     ******************************************/
    public void continueButtonPushed(ActionEvent event) throws IOException {
        /*
        *   This handles the continue button being pressed, and will move onto the next video of the program.
         */
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PreviousMoodEntriesView.fxml"));
        Parent root = fxmlLoader.load();
        PreviousMoodEntriesViewController controller = fxmlLoader.<PreviousMoodEntriesViewController>getController();
        // Passes the instance of MoodLogger into the next controller for access to the new information.
        controller.setLogger(this.logger);
        Scene previousMoodEntriesScene = new Scene(root,800,600);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(previousMoodEntriesScene);
        stage.setTitle("Previous Mood-Logs");
        stage.show();
    }

    /******************************************
     *
     *   ALL SETTERS & GETTERS ARE BELOW HERE.
     *
     ******************************************/

    public void setFeedback(MoodFeedback feedback) {
        // Gets the existing instance of MoodFeedback from previous controllers in the program.
        this.feedback = feedback;
        this.moodScoreFeedbackString = feedback.getMoodScoreFeedback();
        this.variantOneFeedbackString = feedback.getOverallMood();
        this.variantTwoFeedbackString = feedback.getSocialMood();
        this.variantThreeFeedbackString = feedback.getIntrospectiveMood();

        this.dogVideoURL = feedback.getDogVideoURL();
        this.randomDogVideoString = feedback.getDogVideoDescriptionName();
        this.randomMovieString = feedback.getMovieRecommendationName();
        this.randomMovieURL = feedback.getMovieRecommendationURL();
    }
    public void setMoodLog(MoodLogger logger){
        // Gets the existing instance of MoodLogger from previous controllers in the program.
        this.logger = logger;
    }
}
