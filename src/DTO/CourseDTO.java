/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author datly
 */
public class CourseDTO {
    private int CourseID;
    private String Title;
    private int Credits;
    private int DepartmentID;

    public CourseDTO(int CourseID, String Title, int Credits, int DepartmentID) {
        this.CourseID = CourseID;
        this.Title = Title;
        this.Credits = Credits;
        this.DepartmentID = DepartmentID;
    }

    public CourseDTO() {
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getCredits() {
        return Credits;
    }

    public void setCredits(int Credits) {
        this.Credits = Credits;
    }

    public int getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(int DepartmentID) {
        this.DepartmentID = DepartmentID;
    }

    @Override
    public String toString() {
        return "OnlineCourseDTO{" + "CourseID=" + CourseID + ", Title=" + Title + ", Credits=" + Credits + ", DepartmentID=" + DepartmentID + '}';
    }
    
}
