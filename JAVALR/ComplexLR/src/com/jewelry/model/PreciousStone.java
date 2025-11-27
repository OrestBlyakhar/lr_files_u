package com.jewelry.model;

public class PreciousStone extends Stone {
    public PreciousStone(String name, double weight, double cost, double transparency) {
        super(name, weight, cost, transparency);
    }

    @Override
    public String toString() {
        return "[Дорогоцінний]   " + super.toString();
    }
}