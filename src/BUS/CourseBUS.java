/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAL.CourseDAL;
import DTO.CourseDTO;
import DTO.OnlineCourseDTO;
import DTO.OnsiteCourseDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class CourseBUS {
    
    CourseDAL courseDAL = new CourseDAL();
    
    private ArrayList<CourseDTO> list = courseDAL.getCourse();
    private ArrayList<OnlineCourseDTO> Onlinelist = courseDAL.getOnlineCourse();
    private ArrayList<OnsiteCourseDTO> Onsitelist = courseDAL.getOnsiteCourse();
    
    public ArrayList<CourseDTO> getCourse() throws SQLException{
        return list;
    }
    
    public void reload(){
        list.clear();
        Onlinelist.clear();
        Onsitelist.clear();
        list = courseDAL.getCourse();
        Onlinelist = courseDAL.getOnlineCourse();
        Onsitelist = courseDAL.getOnsiteCourse();
    }
    
    //Hàm lấy list online course
    public ArrayList<CourseDTO> getOnlineCourse(ArrayList<CourseDTO> listC) throws SQLException{
        Onlinelist = courseDAL.getOnlineCourse();
        ArrayList<CourseDTO> result = new ArrayList<CourseDTO>();
        
        for(OnlineCourseDTO i : Onlinelist){
            for(CourseDTO j : listC){
                if(i.getOnlineCourseID() == j.getCourseID()){
                    result.add(j);
                    break;
                }
            }
        }
        
        return result;
    }
    //Hàm lấy list onsite course
    public ArrayList<CourseDTO> getOnsiteCourse(ArrayList<CourseDTO> listC) throws SQLException{
        Onsitelist = courseDAL.getOnsiteCourse();
        ArrayList<CourseDTO> result = new ArrayList<CourseDTO>();
        
        for(OnsiteCourseDTO i : Onsitelist){
            for(CourseDTO j : listC){
                if(i.getOnsiteCourseID() == j.getCourseID()){
                    result.add(j);
                    break;
                }
            }
        }
        
        return result;
    }
    
    //Hàm chuyển list course online sang table
    public DefaultTableModel ConvertOnlineCourse(ArrayList<CourseDTO> listC) throws SQLException{
        String[] ColumnNames = {"STT","CourseID","Title","Credits","DepartmentID","URL"};
        
        ArrayList<OnlineCourseDTO> onlineCourseList = courseDAL.getOnlineCourse();
        
        Object[][] data = new Object[listC.size()][6];
        
        for(int i = 0; i < listC.size(); i++){
            CourseDTO course = listC.get(i);
            //Thêm thứ tự và dòng các cột khác
            data[i][0] = i+1;
            data[i][1] = course.getCourseID();
            data[i][2] = course.getTitle();
            data[i][3] = course.getCredits();
            data[i][4] = course.getDepartmentID();
            //Thêm cột link url
            for(int j = 0; j < onlineCourseList.size(); j++){
                if(onlineCourseList.get(j).getOnlineCourseID() == listC.get(i).getCourseID()){
                    data[i][5] = onlineCourseList.get(j).getUrl();
                }
            }
        }
        
        DefaultTableModel model = new DefaultTableModel(data, ColumnNames);
        return model;
    }
    //Hàm chuyển list onsite course sang dạng table
    public DefaultTableModel ConvertOnsiteCourse(ArrayList<CourseDTO> listC) throws SQLException{
        String[] CollumnNames = {"STT","CourseID","Title","Credits","DepartmentID","Location","Days","Time"};
        
        ArrayList<OnsiteCourseDTO> onsite = courseDAL.getOnsiteCourse();
        
        Object[][] data = new Object[listC.size()][8];
        
        for(int i = 0; i < listC.size(); i++){
            CourseDTO course = listC.get(i);
            data[i][0] = i+1;
            data[i][1] = course.getCourseID();
            data[i][2] = course.getTitle();
            data[i][3] = course.getCredits();
            data[i][4] = course.getDepartmentID();
            
            for(int j = 0; j < onsite.size(); j++){
                if(onsite.get(j).getOnsiteCourseID() == listC.get(i).getCourseID()){
                    data[i][5] = onsite.get(j).getLocation();
                    data[i][6] = onsite.get(j).getDays();
                    data[i][7] = onsite.get(j).getTime();
                }
            }
        }
        DefaultTableModel model = new DefaultTableModel(data, CollumnNames);
        return model;
    }
    //Hàm chuyển list course sang table
    public DefaultTableModel ConvertCourse(ArrayList<CourseDTO> list){
        
        String[] ColumnNames = {"STT","CourseID","Title","Credits","DepartmentID"};
        Object[][] data = new Object[list.size()][5];
        
        for(int i=0; i<list.size();i++){
            CourseDTO course = (CourseDTO) list.get(i);
            data[i][0] = i+1;
            data[i][1] = course.getCourseID();
            data[i][2] = course.getTitle();
            data[i][3] = course.getCredits();
            data[i][4] = course.getDepartmentID();
        }
        
        DefaultTableModel model = new DefaultTableModel(data, ColumnNames);
        return model;
    }
    
    //Functions of Add
    public String addCourse(CourseDTO course) throws SQLException {
        if(course.getCourseID() == 0){
            return "Require_Course_ID";
        }
        
        int rs = courseDAL.addCourse(course);
        list.add(course);
        System.out.println("BUS.CourseBUS.addOnsiteCourse() " + String.valueOf(rs));
        if(rs > 0){
            return "Success";
        }
        else
            return "Fail";
    }
    public String addOnlineCourse(OnlineCourseDTO onlinecourse) throws SQLException{
        
        if(onlinecourse.getOnlineCourseID() == 0){
            return "Require Course ID";
        }
        
        int rs = courseDAL.addOnlineCourse(onlinecourse);
        
        if(rs > 0){
            return "Success";
        }
        else
            return "Fail";
    }
    public String addOnsiteCourse(OnsiteCourseDTO onsitecourse) throws SQLException{
        if(onsitecourse.getOnsiteCourseID() == 0){
            return "Require Course ID";
        }
        
        int rs = courseDAL.addOnsiteCourse(onsitecourse);
        
        if(rs > 0){
            return "Success";
        }
        else
            return "Fail";
    }
    
    //Functions of Update Course
    public int UpdateCourse(CourseDTO course) throws SQLException{
        int rs = courseDAL.updateCourse(course);
        //Update List Course
        for(int i = 0;i < list.size();i++){
            if(list.get(i).getCourseID() == course.getCourseID()){
                list.remove(i);
                list.add(course);
            }
        }
        return rs;
    }
    public int UpdateOnlCourse(OnlineCourseDTO course) throws SQLException{
        int rs = courseDAL.updateOnlCourse(course);
        return rs;
    }
    public int UpdateOnsCourse(OnsiteCourseDTO course) throws SQLException{
        int rs = courseDAL.updateOnsCourse(course);
        return rs;
    }
    
    //Functions of Delete Course
    public int DeleteCourse(int courseID) throws SQLException{
        int rs = courseDAL.deleteCourse(courseID);
        //Remove List Course
        for(int i = 0;i < list.size();i++){
            if(list.get(i).getCourseID() == courseID){
                list.remove(i);
            }
        }
        return rs;
    }
    public int DeleteOnlCourse(int courseID) throws SQLException{
        int rs = courseDAL.deleteOnlCourse(courseID);
        return rs;
    }
    public int DeleteOnsCourse(int courseID) throws SQLException{
        int rs = courseDAL.deleteOnsCourse(courseID);
        return rs;
    }
    
    //Functions of Search
    public ArrayList<CourseDTO> SearchOnlineCourse(String s, String chon) throws SQLException{
        ArrayList<CourseDTO> result = new ArrayList<CourseDTO>();
        //temp for searching list which we wanted in List 
        ArrayList<CourseDTO> temp = getOnlineCourse(list);
        
        Onlinelist = courseDAL.getOnlineCourse();
        
        switch (chon) {
            case "CourseID":
                for(CourseDTO i : temp){
                    String id = String.valueOf(i.getCourseID());
                    if(id.contains(s)){
                        result.add(i);
                    }
                }
                break;
            case "Title":
                for(CourseDTO i : temp){
                    if(i.getTitle().toLowerCase().contains(s.toLowerCase())){
                        result.add(i);
                    }
                }
                break;
            case "Credits":
                for(CourseDTO i : temp){
                    String credits = String.valueOf(i.getCredits());
                        if(credits.contains(s)){
                        result.add(i);
                    }
                }
                break;
            case "Departments":
                for(CourseDTO i : temp){
                    String department = String.valueOf(i.getDepartmentID());
                        if(department.contains(s)){
                        result.add(i);
                    }
                }
                break;
            case "URL":
                for(CourseDTO i : temp){
                    for(OnlineCourseDTO j : Onlinelist){
                        if(i.getCourseID() == j.getOnlineCourseID()){
                            if(j.getUrl().toLowerCase().contains(s.toLowerCase())){
                                result.add(i);
                            }
                        }
                    }
                }
                break;
            default:
                throw new AssertionError();
        }
        
        return result;
    }
    
    //Functions of Searching Onsite Course
    public ArrayList<CourseDTO> SearchOnsiteCourse(String s,String chon) throws SQLException{
        ArrayList<CourseDTO> temp = getOnsiteCourse(list);
        ArrayList<CourseDTO> result = new ArrayList<CourseDTO>();
        
        Onsitelist = courseDAL.getOnsiteCourse();
        
        switch (chon) {
            case "CourseID":
                for(CourseDTO i : temp){
                    String id = String.valueOf(i.getCourseID());
                    if(id.contains(s)){
                        result.add(i);
                    }
                }
                break;
            case "Title":
                for(CourseDTO i : temp){
                    if(i.getTitle().toLowerCase().contains(s.toLowerCase())){
                        result.add(i);
                    }
                }
                break;
            case "Credits":
                for(CourseDTO i : temp){
                    String credits = String.valueOf(i.getCredits());
                        if(credits.contains(s)){
                        result.add(i);
                    }
                }
                break;
            case "Departments":
                for(CourseDTO i : temp){
                    String department = String.valueOf(i.getDepartmentID());
                        if(department.contains(s)){
                        result.add(i);
                    }
                }
                break;
            case "Location":
                for(CourseDTO i : temp){
                    for(OnsiteCourseDTO j : Onsitelist){
                        if(i.getCourseID() == j.getOnsiteCourseID()){
                            if(j.getLocation().toLowerCase().contains(s.toLowerCase())){
                                result.add(i);
                            }
                        }
                    }
                }
                break;
            case "Days":
                for(CourseDTO i : temp){
                    for(OnsiteCourseDTO j : Onsitelist){
                        if(i.getCourseID() == j.getOnsiteCourseID()){
                            if(j.getDays().toLowerCase().contains(s.toLowerCase())){
                                result.add(i);
                            }
                        }
                    }
                }
                break;
            default:
                throw new AssertionError();
        }
        
        return result;
    }
}
