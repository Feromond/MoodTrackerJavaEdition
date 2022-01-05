import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class MoodLogger {

    /******************************************
     *
     * This class is responsible for reading and writing to the mood log file. Handles the passage of information between
     *   different aspects of the program.
     *
     ******************************************/


    // All attributes for the mood logger class
    private final String fileName;
    private float moodScore;
    private String journalEntry;
    private LocalDate entryDate;
    private String oldEntryDate;
    private final int maxDaysSinceAccess = 2;
    private String name;
    private final File moodLog;
    private ArrayList<String> variantChoices;
    private final ArrayList<String> oldLog = new ArrayList<>();


    // First constructor, Uses the default filename, prepares the file with the file name and gathers the date.
    public MoodLogger(){
        this.entryDate = LocalDate.now();
        this.fileName = "./MoodLog.txt";
        this.moodLog = new File(this.fileName);
    }

    // Optionally, this constructor could be used in a variant of the program, which could have multiple users, each
    //  with their own file.
    public MoodLogger(String fileName) {
        this.entryDate = LocalDate.now();
        this.fileName = fileName;
        this.moodLog = new File(this.fileName);
    }


    public void saveLog() throws IOException {
        /*
        * This method saves the log information for an already existing user. Saves latest log along with all previous logs.
        *   Input:
        *       N/A, uses attributes
        *   Returns:
        *       N/A, saves to file.
         */
        BufferedWriter moodFileWriter = new BufferedWriter(new FileWriter(moodLog, false));
        moodFileWriter.write("DATE:");
        moodFileWriter.newLine();
        moodFileWriter.write(String.valueOf(entryDate));
        moodFileWriter.newLine();
        moodFileWriter.write(name);
        moodFileWriter.newLine();
        moodFileWriter.write(String.valueOf(moodScore));
        moodFileWriter.newLine();
        moodFileWriter.write("MOOD CHOICES:");
        moodFileWriter.newLine();
        ArrayList<String> moodChoices = formatMoodChoices();
        for (String choices : moodChoices){
            moodFileWriter.write("          "+choices);
            moodFileWriter.newLine();
        }
        moodFileWriter.write("JOURNAL ENTRY:");
        moodFileWriter.newLine();
        moodFileWriter.write("          '"+journalEntry+"'");
        moodFileWriter.newLine();
        moodFileWriter.write("-------------------------------------------------------------------------------");
        moodFileWriter.newLine();
        moodFileWriter.newLine();

        for (String s : oldLog) {
            moodFileWriter.newLine();
            moodFileWriter.write(s);
        }
        moodFileWriter.close();
    }


    public void firstSaveLog() throws IOException {
        /*
        * This method saves the content of the entry if it is the users first time saving a log.
        *   Inputs:
        *       N/A, uses attributes
        *   Returns:
        *       N/A, saves file for the first time
         */
        BufferedWriter moodFileWriter = new BufferedWriter(new FileWriter(moodLog, true));
        moodFileWriter.write("DATE:");
        moodFileWriter.newLine();
        moodFileWriter.write(String.valueOf(entryDate));
        moodFileWriter.newLine();
        moodFileWriter.write(name);
        moodFileWriter.newLine();
        moodFileWriter.write(String.valueOf(moodScore));
        moodFileWriter.newLine();
        moodFileWriter.write("MOOD CHOICES:");
        moodFileWriter.newLine();
        ArrayList<String> moodChoices = formatMoodChoices();
        for (String choices : moodChoices){
            moodFileWriter.write("          "+choices);
            moodFileWriter.newLine();
        }
        moodFileWriter.write("JOURNAL ENTRY:");
        moodFileWriter.newLine();
        moodFileWriter.write("          '"+journalEntry+"'");
        moodFileWriter.newLine();
        moodFileWriter.write("-------------------------------------------------------------------------------");
        moodFileWriter.close();
    }


    public boolean loggerExists(){
        /*
        * This method determines whether there are any mood logs for a file.
        *   Inputs:
        *       N/A
        *   Returns:
        *       True: if the file is not empty
        *       False: if the file is empty
         */

        return moodLog.length() > 0;
    }


    public void getOldLog() throws FileNotFoundException {
        /*
        * This method gets the content of all previously logged entries and sets the attributes
        *   of name and old entry date
        *   Inputs:
        *       N/A, uses attributes
        *   Returns:
        *       N/A, sets attributes of name oldEntryDate, and oldLog.
         */
        Scanner fileReader = new Scanner(moodLog);
        while (fileReader.hasNext()){
            String data = fileReader.nextLine();
            this.oldLog.add(data);
            }
        this.oldEntryDate = this.oldLog.get(1);
        this.name = this.oldLog.get(2);
        fileReader.close();
    }

    public boolean lastAccessDayOld() {
        /*
         * This Function compares two dates and determines the number of days between them.
         *   Input:
         *        N/A, Uses class attributes
         *    Returns:
         *        True: when the number of days between are greater than the set maximum days between.
         *        False: Else
         */
        LocalDate dOldDate = LocalDate.parse(this.oldEntryDate);
        long numOfDaysBetween = ChronoUnit.DAYS.between(dOldDate, this.entryDate);
        return (numOfDaysBetween > this.maxDaysSinceAccess);
    }


    public ArrayList<String> formatMoodChoices(){
        /*
        * This method formats the string of information containing the user's choices of words that described
        *   their mood. This choices are formatted to have the Variant, Choice of description, and Score written to the file
        *   for each specific mood category.
        *   Inputs:
        *       N/A, uses attribute of mood choices
        *   Returns:
        *       An ArrayList<String>, where each index contains a specific formatted string for each category.
         */
        ArrayList<String> copyOfChoices = this.variantChoices;
        ArrayList<String> formattedChoices = new ArrayList<>();
        while (copyOfChoices.size() > 0){
            formattedChoices.add("VARIANT:'" + copyOfChoices.get(0) + "',  DESCRIPTION OF CHOICE:'" + copyOfChoices.get(2) + "', SCORE :'" + copyOfChoices.get(1) + "'");
            copyOfChoices.remove(0);
            copyOfChoices.remove(0);
            copyOfChoices.remove(0);
        }
        return formattedChoices;
    }


    public ArrayList<String> locatePastJournalEntries() throws FileNotFoundException {
        /*
        * This method reads a formatted mood log file and locates all past journal entries.
        *   Inputs:
        *       N/A, uses the moodLog file.
        *   Returns:
        *           Returns: An ArrayList<String> containing all past journal entries, ordered
         *   from the most recent entry at index 0 and oldest at index -1
         */
        ArrayList<String> journalEntry = new ArrayList<>();
        Scanner fileReader = new Scanner(moodLog);
        while (fileReader.hasNext()){
            String data = fileReader.nextLine();
            if (data.equals("JOURNAL ENTRY:")){
                journalEntry.add(fileReader.nextLine().stripLeading());
            }
        }
        fileReader.close();
        return journalEntry;
    }

    public ArrayList<ArrayList<String>> locatePastMoodVariantChoices() throws FileNotFoundException {
        /*
         * This method reads a formatted mood log file and locates all past mood variant selections.
         *   Inputs:
         *       N/A, uses the moodLog file.
         *   Returns:
         *           Returns: An ArrayList<ArrayList<String>> which contains arrays for each entry date,
         *                      where within each array there is is each response to a specific variant.
         */
        ArrayList<ArrayList<String>> pastMoodVariantChoices = new ArrayList<>();
        Scanner fileReader = new Scanner(moodLog);
        while (fileReader.hasNext()){
            String data = fileReader.nextLine();
            if (data.equals("MOOD CHOICES:")){
                ArrayList<String> moodVariants = new ArrayList<>();
                moodVariants.add(fileReader.nextLine());
                moodVariants.add(fileReader.nextLine());
                moodVariants.add(fileReader.nextLine());
                pastMoodVariantChoices.add(moodVariants);
            }
        }
        fileReader.close();
        return pastMoodVariantChoices;
    }

    public ArrayList<String> locatePastEntryDates() throws FileNotFoundException {
        /*
         * This method reads a formatted mood log file and locates all entry dates for past entries.
         *   Inputs:
         *       N/A, uses the moodLog file.
         *   Returns:
         *           Returns: An ArrayList<String> containing all entry dates for past logged mood entries.
         */
        ArrayList<String> entryDates = new ArrayList<>();
        Scanner fileReader = new Scanner(moodLog);
        while (fileReader.hasNext()){
            String data = fileReader.nextLine();
            if (data.equals("DATE:")){
                entryDates.add(fileReader.nextLine());
            }
        }
        fileReader.close();
        return entryDates;
    }




    /******************************************
    *
    *  ALL SETTERS AND GETTERS ARE BELOW HERE
    *
     ******************************************/

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public String getOldEntryDate() {
        return oldEntryDate;
    }

    public int getMaxDaysSinceAccess() {
        return maxDaysSinceAccess;
    }

    public void setMoodScore(float moodScore) { this.moodScore = moodScore; }

    public float getMoodScore() {
        return moodScore;
    }

    public void setJournalEntry(String journalEntry) {
        this.journalEntry = journalEntry.replaceAll("( +)"," ").trim();
    }

    public String getJournalEntry() {
        return journalEntry;
    }

    public void setVariantChoices(ArrayList<String> variantChoices) {
        this.variantChoices = variantChoices;
    }

    public ArrayList<String> getVariantChoices() {
        return variantChoices;
    }
}
