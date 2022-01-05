import java.util.ArrayList;
import java.util.Arrays;

public enum MoodVariants {

    /******************************************
     *
     * This class is an enumerator class. Contains the different variants of moods and connects descriptions with each
     *   which go from very negative responses to positive responses. This descriptions can be used as a way for the
     *   user to choose how they feel specifically about a certain variant.
     *
     ******************************************/


    // All MoodVariants are written here. Each category contains multiple descriptions in a negative - positive range.
    Overall("Sad","Unhappy","Under the Weather","Neutral","Okay","Pretty Good", "Excellent"),
    Socially("Want to be Isolated", "Be with one Close Person", "Okay with Seeing Anyone", "Want to Party with Everyone"),
    Introspectively("Don't like myself today", "Not the happiest with myself", "Fine with myself","Content with myself");

    // Attributes for this class1
    private final ArrayList<String> variantDescriptions;

    // Enumerator constructor which sets the descriptions of each variant to an ArrayList<String>.
    MoodVariants(String... variant) {
        this.variantDescriptions = new ArrayList<>();
        this.variantDescriptions.addAll(Arrays.asList(variant));
    }


    /******************************************
    *
    * ALL GETTERS FOR THIS CLASS ARE SET BELOW HERE.
    *
     ******************************************/

    public ArrayList<String> getVariantDescriptions(){
        return this.variantDescriptions;
    }

    public String getVariantName(){
        return this.toString();
    }

}
