/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.view;

import BUS.TeachingScheduleBUS;
import DTO.CourseDTO;
import DTO.PersonDTO;
import DTO.TeachingScheduleDTO;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author haidu
 */
public final class TeachingSchedulePanel extends javax.swing.JPanel {

    /**
     * Creates new form TeachingSchedulePanel
     */
    public JComboBox<String> cbbInstructor, cbbCourse;
    TeachingScheduleBUS tcsdBUS = new TeachingScheduleBUS();
    ArrayList<TeachingScheduleDTO> listTeachingSchedule = new ArrayList<TeachingScheduleDTO>();
    ArrayList<PersonDTO> listInstructor = new ArrayList<PersonDTO>();
    ArrayList<CourseDTO> listCourse = new ArrayList<CourseDTO>();

    boolean activeTableCheck = false;
    String courseIDCell, instructorIDCell;

    public TeachingSchedulePanel() {
        getData();
        initCbbInstructor();
        initCbbDepartment();
        initComponents();
        loadTable(listTeachingSchedule);
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    // Lấy số thứ tự của hàng được chọn
                    int rowIndex = jTable1.getSelectedRow();
                    // Lấy dữ liệu từ hàng được chọn
                    if (rowIndex != -1) {
                        activeTableCheck = true;
                        courseIDCell = jTable1.getValueAt(rowIndex, 2).toString();
                        instructorIDCell = jTable1.getValueAt(rowIndex, 0).toString();
                        int i = 0, j = 0;
                        for (PersonDTO person : listInstructor) {
                            if (person.getPersonID() == Integer.parseInt(instructorIDCell)) {
                                cbbInstructor.setSelectedIndex(i);
                                break;
                            }
                            i++;
                        }
                        for (CourseDTO course : listCourse) {
                            if (course.getCourseID() == Integer.parseInt(courseIDCell)) {
                                cbbCourse.setSelectedIndex(j);
                                break;
                            }
                            j++;
                        }
                    }

                }
            }
        });
        jTable1.setAutoCreateRowSorter(true);
    }

    public void getData() {
        try {
            listInstructor = tcsdBUS.getInstructor();
            listCourse = tcsdBUS.getCourse();
            listTeachingSchedule = tcsdBUS.getTeachingSchedule();
        } catch (SQLException ex) {
            Logger.getLogger(TeachingSchedulePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadTable(ArrayList<TeachingScheduleDTO> lists) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("InstructorID");
        model.addColumn("Instructor");
        model.addColumn("CourseID");
        model.addColumn("Title");

        for (TeachingScheduleDTO tcsd : lists) {
            model.addRow(new Object[]{tcsd.getInstructorID(), tcsd.getName(), tcsd.getCourseID(), tcsd.getTitle()});
        }

        jTable1.setModel(model);

    }

    public void initCbbInstructor() {
        cbbInstructor = new JComboBox<String>();
        for (PersonDTO instructor : listInstructor) {
            String str = "[" + instructor.getPersonID() + "] " + instructor.getFirstname() + " " + instructor.getLastname();
            cbbInstructor.addItem(str);
        }
        cbbInstructor.setBounds(120, 58, 165, 22);
        this.add(cbbInstructor);
    }

    public void initCbbDepartment() {
        cbbCourse = new JComboBox<String>();
        for (CourseDTO course : listCourse) {
            String str = "[" + course.getCourseID() + "] " + course.getTitle();
            cbbCourse.addItem(str);
        }
        cbbCourse.setBounds(380, 58, 165, 22);
        this.add(cbbCourse);
    }

    public void resetForm() {
        cbbInstructor.setSelectedIndex(0);
        cbbCourse.setSelectedIndex(0);
        jTextField2.setText("");
    }
    public void search(){
        String textSearch = jTextField2.getText();
        ArrayList<TeachingScheduleDTO> list =new ArrayList<TeachingScheduleDTO>();
        for(TeachingScheduleDTO tc: listTeachingSchedule){
            String courseID= tc.getCourseID()+"";
            String instructorID= tc.getInstructorID()+"";
            String name= tc.getName()+"";
            String title= tc.getTitle()+"";
            if(courseID.contains(textSearch) || instructorID.contains(textSearch)
                    || name.contains(textSearch) || title.contains(textSearch)){
                list.add(tc);
            }
        }
        loadTable(list);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(774, 600));

        jPanel1.setPreferredSize(new java.awt.Dimension(774, 600));

        jLabel1.setText("Instructor");
        jLabel1.setName(""); // NOI18N

        jLabel2.setText("Course");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel1)
                .addGap(220, 220, 220)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 774, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        //add
        if (!activeTableCheck) {
            String cbbInstructorSelected = (String) cbbInstructor.getSelectedItem();
            String InstructorID = cbbInstructorSelected.split("\\[|\\]")[1];
            String name = cbbInstructorSelected.split("\\[|\\]")[2];

            String cbbCourseSelected = (String) cbbCourse.getSelectedItem();
            String courseID = cbbCourseSelected.split("\\[|\\]")[1];
            String title = cbbCourseSelected.split("\\[|\\]")[2];

            for (TeachingScheduleDTO std : listTeachingSchedule) {
                if (std.getCourseID() == Integer.parseInt(courseID) && std.getInstructorID() == Integer.parseInt(InstructorID)) {
                    JOptionPane.showMessageDialog(this, "Course này của giảng viên đã được nhập", "Thông báo validate", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }

            TeachingScheduleDTO tcsd = new TeachingScheduleDTO(Integer.parseInt(InstructorID), Integer.parseInt(courseID), name, title);
            try {
                String result = tcsdBUS.addTeachingSchedule(tcsd);
                switch (result) {
                    default:
                        JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, vui lòng thử lại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "success":
                        listTeachingSchedule.add(tcsd);
                        activeTableCheck = false;
                        loadTable(listTeachingSchedule);
                        resetForm();
                        JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "require_courseID":
                        JOptionPane.showMessageDialog(this, "Vui lòng chọn course", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "required_instructorID":
                        JOptionPane.showMessageDialog(this, "Vui lòng chọn instructor", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(StudentGradePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
        JOptionPane.showMessageDialog(this, "Hãy nhấn nút reset để thêm mới", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        //update      
        if (activeTableCheck) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa thông tin này", "Thông báo",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String cbbInstructorSelected = (String) cbbInstructor.getSelectedItem();
                String InstructorID = cbbInstructorSelected.split("\\[|\\]")[1];
                String name = cbbInstructorSelected.split("\\[|\\]")[2];

                String cbbCourseSelected = (String) cbbCourse.getSelectedItem();
                String courseID = cbbCourseSelected.split("\\[|\\]")[1];
                String title = cbbCourseSelected.split("\\[|\\]")[2];
                for (TeachingScheduleDTO std : listTeachingSchedule) {
                    if (std.getCourseID() == Integer.parseInt(courseID) && std.getInstructorID() == Integer.parseInt(InstructorID)) {
                        JOptionPane.showMessageDialog(this, "Course này của giảng viên đã có", "Thông báo validate", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
                TeachingScheduleDTO tcsd = new TeachingScheduleDTO(Integer.parseInt(InstructorID), Integer.parseInt(courseID), name, title);
                try {
                    String result = tcsdBUS.updateTeachingSchedule(tcsd, courseIDCell, instructorIDCell);
                    switch (result) {
                        default:
                            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, vui lòng thử lại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "success":
                            for (TeachingScheduleDTO std : listTeachingSchedule) {
                                if (std.getCourseID() == Integer.parseInt(courseIDCell) && std.getInstructorID() == Integer.parseInt(instructorIDCell)) {
                                    std.setCourseID(tcsd.getCourseID());
                                    std.setInstructorID(tcsd.getInstructorID());
                                    std.setName(tcsd.getName());
                                    std.setTitle(tcsd.getTitle());
                                    break;
                                }
                            }
                            activeTableCheck = false;
                            loadTable(listTeachingSchedule);
                            resetForm();
                            JOptionPane.showMessageDialog(this, "Sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            break;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(StudentGradePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // no option
            }
            return;
        }
        JOptionPane.showMessageDialog(this, "Hãy chọn 1 dòng dưới bảng để sửa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked


    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        //delete
        if (activeTableCheck) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa thông tin này", "Thông báo",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                // yes option
                try {
                    // TODO add your handling code here:
                    //delete student grade

                    String result = tcsdBUS.deleteTeachingSchedule(courseIDCell, instructorIDCell);

                    switch (result) {
                        default:
                            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, vui lòng thử lại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case "success":
                            for (TeachingScheduleDTO std : listTeachingSchedule) {
                                if (std.getCourseID() == Integer.parseInt(courseIDCell) && std.getInstructorID() == Integer.parseInt(instructorIDCell)) {
                                    listTeachingSchedule.remove(std);
                                    break;
                                }
                            }
                            activeTableCheck = false;
                            loadTable(listTeachingSchedule);
                            resetForm();
                            JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            break;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(StudentGradePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // no option
            }
            return;
        }
        JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng cần xoá", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        //reset
        resetForm();
        activeTableCheck = false;
        loadTable(listTeachingSchedule);
    }//GEN-LAST:event_jButton5MouseClicked

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            search();
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        //search     
        search();
    }//GEN-LAST:event_jButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
