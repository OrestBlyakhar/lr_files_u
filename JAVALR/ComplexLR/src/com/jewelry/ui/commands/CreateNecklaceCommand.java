package com.jewelry.ui.commands;

public class CreateNecklaceCommand implements Command {
    @Override
    public void execute() {
        System.out.println("\n[STUB] Виконується: Створення нового порожнього намиста...");
    }
    @Override
    public String getDescription() { return "Створити нове намисто"; }
}