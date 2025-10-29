package com.droidbattle.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

// Клас для запису/відтворення бою
public class BattleLog {
    private List<String> logEntries;
    private static final int BATTLE_DELAY_MS = 800; // Затримка 800 мс

    public BattleLog() {
        this.logEntries = new ArrayList<>();
    }

    public void add(String entry) {
        System.out.println(entry); // Одразу виводимо в консоль
        logEntries.add(entry);     // Зберігаємо в лог

        // Додаємо затримку після кожного виведення дії
        try {
            Thread.sleep(BATTLE_DELAY_MS);
        } catch (InterruptedException e) {
            // Якщо потік перервано (напр. користувачем), відновлюємо статус
            Thread.currentThread().interrupt();
            System.out.println("Бій було перервано.");
        }
    }

    // Метод для виведення статусу (без затримки)
    public void addWithoutDelay(String entry) {
        System.out.println(entry);
        logEntries.add(entry);
    }

    public void saveToFile(String filename) throws IOException {
        Path path = Paths.get(filename);
        Files.write(path, logEntries, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        System.out.println("Бій успішно записано у файл: " + filename);
    }

    public static void replayFromFile(String filename) throws IOException, InterruptedException {
        Path path = Paths.get(filename);
        if (!Files.exists(path)) {
            System.out.println("Помилка: Файл " + filename + " не знайдено.");
            return;
        }

        List<String> entries = Files.readAllLines(path);
        System.out.println("\n===== ВІДТВОРЕННЯ БОЮ З ФАЙЛУ: " + filename + " =====");
        for (String entry : entries) {
            System.out.println(entry);
            Thread.sleep(BATTLE_DELAY_MS); // Затримка для ефекту відтворення
        }
        System.out.println("===== КІНЕЦЬ ВІДТВОРЕННЯ =====");
    }
}