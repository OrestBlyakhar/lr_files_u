package com.jewelry.ui.commands;
import com.jewelry.model.Stone;
import com.jewelry.ui.MenuController;
import java.util.List;
import java.util.Scanner;

public class FindStonesCommand implements Command {
    private MenuController ctx;
    public FindStonesCommand(MenuController ctx) { this.ctx = ctx; }

    @Override
    public void execute() {
        if (ctx.getCurrentNecklace() == null) {
            System.out.println("⚠️ Спочатку виберіть намисто!");
            return;
        }

        Scanner sc = ctx.getScanner();
        try {
            double min = -1;
            double max = -1;

            // Цикл перевірки діапазону
            while (true) {
                System.out.print("Мін. прозорість (0.0 - 1.0): ");
                min = Double.parseDouble(sc.nextLine());

                System.out.print("Макс. прозорість (0.0 - 1.0): ");
                max = Double.parseDouble(sc.nextLine());

                // Перевірка 1: Межі
                if (min < 0 || min > 1 || max < 0 || max > 1) {
                    System.out.println("❌ Помилка: Значення мають бути від 0.0 до 1.0");
                    continue;
                }

                // Перевірка 2: Логіка (мін не може бути більше макс)
                if (min > max) {
                    System.out.println("❌ Помилка: Мінімум не може бути більшим за максимум! Спробуйте ще раз.");
                    continue;
                }

                break; // Все добре
            }

            List<Stone> res = ctx.getService().findStonesByTransparency(ctx.getCurrentNecklace(), min, max);

            System.out.println("\n--- Результати пошуку ---");
            if (res.isEmpty()) {
                System.out.println("Нічого не знайдено в діапазоні [" + min + " - " + max + "]");
            } else {
                res.forEach(System.out::println);
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Помилка вводу: введіть числа.");
        }
    }
    @Override public String getDescription() { return "Знайти камені за прозорістю"; }
}