import java.io.IOException;
import java.util.Scanner;

public class MoodTrackerFinalCMD {

    // userInput: Will be used to get user input from the command line.
    private static final Scanner userInput = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        /*
         *
         * Initializing objects for classes that will be used.
         *
         */
        Moods chosenMoods = new Moods();
        MoodFeedback feedback = new MoodFeedback(chosenMoods);
        MoodLogger moodLogger = new MoodLogger();


        // If the moodLog.txt is not empty, it will append new information to this file and use the data. (empty files have length 0)
        if (moodLogger.loggerExists()) {

            moodLogger.getOldLog();
            System.out.println("Hello, Welcome " + moodLogger.getName() + "");

            // Called lastAccessDayOld method to determine if the last access time is old enough to run encouraging message
            if (moodLogger.lastAccessDayOld()) {
                System.out.println("Its wonderful to see you back! Try to make this a routine, its very important to spend some time on yourself!");
            }
            System.out.println("How would you scale your overall mood from 1 - 10 (1 being very poor, 10 being very good.");
            String moodScore = (userInput.nextLine());
            while (!stringIsFloat(moodScore)){
                System.out.println("Please enter a valid score.");
                moodScore = userInput.nextLine();
            }
            moodLogger.setMoodScore(Float.parseFloat(moodScore));

            // Prompts user with questions about more specific moods and to choose best fitting word
            //  which matches their current emotion.
            chosenMoods.readMoodVariantInput();

            // Retrieves the users choices and saves them to mood log.
            moodLogger.setVariantChoices(chosenMoods.getChoices());

            // Uses the users 1 - 10 mood score and provides response based on initial reading
            feedback.setMoodScore(moodLogger.getMoodScore());
            feedback.moodScoreManager();

            // Uses the users choices for specific moods and provides more specific feedback.
            feedback.moodVariantChoiceManager(feedback.overallMoodManager(),feedback.socialMoodManager(),feedback.introspectiveMoodManager());
            feedback.presentCommandLineFeedback();
            System.out.println(" ");

            // Prompting user for a journal entry
            System.out.println("Write a brief entry about how you're feeling today - ");
            moodLogger.setJournalEntry(userInput.nextLine());

            // Saves the new entry along with all previous entries into mood log file.
            moodLogger.saveLog();
        }

        // If the moodLog.txt is empty, we add fresh data and consider the user is accessing for the first time.
        else {
            // Promoting user for information to build their first file entry
            System.out.println("Hello and welcome to your Mood Log!");
            System.out.println("Please enter your name: ");
            moodLogger.setName(userInput.nextLine().replaceAll("( +)"," ").trim());
            System.out.println("Hello " + moodLogger.getName());
            System.out.println("How would you scale your overall mood from 1 - 10 (1 being very poor, 10 being very good.");
            String moodScore = userInput.nextLine();
            while (!stringIsFloat(moodScore)){
                System.out.println("Please enter a valid score.");
                moodScore = userInput.nextLine();
            }
            moodLogger.setMoodScore(Float.parseFloat(moodScore));

            chosenMoods.readMoodVariantInput();
            moodLogger.setVariantChoices(chosenMoods.getChoices());
            // Using feedback Object to get feedback for mood score (1 - 10) and mood choices.
            feedback.setMoodScore(moodLogger.getMoodScore());
            feedback.moodScoreManager();
            feedback.moodVariantChoiceManager(feedback.overallMoodManager(),feedback.socialMoodManager(),feedback.introspectiveMoodManager());
            feedback.presentCommandLineFeedback();
            System.out.println(" ");
            System.out.println("Write a brief entry about how you're feeling today");
            moodLogger.setJournalEntry(userInput.nextLine());

            // Saving the first log into the file.
            moodLogger.firstSaveLog();
        }

        // Closing message of encouragement after program has completed questions and saved mood log.
        System.out.println("Good work today! Try to record your mood at least once a day!");
    }


    private static boolean stringIsFloat(String string){
        /*
       This function determines if a string input is of type float.
       Inputs:
            string: This is the string to be evaluated

       Returns:
            True: When the input can be converted to type string
            False: When input is not valid format for float.
         */
        try{
            float f = Float.parseFloat(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}




