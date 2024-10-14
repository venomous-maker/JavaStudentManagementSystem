/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.studentmanagementsystem.Interfaces;
import com.studentmanagementsystem.Student;
import com.studentmanagementsystem.User;
import java.util.*;
/**
 *
 * @author v3n0m
 */
public interface IDataBaseHandler {
    void saveToDatabase(Student student) throws Exception;
    void updateStudentInDatabase(String id, Student updatedStudent) throws Exception;
    void deleteFromDatabase(String id) throws Exception;
    Student loadStudentFromDatabase(String id) throws Exception;
    Map<String, Student> loadAllStudentsFromDatabase() throws Exception;
    void saveUserToDatabase(String username, String password) throws Exception;
    Map<String, User> loadAllUsersFromDatabase() throws Exception;
    void saveUsersToDatabase(Map<String, User> users) throws Exception;
}
