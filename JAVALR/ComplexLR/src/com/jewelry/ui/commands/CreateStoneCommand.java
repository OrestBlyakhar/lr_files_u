package com.jewelry.ui.commands;

public class CreateStoneCommand implements Command {
    @Override
    public void execute() {
        System.out.println("\n[STUB] Виконується: Створення нового каменя та додавання на склад...");
    }
    @Override
    public String getDescription() { return "Створити новий камінь"; }
}
