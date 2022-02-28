package com.company.commands;

import com.company.blackjack.BlackJack;
import com.company.blackjack.GameVisualizer;
import com.company.main.Menu;

import java.util.Scanner;

public class BlackJackGame extends Command{
    @Override
    public void execute() {
        BlackJack blackJack = new BlackJack();
        blackJack.play();
    }

    /**
     * Метод для обработки команд в игре.
     * @param bank текущий банк пользователя
     */
    public static void getCommand(int bank) {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        switch (command) {
            case "/start" -> System.out.println("Хорошо! Колоды перетасованы!\nБанк: " + bank + " руб.");
            case "/instrumentation" -> {
                GameVisualizer.getManual();
                getCommand(bank);
            }
            case "/exit" -> {
                System.out.println("Хорошо! Захочешь поиграть, заходи!");
                Menu exitGame = new Menu();
                exitGame.doCommandOfGame();
            }
            default -> {
                System.out.println("Не понял команду!");
                getCommand(bank);
            }
        }
    }
}
