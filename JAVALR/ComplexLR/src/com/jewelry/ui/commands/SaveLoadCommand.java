package com.jewelry.ui.commands;
import com.jewelry.ui.MenuController;

public class SaveLoadCommand implements Command {
    private MenuController ctx;
    public SaveLoadCommand(MenuController ctx) { this.ctx = ctx; }

    @Override
    public void execute() {
        System.out.println("1. Зберегти  2. Перезавантажити");
        String ch = ctx.getScanner().nextLine();
        if (ch.equals("1")) ctx.saveAll();
        else if (ch.equals("2")) ctx.reloadAll();
    }
    @Override public String getDescription() { return "Зберегти/Завантажити дані"; }
}