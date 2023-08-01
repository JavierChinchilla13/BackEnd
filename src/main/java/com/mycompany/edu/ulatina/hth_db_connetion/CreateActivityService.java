/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.edu.ulatina.hth_db_connetion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javi
 */
public class CreateActivityService extends Service implements ICrud< CreateActivityTO> {

    public void insert(CreateActivityTO activity) throws Exception {

        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO HTH.CREATE_ACTIVITY VALUES(?,?,?,?))");
        ps.setInt(1, 0);
        ps.setInt(2, activity.getIdProject());
        ps.setString(3, activity.getName());
        ps.setString(4, activity.getDescription());
        

        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    /*public void insertActivyToEmployee(EmployeeTO employeeTO, ActivityTO activityTO) throws Exception {

        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO HTH.ACTIVITY VALUES(?,?,?))");
        ps.setInt(1, 0);
        ps.setInt(2, employeeTO.getId());
        ps.setInt(3, activityTO.getIdProject());

        ps.executeUpdate();
        close(ps);
        close(conn);
   }*/
    
    /*public void insertActivyToEmployee(int idEmployee, int idActivity) throws Exception {

        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO HTH.ACTIVITY VALUES(?,?,?))");
        ps.setInt(1, 0);
        ps.setInt(2, idEmployee);
        ps.setInt(3, idActivity);

        ps.executeUpdate();
        close(ps);
        close(conn);
    }*/

    public void delete(int id) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = getConn().prepareStatement("DELETE FROM HTH.CREATE_ACTIVITY WHERE ID=?");
        ps.setInt(1, id);

        ps.executeUpdate();

        super.close(ps);
        super.close(conn);

    }

    public void update(CreateActivityTO act, int idProject, String name, String description) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE HTH.CREATE_ACTIVITY SET id_description=?, name = ?; description = ?WHERE id = ?");
        ps.setInt(1, idProject);
        ps.setString(2, name);
        ps.setString(3, description);
        
        ps.setInt(4, act.getId());
        ps.executeUpdate();
        close(ps);
        close(conn);
        close(conn);
    }

    public List<CreateActivityTO> getActividad() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        List<CreateActivityTO> retorno = new ArrayList<CreateActivityTO>();

        ps = getConn().prepareStatement("SELECT * FROM HTH.CREATE_ACTIVITY");
        rs = ps.executeQuery();
        while (rs.next()) {
            CreateActivityTO activity;
            int id = rs.getInt("id");
            int idProject = rs.getInt("id_project");
            String name = rs.getString("name");
            String description = rs.getString("description");
            

            activity = new CreateActivityTO(id, idProject, name, description);

            retorno.add(activity);

        }

        super.close(rs);
        super.close(ps);
        super.close(conn);

        return retorno;
    }

    @Override
    public void delete(CreateActivityTO activity) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = getConn().prepareStatement("DELETE FROM HTH.CREATE_ACTIVITY WHERE ID = ?");
        ps.setInt(1, activity.getId());

        ps.executeUpdate();

        close(ps);
        close(conn);
    }

}
