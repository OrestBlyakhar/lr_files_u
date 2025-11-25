package com.jewelry.ui.commands;

public class CalculateStatsCommand implements Command {
    @Override
    public void execute() {
        System.out.println("\n[STUB] Виконується: Розрахунок загальної ваги (карати) та вартості...");
    }
    @Override
    public String getDescription() { return "Порахувати вагу та ціну намиста"; }
}