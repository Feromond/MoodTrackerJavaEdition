import java.util.ArrayList;
import java.util.Scanner;

public class Moods {

    /******************************************
     *
     * This class is responsible for generating and storing multiple moods. Creates prompts for user to determine
     *   their specific responses for certain mood categories. All of these are of type Mood, and are stored / formatted
     *   for use within the class.
     *
     ******************************************/


    // Attributes of Moods class.
    private final ArrayList<Mood> moods = new ArrayList<>();
    private final Scanner userInput = new Scanner(System.in);
    private final ArrayList<MoodVariants> variantOptions = new ArrayList<>();
    private final ArrayList<ArrayList<String>> variantOptionDescriptions = new ArrayList<>();



    public void setMoodVariantInfo() {
        /*
        *   This method prepares the variant options and descriptions for access.
        *       Input:
        *           N/A, just sets class properties.
        *       Returns:
        *           N/A, sets the class attributes.
         */
        for (MoodVariants variant : MoodVariants.values()){
            this.variantOptions.add(variant);
            this.variantOptionDescriptions.add(variant.getVariantDescriptions());
        }
    }

    public void addMoodChoice(MoodVariants variant, int inputValue){
        /*
        *   This method adds a selected mood response to the moods arraylist.
        *       Inputs:
        *           variant: the specific variant mood type which the user was responding to
        *           inputValue: the value of the response to the specific mood type, correlating to selected description.
        *       Returns:
        *               N/A, adds these values to the ArrayList<Mood> moods
         */
        Mood nextVariant = new Mood(variant,inputValue);
        boolean alreadySaved = false;
        for (Mood mood : this.moods) {
            if ((nextVariant.getVariant().getVariantName().equals(mood.getVariant().getVariantName()))) {
                if (nextVariant.getValue() == mood.getValue()) {
                    alreadySaved = true;
                } else {
                    mood.setValue(nextVariant.getValue());
                    alreadySaved = true;
                }
            }
        }
        if (!alreadySaved){
            this.moods.add(nextVariant);
        }
    }

    public void readMoodVariantInput() {
        /*
         *
         * This method prompts the user with each MoodVariant and allows them to select the description which best
         *   fits the current feeling they have. These responses are each stored as a "Mood" with the Variant and
         *   the associated value. All of these "Mood" terms are then stored as a type "Moods" for future access and use.
         *   Inputs:
         *       N/A, uses attributes of moods, and userInput.
         *   Returns:
         *       N/A, sets attribute moods.
         */

        for (MoodVariants variant : MoodVariants.values()) {
            System.out.println("How are you feeling " + variant.getVariantName() + "?");
            int i = 1;
            for (String moodValue : variant.getVariantDescriptions()) {
                System.out.println(i++ + " - " + moodValue);
            }
            String input = this.userInput.nextLine();
            while (!stringIsInt(input) || (0 >= (Integer.parseInt(input)) || (Integer.parseInt(input)) > variant.getVariantDescriptions().size())) {
                System.out.println("Please enter a valid number ");
                input = this.userInput.nextLine();
            }
            int intInput = Integer.parseInt(input);
            Mood nextVariant = new Mood(variant, intInput);
            this.moods.add(nextVariant);

        }
    }


    private boolean stringIsInt(String string) {
        /*
        *
        * This method determines if a users input which is taken as a string, could be converted to an Integer.
        *   Input:
        *       string: the entered string value from a user
        *   Returns:
        *       True: if the string entered can be converted to an Integer.
        *       False: if the string is not in the format of an Integer.
         */
        try {
            int i = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    /******************************************
    *
    * ALL GETTERS AND SETTERS FOR THIS CLASS ARE BELOW
    *
     ******************************************/


    public ArrayList<Mood> getMoods() {
        return moods;
    }


    // Special getter. Specifically gets the choices, description, and value of each Variant.
    public ArrayList<String> getChoices() {
        /*
         * This function gets the category of mood, the choice of the mood, and the score associated with that mood.
         *     Inputs:
         *         N/A
         *     Returns:
         *         Returns an Arraylist<String> where every 3 index contains the Category, Choice, and Score
         */
        ArrayList<String> variants = new ArrayList<>();
        for (Mood mood : moods) {
            variants.add(mood.getVariant().toString());
            variants.add(String.valueOf(mood.getValue()));
            variants.add(mood.getVariantDescription());
        }
        return variants;
    }

    public ArrayList<MoodVariants> getVariantOptions(){
        return this.variantOptions;
    }
    public ArrayList<ArrayList<String>> getVariantOptionDescriptions(){
        return this.variantOptionDescriptions;
    }
}



