package com.jewelry.model;

public class SemiPreciousStone extends Stone {
    public SemiPreciousStone(String name, double weight, double cost, double transparency) {
        super(name, weight, cost, transparency);
    }

    @Override
    public String toString() {
        return "[Напівкоштовний] " + super.toString();
    }
}