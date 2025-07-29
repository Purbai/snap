package org.example;

//        ### Card
//
//- Has a String suit. Use the unicode characters of heart, club, diamond and spade.
//- Has a String symbol (2,3,4,5,6,7,8,9,10,J,Q,K,A)
//- Has an int value (2,3,4,5,6,7,8,9,10,11,12,13,14)
//- Has a toString method that describes the class
//
public class Card {
    String suit;
    String symbol;
    int value;

    public Card(String suit, String symbol, int value) {
        this.suit = suit;
        this.symbol = symbol;
        this.value = value;
    }
    public boolean matchesSymbol (Card otherCard){
        return this.symbol.equals(otherCard.symbol);
    }

@Override
    public String toString() {
        return symbol + " of " + suit + " (value: " + value + " )";
    }
}
