package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SnapGameTest {
    private Snap game;

    @BeforeEach
    public void setup() {
        game = new Snap();
    }

    @Test
    @DisplayName("new player Joe should be setup with 0 score, increment will add 1 to Joe's score")
    public void testPlayerScoreIncrement() {
        Player player = new Player("Joe");
        assertEquals(0, player.getScore());
        player.incrementScore();
        assertEquals(1, player.getScore());
    }

    @Test
    @DisplayName("Deck has 52 Cards")
    public void createDeckShouldHave52Cards() {
        Snap game = new Snap();
        game.buildDeck();
        assertEquals(52, game.getDeck().size());
    }

    @Test
    @DisplayName("Cards with same symbol should match")
    public void compareCardsWithMatchingSymbolsShouldMatch() {
        Snap game = new Snap();

        Card card1 = new Card("Hearts", "5", 5);
        Card card2 = new Card("Spades", "5", 5);

        assertTrue(card1.matchesSymbol(card2));
    }
    @Test
    @DisplayName("Cards with difference symbol should NOT match")
    public void compareCardsDifferentSymbolsShouldNotMatch() {
        Snap game = new Snap();

        Card card1 = new Card("Hearts", "5", 5);
        Card card2 = new Card("Clubs", "8", 8);

        assertFalse(card1.matchesSymbol(card2));
    }

//    @Test
//    @DisplayName("")
//    public void testTimeoutSnapFails() throws Exception {
//        // Create a mock InputProvider
//        InputProvider mockInput = mock(InputProvider.class);
//
//        // Simulate pressing ENTER first, then hang on the snap response
//        when(mockInput.getInput()).thenReturn(""); // enter to deal card
//
//        Snap game = new Snap(mockInput);
//        game.setupPlayers("TestPlayer");
//        game.buildDeck();
//
//        // Inject a known deck with matching symbols to trigger SNAP
//        Card card1 = new Card("Hearts", "5", 5);
//        Card card2 = new Card("Spades", "5", 5);
//        game.getDeck().clear();
//        game.getDeck().add(card2);
//        game.getDeck().add(card1);
//
//        // Run timeout logic only
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        Future<String> fakeFuture = executor.submit(() -> {
//            Thread.sleep(11000); // Simulate timeout
//            return "snap";
//        });
//
//        try {
//            // This should timeout and throw TimeoutException
//
//            fakeFuture.get(2, TimeUnit.SECONDS); // Should timeout
//            fail("Expected a TimeoutException but did not get one.");
//        } catch (TimeoutException e) {
//            // Expected timeout
//            assertTrue(true);
//        } finally {
//            executor.shutdownNow();
//        }
//    }

//
//    @Test
//    @DisplayName("Full simulated game using predefined input")
//    public void testFullSimulatedGameLoop() {
//        String simulatedInput = "\n\nsnap\nn\n"; // simulate ENTER, ENTER, then SNAP, then NO
//        InputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
//       // Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
//
//        // snapGame = new Snap(scanner::nextLine); // Pass lambda that always uses this Scanner
//        Scanner scanner = new Scanner(input);
//        Snap snapGame = new Snap(() -> scanner.nextLine());
//
//        //Snap snapGame = new Snap();
//        snapGame.setupPlayers("Alice");
//
//        // Custom deck to force a snap condition
//        Card card1 = new Card("Hearts", "7", 7);
//        Card card2 = new Card("Spades", "7", 7);
//        snapGame.getDeck().clear();
//        snapGame.getDeck().add(card2);
//        snapGame.getDeck().add(card1);
//
//        snapGame.startGame();
//        assertEquals(1, snapGame.getPlayers().get(0).getScore());
//    }


}
