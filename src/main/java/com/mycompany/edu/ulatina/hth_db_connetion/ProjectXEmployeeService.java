
package com.mycompany.edu.ulatina.hth_db_connetion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProjectXEmployeeService extends Service implements ICrud<ProjectXEmployeeTO> {

    @Override
    public void insert(ProjectXEmployeeTO projectXEmployeeTO) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO HTH.PROJECT_X_EMPLOYEE VALUES(?,?,?)");
        ps.setInt(1, 0);
        ps.setInt(2, projectXEmployeeTO.getIdProject());
        ps.setInt(3, projectXEmployeeTO.getIdEmployee());
        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    public void insert(int idProject, int idEmployee) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO HTH.PROJECT_X_EMPLOYEE VALUES(?,?,?)");
        ps.setInt(1, 0);
        ps.setInt(2, idProject);
        ps.setInt(3, idEmployee);
        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    @Override
    public void delete(ProjectXEmployeeTO projectXEmployeeTO) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM HTH.PROJECT_X_EMPLOYEE WHERE ID = ?");
        ps.setInt(1, projectXEmployeeTO.getId());
        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    public void delete(int pK) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM HTH.PROJECT_X_EMPLOYEE WHERE ID = ?");
        ps.setInt(1, pK);
        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    public void update(ProjectXEmployeeTO projectXEmployeeTO) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE HTH.PROJECT_X_EMPLOYEE SET ID_PROJECT = ?, ID_EMPLOYEE = ? WHERE ID = ?");
        ps.setInt(1, projectXEmployeeTO.getIdProject());
        ps.setInt(2, projectXEmployeeTO.getIdEmployee());
        ps.setInt(3, projectXEmployeeTO.getId());
        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    public void update(ProjectXEmployeeTO projectXEmployeeTO, int newIdProject, int newIdEmployee) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE HTH.PROJECT_X_EMPLOYEE SET ID_PROJECT = ?, ID_EMPLOYEE = ? WHERE ID = ?");
        ps.setInt(1, newIdProject);
        ps.setInt(2, newIdEmployee);
        ps.setInt(3, projectXEmployeeTO.getId());
        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    public List<ProjectXEmployeeTO> getProjectXEmployeeInfo() throws Exception {
        Connection conn = getConnection();
        List<ProjectXEmployeeTO> projectXEmployeeList = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT ID,ID_PROJECT,ID_EMPLOYEE FROM HTH.PROJECT_X_EMPLOYEE");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            int idProject = rs.getInt("ID_PROJECT");
            int idEmployee = rs.getInt("ID_EMPLOYEE");
            ProjectXEmployeeTO projectXEmployeeTO = new ProjectXEmployeeTO(id, idProject, idEmployee);
            projectXEmployeeList.add(projectXEmployeeTO);
        }
        close(rs);
        close(ps);
        close(conn);
        return projectXEmployeeList;
    }

    public List<ProjectXEmployeeTO> getPXEInfoFrom(int pk) throws Exception {
        Connection conn = getConnection();
        List<ProjectXEmployeeTO> projectXEmployeeList = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT ID,ID_PROJECT,ID_EMPLOYEE FROM HTH.PROJECT_X_EMPLOYEE WHERE ID = ?");
        ps.setInt(1, pk);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("ID");
            int idProject = rs.getInt("ID_PROJECT");
            int idEmployee = rs.getInt("ID_EMPLOYEE");

            ProjectXEmployeeTO projectEmployeeTO = new ProjectXEmployeeTO(id, idProject, idEmployee);
            projectXEmployeeList.add(projectEmployeeTO);
        }

        close(rs);
        close(ps);
        close(conn);

        return projectXEmployeeList;
    }

    public ProjectXEmployeeTO searchPXEByPk(int pK) throws Exception {
        Connection conn = getConnection();
        ProjectXEmployeeTO projectXEmployeeTO = null;
        PreparedStatement ps = conn.prepareStatement("SELECT ID,ID_PROJECT,ID_EMPLOYEE FROM HTH.PROJECT_X_EMPLOYEE WHERE ID = ?");
        ps.setInt(1, pK);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            int projectId = rs.getInt("id_project");
            int idEmployee = rs.getInt("id_employee");
            projectXEmployeeTO = new ProjectXEmployeeTO(id, projectId, idEmployee);
        }
        close(rs);
        close(ps);
        close(conn);
        return projectXEmployeeTO;
    }

    public List<ProjectXEmployeeTO> getProjectEmployeeById(int employeeId) throws Exception {
        Connection conn = getConnection();
        List<ProjectXEmployeeTO> projectXEmployeeList = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT pxe.ID, pxe.ID_PROJECT, pxe.id_employee "
                + "FROM project_x_employee pxe "
                + "INNER JOIN project p ON pxe.id_project = p.id "
                + "WHERE pxe.id_employee = ?");
        ps.setInt(1, employeeId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            int projectId = rs.getInt("id_project");
            int idEmployee = rs.getInt("id_employee");

            ProjectXEmployeeTO projectXEmployeeTO = new ProjectXEmployeeTO(id, projectId, idEmployee);
            projectXEmployeeList.add(projectXEmployeeTO);
        }

        close(rs);
        close(ps);
        close(conn);

        return projectXEmployeeList;
    }

    public void deleteEmployeeFromProject(int pK) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM HTH.PROJECT_X_EMPLOYEE WHERE ID_EMPLOYEE = ?");
        ps.setInt(1, pK);
        ps.executeUpdate();
        close(ps);
        close(conn);
    }
    
}
