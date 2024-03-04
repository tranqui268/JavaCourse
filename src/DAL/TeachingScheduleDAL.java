/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.CourseDTO;
import DTO.PersonDTO;
import DTO.TeachingScheduleDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author haidu
 */
public class TeachingScheduleDAL extends MyDatabaseManager{

    public TeachingScheduleDAL() {
        super();
        this.connectDB();
    }
    public ArrayList<TeachingScheduleDTO> getTeachingSchedule() throws SQLException {
        ArrayList<TeachingScheduleDTO> listTeachingSchedule = new ArrayList<TeachingScheduleDTO>();
        try {
            String query = "SELECT person.PersonID, person.Firstname, person.Lastname, course.CourseID, course.Title "
                    + "FROM `courseinstructor`, `person`, `course` WHERE person.PersonID= courseinstructor.PersonID "
                    + "AND course.CourseID= courseinstructor.CourseID";
            ResultSet rs = this.doReadQuery(query);
            while (rs.next()) {
                TeachingScheduleDTO instructor = new TeachingScheduleDTO(rs.getInt("PersonID"), rs.getInt("CourseID"), rs.getString("Firstname")+" "+ rs.getString("Lastname"), rs.getString("Title"));
                listTeachingSchedule.add(instructor);
            }
        } catch (SQLException e) {
        }
        return listTeachingSchedule;
    }
    public ArrayList<PersonDTO> getInstructor() throws SQLException {
        ArrayList<PersonDTO> listInstructor = new ArrayList<PersonDTO>();
        try {
            String query = "SELECT * FROM `person` WHERE person.HireDate >0";
            ResultSet rs = this.doReadQuery(query);
            while (rs.next()) {
                PersonDTO instructor = new PersonDTO(rs.getInt("PersonID"), rs.getString("Lastname"), rs.getString("Firstname"), rs.getDate("Hiredate"), rs.getDate("EnrollmentDate"));
                listInstructor.add(instructor);
            }
        } catch (SQLException e) {
        }
        return listInstructor;
    }
    public ArrayList<CourseDTO> getCourse() throws SQLException {
        ArrayList<CourseDTO> listCourse = new ArrayList<CourseDTO>();
        try {
            String query = "SELECT * FROM `course`";
            ResultSet rs = this.doReadQuery(query);
            while (rs.next()) {
                CourseDTO course = new CourseDTO(rs.getInt("CourseID"), rs.getString("Title"), rs.getInt("Credits"), rs.getInt("DepartmentID"));
                listCourse.add(course);
            }
        } catch (SQLException e) {
        }
        return listCourse;
    }
    public int addTeachingSchedule(TeachingScheduleDTO teachingSchedule) throws SQLException {
        String query = "INSERT INTO `courseinstructor`(`CourseID`, `PersonID`) VALUES (?, ?)";
        
        p = conn.prepareStatement(query);
        p.setInt(1, teachingSchedule.getCourseID());
        p.setInt(2, teachingSchedule.getInstructorID());
        
        int result = p.executeUpdate();
        return result;
    }
    public int updateTeachingSchedule(TeachingScheduleDTO teachingSchedule , String courseID, String instructorID) throws SQLException {
        String query = "UPDATE `courseinstructor` SET `CourseID`= ? ,`PersonID`= ? WHERE CourseID = "+courseID+" AND PersonID = "+ instructorID;     
        p = conn.prepareStatement(query);
        p.setInt(1, teachingSchedule.getCourseID());
        p.setInt(2, teachingSchedule.getInstructorID());
        
        int result = p.executeUpdate();
        return result;
    }
    
    public int deleteTeachingSchedule(String courseID, String instructorID) throws SQLException {
        String query = "DELETE FROM `courseinstructor` WHERE CourseID = ? AND PersonID = ?";
        p = conn.prepareStatement(query);
        p.setString(1, courseID);
        p.setString(2, instructorID);
        int result = p.executeUpdate();
        return result;
    }
}
