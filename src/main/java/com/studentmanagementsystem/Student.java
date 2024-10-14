/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.studentmanagementsystem;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Student implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private Grades grades;
    private Date dob;
    private String department;
    
    // Constructor
    public Student(String id, String firstName, String lastName, String department, Date dob, Grades grades) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.grades = grades;
        this.department = department;
    }
    
    public Double getMath(){
        if(this.grades.get("Math") == null)
        {
            return 0.0;
        }
        return this.grades.get("Math");
    }
    
    public Double getLang(){
        
        if(this.grades.get("Languages") == null){
            return 0.0;
        }
        
        return this.grades.get("Languages");
    }
    
    public Double getTotal(){
        return this.getLang()+this.getMath();
    }
    
    public void setLastName(String lastName){
       this.lastName = lastName;
    }
    
    public String getLastName(){
        return this.lastName;
    }
    
    public void setDepartment(String department){
       this.department = department;
    }
    
    public String getDepartment(){
        return this.department;
    }
    
    public void setFirstName(String firstName){
       this.firstName = firstName;
    }
    
    public String getFirstName(){
        return this.firstName;
    }
    
    public void setGrades(Grades grades){
       this.grades = grades;
    }
    
    public Grades getGrades(){
        return this.grades;
    }
    
    public void setId(String id){
       this.id = id;
    }
    
    public String getId(){
        return this.id;
    }
    public void setDateOfBirth(Date date)
    {
        this.dob = date;
    }
    
    public Date getDateOfBirth()
    {
        return this.dob;
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + firstName +" "+lastName +'\'' +
                ", date of birth='" + dob + '\'' +
                ", grades=" + grades +'\'' +
                ", department='"+ department+'\''+
                '}';
    }
}
