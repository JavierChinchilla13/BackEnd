package com.mycompany.edu.ulatina.hth_db_connetion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProjectService extends Service
        implements ICrud<ProjectTO>{

    @Override
    public void insert(ProjectTO project) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO HTH.PROJECT VALUES(?,?,?,?,?)");
        ps.setInt(1, 0);
        ps.setString(2, project.getName());
        ps.setInt(3, project.getStatus());
        ps.setDate(4, (java.sql.Date) (Date) project.getStartingDate());
        ps.setDate(5, (java.sql.Date) (Date) project.getEndingDate());
        if (!project.getEmployees().isEmpty()) {
            for (EmployeeTO emp : project.getEmployees()) {
                addCollaborator(project, emp);
            }
        }
        ps.executeUpdate();
        close(ps);
        close(conn);
    }
    public void addCollaborator(ProjectTO project, EmployeeTO emp) throws Exception{
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO HTH.PROJECT_X_EMPLOYEE (ID, ID_PROJECT, ID_EMPLOYEE, HOURS_INVESTED) VALUES(?,?,?)");
        int id = 0;
        int hoursInvested = 0;
        ps.setInt(1, id);
        ps.setInt(2, project.getId());
        ps.setInt(3, emp.getId());
       
        ps.executeUpdate();
        close(ps);
        close(conn);
    }
    
    public void addCollaborator(int idProject, int idEmployee) throws Exception{
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO HTH.PROJECT_X_EMPLOYEE (ID, ID_PROJECT, ID_EMPLOYEE) VALUES(?,?,?)");
        int id = 0;
        int hoursInvested = 0;
        ps.setInt(1, id);
        ps.setInt(2, idProject);
        ps.setInt(3, idEmployee);
        
        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    @Override
    public void delete(ProjectTO projectTO) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = getConn().prepareStatement("DELETE FROM HTH.PROJECT WHERE id=?");
        ps.setInt(1, projectTO.getId());

        ps.executeUpdate();

        close(ps);
        close(conn);
    }
    public void delete(int pk) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = getConn().prepareStatement("DELETE FROM HTH.PROKJECT WHERE id=?");
        ps.setInt(1, pk);

        ps.executeUpdate();

        close(ps);
        close(conn);
        
    }
    public void update(ProjectTO pro, String name, int status, Date startingDate, Date endingDate) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE HTH.PROJECT SET NAME = ?, ID_STATUS_DETAIL = ?, STARTING_DATE = ?, ENDING_DATE = ? WHERE ID = ?");
        ps.setString(1, name);
        ps.setInt(2,status);
        ps.setDate(3, startingDate);
        ps.setDate(4, endingDate);
        ps.setInt(5, pro.getId());
        ps.executeUpdate();
        close(ps);
        close(conn);
    }
    public ProjectTO searchByPK(int pK) throws Exception {
        Connection conn = getConnection();
        ProjectTO projectTO = null;
        PreparedStatement ps = conn.prepareStatement("SELECT ID, NAME, ID_STATUS_DETAIL, STARTING_DATE, ENDING_DATE FROM HTH.PROJECT WHERE ID = ?");
        ps.setInt(1, pK);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int idStatus = rs.getInt("id_status_detail");
            java.sql.Date startingDate = rs.getDate("starting_Date");
            java.sql.Date endingDate = rs.getDate("ending_date");
            projectTO = new ProjectTO(id, name, idStatus, startingDate, endingDate);
        }
        close(rs);
        close(ps);
        close(conn);
        return projectTO;
    }

    
    public List<ProjectTO> getProjects() throws Exception {
        Connection conn = getConnection();
        List<ProjectTO> listaRetorno = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT ID, NAME, ID_STATUS_DETAIL, STARTING_DATE, ENDING_DATE FROM HTH.PROJECT");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("ID");
            String name = rs.getString("NAME");
            int status = rs.getInt("ID_STATUS_DETAIL");
            Date startingDate = rs.getDate("STARTING_DATE");
            Date endingDate = rs.getDate("ENDING_DATE");
           
            ProjectTO project = new ProjectTO(id, name, status, startingDate, endingDate);
            listaRetorno.add(project);
        }
        close(rs);
        close(ps);
        close(conn);
        return listaRetorno;
    }
    
    public List<ProjectTO> getMYProjects(int pk) throws Exception {
        Connection conn = getConnection();
        List<ProjectTO> listaRetorno = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT project.id, project.name, project.id_status_detail, project.starting_date, project.ending_date \n"
                + "FROM hth.project, hth.project_x_employee\n"
                + "WHERE hth.project_x_employee.id_employee = ?\n"
                + "and project.id = hth.project_x_employee.id_project");
        ps.setInt(1, pk);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("ID");
            String name = rs.getString("NAME");
            int status = rs.getInt("ID_STATUS_DETAIL");
            Date startingDate = rs.getDate("STARTING_DATE");
            Date endingDate = rs.getDate("ENDING_DATE");
           
            ProjectTO project = new ProjectTO(id, name, status, startingDate, endingDate);
            listaRetorno.add(project);
        }
        close(rs);
        close(ps);
        close(conn);
        return listaRetorno;
    }

}
