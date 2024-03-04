/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAL.StudentGradeDAL;
import DTO.CourseDTO;
import DTO.PersonDTO;
import DTO.StudentGradeDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author datly
 */
public class StudentGradeBUS {

    StudentGradeDAL studentGradeDAL = new StudentGradeDAL();

    public ArrayList<StudentGradeDTO> getStudentGrade() throws SQLException {
        return studentGradeDAL.getStudentGrade();
    }

    public ArrayList<PersonDTO> getPerson() throws SQLException {
        return studentGradeDAL.getPerson();
    }

    public ArrayList<CourseDTO> getCourse() throws SQLException {
        return studentGradeDAL.getCourse();
    }

    public String addStudentGrade(StudentGradeDTO studentGrade) throws SQLException {
        if (studentGrade.getCourseID() == 0) {
            return "require_courseID";
        }

        if (studentGrade.getStudentID() == 0) {
            return "required_studentID";
        }

        int result = studentGradeDAL.addStudentGrade(studentGrade);

        if (result > 0) {
            return "success";
        } else {
            return "error";
        }

    }

    public String updateStudentGrade(StudentGradeDTO studentGrade) throws SQLException {

        if (studentGrade.getEnrollmentID() == 0) {
            return "require_enrollmentID";
        }

        int result = studentGradeDAL.updateStudentGrade(studentGrade);

        if (result > 0) {
            return "success";
        } else {
            return "error";
        }

    }

    public String deleteStudentGrade(int id) throws SQLException {

        if (id == 0) {
            return "require_enrollmentID";
        }

        int result = studentGradeDAL.deleteStudentGrade(id);

        if (result > 0) {
            return "success";
        } else {
            return "error";
        }

    }

}
