package com.jewelry.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileRepository<T> {
    private String filename;

    public FileRepository(String filename) {
        this.filename = filename;
    }

    public void save(List<T> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(data);
            System.out.println("Дані збережено у " + filename);
        } catch (IOException e) {
            System.out.println("Помилка збереження: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> load() {
        File file = new File(filename);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Помилка завантаження: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}