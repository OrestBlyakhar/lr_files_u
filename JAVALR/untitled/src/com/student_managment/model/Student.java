package com.student_managment.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

enum Gender {
    MALE,
    FEMALE,
    UNKNOWN
}

public class Student {
    private long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate dateOfBirth;
    private String address;
    private String phone;
    private String faculty;
    private int course;
    private String group;

    // Приватний метод для визначення статі за по батькові
    private static Gender determineGenderByPatronymic(String patronymic) {
        if (patronymic.toLowerCase().endsWith("ович") || patronymic.toLowerCase().endsWith("йович")) {
            return Gender.MALE;
        }
        if (patronymic.toLowerCase().endsWith("івна") || patronymic.toLowerCase().endsWith("ївна")) {
            return Gender.FEMALE;
        }
        return Gender.UNKNOWN;
    }

    // Приватний метод для визначення статі за іменем (спрощена версія)
    private static Gender determineGenderByName(String firstName) {
        String lowerCaseName = firstName.toLowerCase();
        // Жіночі імена часто закінчуються на 'а', 'я'
        if (lowerCaseName.endsWith("а") || lowerCaseName.endsWith("я")) {
            // Винятки для чоловічих імен (Микита, Ілля, Микола)
            if (lowerCaseName.equals("микита") || lowerCaseName.equals("ілля") || lowerCaseName.equals("микола")) {
                return Gender.MALE;
            }
            return Gender.FEMALE;
        }
        return Gender.MALE; // В інших випадках припускаємо, що ім'я чоловіче
    }

    // Конструктор для створення об'єкта
    public Student(long id, String firstName, String lastName, String patronymic, LocalDate dateOfBirth, String address, String phone, String faculty, int course, String group) {
        // --- БЛОК ВАЛІДАЦІЇ ---
        Gender nameGender = determineGenderByName(firstName);
        Gender patronymicGender = determineGenderByPatronymic(patronymic);

        // Перевіряємо, тільки якщо обидві статі вдалося визначити
        if (nameGender != Gender.UNKNOWN && patronymicGender != Gender.UNKNOWN && nameGender != patronymicGender) {
            // Якщо стать імені та по батькові не збігаються, кидаємо виняток
            throw new IllegalArgumentException(
                    "Невідповідність статі: ім'я '" + firstName + "' (" + nameGender + ") " +
                            "не відповідає по батькові '" + patronymic + "' (" + patronymicGender + ")."
            );
        }
        // --- КІНЕЦЬ БЛОКУ ВАЛІДАЦІЇ ---

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phone = phone;
        this.faculty = faculty;
        this.course = course;
        this.group = group;
    }

    // Методи getValue() (гетери)
    public long getId() {return id;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getPatronymic() {return patronymic;}
    public LocalDate getDateOfBirth() {return dateOfBirth;}
    public String getAddress() {return address;}
    public String getPhone() {return phone;}
    public String getFaculty() {return faculty;}
    public int getCourse() {return course;}
    public String getGroup() {return group;}

    // Методи setValue() (сетери)
    public void setId(long id) {this.id = id;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setPatronymic(String patronymic) {this.patronymic = patronymic;}
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setAddress(String address) { this.address = address; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setFaculty(String faculty) { this.faculty = faculty; }
    public void setCourse(int course) { this.course = course; }
    public void setGroup(String group) { this.group = group; }

    // Метод toString() для виведення даних
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return "Student [id=" + id + "]\n" +
                "  Full name: " + lastName + " " + firstName + " " + patronymic + "\n" +
                "  Date of birth: " + dateOfBirth.format(formatter) + "\n" +
                "  Address: " + address + "\n" +
                "  Phone number: " + phone + "\n" +
                "  Faculty: " + faculty + ", Course: " + course + ", Group: " + group + "\n" +
                "------------------------------------";
    }
}