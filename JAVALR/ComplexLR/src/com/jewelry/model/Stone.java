package com.jewelry.model;

import java.io.Serializable;

public abstract class Stone implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private double weightCarats;
    private double cost;
    private double transparency; // 0.0 - 1.0

    public Stone(String name, double weightCarats, double cost, double transparency) {
        // --- ВАЛІДАЦІЯ ---
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва каменя не може бути порожньою!");
        }
        if (weightCarats <= 0) {
            throw new IllegalArgumentException("Вага має бути більшою за 0!");
        }
        if (cost < 0) {
            throw new IllegalArgumentException("Ціна не може бути від'ємною!");
        }
        if (transparency < 0.0 || transparency > 1.0) {
            throw new IllegalArgumentException("Прозорість має бути в межах від 0.0 до 1.0!");
        }
        // -----------------

        this.name = name;
        this.weightCarats = weightCarats;
        this.cost = cost;
        this.transparency = transparency;
    }

    public String getName() { return name; }
    public double getWeightCarats() { return weightCarats; }
    public double getCost() { return cost; }
    public double getTransparency() { return transparency; }

    @Override
    public String toString() {
        return String.format("%-15s | %.2f carats | $%.2f | Прозорість: %.2f", name, weightCarats, cost, transparency);
    }
}