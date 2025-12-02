package com.jewelry.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NecklaceTest {

    @Test
    void testNecklaceOperations() {
        Necklace necklace = new Necklace("Test Necklace");
        Stone s1 = new PreciousStone("D", 1, 1, 0.5);

        assertEquals("Test Necklace", necklace.getName());
        assertTrue(necklace.getStones().isEmpty());

        necklace.addStone(s1);
        assertEquals(1, necklace.getStones().size());
        assertEquals(s1, necklace.getStones().get(0));

        necklace.removeStone(s1);
        assertTrue(necklace.getStones().isEmpty());

        assertNotNull(necklace.toString());
    }
}