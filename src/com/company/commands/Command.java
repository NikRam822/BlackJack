package com.company.commands;

/**
 * Класс сущности команда.
 */
public abstract class Command implements ICommand {
    @Override
    public abstract void execute();
}

