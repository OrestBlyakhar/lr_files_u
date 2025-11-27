package com.jewelry.ui.commands;
import com.jewelry.ui.MenuController;

public class SelectNecklaceCommand implements Command {
    private MenuController ctx;
    public SelectNecklaceCommand(MenuController ctx) { this.ctx = ctx; }

    @Override
    public void execute() {
        new ViewNecklacesCommand(ctx).execute();
        if (ctx.getNecklaces().isEmpty()) return;
        System.out.print("Виберіть номер намиста: ");
        try {
            int idx = Integer.parseInt(ctx.getScanner().nextLine()) - 1;
            if (idx >= 0 && idx < ctx.getNecklaces().size()) {
                ctx.setCurrentNecklace(ctx.getNecklaces().get(idx));
                System.out.println("Намисто вибрано.");
            } else System.out.println("Невірний номер.");
        } catch (Exception e) { System.out.println("Помилка."); }
    }
    @Override public String getDescription() { return "Вибрати активне намисто"; }
}