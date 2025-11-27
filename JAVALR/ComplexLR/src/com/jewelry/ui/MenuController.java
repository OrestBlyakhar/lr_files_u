package com.jewelry.ui;

import com.jewelry.model.*;
import com.jewelry.repository.FileRepository;
import com.jewelry.service.NecklaceService;
import com.jewelry.ui.commands.*;
import java.util.*;

public class MenuController {

    // Стан
    private List<Stone> inventory;
    private List<Necklace> necklaces;
    private Necklace currentNecklace;

    // Компоненти
    private FileRepository<Stone> stoneRepo;
    private FileRepository<Necklace> necklaceRepo;
    private NecklaceService necklaceService;
    private Map<String, Command> menuItems;
    private Scanner scanner;

    public MenuController() {
        this.scanner = new Scanner(System.in);
        this.menuItems = new LinkedHashMap<>();

        // Ініціалізація
        this.stoneRepo = new FileRepository<>("stones.dat");
        this.necklaceRepo = new FileRepository<>("necklaces.dat");
        this.necklaceService = new NecklaceService();

        // Завантаження або створення нових списків
        this.inventory = stoneRepo.load();
        this.necklaces = necklaceRepo.load();

        initCommands();
    }

    private void initCommands() {
        // Реєструємо всі 14 команд + вихід
        menuItems.put("1", new CreateStoneCommand(this));
        menuItems.put("2", new ViewInventoryCommand(this));
        menuItems.put("3", new DeleteStoneCommand(this));
        menuItems.put("4", new CreateNecklaceCommand(this));
        menuItems.put("5", new ViewNecklacesCommand(this));
        menuItems.put("6", new SelectNecklaceCommand(this));
        menuItems.put("7", new DeleteNecklaceCommand(this));
        menuItems.put("8", new ViewActiveNecklaceCommand(this));
        menuItems.put("9", new AddStoneToNecklaceCommand(this));
        menuItems.put("10", new RemoveStoneFromNecklaceCommand(this));
        menuItems.put("11", new CalculateStatsCommand(this));
        menuItems.put("12", new SortStonesCommand(this));
        menuItems.put("13", new FindStonesCommand(this));
        menuItems.put("14", new SaveLoadCommand(this));
        menuItems.put("99", new GenerateTestDataCommand(this));
        menuItems.put("0", new ExitCommand(this));
    }

    public void run() {
        System.out.println("=== ЮВЕЛІРНА МАЙСТЕРНЯ v2.0 ===");
        while (true) {
            printMenu();
            System.out.print("\nВаш вибір: ");
            String choice = scanner.nextLine();
            if (menuItems.containsKey(choice)) {
                menuItems.get(choice).execute();
            } else {
                System.out.println("Невірний вибір.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--------------------------------");
        System.out.println("АКТИВНЕ НАМИСТО: " + (currentNecklace != null ? currentNecklace.getName() : "[НЕ ВИБРАНО]"));
        System.out.println("--------------------------------");
        for (Map.Entry<String, Command> entry : menuItems.entrySet()) {
            System.out.printf("%3s. %s\n", entry.getKey(), entry.getValue().getDescription());
        }
    }

    public void saveAll() {
        stoneRepo.save(inventory);
        necklaceRepo.save(necklaces);
    }

    public void reloadAll() {
        this.inventory = stoneRepo.load();
        this.necklaces = necklaceRepo.load();
        this.currentNecklace = null;
    }

    // Гетери для команд
    public List<Stone> getInventory() { return inventory; }
    public List<Necklace> getNecklaces() { return necklaces; }
    public Necklace getCurrentNecklace() { return currentNecklace; }
    public void setCurrentNecklace(Necklace n) { this.currentNecklace = n; }
    public NecklaceService getService() { return necklaceService; }
    public Scanner getScanner() { return scanner; }
}