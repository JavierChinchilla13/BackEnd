package com.mycompany.edu.ulatina.hth_db_connetion;

import java.io.Serializable;
import java.sql.Date;

public class EmployeeTO implements Serializable {
    private int id;
    private String firstName;
    private String password;
    private String lastName;
    private String identification;
    private String email;
    private String phone;
    private int status;
    private int type;
    private Date layoffDate;
    private Date employmentDate;

    public EmployeeTO() {
    }

    public EmployeeTO(int id, String firstName, String lastName, String identification, String email, String phone, int type, int status, String password, Date employmentDate) {
        this.id = id;
        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
        this.identification = identification;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.type = type;
        this.employmentDate = employmentDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getEmail() {
        return email;
    }

    public void setMail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getLayoffDate() {
        return layoffDate;
    }

    public void setLayOffDate(Date layOffDate) {
        this.layoffDate = layOffDate;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }
    
}
