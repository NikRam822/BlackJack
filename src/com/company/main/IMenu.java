package com.company.main;

/**
 * Интерфейс для обязательной реализации меню пользователя.
 */
public interface IMenu {
    /**
     * Метод для обработки команд в меню игры.
     */
    void doCommandOfGame();

    /**
     * Метод для обработки команд в меню бота.
     * @param command введеная польхователем команада.
     */
    void doCommandOfMenu(String command);
}
