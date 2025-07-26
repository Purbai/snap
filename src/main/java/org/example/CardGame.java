package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//        ### CardGame
//
//- Contains an ArrayList\<Card\> for the deckOfCards that contains all 52 cards. This is created and populated when the game is constructed.
//        - Has a name which is also defined in the constructor.
//        - Has a getDeck method that lists out the cards in the deck.
//
//Lets get some core functionality to our CardGame by implementing the following methods:
//
//Card dealCard()
//
//Takes the card from the top of the deck and returns it.
//
//ArrayList<Card> sortDeckInNumberOrder()
//
//Sorts the deck in number order (e.g. 2222333344445555 etc) and stores the new shuffled deck back into the deckOfCards attribute.
//
//        ArrayList<Card> sortDeckIntoSuits()
//
//Sorts the deck into suits (2,3,4,5,6,7,8,9,10,J,Q,K,A of hearts, then 2,3,4,5,6,7,8,9,10,J,Q,K,A of clubs etc.) and stores the new shuffled deck back into the deckOfCards attribute.
//
//        ArrayList<Card> shuffleDeck()
//
//Shuffles the deck into a random order and stores the new shuffled deck back into the deckOfCards attribute.
public class CardGame {
    private String name;
    List<Card> deckOfCards = new ArrayList<>();

    public  CardGame() {
    }
    public CardGame(String name, List<Card> deckOfCards) {
        this.name = name;
        this.deckOfCards = deckOfCards;
    }

    public void buildDeck() {
        Card.Symbol[] symbols = Card.Symbol.values();
        Card.Value[] values = Card.Value.values();

        for (Card.Suit suit : Card.Suit.values()) {
            for (int i=0; i< symbols.length; i++) {
                // use the same loop for the symbol and value of the card
                String cardSuit = suit.getSuitName();
                String cardSymbol = symbols[i].getSymbolName();
                int cardValue = values[i].getValue();
                deckOfCards.add(new Card(cardSuit,cardSymbol, cardValue));
            }
        }
    }

    public void getDeck() {
        System.out.println(deckOfCards.toString());
    }

    public Card dealCard() {

        if (deckOfCards.isEmpty()) return null;
        return deckOfCards.removeLast();
    }

    public List<Card> sortDeckInNumberOrder() {
        deckOfCards.sort(new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                return Integer.compare(c1.value, c2.value);
            }
        });
        return deckOfCards;
    }

    public List<Card> sortDeckIntoSuits() {
        Collections.sort(deckOfCards, new Comparator<Card>() {
            @Override
            public int  compare(Card c1, Card c2) {
                return c1.suit.compareTo(c2.suit);
            }
        });
        return deckOfCards;
    }

    public List<Card> shuffleDeck() {
        Collections.shuffle(deckOfCards);
        return deckOfCards;
    }
}
