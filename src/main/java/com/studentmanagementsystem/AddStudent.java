/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.studentmanagementsystem;
import com.studentmanagementsystem.Interfaces.IStudentManager;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 *
 * @author Admin
 */
public class AddStudent extends javax.swing.JPanel {

    private IStudentManager studentManager;
    /**
     * Creates new form AddStudent
     */
    public AddStudent(IStudentManager studentManager) {
        this.studentManager = studentManager;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabelId = new javax.swing.JLabel();
        jTextId = new javax.swing.JTextField();
        jLabelFirstName = new javax.swing.JLabel();
        iTextFirstName = new javax.swing.JTextField();
        jLabelLastName = new javax.swing.JLabel();
        iTextLastName = new javax.swing.JTextField();
        jLabelDepartment = new javax.swing.JLabel();
        iTextDepartment = new javax.swing.JTextField();
        jLabelDateOfBirth = new javax.swing.JLabel();
        iTextDateOfBirth = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabelMathematics = new javax.swing.JLabel();
        iTextMathScore = new javax.swing.JTextField();
        jLabelLanguages = new javax.swing.JLabel();
        iTextLanguagesScore = new javax.swing.JTextField();
        jButtonSave = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        jLabel1.setText("ADD STUDENT");

        jLabelId.setText("ID");

        jTextId.setText("ID");

        jLabelFirstName.setText("First Name");

        iTextFirstName.setText("FirstName");

        jLabelLastName.setText("Last Name");

        iTextLastName.setText("Last Name");
        iTextLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iTextLastNameActionPerformed(evt);
            }
        });

        jLabelDepartment.setText("Department");

        iTextDepartment.setText("Department");

        jLabelDateOfBirth.setText("Date Of Birth");

        iTextDateOfBirth.setText("dd-MM-yyyy");

        jLabel7.setText("Grades");

        jLabelMathematics.setText("Mathematics");

        iTextMathScore.setText("Score");
        iTextMathScore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iTextMathScoreActionPerformed(evt);
            }
        });

        jLabelLanguages.setText("Languages");

        iTextLanguagesScore.setText("Score");
        iTextLanguagesScore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iTextLanguagesScoreActionPerformed(evt);
            }
        });

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelId)
                            .addComponent(jLabelFirstName)
                            .addComponent(jLabelLastName)
                            .addComponent(jLabelDepartment)
                            .addComponent(jLabelDateOfBirth)
                            .addComponent(jLabelMathematics)
                            .addComponent(jLabelLanguages))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextId)
                            .addComponent(iTextFirstName)
                            .addComponent(iTextLastName)
                            .addComponent(iTextDepartment, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                            .addComponent(iTextDateOfBirth)
                            .addComponent(iTextMathScore)
                            .addComponent(iTextLanguagesScore)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSave)
                        .addGap(60, 60, 60)))
                .addGap(128, 128, 128))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCancel)
                .addGap(52, 52, 52))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelId)
                    .addComponent(jTextId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFirstName)
                    .addComponent(iTextFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLastName)
                    .addComponent(iTextLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDepartment)
                    .addComponent(iTextDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDateOfBirth)
                    .addComponent(iTextDateOfBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMathematics)
                    .addComponent(iTextMathScore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLanguages)
                    .addComponent(iTextLanguagesScore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonCancel))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void iTextLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iTextLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_iTextLastNameActionPerformed

    private void iTextMathScoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iTextMathScoreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_iTextMathScoreActionPerformed

    private void iTextLanguagesScoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iTextLanguagesScoreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_iTextLanguagesScoreActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        // TODO add your handling code here:
        // Get the values entered by the user
        String id = jTextId.getText();
        String firstName = iTextFirstName.getText();
        String lastName = iTextLastName.getText();
        String department = iTextDepartment.getText();
        String dateOfBirth = iTextDateOfBirth.getText(); // You may need to parse this to Date format
        double mathScore = Double.parseDouble(iTextMathScore.getText());
        double languagesScore = Double.parseDouble(iTextLanguagesScore.getText());

        // Create a new Student object with the entered details
        Map<String, Double> gradesMap = new HashMap<>();
        gradesMap.put("Math", mathScore);
        gradesMap.put("Languages", languagesScore);
        Grades grades = new Grades(gradesMap);

        // Add the student to the student manager
        try {
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            studentManager.addStudent(new Student(id, firstName, lastName, department, dateFormat.parse(dateOfBirth), grades));
            // Close the window after saving
            javax.swing.JFrame topFrame = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
            topFrame.dispose(); // Close the window
        } catch (Exception e) {
            // Handle any exceptions (e.g., display an error message)
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        // TODO add your handling code here:
        javax.swing.JFrame topFrame = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
        topFrame.dispose(); // Close the window
    }//GEN-LAST:event_jButtonCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField iTextDateOfBirth;
    private javax.swing.JTextField iTextDepartment;
    private javax.swing.JTextField iTextFirstName;
    private javax.swing.JTextField iTextLanguagesScore;
    private javax.swing.JTextField iTextLastName;
    private javax.swing.JTextField iTextMathScore;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelDateOfBirth;
    private javax.swing.JLabel jLabelDepartment;
    private javax.swing.JLabel jLabelFirstName;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelLanguages;
    private javax.swing.JLabel jLabelLastName;
    private javax.swing.JLabel jLabelMathematics;
    private javax.swing.JTextField jTextId;
    // End of variables declaration//GEN-END:variables
}
