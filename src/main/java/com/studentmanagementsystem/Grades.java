/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.studentmanagementsystem;
import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Admin
 */

public class Grades implements Serializable {
    private Map<String, Double> gradesMap = new HashMap<>();

    // Constructor
    public Grades(Map<String, Double> gradesMap) {
        this.gradesMap = gradesMap;
    }

    // Getters and setters
    public Map<String, Double> getGradesMap() {
        return gradesMap;
    }

    public void setGradesMap(Map<String, Double> gradesMap) {
        this.gradesMap = gradesMap;
    }
    
    public Double get(String key)
    {
        return this.gradesMap.get(key);
    }

    @Override
    public String toString() {
        return "Grades{" +
                "gradesMap=" + gradesMap +
                '}';
    }
}
