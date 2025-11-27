package com.jewelry.ui.commands;
import com.jewelry.model.Stone;
import com.jewelry.ui.MenuController;

public class ViewActiveNecklaceCommand implements Command {
    private MenuController ctx;
    public ViewActiveNecklaceCommand(MenuController ctx) { this.ctx = ctx; }

    @Override
    public void execute() {
        if (ctx.getCurrentNecklace() == null) {
            System.out.println("⚠️ Намисто не вибрано!"); return;
        }
        System.out.println("\n--- Вміст: " + ctx.getCurrentNecklace().getName() + " ---");
        if (ctx.getCurrentNecklace().getStones().isEmpty()) System.out.println("(порожньо)");
        else {
            int i = 0;
            for (Stone s : ctx.getCurrentNecklace().getStones())
                System.out.println((++i) + ". " + s);
        }
    }
    @Override public String getDescription() { return "Переглянути вміст активного намиста"; }
}