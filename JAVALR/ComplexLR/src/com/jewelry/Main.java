package com.jewelry;

import com.jewelry.ui.MenuController;
import com.jewelry.util.AppLogger;

public class Main {
    public static void main(String[] args) {
        AppLogger.info("=== ЗАПУСК ПРОГРАМИ ===");
        try {
            MenuController app = new MenuController();
            app.run();
        } catch (Exception e) {
            // Якщо впала вся програма - це критично!
            AppLogger.error("Критичний збій програми!", e);
        }
        AppLogger.info("=== ЗАВЕРШЕННЯ РОБОТИ ===");
    }
}