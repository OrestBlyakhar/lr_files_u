package com.jewelry.service;

import com.jewelry.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class NecklaceServiceTest {

    private NecklaceService service;
    private Necklace necklace;

    @BeforeEach
    void setUp() {
        service = new NecklaceService();
        necklace = new Necklace("Test");
        // Додаємо камені:
        // 1. Дешевий, важкий, каламутний
        necklace.addStone(new SemiPreciousStone("A", 10.0, 100.0, 0.2));
        // 2. Дорогий, легкий, прозорий
        necklace.addStone(new PreciousStone("B", 1.0, 1000.0, 0.9));
    }

    @Test
    void testCalculateTotalWeight() {
        // 10.0 + 1.0 = 11.0
        assertEquals(11.0, service.calculateTotalWeight(necklace), 0.001);
    }

    @Test
    void testCalculateTotalCost() {
        // 100.0 + 1000.0 = 1100.0
        assertEquals(1100.0, service.calculateTotalCost(necklace), 0.001);
    }

    @Test
    void testSortStonesByValue() {
        service.sortStonesByValue(necklace);
        List<Stone> stones = necklace.getStones();

        // Має бути від найдорожчого до найдешевшого
        assertEquals(1000.0, stones.get(0).getCost()); // B
        assertEquals(100.0, stones.get(1).getCost()); // A
    }

    @Test
    void testFindStonesByTransparency() {
        // Шукаємо прозорість від 0.8 до 1.0 (має знайти тільки B)
        List<Stone> result = service.findStonesByTransparency(necklace, 0.8, 1.0);

        assertEquals(1, result.size());
        assertEquals("B", result.get(0).getName());

        // Шукаємо діапазон, де нічого немає
        List<Stone> empty = service.findStonesByTransparency(necklace, 0.5, 0.6);
        assertTrue(empty.isEmpty());
    }
}