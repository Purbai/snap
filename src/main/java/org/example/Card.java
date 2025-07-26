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

    public enum Symbol {
        TWO("2"),THREE("3"),FOUR("4"),FIVE("5"),
        SIX("6"),SEVEN("7"),EIGHT("8"),NINE("9"),TEN("10"),
        JACK("J"),QUEEN("Q"),KING("K"),ACE("A"),;
        private final String symbolName;

        Symbol(String symbolName) {
            this.symbolName=symbolName;
        }
        public String getSymbolName() {
            return this.symbolName;
        }

    }
    public enum Value {
        TWO(2),THREE(3),FOUR(4),FIVE(5),
        SIX(6),SEVEN(7),EIGHT(8),NINE(9),TEN(10),
        JACK(11),QUEEN(12),KING(13),ACE(14),;
        private final int value;

        Value(int value) {
            this.value =  value;
        }
        public int getValue() {
            return this.value;
        }
    }

    public Card(String suit, String symbol, int value) {
        this.suit = suit;
        this.symbol = symbol;
        this.value = value;
    }

@Override
    public String toString() {
        return symbol + " of " + suit + " (value: " + value + " )";
}

}
