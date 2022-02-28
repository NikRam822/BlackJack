package com.company.commands;

public class MenuHelp extends Command {
    @Override
    public void execute() {
        System.out.println("/play - начать игру.\n/exit - закончить работу с ботом.");
    }
}
