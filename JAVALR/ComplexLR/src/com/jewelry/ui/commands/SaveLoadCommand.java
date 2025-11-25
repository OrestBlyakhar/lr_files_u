package com.jewelry.ui.commands;

public class SaveLoadCommand implements Command {
    @Override
    public void execute() {
        System.out.println("\n[STUB] Виконується: Примусове збереження/завантаження даних...");
    }
    @Override
    public String getDescription() { return "Зберегти/Завантажити дані"; }
}
