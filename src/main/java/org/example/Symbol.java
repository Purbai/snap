package org.example;

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
