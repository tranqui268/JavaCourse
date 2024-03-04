/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.CourseDTO;
import DTO.OnlineCourseDTO;
import DTO.OnsiteCourseDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CourseDAL extends MyDatabaseManager{
    
    public CourseDAL(){
        super();
        this.connectDB();
    }
    
    public ArrayList<CourseDTO> getCourse(){
        ArrayList<CourseDTO> CourseList = new ArrayList<CourseDTO>();
        
        try{
            String query = "Select * from course";
            ResultSet rs = this.doReadQuery(query);
        
            if(rs != null){
                while(rs.next()){
                    CourseDTO c = new CourseDTO();
                    c.setCourseID(rs.getInt("CourseID"));
                    c.setTitle(rs.getString("Title"));
                    c.setCredits(rs.getInt("Credits"));
                    c.setDepartmentID(rs.getInt("DepartmentID"));
                    CourseList.add(c);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        return CourseList;
    }
    
    public ArrayList<OnlineCourseDTO> getOnlineCourse(){
        ArrayList<OnlineCourseDTO> list = new ArrayList<OnlineCourseDTO>();
        
        try{
            String query = "Select * from onlinecourse";
            ResultSet rs = this.doReadQuery(query);
        
            if(rs != null){
                while(rs.next()){
                    OnlineCourseDTO olc = new OnlineCourseDTO(rs.getInt("CourseID"), rs.getString("url"));
                    list.add(olc);
                }
            }
        }catch(Exception e){
            
        }
        
        return list;
    }
    
    public ArrayList<OnsiteCourseDTO> getOnsiteCourse(){
        ArrayList<OnsiteCourseDTO> list = new ArrayList<OnsiteCourseDTO>();
        
        try {
            String query = "Select * from onsitecourse";
            ResultSet rs = this.doReadQuery(query);
        
            if(rs != null){
                while(rs.next()){
                    OnsiteCourseDTO osc = new OnsiteCourseDTO();
                    osc.setOnsiteCourseID(rs.getInt("CourseID"));
                    osc.setLocation(rs.getString("Location"));
                    osc.setDays(rs.getString("Days"));
                    osc.setTime(rs.getTime("Time"));   
                    list.add(osc);
                }
            }
        } catch (SQLException e) {
        }
        
        return list;
    }
    public int addCourse(CourseDTO course) throws SQLException{
        String sqlquery = "Insert into course(CourseID, Title, Credits, DepartmentID) VALUES (?, ?, ?, ?)";
        p = conn.prepareStatement(sqlquery);
        p.setInt(1, course.getCourseID());
        p.setString(2, course.getTitle());
        p.setInt(3, course.getCredits());
        p.setInt(4, course.getDepartmentID());
        
        int rs = p.executeUpdate();
        return rs;
    }
    
    public int addOnlineCourse(OnlineCourseDTO onlinecourse) throws SQLException{
        String query = "Insert into onlinecourse (CourseID, url) VALUES (?, ?)";
        p = conn.prepareStatement(query);
        p.setInt(1, onlinecourse.getOnlineCourseID());
        p.setString(2, onlinecourse.getUrl());
        
        int rs = p.executeUpdate(); 
        return rs;
    }
    public int addOnsiteCourse(OnsiteCourseDTO onsitecourse) throws SQLException{
        String query = "Insert into onsitecourse VALUES (?, ?, ?, ?)";
        p = conn.prepareStatement(query);
        p.setInt(1, onsitecourse.getOnsiteCourseID());
        p.setString(2, onsitecourse.getLocation());
        p.setString(3, onsitecourse.getDays());
        p.setTime(4, onsitecourse.getTime());
        
        int rs = p.executeUpdate();
        return rs;
    }
    
    public int updateCourse(CourseDTO course) throws SQLException{
        String sqlquery = "Update course set  Title = ?, Credits = ?, DepartmentID = ? where CourseID = " + course.getCourseID();
        p = conn.prepareStatement(sqlquery);
        p.setString(1, course.getTitle());
        p.setInt(2, course.getCredits());
        p.setInt(3, course.getDepartmentID());
        
        int rs = p.executeUpdate();
        
        return rs;
    }
    public int updateOnlCourse(OnlineCourseDTO course) throws SQLException{
        String sqlquery = "Update onlinecourse set url = ? where CourseID = " + course.getOnlineCourseID();
        p = conn.prepareStatement(sqlquery);
        p.setString(1, course.getUrl());
        
        int rs = p.executeUpdate();
        return rs;
    }
    public int updateOnsCourse(OnsiteCourseDTO course) throws SQLException{
        String sqlquery = "Update onsitecourse set  Location = ?, Days = ?, Time = ? where CourseID = " + course.getOnsiteCourseID();
        p = conn.prepareStatement(sqlquery);
        p.setString(1, course.getLocation());
        p.setString(2, course.getDays());
        p.setTime(3, course.getTime());
        
        int rs = p.executeUpdate();
        return rs;
    }
    
    public int deleteCourse(int courseID) throws SQLException{
        try {
            String sql = "Delete from course where CourseID = " + courseID;
        p = conn.prepareStatement(sql);
        
        } catch (Exception e) {
            System.out.println(e);
        }
        int rs = p.executeUpdate();
        return rs;
    }
    public int deleteOnlCourse(int courseID) throws SQLException{
        String sqlonline = "Delete from onlinecourse where CourseID = " + courseID;
        p = conn.prepareStatement(sqlonline);
        
        int rs = p.executeUpdate();
        return rs;
    }
    public int deleteOnsCourse(int courseID) throws SQLException{
        String sqlonsite = "Delete from onsitecourse where CourseID = " + courseID;
        p = conn.prepareStatement(sqlonsite);
        
        int rs = p.executeUpdate();
        return rs;
    }
}
