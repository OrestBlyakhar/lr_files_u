package com.jewelry.service;

import com.jewelry.model.Necklace;
import com.jewelry.model.Stone;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class NecklaceService {

    public double calculateTotalWeight(Necklace necklace) {
        return necklace.getStones().stream().mapToDouble(Stone::getWeightCarats).sum();
    }

    public double calculateTotalCost(Necklace necklace) {
        return necklace.getStones().stream().mapToDouble(Stone::getCost).sum();
    }

    public void sortStonesByValue(Necklace necklace) {
        // Сортування від найдорожчого до найдешевшого
        necklace.getStones().sort(Comparator.comparing(Stone::getCost).reversed());
    }

    public List<Stone> findStonesByTransparency(Necklace necklace, double min, double max) {
        return necklace.getStones().stream()
                .filter(s -> s.getTransparency() >= min && s.getTransparency() <= max)
                .collect(Collectors.toList());
    }
}