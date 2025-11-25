package com.jewelry.ui.commands;

public class ViewNecklacesCommand implements Command {
    @Override
    public void execute() {
        System.out.println("\n[STUB] Виконується: Виведення списку всіх створених намист...");
    }
    @Override
    public String getDescription() { return "Переглянути список намист"; }
}