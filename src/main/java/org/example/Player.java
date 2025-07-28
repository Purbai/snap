package org.example;
//### Stage 4
//
//Create a Player class and enable the snap game to be two player, with the users taking it in turns to go. If the snap occurs on the users turn, they win.
//
//Add a timer so that when there is a snap opportunity, the player has 2 seconds to submit the word “snap” in order to win. If they don’t type it in time, they lose.
//

public class Player {
    private final String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore(){
        score++;
    }
}
