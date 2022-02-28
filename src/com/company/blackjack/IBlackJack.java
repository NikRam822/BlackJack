package com.company.blackjack;

import com.company.module.Card;
import com.company.module.CardHand;

import java.util.Stack;

/**
 * Интерфейс для реализации специальных методов игры BlackJack.
 */
public interface IBlackJack {

    /**
    * Метод для раздачи карт Игроку.
    * @param deck колода карт
    * @return Рука Игрока
    */
    CardHand distributionOfCardsPlayer(Stack<Card> deck);

    /**
     * Метод для раздачи карт Банкиру.
     * @param deck колода карт
     * @return Рука Банкира
     */
    CardHand distributionOfCardsBanker(Stack<Card> deck);

    /**
     * Метод для приянтия решениий Игрока.
     * @return решение Игрока
     */
    boolean doDecisionPlayer();

    /**
     * Метод для приянтия решениий Банкира.
     * @param sumBanker сумма очков Банкира
     * @param sumPlayer сумма очков Игрока
     * @return решение Банкира
     */
    boolean doDecisionBanker(int sumBanker , int sumPlayer);

    /**
     * Метод для основного цикла игры.
     * @param deckStart колода карт
     * @param bank банк
     //* @param numberGame порядковый номер игры
     */
    void creatingHandsAndPlayGame(Stack<Card> deckStart, int bank );

    /**
     * Метод для определния исхода BlackJack.
     * @param sumBanker сумма очков Банкира
     * @param sumPlayer сумма очков Игрока
     * @return исход
     */
    String isBlackJack(int sumBanker, int sumPlayer);
}
