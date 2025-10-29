package com.droidbattle.model;

public class WarriorDroid extends Droid {
    public WarriorDroid(String name) {
        // Збалансовані характеристики
        super(name, 110, 20, 25);
    }

    @Override
    public String attack(Droid target) {
        int damageDealt = random.nextInt(this.maxDamage - this.damage + 1) + this.damage; // 20-25 шкоди

        // Викликаємо takeDamage і ЗБЕРІГАЄМО його результат
        String damageLog = target.takeDamage(damageDealt);

        return String.format("⚔️ %s (Warrior) атакує %s. %s",
                this.name, target.getName(), damageLog);
    }
}