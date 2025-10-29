package com.droidbattle.model;

public class AssassinDroid extends Droid {
    private static final double CRIT_CHANCE = 0.2; // 20% шанс крит. удару

    public AssassinDroid(String name) {
        // super(name, health, min_damage)
        // Зберігаємо min шкоду (23) у полі this.damage
        super(name, 70, 23, 31);
    }

    @Override
    public String attack(Droid target) {
        // 1. Генеруємо базову шкоду в діапазоні [23, 31]
        // this.damage містить 23 (min)
        int damageDealt = random.nextInt(maxDamage - this.damage + 1) + this.damage;

        String attackType = "атакує";

        // 2. Перевірка на критичний удар
        if (random.nextDouble() < CRIT_CHANCE) {
            damageDealt *= 2; // Подвоюємо вже випадкову базову шкоду
            attackType = "критично атакує";
        }

        // 3. Наносимо шкоду
        String damageLog = target.takeDamage(damageDealt);

        return String.format("🎯 %s (Assassin) %s %s. %s",
                this.name, attackType, target.getName(), damageLog);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (Crit: %.0f%%)", CRIT_CHANCE * 100);
    }
}