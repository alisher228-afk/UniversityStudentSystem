package system;

import enums.Enums;
import models.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class StudentsSystem {

    List<Student> students = new ArrayList<>();

    public void addStudent(Student student){
        students.add(student);
    }
    public void removeStudent(int id){
        students.removeIf(student -> student.getId() == id);
    }
    public void findById(int id){
        students.stream().filter(x -> x.getId() == id).findFirst().ifPresent(student -> System.out.println(student));
    }

    public double calculateAverageGPA() {
        return students.stream()
                .mapToDouble(Student::getGPA)
                .average()
                .orElse(0.0);
    }

    public void sortByGPA() {
        students.sort(Comparator.comparing(Student::getGPA).reversed());

    }
    public void filterByGPA(double minGPA) {
        students.stream()
                .filter(student -> student.getGPA() >= minGPA)
                .forEach(System.out::println);
    }
    public void filterByStatus(Enums.Status status) {
        students.stream()
                .filter(student -> student.getStatus() == status)
                .forEach(System.out::println);
    }




    public void displayAll(){
        students.forEach(System.out::println);
    }
}
