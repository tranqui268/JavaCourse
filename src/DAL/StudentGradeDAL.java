/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.CourseDTO;
import DTO.PersonDTO;
import DTO.StudentGradeDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author datly
 */
public class StudentGradeDAL extends MyDatabaseManager {
    
    public StudentGradeDAL() {
        super();
        this.connectDB();
    }
    
    public ArrayList<StudentGradeDTO> getStudentGrade() throws SQLException {
        ArrayList<StudentGradeDTO> listStudentGrade = new ArrayList<StudentGradeDTO>();
        try {
            String query = "select * from studentgrade as s, course as c, person as p where s.CourseID = c.CourseID and s.StudentID = p.PersonID";
            ResultSet rs = this.doReadQuery(query);
            while (rs.next()) {
                StudentGradeDTO student = new StudentGradeDTO(rs.getInt("EnrollmentID"), rs.getInt("CourseID"), rs.getInt("StudentID"), rs.getDouble("Grade"), rs.getString("Lastname"), rs.getString("Firstname"), rs.getString("Title"));
                listStudentGrade.add(student);
            }
        } catch (SQLException e) {
        }
        return listStudentGrade;
    }
    
    public ArrayList<PersonDTO> getPerson() throws SQLException {
        ArrayList<PersonDTO> listPerson = new ArrayList<PersonDTO>();
        
        String query = "select * from person";
        ResultSet rs = this.doReadQuery(query);
        
        while (rs.next()) {
            PersonDTO person = new PersonDTO(rs.getInt("PersonID"), rs.getString("Lastname"), rs.getString("Firstname"), rs.getDate("HireDate"), rs.getDate("EnrollmentDate"));
            listPerson.add(person);
        }
        
        return listPerson;
    }
    
    public ArrayList<CourseDTO> getCourse() throws SQLException {
        ArrayList<CourseDTO> listCourse = new ArrayList<CourseDTO>();
        
        String query = "select * from course";
        ResultSet rs = this.doReadQuery(query);
        
        while (rs.next()) {
            CourseDTO course = new CourseDTO(rs.getInt("CourseID"), rs.getString("Title"), rs.getInt("Credits"), rs.getInt("DepartmentID"));
            listCourse.add(course);
        }
        
        return listCourse;
    }
    
    public int addStudentGrade(StudentGradeDTO studentGrade) throws SQLException {
        String query = "INSERT INTO studentgrade values (?,?,?,?)";
        
        p = conn.prepareStatement(query);
        p.setString(1, null);
        p.setInt(2, studentGrade.getCourseID());
        p.setInt(3, studentGrade.getStudentID());
        p.setDouble(4, studentGrade.getGrade());
        
        int result = p.executeUpdate();
        return result;
    }
    
    public int updateStudentGrade(StudentGradeDTO studentGrade) throws SQLException {
        String query = "update studentgrade set CourseID = ?, StudentID = ?, Grade = ? where EnrollmentID = " + studentGrade.getEnrollmentID();
        
        p = conn.prepareStatement(query);
        p.setInt(1, studentGrade.getCourseID());
        p.setInt(2, studentGrade.getStudentID());
        p.setDouble(3, studentGrade.getGrade());
        
        int result = p.executeUpdate();
        return result;
    }
    
    public int deleteStudentGrade(int id) throws SQLException {
        String query = "delete from studentgrade where EnrollmentID = ?";
        p = conn.prepareStatement(query);
        p.setInt(1, id);
        
        int result = p.executeUpdate();
        return result;
    }
}
