/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Admin
 */
public class OnlineCourseDTO{
    private int OnlineCourseID;
    private String url;

    public OnlineCourseDTO(int CourseID, String url) {
        this.OnlineCourseID = CourseID;
        this.url = url;
    }

    public OnlineCourseDTO() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getOnlineCourseID() {
        return OnlineCourseID;
    }

    public void setOnlineCourseID(int OnlineCourseID) {
        this.OnlineCourseID = OnlineCourseID;
    }

    @Override
    public String toString() {
        return "OnlineCourseDTO{" + "OnlineCourseID=" + OnlineCourseID + ", url=" + url + '}';
    }
    
    
    
}
