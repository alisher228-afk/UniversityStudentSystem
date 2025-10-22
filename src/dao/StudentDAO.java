package dao;

import database.DatabaseConnection;
import enums.Enums;
import models.Student;
import service.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO {

    public void addStudent(Student student) {
        String sql = "INSERT INTO students (name, age, gpa, major, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getAge());
            stmt.setDouble(3, student.getGPA());
            stmt.setString(4, student.getMajorType().name());
            stmt.setString(5, student.getStatus().name());
            stmt.executeUpdate();

            Log.logger.log(Level.INFO, "Student successfully added");
        } catch (SQLException e) {
            Log.logger.log(Level.WARNING, "Error " + e.getMessage());
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("gpa"),
                        Enums.MajorType.valueOf(rs.getString("major")),
                        Enums.Status.valueOf(rs.getString("status"))
                );
                students.add(s);
            }

        } catch (SQLException e) {
            Log.logger.log(Level.WARNING, "Error " + e.getMessage());
        }

        return students;
    }
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getDouble("gpa"),
                        Enums.MajorType.valueOf(rs.getString("major")),
                        Enums.Status.valueOf(rs.getString("status"))
                );
            } else {
                Log.logger.log(Level.INFO, "No student found with ID " + id);
            }

        } catch (SQLException e) {
            Log.logger.log(Level.WARNING, "Error " + e.getMessage());
        }
        return null;
    }


    public void removeStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) Log.logger.log(Level.INFO, "Student successfully removed");
            else Log.logger.log(Level.WARNING, "Student not found");
        } catch (SQLException e) {
            Log.logger.log(Level.WARNING, "Error " + e.getMessage());
        }
    }

    public void updateGPA(Student student){
        String sql = "UPDATE students SET gpa = ? WHERE id = ?";
        try(Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, student.getGPA());
            stmt.setInt(2, student.getId());
            int rows = stmt.executeUpdate();
            if (rows > 0) Log.logger.log(Level.INFO, "Student successfully updated");
            else Log.logger.log(Level.WARNING, "Student not found");
        }catch (SQLException e) {
            Log.logger.log(Level.WARNING, "Error " + e.getMessage());
        }
    }
}
