package com.jewelry.ui.commands;

public class DeleteNecklaceCommand implements Command {
    @Override
    public void execute() {
        System.out.println("\n[STUB] Виконується: Видалення існуючого намиста з колекції...");
    }

    @Override
    public String getDescription() {
        return "Видалити намисто";
    }
}