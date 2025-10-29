package com.droidbattle.main;

import com.droidbattle.model.*;
import com.droidbattle.service.BattleLog;
import com.droidbattle.service.BattleService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.stream.Collectors;

// Main-клас гри
public class DroidBattleMain {

    private static List<Droid> allDroids = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static BattleLog lastBattleLog; // Зберігаємо лог останнього бою

    private static final String DROID_SAVE_FILE = "droids.dat"; // .dat - data file

    public static void main(String[] args) {
        loadDroidsFromFile();

        while (true) {
            printMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    showDroids();
                    break;
                case 3:
                    start1v1Fight();
                    break;
                case 4:
                    startTeamFight();
                    break;
                case 5:
                    saveLastBattle();
                    break;
                case 6:
                    replayBattleFromFile();
                    break;
                case 7:
                    deleteDroid();
                    break;
                case 8:
                    saveDroidsToFile();
                    break;
                case 0:
                    System.out.println("Вихід з програми.");
                    return;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== МЕНЮ БИТВИ ДРОЇДІВ =====");
        System.out.println("1. Створити дроїда");
        System.out.println("2. Показати список створених дроїдів");
        System.out.println("3. Запустити бій 1 на 1");
        System.out.println("4. Запустити бій Команда на Команду");
        System.out.println("5. Записати останній бій у файл");
        System.out.println("6. Відтворити бій з файлу");
        System.out.println("7. Видалити дроїда");
        System.out.println("8. Зберегти дроїдів у файл");
        System.out.println("0. Вийти з програми");
        System.out.print("Ваш вибір: ");
    }

    private static void createDroid() {
        System.out.println("\n--- Створення дроїда ---");
        System.out.println("Оберіть тип дроїда:");
        System.out.println("1. Warrior (збалансований)");
        System.out.println("2. Tank (броньований)");
        System.out.println("3. Assassin (критична шкода)");
        System.out.println("4. Healer (лікує союзників)");
        System.out.print("Тип: ");
        int type = getIntInput();

        System.out.print("Введіть ім'я дроїда: ");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            System.out.println("Ім'я не може бути порожнім.");
            return;
        }

        Droid newDroid;
        switch (type) {
            case 1:
                newDroid = new WarriorDroid(name);
                break;
            case 2:
                newDroid = new TankDroid(name);
                break;
            case 3:
                newDroid = new AssassinDroid(name);
                break;
            case 4:
                newDroid = new HealerDroid(name);
                break;
            default:
                System.out.println("Невірний тип. Створення скасовано.");
                return;
        }
        allDroids.add(newDroid);
        System.out.println("Дроїд " + newDroid + " успішно створений!");
    }

    private static void showDroids() {
        System.out.println("\n--- Список ваших дроїдів ---");
        if (allDroids.isEmpty()) {
            System.out.println("У вас ще немає дроїдів.");
            return;
        }
        for (int i = 0; i < allDroids.size(); i++) {
            System.out.printf("%d. %s\n", i, allDroids.get(i));
        }
    }

    private static void start1v1Fight() {
        if (allDroids.size() < 2) {
            System.out.println("Потрібно щонайменше 2 дроїди для бою 1 на 1.");
            return;
        }
        System.out.println("\n--- Бій 1 на 1 ---");
        showDroids();
        System.out.print("Оберіть першого бійця (введіть номер): ");
        Droid droid1 = selectDroid();
        System.out.print("Оберіть другого бійця (введіть номер): ");
        Droid droid2 = selectDroid();

        if (droid1 == null || droid2 == null) {
            System.out.println("Помилка вибору дроїда.");
            return;
        }
        if (droid1 == droid2) {
            System.out.println("Дроїд не може битися сам з собою.");
            return;
        }

        // Скидуємо здоров'я перед боєм
        droid1.resetHealth();
        droid2.resetHealth();

        // Запускаємо бій
        lastBattleLog = new BattleLog();
        BattleService.fight1v1(droid1, droid2, lastBattleLog);
    }

    private static void startTeamFight() {
        if (allDroids.size() < 2) {
            System.out.println("Потрібно щонайменше 2 дроїди для командного бою.");
            return;
        }
        System.out.println("\n--- Командний бій ---");

        System.out.println("--- Формування Команди А ---");
        List<Droid> teamA = selectTeam();
        System.out.println("--- Формування Команди Б ---");
        List<Droid> teamB = selectTeam();

        if (teamA.isEmpty() || teamB.isEmpty()) {
            System.out.println("Обидві команди повинні мати хоча б одного дроїда.");
            return;
        }

        // Скидуємо здоров'я всім учасникам
        teamA.forEach(Droid::resetHealth);
        teamB.forEach(Droid::resetHealth);

        // Запускаємо бій
        lastBattleLog = new BattleLog();
        BattleService.fightTeam(teamA, teamB, lastBattleLog);
    }

    private static List<Droid> selectTeam() {
        List<Droid> team = new ArrayList<>();
        while (true) {
            System.out.println("Доступні дроїди:");
            showDroids();
            System.out.println("Поточна команда: " + team.stream().map(Droid::getName).collect(Collectors.joining(", ")));
            System.out.print("Введіть номер дроїда для додавання (або -1 для завершення): ");
            int index = getIntInput();

            if (index == -1) {
                break;
            }
            if (index >= 0 && index < allDroids.size()) {
                Droid selected = allDroids.get(index);
                if (!team.contains(selected)) {
                    team.add(selected);
                    System.out.println(selected.getName() + " доданий до команди.");
                } else {
                    System.out.println("Цей дроїд вже у команді.");
                }
            } else {
                System.out.println("Невірний номер.");
            }
        }
        return team;
    }

    private static Droid selectDroid() {
        int index = getIntInput();
        if (index >= 0 && index < allDroids.size()) {
            return allDroids.get(index);
        }
        return null;
    }

    private static void saveLastBattle() {
        if (lastBattleLog == null) {
            System.out.println("Ще не відбулося жодного бою для запису.");
            return;
        }
        System.out.print("Введіть ім'я файлу для запису (напр. 'battle.log'): ");
        String filename = scanner.nextLine();
        try {
            lastBattleLog.saveToFile(filename);
        } catch (IOException e) {
            System.out.println("Помилка! Не вдалося зберегти файл: " + e.getMessage());
        }
    }

    private static void replayBattleFromFile() {
        System.out.print("Введіть ім'я файлу для відтворення (напр. 'battle.log'): ");
        String filename = scanner.nextLine();
        try {
            BattleLog.replayFromFile(filename);
        } catch (IOException e) {
            System.out.println("Помилка! Не вдалося прочитати файл: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Відтворення перервано.");
        }
    }

    // Допоміжний метод для безпечного зчитування числа
    private static int getIntInput() {
        try {
            int input = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера
            return input;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Очистка буфера
            System.out.println("Помилка: введіть число.");
            return -99; // Повертаємо невірне значення
        }
    }

    // Завантажує список дроїдів з файлу при старті програми.
    private static void loadDroidsFromFile() {
        try (FileInputStream fis = new FileInputStream(DROID_SAVE_FILE);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            // Читаємо весь список дроїдів з файлу
            allDroids = (ArrayList<Droid>) ois.readObject();
            System.out.println("Дроїди успішно завантажені з файлу " + DROID_SAVE_FILE);

        } catch (FileNotFoundException e) {
            // Це не помилка, просто файл ще не створено
            System.out.println("Файл збереження не знайдено. Список дроїдів порожній.");
        } catch (IOException | ClassNotFoundException e) {
            // Інші помилки при завантаженні
            System.out.println("Помилка завантаження дроїдів: " + e.getMessage());
        }
    }

    // Зберігає поточний список дроїдів у файл.
    private static void saveDroidsToFile() {
        try (FileOutputStream fos = new FileOutputStream(DROID_SAVE_FILE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            // Записуємо весь список дроїдів у файл
            oos.writeObject(allDroids);
            System.out.println("Дроїди успішно збережені у файл " + DROID_SAVE_FILE);

        } catch (IOException e) {
            System.out.println("Помилка збереження дроїдів: " + e.getMessage());
        }
    }

    // Видаляє дроїда зі списку
    private static void deleteDroid() {
        System.out.println("\n--- Видалення дроїда ---");
        showDroids();
        if (allDroids.isEmpty()) {
            System.out.println("Немає дроїдів для видалення.");
            return;
        }

        System.out.print("Оберіть дроїда для видалення (введіть номер): ");
        int index = getIntInput();

        if (index >= 0 && index < allDroids.size()) {
            Droid removedDroid = allDroids.remove(index);
            System.out.println("Дроїд '" + removedDroid.getName() + "' був видалений.");
            System.out.println("Не забудьте зберегти зміни (пункт 8).");
        } else {
            System.out.println("Невірний номер.");
        }
    }
}