package com.student_managment.main;

import com.student_managment.model.Student;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class StudentGenerator {

    private static final AtomicLong idCounter = new AtomicLong(1);

    public static List<Student> generateStudents(int count) {
        List<Student> students = new ArrayList<>();
        Random rand = new Random();

        String[] lastNames = {"Шевченко", "Ковальчук", "Бондаренко", "Ткаченко", "Коваленко", "Кравченко"};
        String[] firstNames = {"Іван", "Петро", "Олександр", "Марія", "Олена", "Анна"};
        String[] patronymics = {"Іванович", "Петрович", "Олександрович", "Миколаївна", "Василівна", "Дмитрівна"};
        String[] faculties = {"Комп'ютерних наук", "Права", "Економіки", "Філології"};
        String[] groupsPrefix = {"КН", "ПР", "ЕК", "ФЛ"};
        String[] streets = {"вул. Хрещатик", "вул. Садова", "просп. Свободи", "вул. Лесі Українки"};

        for (int i = 0; i < count; i++) {
            String lastName = lastNames[rand.nextInt(lastNames.length)];
            String firstName = firstNames[rand.nextInt(firstNames.length)];
            String patronymic = patronymics[rand.nextInt(patronymics.length)];

            int year = 1998 + rand.nextInt(8); // Роки народження з 1998 по 2005
            int month = 1 + rand.nextInt(12);
            int day = 1 + rand.nextInt(28);
            LocalDate dateOfBirth = LocalDate.of(year, month, day);

            String address = streets[rand.nextInt(streets.length)] + ", " + (1 + rand.nextInt(150));
            String phone = "+380" + (500000000 + rand.nextInt(500000000));

            int facultyIndex = rand.nextInt(faculties.length);
            String faculty = faculties[facultyIndex];
            int course = 1 + rand.nextInt(5);
            String group = groupsPrefix[facultyIndex] + "-" + course + rand.nextInt(3);

            students.add(new Student(idCounter.getAndIncrement(), lastName, firstName, patronymic, dateOfBirth, address, phone, faculty, course, group));
        }
        return students;
    }
}
