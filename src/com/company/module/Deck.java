package com.company.module;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/**
 * Класс описывающий сущность колода.
 */
public class Deck {

    /**
     * Метод для генерации колод.
     * @return сгенерированная колода
     */
    public Stack<Card> creatingDeck() {
        int i = 0;
        Stack<Card> deck = new Stack<>();
        String[] deckChanged = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] deckSuit = new String[]{"♥", "♦", "♣", "♠"};
        Card[] deckStart = new Card[52];
        for (String changed : deckChanged){
            for (String suit : deckSuit) {
                deckStart[i] = new Card(changed, suit);
                i++;
            }
        }
        Collections.shuffle(Arrays.asList(deckStart));
        for(int j=0; j<52; j++) {
            deck.push(deckStart[j]);
        }
        return deck;
    }

    /**
     * Метод для выдачи карты из колоды.
     * @param deckStart колода
     * @return карта выбранная из колоды
     */
    public  Card getCard( Stack<Card> deckStart) {
        return deckStart.pop();
    }




    }

