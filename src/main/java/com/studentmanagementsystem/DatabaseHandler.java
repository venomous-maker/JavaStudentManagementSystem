/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.studentmanagementsystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHandler implements IDataBaseHandler {
    private String Password;
    private String UserName;
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/students", this.UserName, this.Password);
    }
    
    public DatabaseHandler(String UserName, String Password){
        this.Password = Password;
        this.UserName = UserName;
    }

    // Method to create tables if they don't exist
    private void createTablesIfNotExist() throws SQLException {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            // Create 'students' table
            String createStudentsTable = "CREATE TABLE IF NOT EXISTS students ("
                + "id VARCHAR(5) PRIMARY KEY, "
                + "first_name VARCHAR(100), "
                + "last_name VARCHAR(100), "
                + "department VARCHAR(100), "
                + "dob DATE)";
            statement.execute(createStudentsTable);

            // Create 'grades' table
            String createGradesTable = "CREATE TABLE IF NOT EXISTS grades ("
                + "id VARCHAR(5), "
                + "subject VARCHAR(100), "
                + "grade DOUBLE, "
                + "PRIMARY KEY (id), "
                + "FOREIGN KEY (id) REFERENCES students(id))";
            statement.execute(createGradesTable);
        }
    }

    @Override
    public void saveToDatabase(Student student) throws Exception {
        createTablesIfNotExist();  // Ensure tables are created before any operations
        try (Connection connection = getConnection()) {
            String studentSql = "INSERT INTO students (id, first_name, last_name, department, dob) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement studentStatement = connection.prepareStatement(studentSql);
            studentStatement.setString(1, student.getId());
            studentStatement.setString(2, student.getFirstName());
            studentStatement.setString(3, student.getLastName());
            studentStatement.setString(4, student.getDepartment());
            studentStatement.setDate(5, new java.sql.Date(student.getDateOfBirth().getTime()));
            studentStatement.executeUpdate();

            // Save grades
            String gradesSql = "INSERT INTO grades (id, subject, grade) VALUES (?, ?, ?)";
            PreparedStatement gradesStatement = connection.prepareStatement(gradesSql);
            for (Map.Entry<String, Double> entry : student.getGrades().getGradesMap().entrySet()) {
                gradesStatement.setString(1, student.getId());
                gradesStatement.setString(2, entry.getKey());
                gradesStatement.setDouble(3, entry.getValue());
                gradesStatement.addBatch();  // Add to batch for bulk insertion
            }
            gradesStatement.executeBatch();
        }
    }

    @Override
    public void updateStudentInDatabase(String id, Student updatedStudent) throws Exception {
        createTablesIfNotExist();  // Ensure tables exist before update
        try (Connection connection = getConnection()) {
            String sql = "UPDATE students SET first_name = ?, last_name = ?, department = ?, dob = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, updatedStudent.getFirstName());
            statement.setString(2, updatedStudent.getLastName());
            statement.setString(3, updatedStudent.getDepartment());
            statement.setDate(4, new java.sql.Date(updatedStudent.getDateOfBirth().getTime()));
            statement.setString(5, id);
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteFromDatabase(String id) throws Exception {
        createTablesIfNotExist();  // Ensure tables exist before deletion
        try (Connection connection = getConnection()) {
            String sql = "DELETE FROM students WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public Student loadStudentFromDatabase(String id) throws Exception {
        createTablesIfNotExist();  // Ensure tables exist before fetching data
        try (Connection connection = getConnection()) {
            String studentSql = "SELECT * FROM students WHERE id = ?";
            PreparedStatement studentStatement = connection.prepareStatement(studentSql);
            studentStatement.setString(1, id);
            ResultSet studentResultSet = studentStatement.executeQuery();

            if (studentResultSet.next()) {
                Student student = new Student(
                    studentResultSet.getString("id"),
                    studentResultSet.getString("first_name"),
                    studentResultSet.getString("last_name"),
                    studentResultSet.getString("department"),
                    studentResultSet.getDate("dob"),
                    loadGradesFromDatabase(id)  // Load grades separately
                );
                return student;
            } else {
                throw new Exception("Student with ID " + id + " not found.");
            }
        }
    }

    private Grades loadGradesFromDatabase(String studentId) throws Exception {
        createTablesIfNotExist();  // Ensure tables exist before fetching grades
        try (Connection connection = getConnection()) {
            String gradesSql = "SELECT * FROM grades WHERE id = ?";
            PreparedStatement gradesStatement = connection.prepareStatement(gradesSql);
            gradesStatement.setString(1, studentId);
            ResultSet gradesResultSet = gradesStatement.executeQuery();

            Map<String, Double> gradesMap = new HashMap<>();
            while (gradesResultSet.next()) {
                gradesMap.put(gradesResultSet.getString("subject"), gradesResultSet.getDouble("grade"));
            }
            return new Grades(gradesMap);
        }
    }

    @Override
    public Map<String, Student> loadAllStudentsFromDatabase() throws Exception {
        createTablesIfNotExist();  // Ensure tables exist before fetching all students
        Map<String, Student> studentMap = new HashMap<>();
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM students";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Student student = new Student(
                    resultSet.getString("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("department"),
                    resultSet.getDate("dob"),
                    loadGradesFromDatabase(resultSet.getString("id"))  // Load grades separately
                );
                studentMap.put(student.getId(), student);
            }
        }
        return studentMap;
    }
}