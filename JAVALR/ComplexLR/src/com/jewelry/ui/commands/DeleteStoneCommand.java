package com.jewelry.ui.commands;

public class DeleteStoneCommand implements Command {
    @Override
    public void execute() {
        System.out.println("\n[STUB] Виконується: Видалення каменя із загального складу...");
    }

    @Override
    public String getDescription() {
        return "Видалити камінь зі складу";
    }
}