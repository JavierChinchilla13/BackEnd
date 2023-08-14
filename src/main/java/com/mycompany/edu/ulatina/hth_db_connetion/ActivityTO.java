/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.edu.ulatina.hth_db_connetion;

import java.io.Serializable;

/**
 *
 * @author javi
 */
public class ActivityTO implements Serializable{
    
    private int id;
    private int idEmployee;
    private int idActivity;
    private double hours;

    public ActivityTO() {
    }

    public ActivityTO(int id, int idEmployee, int idActivity, double hours) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.idActivity = idActivity;
        this.hours = hours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }
    
    
}
