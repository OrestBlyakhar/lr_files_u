package com.jewelry.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Necklace implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private List<Stone> stones;

    public Necklace(String name) {
        this.name = name;
        this.stones = new ArrayList<>();
    }

    public void addStone(Stone stone) { stones.add(stone); }
    public void removeStone(Stone stone) { stones.remove(stone); }
    public List<Stone> getStones() { return stones; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Намисто: '" + name + "' (Кількість каменів: " + stones.size() + ")";
    }
}