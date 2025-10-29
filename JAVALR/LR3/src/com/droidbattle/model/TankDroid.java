package com.droidbattle.model;

public class TankDroid extends Droid {
    private int minShield;
    private int maxShield;

    public TankDroid(String name) {
        // Багато здоров'я, мало шкоди, є щит
        super(name, 150, 10, 15);
        this.minShield = 5;  // Мінімальне поглинання
        this.maxShield = 20; // Максимальне поглинання
    }

    @Override
    public String attack(Droid target) {
        int damageDealt = random.nextInt(this.maxDamage - this.damage + 1) + this.damage; // 10-15 шкоди
        String damageLog = target.takeDamage(damageDealt);
        return String.format("🛡️ %s (Tank) завдає удару %s. %s",
                this.name, target.getName(), damageLog);
    }

    @Override
    public String takeDamage(int damage) {

        // Генеруємо випадкове поглинання щитом у діапазоні [5, 20]
        // Формула: random.nextInt(max - min + 1) + min
        int shieldBlock = random.nextInt(maxShield - minShield + 1) + minShield;

        // Щит поглинає частину шкоди
        int damageTaken = damage - shieldBlock;
        if (damageTaken < 0) {
            damageTaken = 0; // Щит поглинув усю шкоду
        }

        // Викликаємо базовий метод (який зменшує здоров'я)
        // з уже розрахованою шкодою
        String baseLog = super.takeDamage(damageTaken);

        // Додаємо до логу інформацію про заблоковану шкоду
        return baseLog + String.format(" (Щит заблокував %d)", shieldBlock);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (Shield: %d-%d)", minShield, maxShield);
    }
}