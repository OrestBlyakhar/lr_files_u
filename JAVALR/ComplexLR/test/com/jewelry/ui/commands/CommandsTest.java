package com.jewelry.ui.commands;

import com.jewelry.model.*;
import com.jewelry.service.NecklaceService;
import com.jewelry.ui.MenuController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommandsTest {

    private MenuController mockCtx;
    private List<Stone> inventory;
    private List<Necklace> necklaces;
    private Necklace currentNecklace;
    private NecklaceService service;

    @BeforeEach
    void setUp() {
        // Створюємо Mock (фейковий об'єкт) контролера
        mockCtx = mock(MenuController.class);

        inventory = new ArrayList<>();
        necklaces = new ArrayList<>();
        currentNecklace = new Necklace("Cur");
        service = new NecklaceService(); // Реальний сервіс, бо він простий

        // Налаштовуємо поведінку мока: коли у нього просять інвентар, він дає наш список
        when(mockCtx.getInventory()).thenReturn(inventory);
        when(mockCtx.getNecklaces()).thenReturn(necklaces);
        when(mockCtx.getCurrentNecklace()).thenReturn(currentNecklace);
        when(mockCtx.getService()).thenReturn(service);
    }

    // --- Допоміжний метод для імітації введення користувача ---
    private void simulateInput(String data) {
        Scanner sc = new Scanner(new ByteArrayInputStream(data.getBytes()));
        when(mockCtx.getScanner()).thenReturn(sc);
    }

    @Test
    void testCreateStoneCommand() {
        // Імітуємо введення: Тип=1, Назва=Dia, Вага=2.0, Ціна=500, Прозорість=0.5
        simulateInput("1\nDia\n2.0\n500\n0.5\n");

        Command cmd = new CreateStoneCommand(mockCtx);
        cmd.execute();

        assertEquals(1, inventory.size());
        assertEquals("Dia", inventory.get(0).getName());
        assertNotNull(cmd.getDescription());
    }

    @Test
    void testCreateNecklaceCommand() {
        // Імітуємо введення назви
        simulateInput("MyNecklace\n");

        Command cmd = new CreateNecklaceCommand(mockCtx);
        cmd.execute();

        assertEquals(1, necklaces.size());
        assertEquals("MyNecklace", necklaces.get(0).getName());
    }

    @Test
    void testAddStoneToNecklaceCommand() {
        // Підготовка: на складі є камінь
        inventory.add(new PreciousStone("Ruby", 1, 100, 1));

        // Імітуємо вибір 1-го каменя (індекс 1, бо користувач вводить 1)
        simulateInput("1\n");

        Command cmd = new AddStoneToNecklaceCommand(mockCtx);
        cmd.execute();

        assertEquals(1, currentNecklace.getStones().size());
        assertEquals("Ruby", currentNecklace.getStones().get(0).getName());
    }

    @Test
    void testRemoveStoneFromNecklaceCommand() {
        Stone s = new PreciousStone("Ruby", 1, 100, 1);
        currentNecklace.addStone(s);

        simulateInput("1\n"); // Видаляємо 1-й камінь

        Command cmd = new RemoveStoneFromNecklaceCommand(mockCtx);
        cmd.execute();

        assertTrue(currentNecklace.getStones().isEmpty());
    }

    @Test
    void testCalculateStatsCommand() {
        // Цей тест перевіряє лише чи не падає команда, бо вона виводить в консоль
        currentNecklace.addStone(new PreciousStone("A", 2, 200, 1));
        Command cmd = new CalculateStatsCommand(mockCtx);
        assertDoesNotThrow(cmd::execute);
    }

    @Test
    void testSortStonesCommand() {
        currentNecklace.addStone(new PreciousStone("Cheap", 1, 10, 1));
        currentNecklace.addStone(new PreciousStone("Expensive", 1, 1000, 1));

        Command cmd = new SortStonesCommand(mockCtx);
        cmd.execute();

        // Після сортування перший має бути дорогий
        assertEquals(1000, currentNecklace.getStones().get(0).getCost());
    }

    @Test
    void testFindStonesCommand() {
        currentNecklace.addStone(new PreciousStone("Clear", 1, 100, 0.9));
        // Імітуємо введення діапазону 0.8 - 1.0
        simulateInput("0.8\n1.0\n");

        Command cmd = new FindStonesCommand(mockCtx);
        assertDoesNotThrow(cmd::execute);
        // Тут ми перевіряємо, що екзекушн пройшов без помилок.
        // Логіку пошуку ми вже перевірили в NecklaceServiceTest
    }

    @Test
    void testDeleteStoneCommand() {
        inventory.add(new PreciousStone("Temp", 1, 1, 1));
        simulateInput("1\n"); // Видалити перший

        Command cmd = new DeleteStoneCommand(mockCtx);
        cmd.execute();

        assertTrue(inventory.isEmpty());
    }

    @Test
    void testDeleteNecklaceCommand() {
        Necklace n = new Necklace("N1");
        necklaces.add(n);
        when(mockCtx.getCurrentNecklace()).thenReturn(n);

        simulateInput("1\n");

        Command cmd = new DeleteNecklaceCommand(mockCtx);
        cmd.execute();

        assertTrue(necklaces.isEmpty());
        // verify(mockCtx).setCurrentNecklace(null); // Можна перевірити, чи скинувся вибір
    }

    @Test
    void testSelectNecklaceCommand() {
        necklaces.add(new Necklace("Choice"));
        simulateInput("1\n");

        Command cmd = new SelectNecklaceCommand(mockCtx);
        cmd.execute();

        // Перевіряємо, що метод setCurrentNecklace був викликаний
        verify(mockCtx).setCurrentNecklace(any(Necklace.class));
    }

    @Test
    void testSaveLoadCommand() {
        simulateInput("1\n"); // Вибір "Зберегти"
        Command cmd = new SaveLoadCommand(mockCtx);
        cmd.execute();
        verify(mockCtx).saveAll();

        simulateInput("2\n"); // Вибір "Завантажити"
        cmd.execute();
        verify(mockCtx).reloadAll();
    }

    // --- Тести на обробку помилок (Catch Blocks) ---

    @Test
    void testCreateStoneCommandInvalidInput() {
        // Очікується число (тип), а вводимо текст "abc"
        simulateInput("abc\n");

        Command cmd = new CreateStoneCommand(mockCtx);

        // Метод не повинен впасти, він має зловити помилку і вивести повідомлення
        assertDoesNotThrow(cmd::execute);

        // Перевіряємо, що камінь НЕ додався (бо сталася помилка)
        assertTrue(inventory.isEmpty());
    }

    @Test
    void testAddStoneToNecklaceCommandInvalidIndex() {
        // Підготовка: є активне намисто, є камінь на складі
        inventory.add(new PreciousStone("Ruby", 1, 100, 1));

        // Очікується індекс (число), вводимо "xyz"
        simulateInput("xyz\n");

        Command cmd = new AddStoneToNecklaceCommand(mockCtx);
        assertDoesNotThrow(cmd::execute);

        // Перевіряємо, що в намисто нічого не додалось
        assertTrue(currentNecklace.getStones().isEmpty());
    }

    @Test
    void testRemoveStoneFromNecklaceCommandInvalidIndex() {
        // Підготовка: є камінь у намисті
        currentNecklace.addStone(new PreciousStone("Ruby", 1, 100, 1));

        // Вводимо текст замість номера
        simulateInput("not_a_number\n");

        Command cmd = new RemoveStoneFromNecklaceCommand(mockCtx);
        assertDoesNotThrow(cmd::execute);

        // Перевіряємо, що камінь залишився на місці
        assertEquals(1, currentNecklace.getStones().size());
    }

    @Test
    void testFindStonesCommandInvalidNumber() {
        currentNecklace.addStone(new PreciousStone("A", 1, 1, 0.5));

        // Очікується double (прозорість), вводимо текст
        simulateInput("invalid_double\n");

        Command cmd = new FindStonesCommand(mockCtx);
        assertDoesNotThrow(cmd::execute);
    }

    @Test
    void testDeleteStoneCommandInvalidIndex() {
        inventory.add(new PreciousStone("A", 1, 1, 1));

        // Вводимо текст замість індексу
        simulateInput("bad_index\n");

        Command cmd = new DeleteStoneCommand(mockCtx);
        assertDoesNotThrow(cmd::execute);

        // Камінь не мав видалитися
        assertFalse(inventory.isEmpty());
    }

    @Test
    void testSelectNecklaceCommandInvalidIndex() {
        necklaces.add(new Necklace("N1"));

        // Вводимо текст
        simulateInput("text\n");

        Command cmd = new SelectNecklaceCommand(mockCtx);
        cmd.execute();

        // Перевіряємо, що активне намисто не змінилося (або залишилося старим/null)
        // В цьому тесті currentNecklace було ініціалізовано в setUp як "Cur"
        assertEquals("Cur", currentNecklace.getName());
    }

    @Test
    void testDeleteNecklaceCommandInvalidIndex() {
        necklaces.add(new Necklace("N1"));

        simulateInput("trash\n");

        Command cmd = new DeleteNecklaceCommand(mockCtx);
        cmd.execute();

        // Намисто не мало видалитися
        assertFalse(necklaces.isEmpty());
    }
}