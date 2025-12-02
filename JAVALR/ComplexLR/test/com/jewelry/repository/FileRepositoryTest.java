package com.jewelry.repository;

import com.jewelry.model.Stone;
import com.jewelry.model.PreciousStone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileRepositoryTest {

    @Test
    void testSaveAndLoad(@TempDir Path tempDir) {
        // Створюємо файл у тимчасовій папці
        Path file = tempDir.resolve("test_stones.dat");
        FileRepository<Stone> repo = new FileRepository<>(file.toString());

        List<Stone> stones = new ArrayList<>();
        stones.add(new PreciousStone("TestGem", 1, 1, 1));

        // Зберігаємо
        repo.save(stones);

        // Завантажуємо
        List<Stone> loadedStones = repo.load();

        assertEquals(1, loadedStones.size());
        assertEquals("TestGem", loadedStones.get(0).getName());
    }

    @Test
    void testLoadNonExistentFile() {
        FileRepository<Stone> repo = new FileRepository<>("non_existent_file_xyz.dat");
        List<Stone> result = repo.load();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}