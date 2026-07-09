/** Class: Cards
 * This is the class that creates card objects
 * STATEMENT OF AUTHORSHIP 000964569 THIS IS MY OWN WORK I DID NOT FRICKING
 * USE CHATGPT WHATS SO EVER
 *
 *
 */

public class Card {
    //ATTRIBUTES

    /** rank variable**/
    private int rank;
    /** suit variable**/
    private int suit; //heart, spade, clubs, diamond etc.
    /** suit variable calculated**/
    private int value; //rank * suit











    //METHODS

    //CONSTRUCTOR
    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
        this.value = rank*suit;
    }

    //GETTER FOR RANK
    public int getRank() {
        return this.rank;
    }

    //GETTER FOR SUIT
    public String getSuit() {
        String letter = "" + (char)((int)'A'+ (this.suit - 1));
        return letter;
    }

    //GETTER FOR VALUE
    public int getValue() {
        return this.value;
    }

    //TO STRING
    @Override
    public String toString() {
        return "Rank = " + this.getRank() + " \t Suit = " + this.getSuit();
    }

}
