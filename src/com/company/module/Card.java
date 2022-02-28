package com.company.module;

/**
 * Сущность карта.
 */
public class Card {

    /**
     * Номинал карты.
     */
    public String valueCards;

    /**
     * Масть карты.
     */
    public String suit;

    /**
     * Конструктор для присваевания полей сущности Карта.
     * @param value номинал карты.
     * @param suit масть масть.
     */
    public Card (String value, String suit) {
       this.valueCards = value;
       this.suit = suit;
    }
}
