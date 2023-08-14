/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.edu.ulatina.hth_db_connetion;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ActivityService extends Service implements ICrud<ActivityTO>{

    @Override
    public void insert(ActivityTO activity) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO HTH.ACTIVITY VALUES(?,?,?,?)");
        ps.setInt(1, 0);
        ps.setInt(2, activity.getIdEmployee());
        ps.setInt(3, activity.getIdActivity());
        ps.setDouble(4, activity.getHours());
        
        

        ps.executeUpdate();
        close(ps);
        close(conn);
    }
    
    public void insert(int idEmployee, int idActivity, double hours) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO HTH.ACTIVITY VALUES(?,?,?,?)");
        ps.setInt(1, 0);
        ps.setInt(2, idEmployee);
        ps.setInt(3, idActivity);
        ps.setDouble(4, hours);
         
        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    @Override
    public void delete(ActivityTO activity) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = getConn().prepareStatement("DELETE FROM HTH.ACTIVITY WHERE ID = ?");
        ps.setInt(1, activity.getId());

        ps.executeUpdate();

        close(ps);
        close(conn);
        
    }
    public void delete(int id) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = getConn().prepareStatement("DELETE FROM HTH.ACTIVITY WHERE ID = ?");
        ps.setInt(1, id);

        ps.executeUpdate();

        super.close(ps);
        super.close(conn);

    }
    
    public void update(ActivityTO act, int idEmployee, int idActivity, Double hours) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE HTH.CREATE_ACTIVITY SET id_employee=?, id_activity = ?, hours = ? WHERE id = ?");
        ps.setInt(1, idEmployee);
        ps.setInt(2, idActivity);
        ps.setDouble(3, hours);
        
        ps.setInt(4, act.getId());
        ps.executeUpdate();
        close(ps);
        close(conn);
        close(conn);
    }

    public List<ActivityTO> getActivity() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        List<ActivityTO> retorno = new ArrayList<ActivityTO>();

        ps = getConn().prepareStatement("SELECT * FROM hth.activity");
        rs = ps.executeQuery();
        while (rs.next()) {
            ActivityTO activity;
            int id = rs.getInt("id");
            int idEmployee = rs.getInt("id_employee");
            int idActivity = rs.getInt("id_activity");
            double hours = rs.getDouble("hours");
            
            

            activity = new ActivityTO(id, idEmployee, idActivity, hours);

            retorno.add(activity);

        }

        super.close(rs);
        super.close(ps);
        super.close(conn);

        return retorno;
    }
    
    
    
    public List<ActivityTO> getSearchActivity(int id_employee, int id_project) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        List<ActivityTO> retorno = new ArrayList<ActivityTO>();

        ps = getConn().prepareStatement("SELECT a.id, a.id_employee, a.id_activity, a.hours FROM hth.activity a, hth.create_activity ca Where a.id_employee = ? and ca.id_project = ? and ca.id = a.id_activity;");
        ps.setInt(1, id_employee);
        ps.setInt(2, id_project);
        rs = ps.executeQuery();
        while (rs.next()) {
            ActivityTO activity;
            int id = rs.getInt("id");
            int idEmployee = rs.getInt("id_employee");
            int idActivity = rs.getInt("id_activity");
            double hours = rs.getDouble("hours");
            
            
            

            activity = new ActivityTO(id, idEmployee, idActivity, hours);
            //cActivity = new CreateActivityTO(id2, idProject, name, description);

            retorno.add(activity);

        }

        super.close(rs);
        super.close(ps);
        super.close(conn);

        return retorno;
    }
    
    public String getActivityName(int pk) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        String name = " ";

        ps = getConn().prepareStatement("SELECT name FROM hth.activity, hth.create_activity \n"
                + "Where id_activity = ? and hth.create_activity.id = id_activity");
        ps.setInt(1, pk);
        rs = ps.executeQuery();
        while (rs.next()) {
            name = rs.getString("name");
 
        }
        super.close(rs);
        super.close(ps);
        super.close(conn);
        return name;
    }
    
    
    
    
    
}
