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
 * @author Ale
 */
public class VacationService extends Service implements ICrud<VacationTO> {

    @Override
    public void insert(VacationTO vacationTO) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO HTH.VACATION VALUES(?,?,?)");
        ps.setInt(1, 0);
        ps.setInt(2, vacationTO.getIdEmployee());
        ps.setInt(3, vacationTO.getVacationDays());
        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    public void insert(int idEmployee, int vacationDays) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO HTH.VACATION VALUES(?,?,?)");
        ps.setInt(1, 0);
        ps.setInt(2, idEmployee);
        ps.setInt(3, vacationDays);
        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    @Override
    public void delete(VacationTO vacationTO) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM HTH.VACATION WHERE ID = ?");
        ps.setInt(1, vacationTO.getId());
        ps.executeUpdate();
        close(ps);
        close(conn);
    }
    
    public void delete(int pK) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM HTH.VACATION WHERE ID = ?");
        ps.setInt(1, pK);
        ps.executeUpdate();
        close(ps);
        close(conn);
    }
    
    public void update(VacationTO vacationTO) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE HTH.VACATION SET ID_EMPLOYEE = ?, VACATIONS_DAYS = ? WHERE ID = ?");
        ps.setInt(1, vacationTO.getIdEmployee());
        ps.setInt(2, vacationTO.getVacationDays());
        ps.setInt(3, vacationTO.getId());
        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    public void update(VacationTO vacationTO, int newIdEmployee, int newVacationDays) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE HTH.VACATION SET ID_EMPLOYEE = ?, VACATIONS_DAYS = ? WHERE ID = ?");
        ps.setInt(1, newIdEmployee);
        ps.setInt(2, newVacationDays);
        ps.setInt(3, vacationTO.getId());
        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    public List<VacationTO> getVacations() throws Exception {
        Connection conn = getConnection();
        List<VacationTO> vacationTOList = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT ID,ID_EMPLOYEE,VACATIONS_DAYS FROM HTH.VACATION");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            int idEmployee = rs.getInt("id_employee");
            int VacationDays = rs.getInt("vacations_days");
            VacationTO vacationTO = new VacationTO(id, idEmployee, VacationDays);
            vacationTOList.add(vacationTO);
        }
        close(rs);
        close(ps);
        close(conn);
        return vacationTOList;
    }

    public VacationTO searchByPK(int pK) throws Exception {
        Connection conn = getConnection();
        VacationTO vacationTO = null;
        PreparedStatement ps = conn.prepareStatement("SELECT ID,ID_EMPLOYEE,VACATIONS_DAYS FROM HTH.VACATION WHERE ID = ?");
        ps.setInt(1, pK);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            int idEmployee = rs.getInt("id_employee");
            int vacationDays = rs.getInt("vacations_days");
            vacationTO = new VacationTO(id, idEmployee, vacationDays);
        }
        close(rs);
        close(ps);
        close(conn);
        return vacationTO;
    }
    
    public int getVacationDaysOf(int pK) throws Exception {
    Connection conn = getConnection();
    int vacationDays = 0;
    PreparedStatement ps = conn.prepareStatement("SELECT VACATIONS_DAYS FROM HTH.VACATION WHERE ID_EMPLOYEE = ?");
    ps.setInt(1, pK);
    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        vacationDays = rs.getInt("VACATIONS_DAYS");
    }
    
    close(rs);
    close(ps);
    close(conn);
    
    return vacationDays;
}


}
