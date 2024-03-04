/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAL.TeachingScheduleDAL;
import DTO.CourseDTO;
import DTO.PersonDTO;
import DTO.TeachingScheduleDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author haidu
 */
public class TeachingScheduleBUS {
    TeachingScheduleDAL teachingScheduleDAL= new TeachingScheduleDAL();
    public ArrayList<TeachingScheduleDTO> getTeachingSchedule() throws SQLException {
        return teachingScheduleDAL.getTeachingSchedule();
    }
    public ArrayList<PersonDTO> getInstructor() throws SQLException {
        return teachingScheduleDAL.getInstructor();
    }
    public ArrayList<CourseDTO> getCourse() throws SQLException {
        return teachingScheduleDAL.getCourse();
    }
    public String addTeachingSchedule(TeachingScheduleDTO teachingSchedule) throws SQLException {
        if (teachingSchedule.getCourseID() == 0) {
            return "require_courseID";
        }

        if (teachingSchedule.getInstructorID()== 0) {
            return "required_instructorID";
        }

        int result = teachingScheduleDAL.addTeachingSchedule(teachingSchedule);

        if (result > 0) {
            return "success";
        } else {
            return "error";
        }
    }
    public String updateTeachingSchedule(TeachingScheduleDTO teachingSchedule, String courseID, String instructorID) throws SQLException {
        int result = teachingScheduleDAL.updateTeachingSchedule(teachingSchedule, courseID, instructorID);

        if (result > 0) {
            return "success";
        } else {
            return "error";
        }
    }

    public String deleteTeachingSchedule(String courseID, String instructorID) throws SQLException {

        int result = teachingScheduleDAL.deleteTeachingSchedule(courseID, instructorID);

        if (result > 0) {
            return "success";
        } else {
            return "error";
        }
    }
}
