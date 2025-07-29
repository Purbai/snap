package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class HangmanGameTest {
    private Hangman game;

    @BeforeEach
    public void setup() {
        game = new Hangman();
        game.setupPlayers("Bob");
        game.resetGame();
    }

    @Test
    @DisplayName("remainingAttempts should be 6, guessletters & guesswords should be empty on game reset")
    public void resetGameInitializesRemainingAttemptsGuessLettersGuessWords() {
        game.resetGame();
        assertNotNull(getPrivateField(game, "wordToGuess"));
        assertEquals(6, (int) getPrivateField(game, "remainingAttempts"));
        assertTrue(((Set<?>) getPrivateField(game, "guessedLetters")).isEmpty());
        assertTrue(((Set<?>) getPrivateField(game, "guessedWords")).isEmpty());
    }


    private Object getPrivateField(Object obj, String fieldName) {
        try {
            var field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            throw new RuntimeException("Could not access field: " + fieldName, e);
        }
    }
}
