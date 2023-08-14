package com.mycompany.edu.ulatina.hth_db_connetion;


import java.io.Serializable;
import java.sql.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author javi
 */
public class FeedbackTO implements Serializable{
    
    private int id;
    private String name;
    private String description;
    private Date dateOfFeedback;
    private int idStatus;
    private int idType;
    private int idCreator;

    public FeedbackTO() {
    }

    public FeedbackTO(int id, String name, String description, Date dateOfFeedback, int idStatus, int idType, int idCreator) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateOfFeedback = dateOfFeedback;
        this.idStatus = idStatus;
        this.idType = idType;
        this.idCreator = idCreator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOfFeedback() {
        return dateOfFeedback;
    }

    public void setDateOfFeedback(Date dateOfFeedback) {
        this.dateOfFeedback = dateOfFeedback;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public int getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(int idCreator) {
        this.idCreator = idCreator;
    }
    
    
    
    
    
}
