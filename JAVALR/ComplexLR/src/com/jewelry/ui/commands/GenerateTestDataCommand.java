package com.jewelry.ui.commands;

import com.jewelry.model.*;
import com.jewelry.ui.MenuController;

public class GenerateTestDataCommand implements Command {
    private MenuController ctx;

    public GenerateTestDataCommand(MenuController ctx) {
        this.ctx = ctx;
    }

    @Override
    public void execute() {
        System.out.println("\n--- Генерація тестових даних... ---");

        // 1. Створюємо камені
        Stone s1 = new PreciousStone("Діамант", 1.5, 5000.0, 1.0);
        Stone s2 = new PreciousStone("Рубін", 2.0, 3000.0, 0.8);
        Stone s3 = new PreciousStone("Смарагд", 1.8, 4500.0, 0.75);
        Stone s4 = new SemiPreciousStone("Бурштин", 5.5, 200.0, 0.5);
        Stone s5 = new SemiPreciousStone("Аметист", 3.0, 400.0, 0.9);
        Stone s6 = new SemiPreciousStone("Топаз", 2.5, 600.0, 0.95);

        // Додаємо на склад
        ctx.getInventory().add(s1);
        ctx.getInventory().add(s2);
        ctx.getInventory().add(s3);
        ctx.getInventory().add(s4);
        ctx.getInventory().add(s5);
        ctx.getInventory().add(s6);

        System.out.println("✅ Додано 6 каменів на склад.");

        // 2. Створюємо тестове намисто
        Necklace n1 = new Necklace("Вечірнє Сяйво");
        n1.addStone(s1); // Діамант
        n1.addStone(s3); // Смарагд

        Necklace n2 = new Necklace("Осінній Вальс");
        n2.addStone(s4); // Бурштин
        n2.addStone(s2); // Рубін

        // Додаємо в колекцію
        ctx.getNecklaces().add(n1);
        ctx.getNecklaces().add(n2);

        System.out.println("✅ Створено 2 намиста: 'Вечірнє Сяйво' та 'Осінній Вальс'.");

        System.out.println("⚠️ УВАГА: Щоб зберегти ці дані у файл, виберіть пункт '0' (Вихід) або '13' (Зберегти)!");
    }

    @Override
    public String getDescription() {
        return "[TEST] Згенерувати тестові дані";
    }
}