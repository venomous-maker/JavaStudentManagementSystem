/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.studentmanagementsystem;

import java.util.Map;

/**
 *
 * @author Admin
 */
public interface IStudentManager {
    void addStudent(Student student) throws Exception;
    void updateStudent(String id, Student updatedStudent) throws Exception;
    void removeStudent(String id) throws Exception;
    Student getStudentById(String id) throws Exception;
    void viewAllStudents();
    public Map getAllStudents();
}
