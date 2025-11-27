package com.jewelry.ui.commands;
import com.jewelry.ui.MenuController;

public class DeleteStoneCommand implements Command {
    private MenuController ctx;
    public DeleteStoneCommand(MenuController ctx) { this.ctx = ctx; }

    @Override
    public void execute() {
        new ViewInventoryCommand(ctx).execute();
        if (ctx.getInventory().isEmpty()) return;
        System.out.print("Номер для видалення: ");
        try {
            int idx = Integer.parseInt(ctx.getScanner().nextLine()) - 1;
            if (idx >= 0 && idx < ctx.getInventory().size()) {
                ctx.getInventory().remove(idx);
                System.out.println("Видалено.");
            } else System.out.println("Невірний номер.");
        } catch (Exception e) { System.out.println("Помилка."); }
    }
    @Override public String getDescription() { return "Видалити камінь зі складу"; }
}