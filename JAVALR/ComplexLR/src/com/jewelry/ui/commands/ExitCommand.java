package com.jewelry.ui.commands;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.out.println("\nЗавершення роботи. До побачення!");
    }
    @Override
    public String getDescription() { return "Вихід"; }
}