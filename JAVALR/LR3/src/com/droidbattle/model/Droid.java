package com.droidbattle.model;

import java.util.List;
import java.util.Random;
// Додаємо імпорти для серіалізації
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

// Базовий клас Droid
public abstract class Droid implements Serializable {

    // Додаємо serialVersionUID для стабільності
    private static final long serialVersionUID = 1L;

    protected String name;
    protected int health;
    protected int maxHealth; // Для лікування та скидання
    protected int damage; // Це буде minDamage
    protected int maxDamage;

    // Позначаємо 'random' як transient, щоб воно не зберігалося у файл
    protected transient Random random = new Random();

    public Droid(String name, int health, int minDamage, int maxDamage) {
        this.name = name;
        this.maxHealth = health;
        this.health = health;
        this.damage = minDamage; // Зберігаємо min
        this.maxDamage = maxDamage; // Зберігаємо max
    }

    // Цей метод автоматично викличеться при завантаженні (десеріалізації)
    // Він відновить наше 'transient' поле
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Завантажує всі не-transient поля (name, health, etc.)
        random = new Random();  // Створюємо новий об'єкт Random
    }

    // Абстрактний метод, який кожен підклас реалізує по-своєму
    public abstract String attack(Droid target);

    // Метод для отримання шкоди
    public String takeDamage(int damage) {
        if (damage < 0) damage = 0;
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
        return String.format("%s отримав %d шкоди.", this.name, damage);
    }

    // Спеціальна дія (для HealerDroid)
    public String useSpecialAbility(List<Droid> allies, List<Droid> enemies) {
        // За замовчуванням, дроїди просто атакують
        Droid target = enemies.get(random.nextInt(enemies.size()));
        return attack(target);
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    // Гетери та Сетери
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public void resetHealth() { this.health = this.maxHealth; }

    @Override
    public String toString() {
        // Базовий toString, який буде доповнено в підкласах
        return String.format("'%s' (%s | ❤️ %d/%d | ⚔️ %d-%d)",
                name, this.getClass().getSimpleName(), health, maxHealth, damage, maxDamage);
    }
}