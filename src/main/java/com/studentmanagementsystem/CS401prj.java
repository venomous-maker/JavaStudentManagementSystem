/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.studentmanagementsystem;
import com.studentmanagementsystem.Interfaces.IStudentManager;
import com.studentmanagementsystem.Interfaces.IFileHandler;
import com.studentmanagementsystem.Interfaces.IDataBaseHandler;
import java.util.*;
/**
 *
 * @author Admin
 */
public class CS401prj {

    public static void Test(){
         // Example usage:
        Student[] students = new Student[2];
        
        // Create Grades objects for students
        Map<String, Double> gradesMap1 = new HashMap<>();
        gradesMap1.put("Math", 90.0);
        gradesMap1.put("Languages", 85.0);
        Grades grades1 = new Grades(gradesMap1);

        Map<String, Double> gradesMap2 = new HashMap<>();
        gradesMap2.put("Math", 88.0);
        gradesMap2.put("Languages", 92.0);
        Grades grades2 = new Grades(gradesMap2);

        // Create Date objects for DOB
        Calendar cal1 = Calendar.getInstance();
        cal1.set(1998, Calendar.MARCH, 15);
        Date dob1 = cal1.getTime();

        Calendar cal2 = Calendar.getInstance();
        cal2.set(1999, Calendar.JUNE, 20);
        Date dob2 = cal2.getTime();

        // Add students with grades and DOB
        try {
            students[0] = new Student("S001", "John", "Doe", "Computer Science", dob1, grades1);
            students[1] = new Student("S002", "Jane", "Smith", "Mathematics", dob2, grades2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Sort students by first name
        Comparator<Student> firstNameComparator = Comparator.comparing(Student::getFirstName);
        TimSort_<Student> timSort = new TimSort_<>(students, firstNameComparator);
        timSort.sort();

        // Print sorted students by first name
        System.out.println("Sorted students by first name:");
        for (Student student : students) {
            System.out.println(student);
        }

        // Sort students by last name
        Comparator<Student> lastNameComparator = Comparator.comparing(Student::getLastName);
        timSort = new TimSort_<>(students, lastNameComparator);
        timSort.sort();

        // Print sorted students by last name
        System.out.println("Sorted students by last name:");
        for (Student student : students) {
            System.out.println(student);
        
        }
        // Sort students by Mathematics grades
        Comparator<Student> mathGradesComparator = Comparator.comparing(Student::getTotal);
        timSort = new TimSort_<>(students, mathGradesComparator);
        timSort.sort();

        // Print sorted students by Mathematics grades
        System.out.println("Sorted students by Mathematics grades:");
        for (Student student : students) {
            System.out.println(student);
        }
    }
    public static void main(String[] args) {
        //Test();
        // Create a file handler object
        IFileHandler fileHandler = new FileHandler();
        IDataBaseHandler databaseHandler = new DatabaseHandler("root", "*123*VYBZ");
        // Create a student manager object
        IStudentManager studentManager = new StudentManager(fileHandler, databaseHandler, false);
        javax.swing.JFrame frame = new javax.swing.JFrame("Student Management");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); // Exit application when window is closed
        frame.setSize( 1000, 440); // Set initial size of the window

        // Create an instance of the AddStudent panel
        MainWindow mainWin = new MainWindow(studentManager);

        // Add the AddStudent panel to the frame
        frame.add( mainWin);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
        
        //fileHandler.saveToFile("students.dat", studentManager);
        // Load student details from a file
        try{
        IStudentManager loadedStudentManager = (StudentManager) fileHandler.loadFromFile("student.dat");
        loadedStudentManager.viewAllStudents();
        }
        catch(Exception e){
            
        }
        
    }
}
