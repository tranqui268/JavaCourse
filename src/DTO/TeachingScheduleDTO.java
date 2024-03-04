/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author haidu
 */
public class TeachingScheduleDTO {
    public int InstructorID, CourseID;
    public String Name, Title;

    public TeachingScheduleDTO() {
    }

    public TeachingScheduleDTO(int InstructorID, int CourseID, String Name, String Title) {
        this.InstructorID = InstructorID;
        this.CourseID = CourseID;
        this.Name = Name;
        this.Title = Title;
    }

    public int getInstructorID() {
        return InstructorID;
    }

    public void setInstructorID(int InstructorID) {
        this.InstructorID = InstructorID;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
    
}
