# Snap Walkthrough

Walkthrough of the Snap game reproduction with basic functionality written in Java

- [x] Create a deck of cards containing 52 cards
  - [x] a card is made up a suit, symbol and value
    - [x] Has a String suit. Use the unicode characters of heart, club, diamond and spade.
    - [x] Has a String symbol (2,3,4,5,6,7,8,9,10,J,Q,K,A)
    - [x] Has an int value (2,3,4,5,6,7,8,9,10,11,12,13,14)
    - [x] Has a toString method that describes the class
  - [x] shuffle cards
  - [x] sort deck in suit order
  - [x] sort desk in number order
  - [x] card can be dealt from the top of the deck
- [x] Players
  - [x] game can be played by multiple players
  - [x] track games won for each player
- [x] Playing rules
  - [x] shuffle deck of cards in random order
  - [x] By pressing enter in the command line, the Player takes their turn.
  - [x] players take in turn to play
  - [x] Each turn, a new card is dealt from the deck.
  - [x] The game continues until two cards in a row have the same symbol, at which point the “player” wins and the game ends.
  - [x] Timer for snap opportunities 
    - [x] player must enter snap to win the game within the time else they lose the opportunity and game continues
  - [x] Display scoreboard showing players and their games won
-[x] Unit test
  - [x] create deck with 52 cards
  - [x] test for 2 cards with same symbol should match
  - [x] test for 2 cards with different symbols should not match
  - [x] test incrementing score by 1 for player with score, the player score should be 1
  - [] test to simulate the full snap game - work in progress
-[x] Future development
    - [] more unit testing
    - [] refactor code
  

# Hangman Walkthrough

Walkthrough of the Hangman game reproduction with basic functionality written in Java

- [x] playing Rules
  - [x] The program randomly selects a word (from array of words)
    - [x] The program displays the word using the underscore character "_".
    - [x] A underscore for each letter separated with a space.
 - [x] The player is prompted to enter a letter as a guess. The program needs to capture their guess.
   - [x] Player can guess a letter or the entire word
   - [x] Player has 6 attempts to guess the word
   - [x] If the letter is in the word, the _ will be replaced with the guessed letter.
   - [x] If the letter is not in the word the player loses a life.
 - [x] for each guess, display:
   - [x] The word with blanks / correct guesses re-displayed e.g E _ E _ _ _ _ _
   - [x] The letters guessed so far e.g "Guessed letters: E G"
   - [x] The word guessed so far e.g "Guessed words : apple, orange"
   - [x] The amount of attempts left e.g "Attempts left: 5"
 - [x] The game continues with the player guessing more letters until the player correctly guesses the word or they run out of no of attempts.
 - [x] Game was enhanced to draw the (progressive) hangman picture using ascii chars for each attempt lost
- [x] Future development
  - [] unit testing
  - [] refactor code

