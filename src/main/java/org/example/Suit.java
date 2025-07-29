package org.example;

public enum Suit {

    HEART("\u2764"),DIAMOND( "\u2666"), SPADE("\u2660"), CLUB("\u2663");
    private final String suitName;

    Suit(String suitName) {
        this.suitName=suitName;
    }
    public String getSuitName() {
        return this.suitName;
    }

}
