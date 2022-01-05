import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class PreviousMoodEntriesViewController {
    // Contains the instance of the logger passed throughout the program.
    private MoodLogger logger;

    /******************************************
     *
     *   Labels and Attributes for the PreviousMoodEntriesView Controller
     *
     ******************************************/

    // String values which will be used for the associated labels.
    private String entryDate;
    private String journalEntry;
    private String overallMoodChoice;
    private String socialMoodChoice;
    private String introspectiveMoodChoice;

    // Initializing various ArrayList structures to separate and store the required information.
    private ArrayList<ArrayList<String>> oldMoodVariantChoices;
    private ArrayList<String> oldOverallMood = new ArrayList<>();
    private ArrayList<String> oldSocialMood = new ArrayList<>();
    private ArrayList<String> oldIntrospectiveMood = new ArrayList<>();
    private ArrayList<String> oldJournalEntry;
    private ArrayList<String> oldEntryDate;

    // Tracks the index of which entry is being viewed, allowing the values to update based on this index.
    //      Most recent index is 0, older entries are at index values > 1.
    private int currentEntryIndex = 0;

    // All the UI labels (and text area) which will update with the specific text for the entry index.
    @FXML
    private Label currentEntryDateLabel = new Label();
    @FXML
    private TextArea currentJournalEntryTextArea = new TextArea();
    @FXML
    private Label currentOverallMoodLabel = new Label();
    @FXML
    private Label currentSocialMoodLabel = new Label();
    @FXML
    private Label currentIntrospectiveMoodLabel = new Label();


    /******************************************
     *
     * Initializer to prepare / update all necessary components of the view.
     *
     ******************************************/
    public void initialize(){
       // Runs delayed to allow for the controller to gather information from the previous controller classes.
        Platform.runLater(() -> {

            // Reads the MoodLog file to gather the past entry dates, journal entries, and mood variant choices
            try {
                this.oldMoodVariantChoices = this.logger.locatePastMoodVariantChoices();
                this.oldJournalEntry = this.logger.locatePastJournalEntries();
                this.oldEntryDate = this.logger.locatePastEntryDates();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // Initializes the EntryDate Label and JournalEntry Label to the absolute most recent entry.
            currentEntryDateLabel.setText(this.logger.getEntryDate().toString());
            currentJournalEntryTextArea.setText("'" + this.logger.getJournalEntry() + "'");




            /*******************************************
            *   This loop iterates through all past mood choices, where each past entry has multiple variant and choice lines,
            *       and each line of variant lines have different values associated.
            *
            *   For each entry in all old entries, and for each variant in each entry, an empty HashMap was generated.
            *       The variant strings are then split by "," to separate the different information groups. Each group is
            *       again split into Key and KeyValue pairs by splitting by ":". Each key and key-value is then added to the
            *       HashMap. Using the logic statements, specific desired key values can be identified. These key value results
            *       can then indicate which variant the users selection is corresponding with, and can have these results added
            *       to whichever ArrayList. Doing this allows for all of they variants and descriptions to be properly organized
            *       and added to the initialized arrays to be later assigned to the Labels.
            *
            *   The MoodLog.txt file stores the logs based on which is most recent, although the mood variants are not always
            *       in the expected same order. This HashMap removes any problems with that specific section being in a
            *       different order and organizes the data for easy access.
             *******************************************/
            for (ArrayList<String> entries : this.oldMoodVariantChoices){
                for (String variants : entries){
                    Map<String, String> map = new LinkedHashMap<String, String>();
                    String[] groups = variants.split(",");
                    for (int i = 0; i < groups.length;i++){
                        String pair = groups[i];
                        String[] keyValues = pair.split(":");
                        map.put(keyValues[0], keyValues[1]);
                    }
                    if (map.get("          VARIANT").equals("'Overall'")){
                        oldOverallMood.add(map.get("  DESCRIPTION OF CHOICE"));
                    }
                    else if (map.get("          VARIANT").equals("'Socially'")){
                        oldSocialMood.add(map.get("  DESCRIPTION OF CHOICE"));
                    }
                    else if (map.get("          VARIANT").equals("'Introspectively'")){
                        oldIntrospectiveMood.add(map.get("  DESCRIPTION OF CHOICE"));
                    }
                    else{
                        System.out.println("Something is wrong");
                    }
                }
            }


            // Initializes the labels for the chosen variant descriptions to the absolute latest entry.
            currentOverallMoodLabel.setText(oldOverallMood.get(0));
            currentSocialMoodLabel.setText(oldSocialMood.get(0));
            currentIntrospectiveMoodLabel.setText(oldIntrospectiveMood.get(0));

        });
    }

    /******************************************
     *
     *   Controls / Handlers for the PreviousMoodEntries View.
     *
     ******************************************/
    public void nextOldLogButtonPressed(ActionEvent event){
        /*
        *   This handles the next log button being pressed, and updates the view with the next most recent Mood entry
        *       that the user had entered. It is constrained by the amount of entries that the user has made overall.
         */
        // Increases the index by 1 to change the entry index.
        this.currentEntryIndex += 1;

        // Ensures the entry index is not increasing beyond the existing number of total past entries.
        if (!(currentEntryIndex > (this.oldEntryDate.size() - 1))){
            // Updates all of the UI elements with the entry information for the given index of previous entries.
            this.currentEntryDateLabel.setText(this.oldEntryDate.get(currentEntryIndex));
            this.currentJournalEntryTextArea.setText(this.oldJournalEntry.get(currentEntryIndex));
            this.currentOverallMoodLabel.setText(this.oldOverallMood.get(currentEntryIndex));
            this.currentSocialMoodLabel.setText(this.oldSocialMood.get(currentEntryIndex));
            this.currentIntrospectiveMoodLabel.setText(this.oldIntrospectiveMood.get(currentEntryIndex));
        }
        else{
            // Lowers the entry index as it was beyond an allowable value.
            this.currentEntryIndex -= 1;
        }

    }

    public void previousOldLogButtonPressed(ActionEvent event){
        /*
        *   This handles the previous entry log button, which updates the view to a more recent entry than the current view.
        *       It is constrained to not go below the index of 0 as that is that is the index for the latest entry, and
        *       no entries exist that are more recent.
         */
        this.currentEntryIndex -= 1;
        // Changes the view to a more recent entry than the current index.
        if (!(this.currentEntryIndex <= 0)){
            this.currentEntryDateLabel.setText(this.oldEntryDate.get(currentEntryIndex));
            this.currentJournalEntryTextArea.setText(this.oldJournalEntry.get(currentEntryIndex));
            this.currentOverallMoodLabel.setText(this.oldOverallMood.get(currentEntryIndex));
            this.currentSocialMoodLabel.setText(this.oldSocialMood.get(currentEntryIndex));
            this.currentIntrospectiveMoodLabel.setText(this.oldIntrospectiveMood.get(currentEntryIndex));
        }
        // Handles if index is 0, sets the view to the current absolute latest entry
        else if (this.currentEntryIndex == 0){
            this.currentEntryDateLabel.setText(this.logger.getEntryDate().toString());
            this.currentJournalEntryTextArea.setText("'" + this.logger.getJournalEntry() + "'");
            this.currentOverallMoodLabel.setText(this.oldOverallMood.get(0));
            this.currentSocialMoodLabel.setText(this.oldSocialMood.get(0));
            this.currentIntrospectiveMoodLabel.setText(this.oldIntrospectiveMood.get(0));
        }
        else {
            // Increases the index value as it is beyond an allowable value.
            this.currentEntryIndex += 1;
        }

    }

    // Exits the program, acts the same as the red X button in the right corner. Closes the program once the user
    //  is finished reviewing past entries.
    public void quitMoodLoggerButtonPushed(ActionEvent event){
        Platform.exit();
    }


    /******************************************
     *
     * ALL SETTERS & GETTERS ARE BELOW HERE
     *
     ******************************************/

    public void setLogger(MoodLogger logger){
        this.logger = logger;
    }
}
