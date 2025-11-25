package com.jewelry;

import com.jewelry.ui.MenuController;

public class Main {
    public static void main(String[] args) {
        // Створюємо контролер і передаємо йому керування
        MenuController app = new MenuController();
        app.run();
    }
}