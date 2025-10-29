package com.droidbattle.model;

import java.util.Comparator;
import java.util.List;

public class HealerDroid extends Droid {

    // Діапазон лікування
    private int minHeal = 13;
    private int maxHeal = 25;

    public HealerDroid(String name) {
        super(name, 90, 8, 11); // Середнє здоров'я, слабка атака
    }

    @Override
    public String attack(Droid target) {
        // Атака - це крайній випадок
        int damageDealt = random.nextInt(maxDamage - this.damage + 1) + this.damage;
        String damageLog = target.takeDamage(damageDealt);
        return String.format("🩹 %s (Healer) слабко б'є %s. %s",
                this.name, target.getName(), damageLog);
    }

    // Головна логіка Healer'a
    @Override
    public String useSpecialAbility(List<Droid> allies, List<Droid> enemies) {
        // Знаходимо союзника з найменшим % здоров'я
        Droid targetToHeal = allies.stream()
                .filter(Droid::isAlive)
                .min(Comparator.comparingDouble(d -> (double) d.getHealth() / d.getMaxHealth()))
                .orElse(null);

        // Лікуємо, якщо є кого і він не повністю здоровий
        if (targetToHeal != null && targetToHeal.getHealth() < targetToHeal.getMaxHealth()) {

            // Генеруємо випадкове лікування в проміжку [13, 25]
            // Формула: random.nextInt(max - min + 1) + min
            int randomHealAmount = random.nextInt(maxHeal - minHeal + 1) + minHeal;

            // Перевіряємо, щоб не перелікувати (лікувати більше, ніж max HP)
            int actualHeal = Math.min(randomHealAmount, targetToHeal.getMaxHealth() - targetToHeal.getHealth());
            targetToHeal.health += actualHeal;

            return String.format("➕ %s (Healer) лікує %s на %d HP.",
                    this.name, targetToHeal.getName(), actualHeal);
        } else {
            // Якщо всі здорові, атакуємо
            return attack(enemies.get(random.nextInt(enemies.size())));
        }
    }

    @Override
    public String toString() {
        // Оновлюємо toString, щоб показував діапазон
        return super.toString() + String.format(" (Heal: %d-%d)", minHeal, maxHeal);
    }
}