/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.studentmanagementsystem;
import com.studentmanagementsystem.Interfaces.IDataBaseHandler;
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
                + "id VARCHAR(100) PRIMARY KEY, "
                + "first_name VARCHAR(100), "
                + "last_name VARCHAR(100), "
                + "department VARCHAR(100), "
                + "dob DATE)";
            statement.execute(createStudentsTable);

            // Create 'grades' table
            String createGradesTable = "CREATE TABLE IF NOT EXISTS grades (" +
                "    id INT AUTO_INCREMENT, " +
                "    subject VARCHAR(100)," +
                "    grade DOUBLE," +
                "    student VARCHAR(100), " +
                "    PRIMARY KEY (id), " +
                "    FOREIGN KEY (student) REFERENCES students(id) ON DELETE CASCADE ON UPDATE CASCADE" +
                ")";
            statement.execute(createGradesTable);
            
            // Create 'users' table
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users ("
                    + "username VARCHAR(100) PRIMARY KEY, "
                    + "password VARCHAR(100))";
            statement.execute(createUsersTable);
        }
    }
    
     // Save a user to the database
    @Override
    public void saveUserToDatabase(String username, String password) throws Exception {
        try (Connection connection = getConnection()) {
            String userSql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement userStatement = connection.prepareStatement(userSql);
            userStatement.setString(1, username);
            userStatement.setString(2, password);
            userStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving user: " + e.getMessage());
            throw new Exception("Failed to save user: " + username);
        }
    }
    
    @Override
    public void saveUsersToDatabase(Map<String, User> users) throws Exception {
        try (Connection connection = getConnection()) {
            String userSql = "INSERT INTO users (username, password) VALUES (?, ?) ON DUPLICATE KEY UPDATE password = ?";  // Query to insert or update users
            PreparedStatement userStatement = connection.prepareStatement(userSql);

            // Iterate through the map and save each user to the database
            for (Map.Entry<String, User> entry : users.entrySet()) {
                String username = entry.getKey();
                String password = entry.getValue().getPassword();

                // Set values for the prepared statement
                userStatement.setString(1, username);
                userStatement.setString(2, password);
                userStatement.setString(3, password);  // In case of duplicate, update password

                userStatement.addBatch();  // Add to batch for bulk execution
            }

            // Execute the batch of insert/update commands
            userStatement.executeBatch();
        } catch (SQLException e) {
            System.out.println("Error saving users: " + e.getMessage());
            throw new Exception("Failed to save users to the database.");
        }
    }


    // Load a user by username
    @Override
    public Map<String, User> loadAllUsersFromDatabase() throws Exception {
        Map<String, User> usersMap = new HashMap<>();  // To store all users

        try (Connection connection = getConnection()) {
            String userSql = "SELECT * FROM users";  // Query to get all users
            Statement userStatement = connection.createStatement();
            ResultSet userResultSet = userStatement.executeQuery(userSql);

            // Iterate through the result set and populate the map
            while (userResultSet.next()) {
                String username = userResultSet.getString("username");
                String password = userResultSet.getString("password");

                // Create a new User object
                User user = new User(username, password);

                // Add user to the map with username as key
                usersMap.put(username, user);
            }
        } catch (SQLException e) {
            System.out.println("Error loading users: " + e.getMessage());
            throw new Exception("Failed to load users from the database.");
        }

        return usersMap;  // Return the map containing all users
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
            String gradesSql = "INSERT INTO grades (student, subject, grade) VALUES (?, ?, ?)";
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
            String gradesSql = "SELECT * FROM grades WHERE student = ?";
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