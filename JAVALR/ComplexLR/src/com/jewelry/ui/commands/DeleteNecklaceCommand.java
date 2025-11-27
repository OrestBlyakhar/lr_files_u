package com.jewelry.ui.commands;
import com.jewelry.model.Necklace;
import com.jewelry.ui.MenuController;

public class DeleteNecklaceCommand implements Command {
    private MenuController ctx;
    public DeleteNecklaceCommand(MenuController ctx) { this.ctx = ctx; }

    @Override
    public void execute() {
        new ViewNecklacesCommand(ctx).execute();
        if (ctx.getNecklaces().isEmpty()) return;
        System.out.print("Номер для видалення: ");
        try {
            int idx = Integer.parseInt(ctx.getScanner().nextLine()) - 1;
            if (idx >= 0 && idx < ctx.getNecklaces().size()) {
                Necklace removed = ctx.getNecklaces().remove(idx);
                if (ctx.getCurrentNecklace() == removed) ctx.setCurrentNecklace(null);
                System.out.println("Видалено.");
            } else System.out.println("Невірний номер.");
        } catch (Exception e) { System.out.println("Помилка."); }
    }
    @Override public String getDescription() { return "Видалити намисто"; }
}