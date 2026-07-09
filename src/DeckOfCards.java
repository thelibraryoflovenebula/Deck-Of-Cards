import java.util.Arrays; //METHODS FOR SORTING ARRAYS
import java.util.ArrayList; //METHODS FOR REMOVING EMPTY TALLIES IN THE int[] array "valueDeck";
/** Class: DeckOfCards
 * This is the class that handles deck of cards methods and shi
 *
 *
 * STATEMENT OF AUTHORSHIP 000964569 THIS IS MY OWN WORK I DID NOT FRICKING
 * USE CHATGPT WHATS SO EVER
 *
 *
 */

public class DeckOfCards {
    /** VARIABLE CHOSEN FOR USERS MAX RANK **/
    private int maxRank;
    /** VARIABLE CHOSEN FOR USERS NUMBER OF SUITS  **/
    private int numberOfSuits;
    /** This is a calculated variable that calulates the total deck size  **/
    private int deckSize;
    /** A stored card deck **/
    private Card[] deck;
    /** This specific deck is used when dealing a hand, creates a clone **/
    private Card[] dealDeck; //created different deck for dealing hands, leaves current deck unshuffled
    /** Stores a completely unshuffled deck for histogram usage  **/
    private Card[] unshuffledDeck; //stores an unshuffled deck for histogram use
    /** Users input will store a deck name :p **/
    private String deckName;
    /** This Array is an array of integers that stores each card's value **/
    private int[] valueDeck;
    /** array for tallies, used for histogram**/
    private int[][] tallies;




    /** CONSTRUCTOR FOR CLASS DeckOfCards()
     * this constructor should create the array im assuming
     * the array should contain card objects ??
     *
     *
     * @param maxRank the maximum value of ranks (ace, 1, 5, jack, king)
     * @param numberOfSuits the maximum number of suits there are (heart spade)
     */
    public DeckOfCards(String deckName, int maxRank, int numberOfSuits) {
        this.maxRank = maxRank;
        this.numberOfSuits = numberOfSuits;
        this.deckSize = maxRank * numberOfSuits;
        this.deckName = deckName;


        /**ARRAY CARD OBJECT CREATOR >>> established in for loop
         *
         * i = total counter
         * j = rank counter (resets to zero when reaches max)
         * k = suit counter (resets to zero when reaches max)
         *
         * @purpose: create card objects and put into deck (unshuffled)
         * this happens JUST as the deck is created (constructor)
         *
         */
        //establish array size as the deck size
        deck = new Card[deckSize]; //actual usable deck
        unshuffledDeck = new Card[deckSize]; //untouched unshuffled deck
        valueDeck = new int[deckSize]; //the values of the deck in an array,

        int j = 1;
        int k = 1;
        for (int i = 0; i < deckSize; i++) {
            Card card = new Card(j, k);
            deck[i] = card;
            unshuffledDeck[i] = card;
            valueDeck[i] = card.getValue();


            //each rank and suit counter with reset functionality
            if (j == maxRank) {j = 0; k++;}
            j++;

        }
        // //SORTS THE VALUE ARRAY


        this.deck = deck;
        this.unshuffledDeck = unshuffledDeck;

        sortValueDeck();
        this.valueDeck = valueDeck;
    }

    /** getter for deck size
     *
     * @return the deck size
     */
    public int getDeckSize() {
        return this.deckSize;
    }

    /**
     * getter for max rank
     * @return max rank
     */
    public int getMaxRank() {
        return this.maxRank;
    }

    /**
     * getter for number of suits
     * @return number of suits
     */
    public int getNumberOfSuits() {
        return this.numberOfSuits;
    }


    /**
     *  THIS METHOD IS USED TO DEAL HANDS
     * @param numberOfCards ->> how many cards you want to deal
     * @return this one returns a reference to an array of a dealt hand
     */
    public Card[] deal(int numberOfCards) {
        Card[] dealtHand;
        dealtHand = new Card[numberOfCards];
        dealDeck = deck;
        shuffleDeck(1000, dealDeck);


        for (int i =0; i < numberOfCards; i++) {
            dealtHand[i] = dealDeck[i];
        }
        return dealtHand;
    }

    /** First shuffle deck method that only shuffles the actual deck
     *
     * @param maxShuffle - >how many times you want  to shuffle the deck
     */
    public void shuffleDeck(int maxShuffle) {       //FOR OUTSIDE USE OF THE CLASS
        for (int i = 0; i < maxShuffle; i++) {
            swapOnce(this.deck);
        }
    }

    /** This is an overloaded shuffle method that you can choose to pick which deck you want to shuffle
     *
     * @param maxShuffle - >how many times you want  to shuffle the deck
     * @param deck - >chosen deck
     */
    public void shuffleDeck(int maxShuffle, Card[] deck) {  //OVERLOADED FOR INSIDE USE
        for (int i = 0; i < maxShuffle; i++) {
            swapOnce(deck);
        }
    }

    /** ADDITIONAL METHOD FOR shuffleDeck()
     * 1. Choose two random index
     *      - cant be same number
     *      - cap at deckSize
     * 2. replace those two objects in the array
     */
    public void swapOnce(Card[] deck) {
        int num1;
        int num2;
        boolean check = true;
        do {
            num1 = (int)(Math.random()*this.deckSize);
            num2 = (int)(Math.random()*this.deckSize);
        } while (num1 == num2);
        Card tempChosen1 = deck[num1];
        Card tempChosen2 = deck[num2];
        deck[num1] = tempChosen2;
        deck[num2] = tempChosen1;
    }

    /**
     * Sort value deck sorts value
     */
    public void sortValueDeck() {
        Arrays.sort(valueDeck);
    }

    /** HISTOGRAM MUST USE METHODS Deal() and shuffleDeck()
     *
     * input:
     * - maxRank
     * - numberOfSuits
     * output:
     * - array within array of tallies
     *
     * need:
     * - lowest value and highest value -> for array size
     * -
     * @param numberOfCards = the number of cards you choose
     *                      1. Get lowest value possible and highest value possible
     *                      2. Create array of index lowest value -> highst value
     *                      2. deal cards 100,000 times
     *                          - each dealt, should tally the values
     *                          - insert all tallys into an array
     *                      3. display on the array (numbers first)
     */
    public String histogram(int numberOfCards) {
        String asteriksHistogram = "Hand Value: \t\tTallies: \n"; //STARTING OUT

        //find lowest and highest value with given hand (has max hand protection);
            //LOWEST
            int lowestValue = 0;
            for (int i = 0; i < numberOfCards; i++) {
                lowestValue += valueDeck[i];
            }
            //HIGHEST
            int highestValue = 0;
            for (int i = deckSize; i > (deckSize - numberOfCards);i--){
                highestValue += valueDeck[i - 1];
            }

        //RANGE and initializing histogramValues array -> TALLIES
        int range = highestValue - lowestValue + 1;
        tallies = new int[range][2];// has two columns, first column is value, second column is tally

        //initializes tallies by zero
        for (int i = lowestValue; i <= highestValue; i++) {
            tallies[i - lowestValue][0] = i;
            tallies[i - lowestValue][1] = 0; //PUTS COUNTER AS ZERO
        }

        //dealing the hand 100,000 times
        for (int i = 0; i < 100000; i++) {
            int tallyAdd = 0;
            Card[] tempHand = deal(numberOfCards); //initializing tempHand
            for (int j = 0; j < numberOfCards; j++){
                tallyAdd += tempHand[j].getValue();
            }
            tallies[tallyAdd - lowestValue][1]++; // THE SECOND COLUMN SHOULD ADD ONE
            //i did - lowestValue because the index starts at 0, and must compensate for that
        }

        //ERASE OUT THE FIRST COLUMNS THAT HAVE ZERO IN THEIR SECOND COLUMN
        //the if condition omits 0'd tallies


        //PRINTING OUT THE
        for (int i = 0; i < range ; i++) {
            String asteriks = "";

            for (int j = 0; j < tallies[i][1]; j += 100) {
                asteriks += "*";
            }

            if (tallies[i][1] != 0) { //IF THE TALLY IS NOT ZERO, DO NOT PRINT
                asteriksHistogram += tallies[i][0] + ":   \t\t" + tallies[i][1] + "  \t\t" + asteriks + "\n";
            }
        }
         return asteriksHistogram;
    }

    //toString for DeckOfCards
    @Override
    public String toString() {
        String fullDeck = "List of deck for {" + this.deckName+ "}\n";

        //printing out cards
        for (int i = 0; i < this.deckSize; i++) {
            fullDeck = fullDeck + "Card " + (i + 1) + ": \t" + deck[i] + "\n";
        }

        /*  THIS WAS USED TO CHECK THE VALUE ARRAY OR IN OTHER WORDS DEBUGGING
        //printing out value deck array
        for (int i = 0; i < this.deckSize; i++) {
            fullDeck = fullDeck + "Card " + (i + 1) + " value: \t" +  valueDeck[i] + "\n";
        }
        */

        return fullDeck;
    }


}
/**ASSIGNMENT COMMENTS
 *
 * DANG THE SHUFFLE METHOD WORDS SURPRISNGLY WELL
 * I FEEL LIKE A GENIUS WHEN I MADE THAT HISTOGRAM METHOD BAHAHAHAHHAHHA
 *
 *
 */