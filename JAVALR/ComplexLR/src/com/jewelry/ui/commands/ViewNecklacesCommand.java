package com.jewelry.ui.commands;
import com.jewelry.ui.MenuController;

public class ViewNecklacesCommand implements Command {
    private MenuController ctx;
    public ViewNecklacesCommand(MenuController ctx) { this.ctx = ctx; }

    @Override
    public void execute() {
        System.out.println("\n--- Список Намист ---");
        if (ctx.getNecklaces().isEmpty()) System.out.println("(порожньо)");
        else for (int i=0; i<ctx.getNecklaces().size(); i++)
            System.out.println((i+1) + ". " + ctx.getNecklaces().get(i).getName());
    }
    @Override public String getDescription() { return "Переглянути список намист"; }
}