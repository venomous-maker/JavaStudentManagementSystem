/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.studentmanagementsystem;
import java.io.Serializable;
/**
 *
 * @author v3n0m
 */
public class User implements Serializable {
    private String username;
    private String password;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    // Override toString() for better debugging/logging
    @Override
    public String toString() {
        return "User{" +
                "username='" + this.username + '\'' +
                ", password='" + this.password + '\'' +
                '}';
    }
}
