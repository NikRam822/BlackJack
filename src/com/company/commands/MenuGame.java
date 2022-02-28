package com.company.commands;

import com.company.main.Menu;

public class MenuGame extends Command {
    @Override
    public  void execute() {
        Menu menu = new Menu();
        System.out.println("Добро пожаловать в раздел игр!\nИгры ИИ против ИИ:\n/BlackJack - классическая игра Блэкджек.\nОпции:\n   /exit - выход.");
        menu.doCommandOfGame();
    }
}
