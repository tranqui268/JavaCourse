/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Time;

/**
 *
 * @author Admin
 */
public class OnsiteCourseDTO{
    private int OnsiteCourseID;
    private String location;
    private String days;
    private Time time;

    public OnsiteCourseDTO(int CourseID, String location, String days, Time time) {
        this.OnsiteCourseID = CourseID;
        this.location = location;
        this.days = days;
        this.time = time;
    }

    public OnsiteCourseDTO() {
    }

    public int getOnsiteCourseID() {
        return OnsiteCourseID;
    }

    public void setOnsiteCourseID(int OnsiteCourseID) {
        this.OnsiteCourseID = OnsiteCourseID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "OnsiteCourseDTO{" + "OnsiteCourseID=" + OnsiteCourseID + ", location=" + location + ", days=" + days + ", time=" + time + '}';
    }

    
}
