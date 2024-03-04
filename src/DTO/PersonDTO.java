/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author datly
 */
public class PersonDTO {
    private int PersonID;
    private String Lastname;
    private String Firstname;
    private Date HireDate;
    private Date EnrollmentDate;

    public PersonDTO(int PersonID, String Lastname, String Firstname, Date HireDate, Date EnrollmentDate) {
        this.PersonID = PersonID;
        this.Lastname = Lastname;
        this.Firstname = Firstname;
        this.HireDate = HireDate;
        this.EnrollmentDate = EnrollmentDate;
    }

    public PersonDTO() {
    }

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int PersonID) {
        this.PersonID = PersonID;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String Lastname) {
        this.Lastname = Lastname;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    public Date getHireDate() {
        return HireDate;
    }

    public void setHireDate(Date HireDate) {
        this.HireDate = HireDate;
    }

    public Date getEnrollmentDate() {
        return EnrollmentDate;
    }

    public void setEnrollmentDate(Date EnrollmentDate) {
        this.EnrollmentDate = EnrollmentDate;
    }

    @Override
    public String toString() {
        return "PersonDTO{" + "PersonID=" + PersonID + ", Lastname=" + Lastname + ", Firstname=" + Firstname + ", HireDate=" + HireDate + ", EnrollmentDate=" + EnrollmentDate + '}';
    }
    
}
