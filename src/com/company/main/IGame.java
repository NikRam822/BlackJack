package com.company.main;

import java.util.ArrayList;

/**
 * Интерфейс для обязательной реализации методов игр.
 */
public interface IGame {

   /**
     * Метод для сравнения суммы очков Игрока и Банкира.
     * @param Player сумма очков Игрока
     * @param Banker сумма очков Банкира
     * @return победитель
     */
    String isGameOver(int Player, int Banker);

    /**
     * Метод для игры.
     */
    void play();

    /**
     * Метод для вычисления суммы в банке.
     * @param bets сумма ставок
     * @param exodus результат сравнения сумм очков Игрока и Банкира
     * @param exodusSpecial результат наличия наутральной победы у Игрока и/или Банкира
     * @return сумма в банке
     */
    int countOfBank(ArrayList<Integer> bets, int bank, String exodus, String exodusSpecial);
}
