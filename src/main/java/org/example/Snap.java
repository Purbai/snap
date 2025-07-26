package org.example;

//        ### Stage 3
//
//Create class for Snap that extends CardGame.
//
//        This class should use the methods defined
//        above, as well as some new ones, to enable the user to play the game snap according to the
//following rules:
//
//        - By pressing enter in the command line, the user takes their turn.
//- Each turn, a new card is dealt from the deck.
//        - The game continues until two cards in a row have the same symbol, at which point the “player” wins and the game ends.
//

import java.util.ArrayList;
import java.util.Scanner;

public class Snap extends CardGame{
    // loop round and get user 'enter' input
    // on enter, the user takes a turn, and a card is dealt
    // check if previous card and current card are the same symbol - if so, players wins

    public Snap () {
        super();
    }

    public CardGame setUpGame(){
        CardGame snap = new CardGame("Snap", new ArrayList<>());
        snap.buildDeck();
        snap.shuffleDeck();
        return snap;
    }

    public String getUserInput(String player, Scanner s){
        //Scanner s = new Scanner(System.in);
        System.out.println(player + " your turn - Press ENTER to deal card, any other key to exit ");
        String nextInput = s.nextLine();
        //s.close();
        return nextInput;
    }

    public boolean compareCards(Card prevCard, Card currCard){
        if (prevCard != null) {
            System.out.println("Card dealt is: " + currCard.symbol + " previous card is: " + prevCard.symbol);
            // check the dealt with previous card - if symbol is the same, then won

            if (prevCard.symbol.equals(currCard.symbol)) {
                System.out.println("Snap! you have won!" + currCard.symbol);
                //set flag to exit loop
                return true;
            }
        }
        else {
            System.out.println("Card dealt : "+currCard.symbol + " | no previous card");
        }
        return false;
    }

    public boolean checkForEmptyDeck(CardGame snap){
        return snap.deckOfCards.isEmpty();
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        boolean playSnapFlag = true;

        CardGame snap = setUpGame();

        Card previousCard = null;

        while (playSnapFlag && !snap.deckOfCards.isEmpty()) {

            String nextInput = getUserInput("Joe", scanner);

            if (!nextInput.isEmpty()) {
                System.out.println("Thanks for playing Snap!");
                break;
            }

            // deal the card
            Card dealtCard = snap.dealCard();
            // compare cards - if same - player wins - exit loop
            if (compareCards(previousCard, dealtCard)) break;

            // set the previous card to the current dealt card

            if(checkForEmptyDeck(snap)) {
                System.out.println("No more cards left. Game over!");
            };

            previousCard = dealtCard;
        }
        scanner.close(); // closing the Scanner
    }
}
