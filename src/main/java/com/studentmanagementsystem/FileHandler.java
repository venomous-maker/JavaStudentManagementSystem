/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.studentmanagementsystem;
import com.studentmanagementsystem.Interfaces.IFileHandler;
import java.io.*;
/**
 *
 * @author Admin
 */
// FileHandler implementation for storing and retrieving student details from a file
public class FileHandler implements IFileHandler {

    @Override
    public String saveToFile(String filename, Object data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(data);
            return "Data saved to file: " + filename;
        } catch (IOException e) {
           return  "Error saving data to file: " + e.getMessage();
        }
    }

    @Override
    public Object loadFromFile(String filename) throws Exception{
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = ois.readObject();
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("Error loading data from file: " + e.getMessage());
        }
    }
}
