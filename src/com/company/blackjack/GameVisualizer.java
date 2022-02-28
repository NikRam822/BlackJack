package com.company.blackjack;

import com.company.module.CardHand;

/**
 * Класс для описания сущности GameVisualizer.
 */
public class GameVisualizer {
    /**
     * Некорректный ввод ставки.
     */
    private static final String ERROR_BET = "В этой раздаче ставка не действительна!";

    /**
     * Приветсвие бота.
     */
    private static final String GREETING_BOT = "Привет! Тебя приветствует бот Tote!\nНапиши команду /help чтобы узнать, что я могу!\n";


    /**
     * Инструкция к игре.
     */
    private static final String INSTRACTION = "Опции:\n/start - начать игру\n/instrumentation - инстуркция к игре\n/exit - выход";

    /**
     * Приветсвие в игре.
     */
    private static final String GREETING_BLACK_JACK = "Добро пожаловать в игру Black Jack!";

    /**
     * Взятие дополнительной карты.
     */
    private static final String TAKE_CARDS = "Берете дополнительную карту? \n/yes - взять карту\n/no - не брать карту";

    /**
     * Ставки и коэффиценты в игре.
     */
    private static final String BET = """
            
            Делайте ваши ставки!
                1. Выиграет Игрок: x2.00
                2. Будет «BlackJack» - Банкир: x10.00
                3. Будет «BlackJack» - Игрок: x10.00
                /exit - выйти из игры.
            Укажите суммы через пробел (Пример: 400 0 15)!
            """;
    /**
     * Инструкция к игре.
     */
    private static final String MANUAL = """
            Блэкджек — одна из самых популярных карточных игр в казино по всему миру.
            Цель игры — набрать комбинацию карт с общим числом очков 21 или как можно более близким к 21.
            Масти роли в игре не играют. Значения карт:
            A - 11 очков/1 очко (11 пока общая сумма не больше 21, далее 1), K - 10 очков, Q - 10 очков, J - 10 очков.
            Остальные значения соотвествуют значениям карт.
            Если у игрока сразу после раздачи набралось 21 очко.
            То такая ситуация называется «BlackJack».
            Игрок или Банкир, набравший более ближе к 21, но не перебрав его, выигрывает.
            Если Игрок или банкир набирает больше 21 очка, это называется перебор, побеждает, тот у кого его нет.
            Пользователь делает ставку против Банкира.
            Также вы можете поставить на исход «BlackJack» Банкира или Игрока.
            В начале игры Банкир и Игрок получают по две карты. Далее следует добор карт.
            
            Коэффициенты ставок:
            1. Выиграет Игрок: x2.00
            2. Будет «BlackJack» - Банкир: x10.00
            3. Будет «BlackJack» - Игрок: x10.00
            В случае Ничьи ставка против Банкира возвращается в x1.00.
             """;

    /**
     * Метод для визуализации взятия карт.
     */
    public static void takeNewCard() {
        System.out.println(TAKE_CARDS);
    }

    /**
     * Метод для визуализации инструкции пользователю.
     */
    public static void getInstractionGreeting() {
        System.out.println(GREETING_BLACK_JACK);
        System.out.println(INSTRACTION);
    }
    /**
     * Метод для визуализации инструкции к игре.
     */
    public static void getManual() {
        System.out.println(MANUAL);
    }

    /**
     * Метод для визуализации раздачи.
     */
    public static void getDistributionNumber(int numberGame) {
        System.out.println("\nРаздача №" + numberGame + ".");
    }

    /**
     * Метод для визуализации сущностей рука Банкира и рука Игрока.
     * @param handBanker рука Банкира
     * @param handPlayer рука Игрока
     */
    public static void getDistributionCloseHands(CardHand handBanker, CardHand handPlayer){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("Banker: ");
        for (int i = 0; i < handBanker.size(); i++) {
            if (i == 0) {
                System.out.print(handBanker.get(i).valueCards + handBanker.get(i).suit+ " ");
            } else {
                System.out.print("?⍰ ");
            }
        }
        System.out.print("|");
        for (int i = 0; i < handPlayer.size(); i++) {
            System.out.print(" " + handPlayer.get(i).valueCards + handPlayer.get(i).suit);
        }
        System.out.println(" :Player");
    }

    /**
     * Метод для визуализации сущностей Рука Банкира.
     * @param handBanker рука Банкира
     */
    public static void getDistributionHands(CardHand handBanker, CardHand handPlayer){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("Banker: ");
        for (int i = 0; i < handBanker.size(); i++) {
            System.out.print(handBanker.get(i).valueCards + handBanker.get(i).suit+ " ");
        }
        System.out.print("|");
        for (int i = 0 ; i < handPlayer.size(); i++) {
            System.out.print(" " + handPlayer.get(i).valueCards + handPlayer.get(i).suit);
        }
        System.out.println(" :Player");
    }

    /**
     * Метод для визуализации ставок;
     */
    public static void getBet() {
        System.out.println(BET);
    }

    /**
     * Метод для визуализации команды игры.
     */
    public static void getHello() {
        System.out.print(GREETING_BOT);
    }

    /**
     * Метод для визуализации ошибок раздачи.
     */
    public static void getDistributionError() {
        System.out.println(ERROR_BET);
    }
}