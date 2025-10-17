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

        // Незмінні прізвища (багато з них унісекс)
        String[] lastNames = {"Шевченко", "Ковальчук", "Бондаренко", "Ткаченко", "Коваленко", "Кравченко", "Мельник"};

        // --- РОЗДІЛЕНІ СПИСКИ ІМЕН ТА ПО БАТЬКОВІ ---
        String[] maleFirstNames = {"Іван", "Петро", "Олександр", "Андрій", "Дмитро", "Михайло", "Сергій"};
        String[] femaleFirstNames = {"Марія", "Олена", "Анна", "Тетяна", "Наталія", "Ольга", "Катерина"};

        String[] malePatronymics = {"Іванович", "Петрович", "Олександрович", "Андрійович", "Дмитрович", "Михайлович", "Сергійович"};
        String[] femalePatronymics = {"Іванівна", "Петрівна", "Олександрівна", "Андріївна", "Дмитрівна", "Михайлівнa", "Сергіївна"};
        String[] faculties = {"Комп'ютерних наук", "Права", "Економіки", "Філології"};
        String[] groupsPrefix = {"КН", "ПР", "ЕК", "ФЛ"};
        String[] streets = {"вул. Хрещатик", "вул. Садова", "просп. Свободи", "вул. Лесі Українки"};

        for (int i = 0; i < count; i++) {
            String firstName;
            String patronymic;
            String lastName = lastNames[rand.nextInt(lastNames.length)];

            // 1. Визначаємо стать майбутнього студента (50/50)
            boolean isMale = rand.nextBoolean();

            // 2. Залежно від статі, вибираємо ім'я та по батькові з відповідних списків
            if (isMale) {
                firstName = maleFirstNames[rand.nextInt(maleFirstNames.length)];
                patronymic = malePatronymics[rand.nextInt(malePatronymics.length)];
            } else { // isFemale
                firstName = femaleFirstNames[rand.nextInt(femaleFirstNames.length)];
                patronymic = femalePatronymics[rand.nextInt(femalePatronymics.length)];
            }

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

            students.add(new Student(idCounter.getAndIncrement(), firstName, lastName, patronymic, dateOfBirth, address, phone, faculty, course, group));
        }
        return students;
    }
}
