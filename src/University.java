import enums.Enums;
import models.Student;
import dao.StudentDAO;

import java.util.Scanner;

public class University {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();
        while (true) {
            System.out.println("\n=== University Menu ===");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Display Student By ID");
            System.out.println("4. Update Student GPA");
            System.out.println("5. Remove Student");
            System.out.println("6. Exit");
            int choice = scr.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter name: ");
                    scr.nextLine();
                    String name = scr.nextLine();

                    System.out.print("Enter age: ");
                    int age = scr.nextInt();

                    System.out.print("Enter GPA: ");
                    double gpa = scr.nextDouble();

                    System.out.println("Choose Major: 1-CS 2-Math 3-Physics 4-Eng");
                    int m = scr.nextInt();
                    Enums.MajorType major = switch (m) {
                        case 1 -> Enums.MajorType.COMPUTER_SCIENCE;
                        case 2 -> Enums.MajorType.MATHEMATICS;
                        case 3 -> Enums.MajorType.PHYSICS;
                        case 4 -> Enums.MajorType.ENGINEERING;
                        default -> Enums.MajorType.COMPUTER_SCIENCE;
                    };

                    System.out.println("Status: 1-ACTIVE 2-GRADUATED 3-EXPELLED");
                    int s = scr.nextInt();
                    Enums.Status status = switch (s) {
                        case 1 -> Enums.Status.ACTIVE;
                        case 2 -> Enums.Status.GRADUATED;
                        default -> Enums.Status.EXPELLED;
                    };

                    dao.addStudent(new Student(0, name, age, gpa, major, status));
                }

                case 2 -> {
                    dao.getAllStudents().forEach(System.out::println);
                }
                case 3 ->{
                    System.out.println("Enter Student ID: ");
                    int id = scr.nextInt();
                    System.out.println(dao.getStudentById(id));
                }case 4 -> {
                    System.out.print("Enter Student ID: ");
                    int id = scr.nextInt();

                    System.out.print("Enter new GPA: ");
                    double newGPA = scr.nextDouble();

                    // Находим студента по ID
                    Student student = dao.getStudentById(id);
                    if (student != null) {
                        student.setGPA(newGPA);
                        dao.updateGPA(student);
                        System.out.println("✅ GPA updated successfully!");
                    } else {
                        System.out.println("⚠️ Student not found!");
                    }
                }


                case 5 -> {
                    System.out.print("Enter ID to remove: ");
                    int id = scr.nextInt();
                    dao.removeStudent(id);
                }
                case 6 -> {
                    System.out.println("Exit...");
                    System.exit(0);
                }

                default -> System.out.println("Invalid choice");
            }
        }
    }
}
