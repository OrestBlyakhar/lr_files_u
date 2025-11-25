package com.jewelry.ui.commands;

public class RemoveStoneFromNecklaceCommand implements Command {
    @Override
    public void execute() {
        System.out.println("\n[STUB] Виконується: Видалення каменя з поточного намиста...");
    }
    @Override
    public String getDescription() { return "Видалити камінь з поточного намиста"; }
}
