package com.company.commands;

public class GameExit extends Command {
    @Override
    public void execute() {
        System.out.println("/play - начать игру.\n/exit - закончить работу с ботом.");
    }
}
