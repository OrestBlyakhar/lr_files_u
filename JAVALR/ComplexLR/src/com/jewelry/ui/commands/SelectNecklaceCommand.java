package com.jewelry.ui.commands;

public class SelectNecklaceCommand implements Command {
    @Override
    public void execute() {
        System.out.println("\n[STUB] Виконується: Вибір активного намиста для роботи...");
        System.out.println("(Тут користувач вибере намисто, і воно збережеться в currentNecklace)");
    }
    @Override
    public String getDescription() { return "Вибрати намисто для роботи"; }
}
