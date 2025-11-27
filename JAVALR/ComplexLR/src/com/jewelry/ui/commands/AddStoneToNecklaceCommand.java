package com.jewelry.ui.commands;
import com.jewelry.ui.MenuController;

public class AddStoneToNecklaceCommand implements Command {
    private MenuController ctx;
    public AddStoneToNecklaceCommand(MenuController ctx) { this.ctx = ctx; }

    @Override
    public void execute() {
        if (ctx.getCurrentNecklace() == null) { System.out.println("⚠️ Намисто не вибрано!"); return; }
        new ViewInventoryCommand(ctx).execute();
        if (ctx.getInventory().isEmpty()) return;
        System.out.print("Номер каменя зі складу: ");
        try {
            int idx = Integer.parseInt(ctx.getScanner().nextLine()) - 1;
            if (idx >= 0 && idx < ctx.getInventory().size()) {
                ctx.getCurrentNecklace().addStone(ctx.getInventory().get(idx));
                System.out.println("Додано!");
            } else System.out.println("Невірний номер.");
        } catch (Exception e) { System.out.println("Помилка."); }
    }
    @Override public String getDescription() { return "Додати камінь у намисто"; }
}