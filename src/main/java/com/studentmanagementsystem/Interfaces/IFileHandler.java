/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.studentmanagementsystem.Interfaces;

/**
 *
 * @author Admin
 */
// Interface for file handling
public interface IFileHandler {
    String saveToFile(String filename, Object data);
    Object loadFromFile(String filename) throws Exception;
}
