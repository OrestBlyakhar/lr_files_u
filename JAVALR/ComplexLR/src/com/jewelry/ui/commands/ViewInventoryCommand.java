package com.jewelry.ui.commands;

public class ViewInventoryCommand implements Command {
    @Override
    public void execute() {
        System.out.println("\n[STUB] Виконується: Виведення списку всіх каменів на складі...");
    }
    @Override
    public String getDescription() { return "Переглянути склад каменів"; }
}
