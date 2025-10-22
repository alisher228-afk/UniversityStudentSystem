package service;
import dao.StudentDAO;
import java.util.List;
import models.Student;
import java.util.logging.Logger;

public class StudentService {
    private StudentDAO studentDAO = new StudentDAO();

    public void addStudent(Student student) {
        try {
            studentDAO.addStudent(student);
            System.out.println("Student added successfully");
        }catch(Exception e) {
            System.out.println("Error cant add"+e.getMessage());
        }
    }
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }
    public void removeStudent(int id) {
        try {
            studentDAO.removeStudent(id);
        }catch(Exception e) {
            System.out.println("Error cant remove "+e.getMessage());
        }
    }
}
