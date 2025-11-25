package com.jewelry.ui.commands;

public class FindStonesCommand implements Command {
    @Override
    public void execute() {
        System.out.println("\n[STUB] Виконується: Пошук каменів за діапазоном прозорості...");
    }
    @Override
    public String getDescription() { return "Знайти камені за прозорістю"; }
}