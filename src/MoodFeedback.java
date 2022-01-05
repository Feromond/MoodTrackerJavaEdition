import java.util.ArrayList;
import java.util.Random;

public class MoodFeedback {

    /******************************************
     *
     * This class is responsible for managing feedback based on user mood inputs. Uses the overall mood score value,
     *   along with the responses to the different MoodVariants, to determine how to respond to the user and provide
     *   helpful and useful feedback.
     *
     ******************************************/

    // Class Attributes
    private final ArrayList<Mood> combinedMoods;
    private float moodScore;

    private String moodScoreFeedback;
    private String overallMood;
    private String socialMood;
    private String introspectiveMood;

    private String dogVideoDescriptionName = "";
    private String dogVideoURL = "";
    private String movieRecommendationName = "";
    private String movieRecommendationURL = "";


    // Constructor requires "Moods" to evaluate for the feedback.
    public MoodFeedback(Moods choices){

        this.combinedMoods = choices.getMoods();
    }



    public String randomDogVideoURL(){
        /*
         * This function picks a random index of a list containing different dog videos to watch.
         *   Inputs:
         *       N/A
         *   Returns:
         *       Returns a string containing the selected URL for the video.
         */
        ArrayList<String> urls = new ArrayList<>();
        urls.add("https://www.youtube.com/watch?v=EfVTDEWHr5o&feature=youtu.be");
        urls.add("https://www.youtube.com/watch?v=re40ray4RM0&feature=youtu.be");
        urls.add("https://www.youtube.com/watch?v=ut_8lkPwRU4&feature=youtu.be");
        urls.add("https://www.youtube.com/watch?v=SGxBl_dpFZg&feature=youtu.be");
        urls.add("https://www.youtube.com/watch?v=MRN21DyBchI&feature=youtu.be");
        urls.add("https://www.youtube.com/watch?v=jP2ZQHrYRME&feature=youtu.be");
        Random rand = new Random();
        int choice = rand.nextInt(urls.size());
        return urls.get(choice);

    }
    public ArrayList<String> randomPopularMovies(){
        /*
        * This function picks a random index of a list containing different movies to watch.
        *   Inputs:
        *       N/A
        *   Returns:
        *       Returns a string containing the selected movie and URL.
         */
        ArrayList<String> movieAndURL = new ArrayList<>();
        ArrayList<String> movies = new ArrayList<>();
        ArrayList<String> urls = new ArrayList<>();
        movies.add("Check out 'Soul' if you have Disney+");
        urls.add("https://movies.disney.com/soul");
        movies.add("Check out 'Avatar: The Last Airbender' for a wholesome journey, on Netflix");
        urls.add("https://www.netflix.com/watch/70116061?trackId=254109355&tctx=18%2C0%2C72928e11-484c-46ed-8a98-fc44ec436403-68236839%2Cc9b6e039-0510-4f14-b969-a837069e6587_118778492X28X81246414X1613862760699%2C%2C");
        movies.add("Check out 'Spider-Man: Homecoming' on Netflix");
        urls.add("https://www.netflix.com/watch/80166369?source=35");
        movies.add("Check out 'Moana' if you have Disney+");
        urls.add("https://www.disneyplus.com/movies/moana/70GoJHflgHH9");
        Random rand = new Random();
        int choice = rand.nextInt(movies.size());
        movieAndURL.add(movies.get(choice));
        movieAndURL.add(urls.get(choice));
        return movieAndURL;
    }


    public void moodScoreManager() {
    /*
    * This Function handles the mood rating input from the user, and provides a response according to what the value is.
    *   Input:
    *        moodValue: this will be either an integer or floating point value rating from 1-10 on a mood scale.
    *            (This is also capable of handling invalid inputs greater or less than the set bounds)
     */
        if (this.moodScore <= 3) {
            this.moodScoreFeedback = ("Your 1 - 10 overall score appeared to be low today. Some days are not always easy. Sometimes it can help to make time for yourself.");
        } else if (this.moodScore <= 6) {
            this.moodScoreFeedback = ("Your 1 - 10 score seems to be mediocre. Try and focus on the positive moments you may encounter today!");
        } else {
            this.moodScoreFeedback = ("Based on your 1 - 10 score, it seems like today is not too bad! Try and remember what things made you happy!");
        }
    }



    public void moodVariantChoiceManager(int overall, int social, int introspective){
        /*
        * This method determines the output response based on the users scores when they selected descriptions for
        *   differing MoodVariants.
        *   Input:
        *       overall: either 1, 0, or -1. Determines either positive, neutral, or negative response for overall mood variant.
        *       social: either 1, 0, or -1. Determines either positive, neutral, or negative response for social mood variant.
        *       introspective: either 1, 0, or -1. Determines either positive, neutral, or negative response for introspective mood variant.
         */


        if (overall == 1){
            this.overallMood = ("It seems that you're overall mood today is good! Enjoy the day!");
        }
        else if (overall == 0){
            this.overallMood = ("It seems that you're feeling decently okay overall.");
        }
        else{
            this.overallMood = ("Your overall mood today isn't the best, maybe try watching some dog videos!");
            this.dogVideoURL = randomDogVideoURL();
            this.dogVideoDescriptionName = "Check out a random cute dog video!";
        }

        if (social == 1){
            this.socialMood = ("It seems that you're feeling quite social!");
        }
        else if (social == 0){
            this.socialMood = ("Not every day has to be extremely social! Spending time with people closest can be really good!");
            ArrayList<String> movieAndURL = randomPopularMovies();
            this.movieRecommendationName = movieAndURL.get(0);
            this.movieRecommendationURL = movieAndURL.get(1);
        }
        else{
            this.socialMood = ("It can be really hard to go and talk sometimes. Give yourself some time but don't be afraid to reach out to others.");
            ArrayList<String> movieAndURL = randomPopularMovies();
            this.movieRecommendationName = movieAndURL.get(0);
            this.movieRecommendationURL = movieAndURL.get(1);
        }
        if (introspective == 1){
            this.introspectiveMood = ("Glad to see you're feeling good about yourself!");
        }
        else if (introspective == 0){
            this.introspectiveMood = ("Keep working on finding ways to appreciate who you are!");
        }
        else{
            this.introspectiveMood = ("It can be really hard to appreciate yourself. Try to focus on the best parts of yourself.");
        }
    }

    public void presentCommandLineFeedback(){
        System.out.println(this.moodScoreFeedback);
        System.out.println(this.overallMood);
        if (!this.dogVideoDescriptionName.isBlank()){
            System.out.println(this.dogVideoDescriptionName + " " + this.dogVideoURL);
        }
        System.out.println(this.socialMood);
        if (!this.movieRecommendationName.isBlank()){
            System.out.println(this.movieRecommendationName +" "+ this.movieRecommendationURL);
        }
        System.out.println(this.introspectiveMood);
    }


    public int overallMoodManager(){
        /*
        * This method uses a score determined by the users choice of description for the overall variant, and
        *   computes the "decisionFactor" which will determine whether the users choice is negative, neutral, or positive.
        *   Inputs:
        *       N/A, uses class attributes
        *   Returns:
        *       decisionFactor: a score of 1, 0, or -1. 1 indicates positive response, 0 is neutral, and -1 is negative.
        *
         */
        int decisionFactor;
        Mood overallMood = combinedMoods.get(0);
        int valueSize = overallMood.getVariant().getVariantDescriptions().size();
        int value = overallMood.getValue();
        if (valueSize - value < 2){
            decisionFactor = 1;
        }
        else if(valueSize-value < 4){
            decisionFactor = 0;
        }
        else{
            decisionFactor = -1;
        }
        return decisionFactor;

    }

    public int socialMoodManager(){
        /*
         * This method uses a score determined by the users choice of description for the social variant, and
         *   computes the "decisionFactor" which will determine whether the users choice is negative, neutral, or positive.
         *   Inputs:
         *       N/A, uses class attributes
         *   Returns:
         *       decisionFactor: a score of 1, 0, or -1. 1 indicates positive response, 0 is neutral, and -1 is negative.
         *
         */
        int decisionFactor;
        Mood socialMood = combinedMoods.get(1);
        int valueSize = socialMood.getVariant().getVariantDescriptions().size();
        int value = socialMood.getValue();
        if (valueSize - value <= 1){
            decisionFactor = 1;
        }
        else if(valueSize-value <= 3){
            decisionFactor = 0;
        }
        else{
            decisionFactor = -1;
        }
        return decisionFactor;

    }

    public int introspectiveMoodManager(){
        /*
         * This method uses a score determined by the users choice of description for the introspective variant, and
         *   computes the "decisionFactor" which will determine whether the users choice is negative, neutral, or positive.
         *   Inputs:
         *       N/A, uses class attributes
         *   Returns:
         *       decisionFactor: a score of 1, 0, or -1. 1 indicates positive response, 0 is neutral, and -1 is negative.
         *
         */
        int decisionFactor;
        Mood introspectiveMood = combinedMoods.get(2);
        int valueSize = introspectiveMood.getVariant().getVariantDescriptions().size();
        int value = introspectiveMood.getValue();
        if (valueSize - value <= 1){
            decisionFactor = 1;
        }
        else if(valueSize-value <= 3){
            decisionFactor = 0;
        }
        else{
            decisionFactor = -1;
        }
        return decisionFactor;
    }


    /******************************************
     *
     *  ALL SETTERS AND GETTERS ARE BELOW HERE.
     *
     ******************************************/

    public float getMoodScore() {
        return moodScore;
    }

    public void setMoodScore(float moodScore) {
        this.moodScore = moodScore;
    }

    public String getMoodScoreFeedback(){
        return this.moodScoreFeedback;
    }
    public String getOverallMood(){
        return this.overallMood;
    }
    public String getSocialMood(){
        return this.socialMood;
    }
    public String getIntrospectiveMood(){
        return this.introspectiveMood;
    }

    public String getDogVideoURL() {
        return dogVideoURL;
    }

    public String getDogVideoDescriptionName() {
        return dogVideoDescriptionName;
    }

    public String getMovieRecommendationName() {
        return movieRecommendationName;
    }

    public String getMovieRecommendationURL() {
        return movieRecommendationURL;
    }
}
