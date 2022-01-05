public class Mood {

    /******************************************
     *
     * This class defines what a "Mood" is. A mood is determined for a specific category (Variant), from
     *   the MoodVariants enumerator class. These variant's contain a value which is associated with the
     *   user's chosen description. The value indicates the range of either negative or positive responses.
     *
     ******************************************/


    // All used attributes in this class. Both are final as each mood is constructed
    // and left unchanged.
    private final MoodVariants variant;
    private int value;

    // Constructor for a Mood. Requires a variant of mood, and the value chosen for the variant.
    public Mood(MoodVariants variants, int value) {
        this.variant = variants;
        this.value = value;
    }

    /******************************************
     *
     * ALL SETTERS AND GETTERS ARE BELOW HERE
     *
     ******************************************/

    public MoodVariants getVariant(){
        return this.variant;
    }

    public int getValue() {
        return value;
    }


    // Special Getter. Collects the description associated with the value chosen, for the specific variant.
    public String getVariantDescription(){
        return variant.getVariantDescriptions().get(value-1);

    }

    public void setValue(int value){
        this.value = value;
    }
}


