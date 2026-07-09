import java.util.Scanner;
/** Class: Main
 * THIS IS THE VIEW CLASS RAHHH
 *
 *
 * STATEMENT OF AUTHORSHIP 000964569 THIS IS MY OWN WORK I DID NOT FRICKING
 * USE CHATGPT WHATS SO EVER
 *
 * also for the validaiton question, this is because each card has a different value,
 * just because you can pick three cards doesnt mean those three cards will add up to three,
 * some of the lowest value cards can go up in suit and it changes the value thus the total value
 *
 *
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String error = "Whoops, wrong input, try again please!";


        System.out.println("Welcome to the deck of cards game");
        System.out.println("What would you like to call your deck?>>> ");
        String name = sc.nextLine();


        int ranks = 0;
        boolean rankCheck = false;
        while (!rankCheck) {
            System.out.println("How many ranks would you like for your deck?>>> ");
            if (sc.hasNextInt()) {
                ranks = sc.nextInt();
                sc.nextLine();
                rankCheck = true;
            }
            else {
                System.out.println(error);
                sc.nextLine();
            }
        }



        int suits = 0;
        boolean suitsCheck = false;
        while (!suitsCheck) {
            System.out.println("How many suits would you like for your deck?>>> ");
            if (sc.hasNextInt()) {
                suits = sc.nextInt();
                sc.nextLine();
                suitsCheck = true;
            }
            else {
                System.out.println(error);
                sc.nextLine();
            }
        }



        DeckOfCards Deck  = new DeckOfCards(name, ranks, suits);
        System.out.println("These are your cards ");
        System.out.println(Deck);









        boolean menu = true;
        /**
         * 1 = shuffle deck
         * 2 = deal 1 hand -> print the cards they got
         * 3 = deal 100,000 times -> histogram method
         * 4 = quit
         */
        while (menu) {
            String menuString = "Total Cards=" + Deck.getDeckSize() + "\t Ranks=" + Deck.getMaxRank() + "\t Suits= " + Deck.getNumberOfSuits() + "\n1=Shuffle\t2=Deal 1 hand\t3=Deal 100000 times\t4=Quit";
            System.out.println(menuString);
            System.out.println("What would you like to do?>>> ");
            int input = sc.nextInt();

            if (input == 1) { //shuffle deck
                Deck.shuffleDeck(1000);
            }

            else if (input == 2) { //deal 1 hand
                boolean check1 = false;
                int hand = 0;
                while (!check1) {
                    System.out.println("How many cards would you like to deal?>>> ");

                    if (sc.hasNextInt()){ //if next line has an integer and is lower than deck size
                        hand = sc.nextInt();
                        check1 = true;
                    }
                    else {
                        System.out.println(error);
                        sc.next();
                    }
                }

                Card[] deal = Deck.deal(hand);
                System.out.println("You dealt these cards: \n");
                for (int i = 0; i < hand; i++) {
                    System.out.print("Card " + i + ": " + deal[i] + "\n");
                }


            }

            else if (input == 3) { //deal 100,000 times
                boolean check2 = false;
                int hand = 0;

                while (!check2) {
                    System.out.println("How many cards would you like to deal?>>> ");

                    if (sc.hasNextInt()){ //if next line has an integer
                        hand = sc.nextInt();
                        check2 = true;
                    }
                    else {
                        System.out.println(error);
                        sc.next();
                    }
                }
                System.out.println(Deck.histogram(hand)); //DOES THE HISTOGRAM METHOD
            }

            else if (input == 4) { //quit
                menu = false;
                System.out.println("Thanks for playing!!!!");
            }

            else {
                System.out.println(error);
            }

        }
    }
}
