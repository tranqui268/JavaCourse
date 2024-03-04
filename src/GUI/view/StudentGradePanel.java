/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.view;

import BUS.StudentGradeBUS;
import DTO.CourseDTO;
import DTO.PersonDTO;
import DTO.StudentGradeDTO;
import GUI.ColorTheme;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicListUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author datly
 */
public class StudentGradePanel extends javax.swing.JPanel {

    ArrayList<PersonDTO> persons = new ArrayList<PersonDTO>();
    ArrayList<CourseDTO> courses = new ArrayList<CourseDTO>();
    ArrayList<StudentGradeDTO> studentGrades = new ArrayList<StudentGradeDTO>();
    JComboBox<String> jCourse = new JComboBox<String>();
    JComboBox<String> jStudent = new JComboBox<String>();
    JComboBox<String> jSearch = new JComboBox<String>();

    int id = 0;

    StudentGradeBUS studentGradeBus = new StudentGradeBUS();

    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Creates new form StudentGradePanel
     */
    public StudentGradePanel() throws SQLException {
        initComponents();

        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    // Lấy số thứ tự của hàng được chọn
                    int rowIndex = jTable1.getSelectedRow();
                    // Lấy dữ liệu từ hàng được chọn
                    if (rowIndex != -1) {
                        String enrollmentCell = jTable1.getValueAt(rowIndex, 0).toString();
                        id = Integer.parseInt(enrollmentCell);
                        String courseCell = jTable1.getValueAt(rowIndex, 1).toString();
                        String courseID = courseCell.split("\\[|\\]")[1];
                        String studentCell = jTable1.getValueAt(rowIndex, 2).toString();
                        String studentID = studentCell.split("\\[|\\]")[1];

                        String gradeCell = jTable1.getValueAt(rowIndex, 3).toString();
                        int i = 0, j = 0;
                        for (PersonDTO person : persons) {
                            if (person.getPersonID() == Integer.parseInt(studentID)) {
                                jStudent.setSelectedIndex(i);
                                break;
                            }
                            i++;
                        }

                        for (CourseDTO course : courses) {
                            if (course.getCourseID() == Integer.parseInt(courseID)) {
                                jCourse.setSelectedIndex(j);
                                break;
                            }
                            j++;
                        }

                        jTextField1.setText(gradeCell);
                    }

                }
            }
        });
        setBackground(Color.decode(ColorTheme.neutral));
        getData();
        initJStudent();
        initJCourse();
        initJSearch();
        loadTable(studentGrades);

    }

    public void initJSearch() {
        jSearch.setBounds(335, 17, 100, 22);
        String[] dataSearch = {"EnrollmentID", "StudentID", "CourseID", "StudentName", "CourseName", ">=Grade", "<Grade"};

        for (String item : dataSearch) {
            jSearch.addItem(item);
        }

        add(jSearch);
    }

    public void search() {
        String textSearch = jTextField2.getText();
        String searchSelected = jSearch.getSelectedItem().toString();

        if (textSearch.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui long nhap text search");
        } else {
            ArrayList<StudentGradeDTO> listSearch = new ArrayList<StudentGradeDTO>();
            switch (searchSelected) {
                case "EnrollmentID":
                    if (!isNumber(textSearch)) {
                        JOptionPane.showMessageDialog(this, "StudentID phai la so");
                        break;
                    }
                    for (StudentGradeDTO stdg : studentGrades) {
                        if (stdg.getEnrollmentID() == Integer.parseInt(textSearch)) {
                            listSearch.add(stdg);
                        }
                    }
                    break;
                case "StudentID":
                    if (!isNumber(textSearch)) {
                        JOptionPane.showMessageDialog(this, "StudentID phai la so");
                        break;
                    }
                    for (StudentGradeDTO stdg : studentGrades) {
                        if (stdg.getStudentID() == Integer.parseInt(textSearch)) {
                            listSearch.add(stdg);
                        }
                    }
                    break;
                case "CourseID":
                    if (!isNumber(textSearch)) {
                        JOptionPane.showMessageDialog(this, "StudentID phai la so");
                        break;
                    }
                    for (StudentGradeDTO stdg : studentGrades) {
                        if (stdg.getCourseID() == Integer.parseInt(textSearch)) {
                            listSearch.add(stdg);
                        }
                    }
                    break;
                case ">=Grade":
                    if (!isNumber(textSearch)) {
                        JOptionPane.showMessageDialog(this, "Grade phai la so");
                        break;
                    }
                    for (StudentGradeDTO stdg : studentGrades) {
                        double point = Double.parseDouble(textSearch);
                        if (stdg.getGrade() >= point) {
                            listSearch.add(stdg);
                        }
                    }
                    break;
                case "<Grade":
                    if (!isNumber(textSearch)) {
                        JOptionPane.showMessageDialog(this, "Grade phai la so");
                        break;
                    }
                    for (StudentGradeDTO stdg : studentGrades) {
                        double point = Double.parseDouble(textSearch);
                        if (stdg.getGrade() < point) {
                            listSearch.add(stdg);
                        }
                    }
                    break;
                case "StudentName":
                    for (StudentGradeDTO stdg : studentGrades) {
                        String studentName = stdg.getStudentFirstName() + " " + stdg.getStudentLastName();
                        if(studentName.indexOf(textSearch) != -1){
                            listSearch.add(stdg);
                        }
                    }
                    break;
                case "CourseName":
                    for (StudentGradeDTO stdg : studentGrades) {
                        if(stdg.getCourseName().indexOf(textSearch) != -1){
                            listSearch.add(stdg);
                        }
                    }
                    break;
            }
            loadTable(listSearch);

        }

    }

    public void loadTable(ArrayList<StudentGradeDTO> lists) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("EnrollmentID");
        model.addColumn("Course");
        model.addColumn("Student");
        model.addColumn("Grade");

        for (StudentGradeDTO stdg : lists) {
            model.addRow(new Object[]{stdg.getEnrollmentID(), "[" + stdg.getCourseID() + "] " + stdg.getCourseName(), "[" + stdg.getStudentID() + "] " + stdg.getStudentFirstName() + " " + stdg.getStudentLastName(), stdg.getGrade()});
        }

        jTable1.setModel(model);

    }

    public void initJCourse() {

        jCourse.setBounds(387, 55, 165, 22);

        for (CourseDTO course : courses) {
            String data = "[" + course.getCourseID() + "] " + course.getTitle();
            jCourse.addItem(data);
        }

        add(jCourse);
    }

    public void initJStudent() {

        jStudent.setBounds(118, 55, 165, 22);

        for (PersonDTO person : persons) {
            String data = "[" + person.getPersonID() + "] " + person.getFirstname() + " " + person.getLastname();
            jStudent.addItem(data);
        }

        add(jStudent);
    }

    public void getData() throws SQLException {
        persons = studentGradeBus.getPerson();
        courses = studentGradeBus.getCourse();
        studentGrades = studentGradeBus.getStudentGrade();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(774, 600));

        jLabel1.setText("Student");
        jLabel1.setName(""); // NOI18N

        jLabel2.setText("Course");

        jLabel3.setText("Grade");

        jButton2.setText("Thêm");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Cập nhật");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.setText("Xóa");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Reset");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jButton1.setText("Search");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(220, 220, 220)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(26, 26, 26)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        //add studentGrade
        String jStudentSelected = (String) jStudent.getSelectedItem();
        String studentID = jStudentSelected.split("\\[|\\]")[1];

        String jCourseSelected = (String) jCourse.getSelectedItem();
        String courseID = jCourseSelected.split("\\[|\\]")[1];

        for (StudentGradeDTO std : studentGrades) {
            if (std.getCourseID() == Integer.parseInt(courseID) && std.getStudentID() == Integer.parseInt(studentID)) {
                JOptionPane.showMessageDialog(this, "Course này của SV đã được nhập", "Thông báo validate", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        String grade = jTextField1.getText();
        if (!isNumber(grade)) {
            JOptionPane.showMessageDialog(this, "Grade phải là số", "Thông báo validate", JOptionPane.INFORMATION_MESSAGE);
        } else if (Double.parseDouble(grade) < 0) {
            JOptionPane.showMessageDialog(this, "Grade phải > 0", "Thông báo validate", JOptionPane.INFORMATION_MESSAGE);
        }

        StudentGradeDTO stdg = new StudentGradeDTO(0, Integer.parseInt(courseID), Integer.parseInt(studentID), Double.parseDouble(grade));
        try {
            String result = studentGradeBus.addStudentGrade(stdg);
            switch (result) {
                default:
                    JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, vui lòng thử lại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "success":
                    studentGrades = studentGradeBus.getStudentGrade();
                    loadTable(studentGrades);
                    resetForm();
                    break;
                case "require_courseID":
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn course", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "required_studentID":
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn student", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentGradePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2MouseClicked

    public void resetForm() {
        jTextField1.setText("");
        jStudent.setSelectedIndex(0);
        jCourse.setSelectedIndex(0);
    }
    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        // reset form
        resetForm();
        loadTable(studentGrades);
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        try {
            // TODO add your handling code here:
            //delete student grade

            String result = studentGradeBus.deleteStudentGrade(id);

            switch (result) {
                default:
                    JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, vui lòng thử lại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "require_enrollmentID":
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng cần xoá", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "success":
                    studentGrades = studentGradeBus.getStudentGrade();
                    loadTable(studentGrades);
                    resetForm();
                    id = 0;
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentGradePanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        //update student grade
        String jStudentSelected = (String) jStudent.getSelectedItem();
        String studentID = jStudentSelected.split("\\[|\\]")[1];

        String jCourseSelected = (String) jCourse.getSelectedItem();
        String courseID = jCourseSelected.split("\\[|\\]")[1];

        String grade = jTextField1.getText();
        if (!isNumber(grade)) {
            JOptionPane.showMessageDialog(this, "Grade phải là số", "Thông báo validate", JOptionPane.INFORMATION_MESSAGE);
        } else if (Double.parseDouble(grade) < 0) {
            JOptionPane.showMessageDialog(this, "Grade phải > 0", "Thông báo validate", JOptionPane.INFORMATION_MESSAGE);
        }

        StudentGradeDTO stdg = new StudentGradeDTO(id, Integer.parseInt(courseID), Integer.parseInt(studentID), Double.parseDouble(grade));
        try {
            String result = studentGradeBus.updateStudentGrade(stdg);
            switch (result) {
                default:
                    JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, vui lòng thử lại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "success":
                    studentGrades = studentGradeBus.getStudentGrade();
                    loadTable(studentGrades);
                    resetForm();
                    break;
                case "require_enrollmentID":
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng cần cập nhật", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    break;

            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentGradePanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton3MouseClicked

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        //search
        search();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        // TODO add your handling code here:
        //search
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            search();
        }
    }//GEN-LAST:event_jTextField2KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
