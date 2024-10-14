/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.studentmanagementsystem;
import com.studentmanagementsystem.Interfaces.IStudentManager;
import com.studentmanagementsystem.Interfaces.IFileHandler;
import com.studentmanagementsystem.Interfaces.IDataBaseHandler;
import java.util.*;

import java.util.concurrent.ThreadLocalRandom;
/**
 *
 * @author Morgan Okumu
 */
public class StudentManager implements IStudentManager {
    private Map<String, Student> studentMap = new HashMap<>();
    private IFileHandler fileHandler;
    private Map<String, User> registeredUsers = new HashMap<>(); // To keep track of registered users
    private User loggedInUser; // To keep track of the currently logged-in user
    private Boolean useFileMode = true;
    private IDataBaseHandler dbHandler; // Add database handler
    private final Object fileLock = new Object(); // Object for file access synchronization

    public StudentManager(IFileHandler fileHandler, IDataBaseHandler dbHandler, Boolean useFileMode) {
        this.fileHandler = fileHandler;
        this.dbHandler = dbHandler;
        this.useFileMode = useFileMode;
        // Load student data from file when the StudentManager is instantiated
         try {
            if (this.useFileMode) {
                loadStudentsFromFile();
                loadUsersFromFile(); // Load users from file
            } else {
                loadStudentsFromDatabase();
                loadUsersFromDatabase(); // Load users from database
            }
        } catch (Exception e) {
            //throw new Exception("Error loading data: " + e.getMessage());
        }
    }
    
    // Login action
    @Override
    public boolean login(String username, String password) throws Exception {
        if (registeredUsers.containsKey(username)) {
            User user =  registeredUsers.get(username);
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user; // Set the logged-in user
                return true; // Successful login
            }
        }
        throw new Exception("Login failed for user: " + username);
    }
    
    @Override
    public boolean isloggedIn(){
        return this.loggedInUser != null;
    }
    // Register a new user
    @Override
    public void registerUser(String username, String password) throws Exception {
        if (registeredUsers.containsKey(username)) {
            throw new Exception("Username already exists.");
        }
        registeredUsers.put(username,  new User(username, password));
        if(!this.useFileMode) this.dbHandler.saveUserToDatabase(username, password);
        else saveUsers(); // Save updated users after registration
        throw new Exception("User registered successfully: " + username);
    }
    
    // Logout action
    @Override
    // Logout action
    public void logout() throws Exception {
        if (loggedInUser != null) {
            String temp = loggedInUser.getUsername();
            loggedInUser = null; // Clear the logged-in user
            System.out.println("User logged out: " + temp);
        } else {
            throw new Exception("No user is currently logged in.");
        }
    }
    
    // Save users to file
    private void saveUsers() {
        synchronized (fileLock) {
            try {
                fileHandler.saveToFile("users.dat", registeredUsers); // Save registered users to file
            } catch (Exception e) {
                System.out.println("Error saving users to file: " + e.getMessage());
            }
        }
    }

    @Override
    public void addStudent(Student student) throws Exception{
        //studentMap.put(student.getId(), student);
        if (studentMap.containsKey(student.getId())) {
            throw new Exception("Student with ID " + student.getId() + " already registered.");
        } else {
            studentMap.put(student.getId(), student);   
            try{
                if (this.useFileMode) saveStudentsToFile();
                else saveStudentToDatabase(student);
            }
            catch
                    (Exception e){
                throw new Exception(e.getMessage());
            }
            
        }
    }
    
    // Load users from database
    private void loadUsersFromDatabase() {
        try {
            registeredUsers = dbHandler.loadAllUsersFromDatabase();
        } catch (Exception e) {
            System.out.println("Error loading users from database: " + e.getMessage());
        }
    }
    
    // Load users from file
    private void loadUsersFromFile() {
        synchronized (fileLock) {
            try {
                Map<String, User> loadedUsers = (Map<String, User>) fileHandler.loadFromFile("users.dat");
                if (loadedUsers != null) {
                    registeredUsers.putAll(loadedUsers);
                }
            } catch (Exception e) {
                System.out.println("Error loading users from file: " + e.getMessage());
            }
        }
    }

    // Save users to database (this should be called after registration)
    private void saveUsersToDatabase() {
        try {
            dbHandler.saveUsersToDatabase(registeredUsers);
        } catch (Exception e) {
            System.out.println("Error saving users to database: " + e.getMessage());
        }
    }

    @Override
    public void updateStudent(String id, Student updatedStudent) throws Exception{
        if (studentMap.containsKey(id)) {
            studentMap.put(id, updatedStudent);
            try{
            if (this.useFileMode) saveStudentsToFile();
            else updateStudentInDatabase(id, updatedStudent);
            }
            catch
                    (Exception e){
                throw new Exception(e.getMessage());
            }
        } else {
            throw new Exception("Student with ID " + id + " not found.");
        }
    }

    @Override
    public void removeStudent(String id) throws Exception{
        if (studentMap.containsKey(id)) {
            studentMap.remove(id);
            try{
            if (this.useFileMode) saveStudentsToFile();
            else deleteStudentFromDatabase(id);
            }
            catch
                    (Exception e){
                throw new Exception(e.getMessage());
            }
        } else {
            throw new Exception("Student with ID " + id + " not found.");
        }
    }

    @Override
    public Student getStudentById(String id) throws Exception{
        if(studentMap.containsKey(id))
            return studentMap.get(id);
        else
            throw new Exception("Student with ID " + id + " not found.");
    }
    
    @Override
    public Map getAllStudents()
    {
        try{
            //saveStudentsToFile();
            if (this.useFileMode) loadStudentsFromFile();
            else loadStudentsFromDatabase();
            }
            catch
                    (Exception e){
                //throw new Exception(e.getMessage());
            }
        return studentMap;
    }

    @Override
    public void viewAllStudents() {
        System.out.println("List of Students:");
        for (Student student : studentMap.values()) {
            System.out.println(student);
        }
    }
    
     // Method to save students to file
    private void saveStudentsToFile() {
        synchronized (fileLock) {
            try {
                fileHandler.saveToFile("student.dat", studentMap); // Save student map to file
            } catch (Exception e) {
                System.out.println("Error saving students to file: " + e.getMessage());
            }
        }
    }
    // Method to load students from file
    private void loadStudentsFromFile() {
        synchronized (fileLock) {
            try {
                Object data = fileHandler.loadFromFile("student.dat");
                if (data instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<String, Student> loadedData = (Map<String, Student>) data;
                    studentMap.putAll(loadedData); // Add loaded data to studentMap
                }
            } catch (Exception e) {
                System.out.println("Error loading students from file: " + e.getMessage());
            }
        }
    }
    
    // Method to save students to database
    private void saveStudentToDatabase(Student student) {
        try {
            dbHandler.saveToDatabase(student);
        } catch (Exception e) {
            System.out.println("Error saving student to database: " + e.getMessage());
        }
    }

    private void updateStudentInDatabase(String id, Student updatedStudent) {
        try {
            dbHandler.updateStudentInDatabase(id, updatedStudent);
        } catch (Exception e) {
            System.out.println("Error updating student in database: " + e.getMessage());
        }
    }

    private void deleteStudentFromDatabase(String id) {
        try {
            dbHandler.deleteFromDatabase(id);
        } catch (Exception e) {
            System.out.println("Error deleting student from database: " + e.getMessage());
        }
        
    }
    // Method to load students from the database
    private void loadStudentsFromDatabase() {
        try {
            Map<String, Student> loadedData = dbHandler.loadAllStudentsFromDatabase();
            studentMap.putAll(loadedData);
        } catch (Exception e) {
            System.out.println("Error loading students from database: " + e.getMessage());
        }
    }

}

class RandomGenerator {

    private static final String[] FIRST_NAMES = {"John", "Jane", "Michael", "Emily", "David", "Sarah", "Christopher", "Emma", "Daniel", "Olivia"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};
    private static final String[] DEPARTMENTS = {"Computer Science", "Mathematics", "Physics", "Biology", "Chemistry", "History", "English", "Engineering"};
    private static final String[] SUBJECTS = {"Math", "Languages"};

    public static void generateRandomStudents(IStudentManager studentManager, int count) {
        for (int i = 0; i < count; i++) {
            String id = generateRandomId();
            String firstName = generateRandomFirstName();
            String lastName = generateRandomLastName();
            String department = generateRandomDepartment();
            Date dob = generateRandomDateOfBirth();
            Grades grades = generateRandomGrades();
            
            Student student = new Student(id, firstName, lastName, department, dob, grades);
            try {
                studentManager.addStudent(student);
            } catch (Exception e) {
                System.out.println("Error adding student: " + e.getMessage());
            }
        }
    }

    private static String generateRandomId() {
        // Generate a random alphanumeric ID of length 5
        StringBuilder idBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            char randomChar = (char) ThreadLocalRandom.current().nextInt('A', 'Z' + 1);
            idBuilder.append(randomChar);
        }
        return idBuilder.toString();
    }

    private static String generateRandomFirstName() {
        // Select a random first name from the array
        return FIRST_NAMES[ThreadLocalRandom.current().nextInt(FIRST_NAMES.length)];
    }

    private static String generateRandomLastName() {
        // Select a random last name from the array
        return LAST_NAMES[ThreadLocalRandom.current().nextInt(LAST_NAMES.length)];
    }

    private static String generateRandomDepartment() {
        // Select a random department from the array
        return DEPARTMENTS[ThreadLocalRandom.current().nextInt(DEPARTMENTS.length)];
    }

    private static Date generateRandomDateOfBirth() {
        // Generate a random date of birth between 1980 and 2005
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, ThreadLocalRandom.current().nextInt(1980, 2006));
        calendar.set(Calendar.MONTH, ThreadLocalRandom.current().nextInt(0, 12));
        calendar.set(Calendar.DAY_OF_MONTH, ThreadLocalRandom.current().nextInt(1, 29));
        return calendar.getTime();
    }

    private static Grades generateRandomGrades() {
        // Generate random grades for each subject
        Map<String, Double> gradesMap = new HashMap<>();
        for (String subject : SUBJECTS) {
            double grade = ThreadLocalRandom.current().nextDouble(50, 101); // Random grade between 50 and 100
            gradesMap.put(subject, grade);
        }
        return new Grades(gradesMap);
    }
}
