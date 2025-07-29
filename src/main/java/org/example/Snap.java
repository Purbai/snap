package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Snap extends CardGame implements PlayGame {
    private final InputProvider inputProvider;
    private List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;

    public Snap(InputProvider inputProvider) {
        super("Snap");
        this.inputProvider = inputProvider;
    }

    public Snap() {
        this(() -> {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        });
    }

    @Override
    public void buildDeck() {
        for (Suit suit : Suit.values()) {
            for (Symbol symbol : Symbol.values()) {
                int value = Value.valueOf(symbol.name()).getValue();
                deckOfCards.add(new Card(suit.getSuitName(), symbol.getSymbolName(), value));
            }
        }
    }

    @Override
    public void setupPlayers(String... names) {
        for (String name : names) {
            players.add(new Player(name));
        }
    }

    public boolean playGameAgain() {
        System.out.println("Play Again? y/n");
        //String playAgain = scanner.nextLine();
        String playAgain = inputProvider.getInput();
        return (playAgain.equalsIgnoreCase("y"));
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public void resetGame() {
        deckOfCards.clear();
        buildDeck();
        shuffleDeck();
        //currentPlayerIndex = 0;
    }

    @Override
    public void startGame() {
        if (players.isEmpty()) {
            System.out.println("No players! Cannot start game.");
            return;
        }
        resetGame();

        Card previousCard = null;
        boolean playing = true;

        while (playing && !deckOfCards.isEmpty()) {
            Player player = players.get(currentPlayerIndex);
            System.out.println("\n" + player.getName() + ", press ENTER to deal card, or any other key to exit ");
            //String input = scanner.nextLine();
            String input = inputProvider.getInput();

            if (!input.isEmpty()) {
                System.out.println("Thanks for playing!");
                break;
            }

            Card dealtCard = dealCard();
            System.out.println("Card dealt: " + dealtCard);

            if (previousCard != null && previousCard.matchesSymbol((dealtCard))) {
                System.out.println("SNAP! Type 'snap' in 10 seconds to win!");
                ExecutorService executor = Executors.newSingleThreadExecutor();
                //Future<String> future = executor.submit(() -> scanner.nextLine());
                Future<String> future = executor.submit(() -> inputProvider.getInput());
                try {
                    String response = future.get(10, TimeUnit.SECONDS);
                    if (response.equalsIgnoreCase("snap")) {
                        System.out.println(player.getName() + " wins the round!");
                        player.incrementScore();
                        if (playGameAgain()) {
                            resetGame();
                        } else {
                            break;
                        }
                    } else {
                        System.out.println("Too slow or wrong input.");
                    }
                } catch (TimeoutException e) {
                    System.out.println("Time's up! No snap entered.");
                    future.cancel(true);
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println("An error occurred: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("No input - timeout.");
                } finally {
                    executor.shutdown();
                }
            }

            previousCard = dealtCard;
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            if (deckOfCards.isEmpty()) {
                System.out.println("No cards left!");
                if (playGameAgain()) {
                    resetGame();
                } else {
                    break;
                }
            }
        }

        // Show final scores
        System.out.println("\nFinal scores:");
        for (Player p : players) {
            System.out.println(p.getName() + ": " + p.getScore());
        }
    }
}
