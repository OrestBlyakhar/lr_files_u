package com.student_managment.main;

import com.student_managment.model.Student;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.concurrent.atomic.AtomicLong;

public class Main {

    private static List<Student> students = new ArrayList<>();
    private static final AtomicLong nextId = new AtomicLong(101); // Починаємо ID після згенерованих

    public static void main(String[] args) {
        // Створюємо масив об'єктів (100 записів)
        students.addAll(StudentGenerator.generateStudents(100));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера після nextInt()
                switch (choice) {
                    case 1:
                        addNewStudent(scanner);
                        break;
                    case 2:
                        deleteStudentById(scanner);
                        break;
                    case 3:
                        printAllStudents();
                        break;
                    case 4:
                        printAllStudentsByFaculty(scanner);
                        break;
                    case 5:
                        printAllStudentsBornAfterYear(scanner);
                        break;
                    case 6:
                        printAllStudentsByGroup(scanner);
                        break;
                    case 0:
                        System.out.println("Bye!");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                scanner.nextLine(); // Очистка буфера
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== STUDENT MANAGEMENT MENU =====");
        System.out.println("1. Add a new student");
        System.out.println("2. Delete a student by ID");
        System.out.println("3. Display the entire list of students");
        System.out.println("4. Display students of a given faculty");
        System.out.println("5. Display students born after a given year");
        System.out.println("6. Display students of a study group");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
    }

    private static void addNewStudent(Scanner scanner) {
        try{
            System.out.print("Enter Last Name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter First Name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter patronymic ");
            String patronymic = scanner.nextLine();
            System.out.print("Enter Date of Birth (yyyy-mm-dd): ");
            LocalDate dob = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter address: ");
            String address = scanner.nextLine();
            System.out.print("Enter Phone Number: ");
            String phone = scanner.nextLine();
            System.out.print("Enter Faculty: ");
            String faculty = scanner.nextLine();
            System.out.print("Enter Course: ");
            int course = scanner.nextInt();
            scanner.nextLine(); // очистка буфера
            System.out.print("Enter Group: ");
            String group = scanner.nextLine();

            students.add(new Student(nextId.getAndIncrement(), firstName, lastName, patronymic, dob, address, phone, faculty, course, group));
            System.out.println("Student has been added successfully!");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid course type!");
        } catch (IllegalArgumentException e) {
            // Ловимо помилку невідповідності статі і виводимо її повідомлення
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    private static void deleteStudentById(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        try{
            long idToDelete = scanner.nextLong();
            scanner.nextLine(); // Очистка буфера

            boolean removed = students.removeIf(student -> student.getId() == idToDelete);

            if (removed) {
                System.out.println("Student with ID=" + idToDelete + " has been deleted successfully!");
            } else  {
                System.out.println("Student with ID=" + idToDelete + " was not found!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid ID type!");
            scanner.nextLine(); // Очистка буфера
        }
    }

    private static void printAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Student list is empty!");
            return;
        }
        System.out.println("\n===== Student list =====");
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }

    /**
     *a) Вивести список студентів заданого факультету
     */
    private static void printAllStudentsByFaculty(Scanner scanner) {
        System.out.print("Enter Faculty: ");
        String faculty = scanner.nextLine();
        System.out.println("\n===== Students of Faculty '" + faculty + "' =====");

        int count = 0;
        for (Student student : students) {
            if (student.getFaculty().equalsIgnoreCase(faculty)) {
                System.out.println(student.toString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Faculty '" + faculty + "' was not found!");
        }
    }

    /**
     * b) Вивести список студентів, які народились після заданого року
     */
    private static void printAllStudentsBornAfterYear(Scanner scanner) {
        System.out.print("Enter year of Birth: ");
        try{
            int year = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера
            System.out.println("\n===== Student Born After" + year + " =====");

            int count = 0;
            for (Student student : students) {
                if(student.getDateOfBirth().getYear() > year) {
                    System.out.println(student.toString());
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("Students that were born after" + year + " was not found!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid year of birth type!");
            scanner.nextLine(); // Очистка буфера
        }
    }

    /**
     * c) Вивести список навчальної групи
     */
    private static void printAllStudentsByGroup(Scanner scanner) {
        System.out.print("Enter Group: ");
        String group = scanner.nextLine();
        System.out.println("\n===== Students of Group '" + group + "' =====");

        int count = 0;
        for (Student student : students) {
            if (student.getGroup().equalsIgnoreCase(group)) {
                System.out.println(student.toString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Group '" + group + "' was not found!");
        }
    }
}
