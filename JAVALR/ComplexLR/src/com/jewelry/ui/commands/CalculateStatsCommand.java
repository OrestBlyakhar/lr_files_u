package com.jewelry.ui.commands;
import com.jewelry.ui.MenuController;

public class CalculateStatsCommand implements Command {
    private MenuController ctx;
    public CalculateStatsCommand(MenuController ctx) { this.ctx = ctx; }

    @Override
    public void execute() {
        if (ctx.getCurrentNecklace() == null) { System.out.println("⚠️ Намисто не вибрано!"); return; }
        double weight = ctx.getService().calculateTotalWeight(ctx.getCurrentNecklace());
        double cost = ctx.getService().calculateTotalCost(ctx.getCurrentNecklace());
        System.out.printf("\nЗагальна вага: %.2f carats\nЗагальна ціна: $%.2f\n", weight, cost);
    }
    @Override public String getDescription() { return "Розрахувати вагу та вартість"; }
}