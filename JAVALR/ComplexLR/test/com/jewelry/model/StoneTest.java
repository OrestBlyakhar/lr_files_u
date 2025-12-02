package com.jewelry.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StoneTest {

    @Test
    void testValidStoneCreation() {
        Stone stone = new PreciousStone("Diamond", 1.5, 5000, 0.9);
        assertEquals("Diamond", stone.getName());
        assertEquals(1.5, stone.getWeightCarats());
        assertEquals(5000, stone.getCost());
        assertEquals(0.9, stone.getTransparency());
        assertTrue(stone.toString().contains("[Дорогоцінний]"));
    }

    @Test
    void testSemiPreciousString() {
        Stone stone = new SemiPreciousStone("Amber", 1.0, 100, 0.5);
        assertTrue(stone.toString().contains("[Напівкоштовний]"));
    }

    @Test
    void testInvalidNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new PreciousStone("", 1.0, 100, 0.5));
    }

    @Test
    void testInvalidWeightThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new PreciousStone("Ruby", 0, 100, 0.5));
    }

    @Test
    void testInvalidCostThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new PreciousStone("Ruby", 1.0, -10, 0.5));
    }

    @Test
    void testInvalidTransparencyThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new PreciousStone("Ruby", 1.0, 100, 1.1));
        assertThrows(IllegalArgumentException.class, () ->
                new PreciousStone("Ruby", 1.0, 100, -0.1));
    }
}