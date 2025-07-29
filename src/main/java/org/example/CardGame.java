package org.example;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class CardGame {

    protected List<Card> deckOfCards = new ArrayList<>();
    protected String gameName;

    public CardGame(String gameName) {
        this.gameName = gameName;
    }

    public abstract void buildDeck();

    public abstract void startGame();

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
            public int compare(Card c1, Card c2) {
                return c1.suit.compareTo(c2.suit);
            }
        });
        return deckOfCards;
    }

    public void shuffleDeck() {
        Collections.shuffle(deckOfCards);
    }

    public Card dealCard() {
        return deckOfCards.remove(deckOfCards.size() - 1);
    }

    public boolean isDeckEmpty() {
        return deckOfCards.isEmpty();
    }

    public List<Card> getDeck() {
        return deckOfCards;
    }

    public String getGameName() {
        return gameName;
    }

}
