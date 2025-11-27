package com.jewelry.ui.commands;
import com.jewelry.model.Necklace;
import com.jewelry.ui.MenuController;

public class CreateNecklaceCommand implements Command {
    private MenuController ctx;
    public CreateNecklaceCommand(MenuController ctx) { this.ctx = ctx; }

    @Override
    public void execute() {
        while (true) {
            System.out.print("\nВведіть назву нового намиста: ");
            String name = ctx.getScanner().nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("❌ Назва не може бути порожньою.");
                continue;
            }

            // Можна додати перевірку на унікальність (необов'язково, але круто)
            boolean exists = ctx.getNecklaces().stream()
                    .anyMatch(n -> n.getName().equalsIgnoreCase(name));

            if (exists) {
                System.out.println("❌ Намисто з такою назвою вже існує!");
                continue;
            }

            ctx.getNecklaces().add(new Necklace(name));
            System.out.println("✅ Намисто '" + name + "' створено!");
            break;
        }
    }
    @Override public String getDescription() { return "Створити нове намисто"; }
}