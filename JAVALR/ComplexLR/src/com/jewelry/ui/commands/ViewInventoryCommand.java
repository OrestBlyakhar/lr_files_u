package com.jewelry.ui.commands;
import com.jewelry.ui.MenuController;

public class ViewInventoryCommand implements Command {
    private MenuController ctx;
    public ViewInventoryCommand(MenuController ctx) { this.ctx = ctx; }

    @Override
    public void execute() {
        System.out.println("\n--- Склад Каменів ---");
        if (ctx.getInventory().isEmpty()) System.out.println("(порожньо)");
        else for (int i=0; i<ctx.getInventory().size(); i++)
            System.out.println((i+1) + ". " + ctx.getInventory().get(i));
    }
    @Override public String getDescription() { return "Переглянути склад"; }
}