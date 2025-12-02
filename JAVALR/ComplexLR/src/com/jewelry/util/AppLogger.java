package com.jewelry.util;

import java.io.IOException;
import java.util.logging.*;

public class AppLogger {
    private static final Logger logger = Logger.getLogger("JewelryApp");
    private static FileHandler fileHandler;

    // Статичний блок ініціалізації (запускається один раз)
    static {
        try {
            // true = додавати в кінець файлу, а не перезаписувати
            fileHandler = new FileHandler("application.log", true);
            fileHandler.setFormatter(new SimpleFormatter()); // Простий текстовий формат
            logger.addHandler(fileHandler);

            // Вимикаємо вивід логів у консоль (щоб не засмічувати меню), або залишаємо
            logger.setUseParentHandlers(false);

        } catch (IOException e) {
            System.err.println("Не вдалося створити файл логів!");
        }
    }

    /**
     * Логування звичайних дій (Info) -> Тільки файл
     */
    public static void info(String message) {
        logger.info(message);
    }

    /**
     * Логування попереджень (Warning) -> Файл + Консоль
     */
    public static void warn(String message) {
        logger.warning(message);
        System.out.println("⚠️ [LOG] " + message);
    }

    /**
     * Логування КРИТИЧНИХ помилок (Severe) -> Файл + Email
     */
    public static void error(String message, Exception e) {
        logger.log(Level.SEVERE, message, e);

        // Формуємо текст для листа
        String emailBody = message + "\nException: " + e.toString();

        // Відправляємо на пошту
        EmailService.sendCriticalError(emailBody);
    }
}