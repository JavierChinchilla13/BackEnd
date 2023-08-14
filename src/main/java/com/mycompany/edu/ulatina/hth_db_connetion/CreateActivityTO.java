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
public class CreateActivityTO implements Serializable{
    
    private int id;
    private int idProject;
    private String Name;
    private String Description;
    

    public CreateActivityTO() {
    }

    public CreateActivityTO(int id, int idProject, String Name, String Description) {
        this.id = id;
        this.idProject = idProject;
        this.Name = Name;
        this.Description = Description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    

    
    
}
