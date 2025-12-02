package com.jewelry.ui.commands;

import com.jewelry.model.*;
import com.jewelry.ui.MenuController;
import com.jewelry.util.AppLogger;

import java.util.Scanner;

public class CreateStoneCommand implements Command {
    private MenuController ctx;

    public CreateStoneCommand(MenuController ctx) {
        this.ctx = ctx;
    }

    @Override
    public void execute() {
        Scanner sc = ctx.getScanner();
        System.out.println("\n--- Створення Каменя ---");

        try {
            // Використовуємо наші безпечні методи введення
            int type = readInt(sc, "Тип (1-Дорогоцінний, 2-Напівкоштовний): ", 1, 2);

            String name = readString(sc, "Назва: ");

            double weight = readDouble(sc, "Вага (карати): ", 0.01, 1000.0);

            // Ціна може бути 0 (безкоштовно), але не мінус
            double cost = readDouble(sc, "Ціна ($): ", 0.0, Double.MAX_VALUE);

            // Прозорість строго від 0 до 1
            double transp = readDouble(sc, "Прозорість (0.0 - 1.0): ", 0.0, 1.0);

            Stone stone;
            if (type == 1) {
                stone = new PreciousStone(name, weight, cost, transp);
            } else {
                stone = new SemiPreciousStone(name, weight, cost, transp);
            }

            ctx.getInventory().add(stone);
            System.out.println("✅ Камінь успішно додано на склад!");
            AppLogger.info("Користувач додав новий камінь: " + name);

        } catch (Exception e) {
            System.out.println("❌ Помилка створення: " + e.getMessage());
            AppLogger.warn("Невдала спроба створення каменя: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() { return "Створити новий камінь"; }

    // --- Допоміжні методи для валідації ---

    // Читає число в заданому діапазоні
    private double readDouble(Scanner sc, String prompt, double min, double max) {
        double value;
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine();
            try {
                value = Double.parseDouble(input);
                if (value >= min && value <= max) {
                    return value; // Все ок, виходимо
                } else {
                    System.out.printf("Помилка: число має бути від %.2f до %.2f\n", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введіть коректне число (наприклад 0.5)");
            }
        }
    }

    private int readInt(Scanner sc, String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(sc.nextLine());
                if (value >= min && value <= max) return value;
                System.out.printf("Помилка: введіть число від %d до %d\n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введіть ціле число.");
            }
        }
    }

    private String readString(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String str = sc.nextLine().trim();
            if (!str.isEmpty()) return str;
            System.out.println("Помилка: текст не може бути порожнім.");
        }
    }
}