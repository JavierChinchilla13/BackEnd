
package com.mycompany.edu.ulatina.hth_db_connetion;

import java.io.Serializable;
import java.sql.Date;

public class ScheduleVacationTO implements Serializable {

    private int id;
    private int idVacation;
    private Date startDate;
    private Date endDate;
    private int idStatus;
    private String description;

    public ScheduleVacationTO() {
    }

    public ScheduleVacationTO(int id, int idVacation, Date startDate, Date endDate, int idStatus, String description) {
        this.id = id;
        this.idVacation = idVacation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.idStatus = idStatus;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }
}
