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
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Snap extends CardGame{
    // loop round and get user 'enter' input
    // on enter, the user takes a turn, and a card is dealt
    // check if previous card and current card are the same symbol - if so, players wins
    private List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;

    public Snap () {
        super();
    }

    // setup players
    public void setupPlayers(String... playerNames){
        for (String name : playerNames){
            players.add(new Player(name));
        }
    }

    public CardGame setUpGame(){
        CardGame snap = new CardGame("Snap", new ArrayList<>());
        snap.buildDeck();
        snap.shuffleDeck();
        return snap;
    }

    public String getUserInput(Player player, Scanner s){
        //Scanner s = new Scanner(System.in);
        System.out.println(player.getName() + " your turn - Press ENTER to deal card, any other key to exit ");
        String nextInput = s.nextLine();
        //s.close();
        return nextInput;
    }

    public boolean compareCards(Card prevCard, Card currCard, Player currentPlayer, Scanner s){
        if (prevCard != null) {
            System.out.println("Card dealt is: " + currCard.symbol + " previous card is: " + prevCard.symbol);
            // check the dealt with previous card - if symbol is the same, then won

            if (prevCard.symbol.equals(currCard.symbol)) {
                // add timer for user to enter "snap" within 2 secs else they lose
                ExecutorService executor = Executors.newSingleThreadExecutor();
                System.out.println("You must enter snap to win the game!");

                //String nextInput = s.nextLine();
                Future<String> nextInput = executor.submit(() -> s.nextLine());

                try {
                    // Wait for input with a timeout of 10 seconds
                    String input = nextInput.get(10, TimeUnit.SECONDS);
                    if (!input.equalsIgnoreCase("snap")) {
                        // exiting game
                        return false;
                    }

                    System.out.println("Snap! "+ currentPlayer.getName()+" have wins with card symbol : " + currCard.symbol +"!");
                    currentPlayer.incrementScore();
                    //set flag to exit loop
                    return true;
                } catch (TimeoutException e) {
                    System.out.println("Time is up! No input received.");
                    nextInput.cancel(true);
                    return false;
                } catch (Exception e) {
                    System.out.println("An error occurred: " + e.getMessage());
                    return false;
                } finally {
                    executor.shutdown();
                }
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

        if (players.size()!=0) {
            while (playSnapFlag && !snap.deckOfCards.isEmpty()) {

                Player currentPlayer = players.get(currentPlayerIndex);

                String nextInput = getUserInput(currentPlayer, scanner);

                if (!nextInput.isEmpty()) {
                    System.out.println("Thanks for playing Snap - no winners!");
                    break;
                }

                // deal the card
                Card dealtCard = snap.dealCard();
                // compare cards - if same - player wins - check if want to play again
                if (compareCards(previousCard, dealtCard, currentPlayer, scanner)) {

                    System.out.println("Score:");
                    for (Player player : players) {
                        System.out.println(player.getName() + ": " + player.getScore());
                    }
                    System.out.println("Play again? y/n");
                    String plagAgainReply = scanner.nextLine();
                    if (!plagAgainReply.equalsIgnoreCase("y")) {
                        // exiting game
                        playSnapFlag = false;
                        break;
                    }
                    snap = setUpGame(); // reset the deck
                    previousCard = null;
                    continue;
                }

                // set the previous card to the current dealt card
                if (checkForEmptyDeck(snap)) {
                    System.out.println("No more cards left. Game over!");
                }
                ;

                previousCard = dealtCard;
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            }
            System.out.println("\nGame Over - final score :");
            for (Player player : players) {
                System.out.println(player.getName() + ": " + player.getScore());
            }
        }
        else {
            System.out.println("Cannot play - No players set!");
        }
        scanner.close(); // closing the Scanner
    }

}
