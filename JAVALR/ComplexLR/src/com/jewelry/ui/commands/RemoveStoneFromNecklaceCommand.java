package com.jewelry.ui.commands;
import com.jewelry.model.Stone;
import com.jewelry.ui.MenuController;

public class RemoveStoneFromNecklaceCommand implements Command {
    private MenuController ctx;
    public RemoveStoneFromNecklaceCommand(MenuController ctx) { this.ctx = ctx; }

    @Override
    public void execute() {
        if (ctx.getCurrentNecklace() == null) { System.out.println("⚠️ Намисто не вибрано!"); return; }
        new ViewActiveNecklaceCommand(ctx).execute();
        if (ctx.getCurrentNecklace().getStones().isEmpty()) return;
        System.out.print("Номер каменя для видалення: ");
        try {
            int idx = Integer.parseInt(ctx.getScanner().nextLine()) - 1;
            if (idx >= 0 && idx < ctx.getCurrentNecklace().getStones().size()) {
                Stone removed = ctx.getCurrentNecklace().getStones().remove(idx);
                // Повертаємо на склад, якщо хочете
                ctx.getInventory().add(removed);
                System.out.println("Камінь прибрано з намиста.");
            } else System.out.println("Невірний номер.");
        } catch (Exception e) { System.out.println("Помилка."); }
    }
    @Override public String getDescription() { return "Видалити камінь з намиста"; }
}