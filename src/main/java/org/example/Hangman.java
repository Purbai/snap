package org.example;

import java.util.*;

public class Hangman implements PlayGame {
    private String playerName;
    private final List<String> wordList = Arrays.asList("elephant", "softwaredeveloper", "hangman", "crocodile", "donkey");
    private final int maxAttempts = 6;
    private Scanner scanner = new Scanner(System.in);

    private String wordToGuess;
    private Set<Character> guessedLetters;
    private Set<String> guessedWords;
    private int remainingAttempts;

    @Override
    public void setupPlayers(String... names) {
        playerName = names[0];
    }

    @Override
    public void resetGame() {
        wordToGuess = wordList.get(new Random().nextInt(wordList.size())).toLowerCase();
        guessedLetters = new HashSet<>();
        guessedWords = new HashSet<>();
        remainingAttempts = maxAttempts;
    }

    private String getMaskedWord() {
        StringBuilder masked = new StringBuilder();
        for (char c : wordToGuess.toCharArray()) {
            masked.append(guessedLetters.contains(c) ? c : '_').append(' ');
        }
        return masked.toString().trim();
    }

    private boolean isWordGuessed() {
        for (char c : wordToGuess.toCharArray()) {
            if (!guessedLetters.contains(c)) return false;
        }
        return true;
    }

    private void drawHangman(int remainingAttempts, int maxAttempts) {
        //draw the hangman
        if (maxAttempts - remainingAttempts == 6) {
            System.out.println("___________________");
            System.out.println("  |/            |");
            System.out.println("  |            (_)");
            System.out.println("  |             |");
            System.out.println("  |            /|\\");
            System.out.println("  |             |");
            System.out.println("  |            /|\\");
            System.out.println("  |");
            System.out.println("__|______");
        }
        if (maxAttempts - remainingAttempts == 1) {
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("_________");
        }
        if (maxAttempts - remainingAttempts == 2) {
            System.out.println("___________________");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("__|______");
        }
        if (maxAttempts - remainingAttempts == 3) {
            System.out.println("___________________");
            System.out.println("  |/");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("__|______");
        }
        if (maxAttempts - remainingAttempts == 4) {
            System.out.println("___________________");
            System.out.println("  |/            |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("__|______");
        }
        if (maxAttempts - remainingAttempts == 5) {
            System.out.println("___________________");
            System.out.println("  |/            |");
            System.out.println("  |            (_)");
            System.out.println("  |             |");
            System.out.println("  |            /|");
            System.out.println("  |             |");
            System.out.println("  |            /|");
            System.out.println("  |");
            System.out.println("__|______");
        }
    }

    @Override
    public void startGame() {
        if (playerName == null) {
            System.out.println("No player! Cannot start game.");
            return;
        }
        resetGame();

        while (remainingAttempts > 0) {
            System.out.println("\nWord: " + getMaskedWord());
            System.out.println("Guessed letters: " + guessedLetters);
            System.out.println("Guessed words: " + guessedWords);
            System.out.println("Attempts left: " + remainingAttempts);
            System.out.print("Guess a letter or full word: ");

            String input = scanner.nextLine().toLowerCase();

            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                // single char enter
                char guess = input.charAt(0);
                if (guessedLetters.contains(guess)) {
                    System.out.println("You've already guessed that letter.");
                    continue;
                }
                guessedLetters.add(guess);

                if (wordToGuess.indexOf(guess) == -1) {
                    remainingAttempts--;
                    System.out.println("Wrong guess!");
                    drawHangman(remainingAttempts, maxAttempts);
                } else {
                    System.out.println("Good guess!");
                }

                continue;
            } else if (input.length() > 1) {
                if (guessedWords.contains(input)) {
                    System.out.println("You have already guessed that word!");
                    continue;
                }
                ;
                guessedWords.add(input);
                if (input.equalsIgnoreCase(wordToGuess)) {
                    System.out.println("Totally amazing - you've guessed the word " + playerName + "!");
                    return;
                } else {
                    remainingAttempts--;
                    System.out.println("Wrong word guess!");
                    drawHangman(remainingAttempts, maxAttempts);
                }
            } else {
                System.out.println("invalid input - try again!");
            }


            if (isWordGuessed()) {
                System.out.println("\nYou've won Hangman " + playerName + "! You guessed the word: " + wordToGuess);
                return;
            }
        }

    }
}