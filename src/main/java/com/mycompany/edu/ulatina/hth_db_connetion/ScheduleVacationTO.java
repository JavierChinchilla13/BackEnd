/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.edu.ulatina.hth_db_connetion;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Ale
 */
public class ScheduleVacationTO implements Serializable {

    private int id;
    private int idVacation;
    private Date startDate;
    private Date endDate;

    public ScheduleVacationTO() {
    }

    public ScheduleVacationTO(int id, int idVacation, Date startDate, Date endDate) {
        this.id = id;
        this.idVacation = idVacation;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVacation() {
        return idVacation;
    }

    public void setIdVacation(int idVacation) {
        this.idVacation = idVacation;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
