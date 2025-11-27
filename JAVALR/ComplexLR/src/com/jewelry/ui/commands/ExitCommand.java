package com.jewelry.ui.commands;
import com.jewelry.ui.MenuController;

public class ExitCommand implements Command {
    private MenuController ctx;
    public ExitCommand(MenuController ctx) { this.ctx = ctx; }

    @Override
    public void execute() {
        System.out.println("Збереження... До побачення!");
        ctx.saveAll();
        System.exit(0);
    }
    @Override public String getDescription() { return "Вихід"; }
}