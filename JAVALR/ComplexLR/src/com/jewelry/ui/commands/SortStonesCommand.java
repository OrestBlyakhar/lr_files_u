package com.jewelry.ui.commands;
import com.jewelry.ui.MenuController;

public class SortStonesCommand implements Command {
    private MenuController ctx;
    public SortStonesCommand(MenuController ctx) { this.ctx = ctx; }

    @Override
    public void execute() {
        if (ctx.getCurrentNecklace() == null) { System.out.println("⚠️ Намисто не вибрано!"); return; }
        ctx.getService().sortStonesByValue(ctx.getCurrentNecklace());
        System.out.println("Відсортовано за спаданням ціни.");
        new ViewActiveNecklaceCommand(ctx).execute();
    }
    @Override public String getDescription() { return "Сортувати камені за цінністю"; }
}