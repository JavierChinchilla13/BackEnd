package com.mycompany.edu.ulatina.hth_db_connetion;

import java.io.Serializable;

public class DocumentTO implements Serializable {
    private int id;
    private int idEmployee;
    private String name;
    private String path;

    public DocumentTO() {
    }

    public DocumentTO(int id, int idEmployee, String name, String path) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.name = name;
        this.path = path;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    } 
}
