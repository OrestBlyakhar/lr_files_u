package com.jewelry.ui;

import com.jewelry.ui.commands.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuController {

    // Поля з вашої Class Diagram (поки що null, бо це скелет)
    // private Necklace currentNecklace;
    // private NecklaceRepository necklaceRepo;
    // private StoneRepository stoneRepo;

    // Зберігання команд: Ключ (пункт меню) -> Команда
    private Map<String, Command> menuItems;
    private Scanner scanner;

    public MenuController() {
        this.scanner = new Scanner(System.in);
        this.menuItems = new LinkedHashMap<>(); // LinkedHashMap зберігає порядок додавання

        // === Ініціалізація команд ===

        // 1. Склад
        menuItems.put("1", new CreateStoneCommand());
        menuItems.put("2", new ViewInventoryCommand());
        menuItems.put("3", new DeleteStoneCommand());

        // 2. Керування Намистами
        menuItems.put("4", new CreateNecklaceCommand());
        menuItems.put("5", new ViewNecklacesCommand());
        menuItems.put("6", new SelectNecklaceCommand());
        // У конструкторі MenuController:
        menuItems.put("7", new DeleteNecklaceCommand());

        // 3. Робота з активним намистом
        menuItems.put("8", new AddStoneToNecklaceCommand());
        menuItems.put("9", new RemoveStoneFromNecklaceCommand());

        // 4. Аналітика
        menuItems.put("10", new CalculateStatsCommand());
        menuItems.put("11", new SortStonesCommand());
        menuItems.put("12", new FindStonesCommand());

        // Системні
        menuItems.put("13", new SaveLoadCommand());
        menuItems.put("0", new ExitCommand());
    }

    public void run() {
        System.out.println("=== Ювелірна Майстерня (Skeleton v1.0) ===");

        while (true) {
            printMenu();
            System.out.print("\nВаш вибір: ");
            String choice = scanner.nextLine();

            if (menuItems.containsKey(choice)) {
                Command command = menuItems.get(choice);
                command.execute();

                // Перевірка на вихід
                if (command instanceof ExitCommand) {
                    break;
                }
            } else {
                System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- ГОЛОВНЕ МЕНЮ ---");
        for (Map.Entry<String, Command> entry : menuItems.entrySet()) {
            System.out.printf("%3s. %s\n", entry.getKey(), entry.getValue().getDescription());
        }
    }
}