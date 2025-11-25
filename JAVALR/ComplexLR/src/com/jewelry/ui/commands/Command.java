package com.jewelry.ui.commands;

public interface Command {
    // Метод, який виконує дію
    void execute();

    // Опис для виведення в меню
    String getDescription();
}