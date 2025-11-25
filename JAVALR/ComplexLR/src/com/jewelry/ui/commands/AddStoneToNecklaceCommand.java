package com.jewelry.ui.commands;

public class AddStoneToNecklaceCommand implements Command {
    @Override
    public void execute() {
        System.out.println("\n[STUB] Виконується: Додавання каменя зі складу у вибране намисто...");
    }
    @Override
    public String getDescription() { return "Додати камінь у поточне намисто"; }
}
