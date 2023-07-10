
package com.mycompany.edu.ulatina.hth_db_connetion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService extends Service implements ICrud<EmployeeTO>{

    public EmployeeService() {
    }
    
    @Override
    public void insert(EmployeeTO emp) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO HTH.EMPLOYEE (ID, first_name, last_name, IDENTIFICATION, EMAIL, PHONE, id_type_detail, id_status_detail, PASSWORD, employment_date)VALUES(?,?,?,?,?,?,?,?,?,?)");
        int id = 0;
        ps.setInt(1, id);
        ps.setString(2, emp.getFirstName());
        ps.setString(3, emp.getLastName());
        ps.setString(4, emp.getIdentification());
        ps.setString(5, emp.getEmail());
        ps.setString(6, emp.getPhone());
        ps.setInt(7,emp.getType());
        ps.setInt(8,emp.getStatus());
        ps.setString(9, emp.getPassword());
        ps.setDate(10, emp.getEmploymentDate());
        ps.executeUpdate();
        close(ps);
        close(conn);
    }
    
    public void insert(String firstName, String lastName, String identification, String email, String phone, int type, int status, String password, Date employmentDate) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO HTH.EMPLOYEE (ID, first_name, last_name, IDENTIFICATION, EMAIL, PHONE, id_type_detail, id_status_detail, PASSWORD, employment_date)VALUES(?,?,?,?,?,?,?,?,?,?)");
        
        int id = 0;
        ps.setInt(1, id);
        ps.setString(2, firstName);
        ps.setString(3,lastName);
        ps.setString(4, identification);
        ps.setString(5, email);
        ps.setString(6, phone);
        ps.setInt(7,type);
        ps.setInt(8,status);
        ps.setString(9, password);
        ps.setDate(10, employmentDate);
        ps.executeUpdate();
        close(ps);
    }

    @Override
    public void delete(EmployeeTO employeeTO) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = getConn().prepareStatement("DELETE FROM HTH.EMPLOYEE WHERE identification=?");
        ps.setString(1, employeeTO.getIdentification());

        ps.executeUpdate();

        super.close(ps);
        super.close(conn);

    }
    public void delete(String identificacion) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = getConn().prepareStatement("DELETE FROM HTH.EMPLOYEE WHERE identification=?");
        ps.setString(1, identificacion);

        ps.executeUpdate();

        super.close(ps);
        super.close(conn);

    }
    public void delete(int id) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = getConn().prepareStatement("DELETE FROM HTH.EMPLOYEE WHERE id=?");
        ps.setInt(1, id);

        ps.executeUpdate();

        super.close(ps);
        super.close(conn);

    }

   public void update(EmployeeTO emp, String firstName, String lastName, String identification, String email, String phone,  int type, int status, String password, Date employmentDate, Date layoffDate) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE HTH.EMPLOYEE SET first_name = ?, last_name = ?, IDENTIFICATION = ?, EMAIL = ? , PHONE = ? , id_type_detail = ? , id_status_detail = ? ,  PASSWORD = ?, employment_date = ?, layoff_date = ? WHERE ID = ?");
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setString(3, identification);
        ps.setString(4, email);
        ps.setString(5, phone);
        ps.setInt(6,type);
        ps.setInt(7,status);
        ps.setString(8, password);
        ps.setDate(9, employmentDate);
        ps.setDate(10, layoffDate);
        ps.setInt(11, emp.getId());
        ps.executeUpdate();
        close(ps);
        close(conn);
        close(conn);
    }

    public EmployeeTO searchByPK(int idToSearch) throws Exception {
        Connection conn = getConnection();
        EmployeeTO employee = null;
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM HTH.EMPLOYEE WHERE ID = ?");
        ps.setInt(1, idToSearch);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString("FIRST_NAME");
            String lastName = rs.getString("LAST_NAME");
            String identification = rs.getString("IDENTIFICATION");
            String email = rs.getString("EMAIL");
            String phone = rs.getString("PHONE");
            int type = rs.getInt("ID_TYPE_DETAIL");
            int status = rs.getInt("ID_STATUS_DETAIL");
            String password = rs.getString("PASSWORD");
            Date employmentDate = rs.getDate("eMPLOYMENT_DATE");
            if(rs.getDate("LAYOFF_DATE") != null){
                Date layoffDate = rs.getDate("LAYOFF_DATE");
                employee = new EmployeeTO(id, firstName, lastName, identification, email, phone,type, status, password, layoffDate, employmentDate);
            }else{
                employee = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, employmentDate);
            }
        }
        close(rs);
        close(ps);
        close(conn);
        return employee;
    }
    
    public EmployeeTO searchByEmail(String emailToSearch) throws Exception {
        Connection conn = getConnection();
        EmployeeTO employee = null;
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM HTH.EMPLOYEE WHERE EMAIL = ?");
        ps.setString(1, emailToSearch);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString("FIRST_NAME");
            String lastName = rs.getString("LAST_NAME");
            String identification = rs.getString("IDENTIFICATION");
            String email = rs.getString("EMAIL");
            String phone = rs.getString("PHONE");
            int type = rs.getInt("ID_TYPE_DETAIL");
            int status = rs.getInt("ID_STATUS_DETAIL");
            String password = rs.getString("PASSWORD");
            Date employmentDate = rs.getDate("eMPLOYMENT_DATE");
            if(rs.getDate("LAYOFF_DATE") != null){
                Date layoffDate = rs.getDate("LAYOFF_DATE");
                employee = new EmployeeTO(id, firstName, lastName, identification, email, phone,type, status, password, layoffDate, employmentDate);
            }else{
                employee = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, employmentDate);
            }
        }
        close(rs);
        close(ps);
        close(conn);
        return employee;
    }
    
    public EmployeeTO searchByIdentification(String identificationToSearch) throws Exception {
        Connection conn = getConnection();
        EmployeeTO employee = null;
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM HTH.EMPLOYEE WHERE IDENTIFICATION = ?");
        ps.setString(1, identificationToSearch);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString("FIRST_NAME");
            String lastName = rs.getString("LAST_NAME");
            String identification = rs.getString("IDENTIFICATION");
            String email = rs.getString("EMAIL");
            String phone = rs.getString("PHONE");
            int type = rs.getInt("ID_TYPE_DETAIL");
            int status = rs.getInt("ID_STATUS_DETAIL");
            String password = rs.getString("PASSWORD");
            Date employmentDate = rs.getDate("eMPLOYMENT_DATE");
            if(rs.getDate("LAYOFF_DATE") != null){
                Date layoffDate = rs.getDate("LAYOFF_DATE");
                employee = new EmployeeTO(id, firstName, lastName, identification, email, phone,type, status, password, layoffDate, employmentDate);
            }else{
                employee = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, employmentDate);
            }
            
        }
        close(rs);
        close(ps);
        close(conn);
        return employee;
    }

    public List<EmployeeTO> getEmployees() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        List<EmployeeTO> retorno = new ArrayList<EmployeeTO>();

        ps = getConn().prepareStatement("SELECT * FROM HTH.EMPLOYEE");
        rs = ps.executeQuery();
        while (rs.next()) {
            EmployeeTO employeeTO;
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String identification = rs.getString("identification");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            int type = rs.getInt("id_type_detail");
            int status = rs.getInt("id_status_detail");
            String password = rs.getString("password");
            Date employmentDate = rs.getDate("employment_Date");
            if(rs.getDate("layoff_date") != null){
                Date layoffDate = rs.getDate("layoff_Date");
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone,type, status, password, layoffDate, employmentDate);
            }else{
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, employmentDate);
            }
            retorno.add(employeeTO);

        }

        super.close(rs);
        super.close(ps);
        super.close(conn);

        return retorno;
    }

    public EmployeeTO login(String emailToLogin, String passwordToLogin) throws Exception {
        Connection conn = getConnection();
        EmployeeTO employeeTO = null;
        PreparedStatement ps = getConn().prepareStatement("SELECT * FROM HTH.EMPLOYEE where email= ? AND password= ?");
        ps.setString(1, emailToLogin);
        ps.setString(2, passwordToLogin);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String identification = rs.getString("identification");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            int type = rs.getInt("id_type_detail");
            int status = rs.getInt("id_status_detail");
            String password = rs.getString("password");
            Date employmentDate = rs.getDate("employment_Date");
            if(rs.getDate("layoff_date") != null){
                Date layoffDate = rs.getDate("layoff_Date");
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone,type, status, password, layoffDate, employmentDate);
            }else{
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, employmentDate);
            }
            super.close(rs);
            super.close(ps);
            super.close(conn);
            return employeeTO;

        }

        return null;
    }
    
    public List<EmployeeTO> getActiveEmployees() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        List<EmployeeTO> retorno = new ArrayList<EmployeeTO>();
        ps = getConn().prepareStatement("SELECT * FROM HTH.EMPLOYEE WHERE id_status_detail = 5");
        rs = ps.executeQuery();
        while (rs.next()) {
            EmployeeTO employeeTO;
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String identification = rs.getString("identification");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            int type = rs.getInt("id_type_detail");
            int status = rs.getInt("id_status_detail");
            String password = rs.getString("password");
            Date employmentDate = rs.getDate("employment_Date");
            if(rs.getDate("layoff_date") != null){
                Date layoffDate = rs.getDate("layoff_Date");
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone,type, status, password, layoffDate, employmentDate);
            }else{
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, employmentDate);
            }
            retorno.add(employeeTO);

        }
        super.close(rs);
        super.close(ps);
        super.close(conn);
        return retorno;
    }

}

    
     




