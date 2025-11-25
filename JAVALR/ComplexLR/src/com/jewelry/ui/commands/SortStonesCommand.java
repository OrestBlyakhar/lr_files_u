package com.jewelry.ui.commands;

public class SortStonesCommand implements Command {
    @Override
    public void execute() {
        System.out.println("\n[STUB] Виконується: Сортування каменів у намисті за цінністю...");
    }
    @Override
    public String getDescription() { return "Сортувати камені за цінністю"; }
}