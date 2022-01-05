import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MoodInputViewController {



    /******************************************
     *
     *  Section to Control the MoodInputView Properties
     *
     ******************************************/

    // Creating a MoodLogger Object to handle all information
    private final MoodLogger logger = new MoodLogger();
    // Creating a Moods object to generate and manage the different moods.
    private Moods moods = new Moods();

    private String userNameString;

    // Initialized variables for mood variants
    private MoodVariants variantOne;
    private MoodVariants variantTwo;
    private MoodVariants variantThree;

    // Mood integer Score Controls
    @FXML
    private ChoiceBox<Integer> moodScoreIntChoiceBox = new ChoiceBox<Integer>();
    private Boolean moodScoreSaved = false;

    // Journal Entry Controls
    @FXML
    private TextArea journalEntryTextArea = new TextArea();
    private Boolean journalEntrySaved = false;

    // Mood Variant Selection Controls
    @FXML
    private Label moodVariantOne = new Label();
    @FXML
    private ChoiceBox<String> moodVariantOneChoiceBox = new ChoiceBox<>();
    private Boolean moodVariantOneSaved = false;
    @FXML
    private Label moodVariantTwo = new Label();
    @FXML
    private ChoiceBox<String> moodVariantTwoChoiceBox = new ChoiceBox<>();
    private Boolean moodVariantTwoSaved = false;
    @FXML
    private Label moodVariantThree = new Label();
    @FXML
    private ChoiceBox<String> moodVariantThreeChoiceBox = new ChoiceBox<>();
    private Boolean moodVariantThreeSaved = false;

    // Saving Log Controls for MoodScoreView
    @FXML
    private Label attemptedSaveLogResponseLabel = new Label();
    @FXML
    private Label pressToContinueLabel = new Label();


    // Unsaved Warning Labels for MoodScoreView
    @FXML
    private Label unsavedMoodScoreLabel = new Label();
    @FXML
    private Label unsavedMoodVariantOneLabel = new Label();
    @FXML
    private Label unsavedMoodVariantTwoLabel = new Label();
    @FXML
    private Label unsavedMoodVariantThreeLabel = new Label();
    @FXML
    private Label unsavedJournalEntryLabel = new Label();


    /******************************************
     *
     *   Initializer for all required components
     *
     ******************************************/
    public void initialize(){
        // Sets the username in the logger object from the previous controller (either first welcome or get username)
        Platform.runLater(() -> {
            this.logger.setName(this.userNameString);
        });

        // Initialize mood score choice box with range of integers
        moodScoreIntChoiceBox.setValue(1);
        moodScoreIntChoiceBox.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10));


        // Initialize Mood Choices with different variant names and with descriptions
        moods.setMoodVariantInfo();
        this.variantOne = moods.getVariantOptions().get(0);
        this.variantTwo = moods.getVariantOptions().get(1);
        this.variantThree = moods.getVariantOptions().get(2);

        ArrayList<String> variantNames = new ArrayList<>();

        // Adds the variant names to the variantNames ArrayList to separate.
        for (MoodVariants variants: moods.getVariantOptions()){
            variantNames.add(variants.getVariantName());
        }

        // Sets the Variant name Labels in the UI to the correct names.
        moodVariantOne.setText("How are you feeling " + variantNames.get(0) + "?");
        moodVariantTwo.setText("How are you feeling " + variantNames.get(1) + "?");
        moodVariantThree.setText("How are you feeling " + variantNames.get(2) + "?");

        // This initializes the displayed test in the choice boxes to say options, then allows users to change after.
        moodVariantOneChoiceBox.setValue("Options");
        moodVariantTwoChoiceBox.setValue("Options");
        moodVariantThreeChoiceBox.setValue("Options");

        // This adds all of the choices (descriptions) that the user can choose from to each of the choice boxes in the UI.
        moodVariantOneChoiceBox.setItems(FXCollections.observableArrayList(moods.getVariantOptionDescriptions().get(0)));
        moodVariantTwoChoiceBox.setItems(FXCollections.observableArrayList(moods.getVariantOptionDescriptions().get(1)));
        moodVariantThreeChoiceBox.setItems(FXCollections.observableArrayList(moods.getVariantOptionDescriptions().get(2)));

    }


    /*****************************************
     *
     *   Controls / Handlers for the MoodScoreView
     *
     ******************************************/
    public void moodScoreIntSelectedButtonPushed(ActionEvent event){
        /*
         *   This saves the entry of the mood score integer that the user had selected.
         *       It is activated when the save button is pressed for the mood score, and saves the value to the MoodLogger
         */
        logger.setMoodScore(moodScoreIntChoiceBox.getValue());
        this.moodScoreSaved = true;
        unsavedMoodScoreLabel.setText("Saved");
        unsavedMoodScoreLabel.setTextFill(Color.LIGHTGREEN);
    }

    public void saveJournalEntryButtonPushed(ActionEvent event){
        /*
         *   This saves the journal entry, under the conditions that the field is not empty, and not only blank spaces.
         *       If conditions are met, the text is saved to the MoodLogger.
         */
        if (journalEntryTextArea.getText() != null && (!journalEntryTextArea.getText().isEmpty())
                && (!Objects.requireNonNull(journalEntryTextArea.getText()).isBlank())){

            logger.setJournalEntry(journalEntryTextArea.getText());
            this.journalEntrySaved = true;
            unsavedJournalEntryLabel.setText("Saved");
            unsavedJournalEntryLabel.setTextFill(Color.LIGHTGREEN);
        }
    }

    public void moodVariantOneSaveButtonPressed(ActionEvent event){
        /*
         *   This handles the button press which saves the first Variant choice.
         *   Ensures that a valid option was selected before attempting to save to the MoodLogger.
         */
        if (!moodVariantOneChoiceBox.getValue().equals("Options")){
            int value = moodVariantOneChoiceBox.getSelectionModel().getSelectedIndex() + 1;
            this.moods.addMoodChoice(this.variantOne, value);
            this.moodVariantOneSaved = true;
            unsavedMoodVariantOneLabel.setText("Saved");
            unsavedMoodVariantOneLabel.setTextFill(Color.LIGHTGREEN);
        }
    }
    public void moodVariantTwoSaveButtonPressed(ActionEvent event){
        /*
         *   This handles the button press which saves the second Variant choice.
         *   Ensures that a valid option was selected before attempting to save to the MoodLogger.
         */
        if (!moodVariantTwoChoiceBox.getValue().equals("Options")){
            int value = moodVariantTwoChoiceBox.getSelectionModel().getSelectedIndex() + 1;
            this.moods.addMoodChoice(this.variantTwo, value);
            this.moodVariantTwoSaved = true;
            unsavedMoodVariantTwoLabel.setText("Saved");
            unsavedMoodVariantTwoLabel.setTextFill(Color.LIGHTGREEN);
        }
    }
    public void moodVariantThreeSaveButtonPressed(ActionEvent event){
        /*
         *   This handles the button press which saves the third Variant choice.
         *   Ensures that a valid option was selected before attempting to save to the MoodLogger.
         */
        if (!moodVariantThreeChoiceBox.getValue().equals("Options")){
            int value = moodVariantThreeChoiceBox.getSelectionModel().getSelectedIndex() + 1;
            this.moods.addMoodChoice(this.variantThree, value);
            this.moodVariantThreeSaved = true;
            unsavedMoodVariantThreeLabel.setText("Saved");
            unsavedMoodVariantThreeLabel.setTextFill(Color.LIGHTGREEN);
        }
    }

    public void saveTodayLogButtonPushed(ActionEvent event) throws IOException {
        /*
         *   This method is called when the Save Log button is pushed, and ensures that all required input fields
         *       have been saved with valid entries. If they are not, it enables labels which inform the user which
         *       fields are still left to be filled, and that there are missing entries. As each field is saved,
         *       the labels will disappear to indicate the field has been fixed.
         *   When all fields are saved correctly and are valid, the method will change the scene to the next view & controller. (MoodFeedBacK)
         */
        if (journalEntrySaved && moodScoreSaved && moodVariantOneSaved && moodVariantTwoSaved && moodVariantThreeSaved){
            logger.setVariantChoices(moods.getChoices());
            attemptedSaveLogResponseLabel.setText("");


            // Change scene to feedback
            MoodFeedback feedback = runMoodFeedback(this.moods,this.logger);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MoodFeedBackView.fxml"));
            Parent root = fxmlLoader.load();
            MoodFeedbackViewController controller = fxmlLoader.<MoodFeedbackViewController>getController();
            // Passes on certain instance variables such as the logger and the feedback into the next class to
            //      maintain the built up information.
            controller.setFeedback(feedback);
            controller.setMoodLog(this.logger);
            Scene moodFeedbackViewScene = new Scene(root,800,600);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            stage.setScene(moodFeedbackViewScene);
            stage.setTitle("Feedback");
            stage.show();
        }
        else{
            attemptedSaveLogResponseLabel.setText("Missing Selections!");
        }
        if (!moodScoreSaved){
            unsavedMoodScoreLabel.setText("Unsaved Entry");
            unsavedMoodScoreLabel.setTextFill(Color.RED);
        }
        if (!moodVariantOneSaved){
            unsavedMoodVariantOneLabel.setText("Unsaved Entry");
            unsavedMoodVariantOneLabel.setTextFill(Color.RED);
        }
        if (!moodVariantTwoSaved){
            unsavedMoodVariantTwoLabel.setText("Unsaved Entry");
            unsavedMoodVariantTwoLabel.setTextFill(Color.RED);
        }
        if (!moodVariantThreeSaved){
            unsavedMoodVariantThreeLabel.setText("Unsaved Entry");
            unsavedMoodVariantThreeLabel.setTextFill(Color.RED);
        }
        if (!journalEntrySaved){
            unsavedJournalEntryLabel.setText("Unsaved Entry");
            unsavedJournalEntryLabel.setTextFill(Color.RED);
        }
    }

    public static MoodFeedback runMoodFeedback(Moods moods, MoodLogger logger){
        /*
         *   Method which prepares the required information the MoodFeedBack class.
         *       This includes the MoodFeedBack instance which is generated from the users inputs.
         *      Inputs:
         *          moods: The instance of Moods which is created from the users input
         *          logger: The instance of the MoodLogger which contains the stored information of the entry
         *      Returns:
         *          feedback: This is the MoodFeedBack instance resulting from the current entry information.
         */
        MoodFeedback feedback = new MoodFeedback(moods);
        feedback.setMoodScore(logger.getMoodScore());
        feedback.moodScoreManager();
        feedback.moodVariantChoiceManager(feedback.overallMoodManager(),feedback.socialMoodManager(),feedback.introspectiveMoodManager());
        return feedback;
    }


    /******************************************
     *
     *   Setters & Getters for this controller.
     *
     ******************************************/

    public void setUserNameString(String name){
        this.userNameString = name;
    }
}
