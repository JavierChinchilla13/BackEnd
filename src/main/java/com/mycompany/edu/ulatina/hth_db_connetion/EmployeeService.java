package com.mycompany.edu.ulatina.hth_db_connetion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EmployeeService extends Service implements ICrud<EmployeeTO> {

    public EmployeeService() {
    }

    @Override
    public void insert(EmployeeTO emp) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO HTH.EMPLOYEE (ID, first_name, last_name, IDENTIFICATION, EMAIL, PHONE, id_type_detail, id_status_detail, PASSWORD, employment_date, id_supervisor)VALUES(?,?,?,?,?,?,?,?,?,?,?)");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        int id = 0;
        ps.setInt(1, id);
        ps.setString(2, emp.getFirstName());
        ps.setString(3, emp.getLastName());
        ps.setString(4, emp.getIdentification());
        ps.setString(5, emp.getEmail());
        ps.setString(6, emp.getPhone());
        ps.setInt(7, emp.getType());
        ps.setInt(8, emp.getStatus());
        ps.setString(9, emp.getPassword());
        ps.setDate(10, date);
        ps.setInt(11, emp.getIdSupervisor());
        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    public void insert(String firstName, String lastName, String identification, String email, String phone, int type, int status, String password, Date employmentDate, int idSupervisor) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO HTH.EMPLOYEE (ID, first_name, last_name, IDENTIFICATION, EMAIL, PHONE, id_type_detail, id_status_detail, PASSWORD, employment_date, id_supervisor)VALUES(?,?,?,?,?,?,?,?,?,?,?)");

        int id = 0;
        ps.setInt(1, id);
        ps.setString(2, firstName);
        ps.setString(3, lastName);
        ps.setString(4, identification);
        ps.setString(5, email);
        ps.setString(6, phone);
        ps.setInt(7, type);
        ps.setInt(8, status);
        ps.setString(9, password);
        ps.setDate(10, employmentDate);
        ps.setInt(11, idSupervisor);
        ps.executeUpdate();
        VacationService v = new VacationService();
        v.insert(id, 0);
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

    public void rehire(int id) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = getConn().prepareStatement("update hth.employee set hth.employee.layoff_date = null where hth.employee.id = ?");
        ps.setInt(1, id);

        ps.executeUpdate();

        super.close(ps);
        super.close(conn);

    }

    public void insertVac() throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = getConn().prepareStatement("INSERT INTO HTH.VACATION (id_employee, vacations_days)\n"
                + "SELECT HTH.EMPLOYEE.id, 0\n"
                + "FROM HTH.EMPLOYEE where HTH.EMPLOYEE.id NOT IN (SELECT id_employee FROM hth.vacation)");

        ps.executeUpdate();

        super.close(ps);
        super.close(conn);

    }

    public void update1() throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = getConn().prepareStatement("UPDATE HTH.VACATION \n"
                + "INNER JOIN HTH.EMPLOYEE\n"
                + "ON HTH.VACATION.id_employee= HTH.EMPLOYEE.id\n"
                + "SET HTH.VACATION.vacations_days =  DATEDIFF(current_date(), HTH.EMPLOYEE.employment_date)/(30.416/0.8)\n"
                + "WHERE HTH.VACATION.id_employee= HTH.EMPLOYEE.id AND DATEDIFF(current_date(), HTH.EMPLOYEE.employment_date)< 365");

        ps.executeUpdate();

        super.close(ps);
        super.close(conn);

    }

    public void update365() throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = getConn().prepareStatement("UPDATE HTH.VACATION \n"
                + "INNER JOIN HTH.EMPLOYEE\n"
                + "ON HTH.VACATION.id_employee= HTH.EMPLOYEE.id\n"
                + "SET HTH.VACATION.vacations_days =  (DATEDIFF(current_date(), HTH.EMPLOYEE.employment_date)-365)/(30.416/1)+12\n"
                + "WHERE HTH.VACATION.id_employee= HTH.EMPLOYEE.id AND DATEDIFF(current_date(), HTH.EMPLOYEE.employment_date)> 365");

        ps.executeUpdate();

        super.close(ps);
        super.close(conn);

    }

    public void update(EmployeeTO emp, String firstName, String lastName, String identification, String email, String phone, int type, int status, String password, Date employmentDate, Date layoffDate, int idSupervisor) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE HTH.EMPLOYEE SET first_name = ?, last_name = ?, IDENTIFICATION = ?, EMAIL = ? , PHONE = ? , id_type_detail = ? , id_status_detail = ? ,  PASSWORD = ?, employment_date = ?, layoff_date = ?, id_supervisor = ? WHERE ID = ?");
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setString(3, identification);
        ps.setString(4, email);
        ps.setString(5, phone);
        ps.setInt(6, type);
        ps.setInt(7, status);
        ps.setString(8, password);
        ps.setDate(9, employmentDate);
        ps.setDate(10, layoffDate);
        ps.setInt(11, idSupervisor);
        ps.setInt(12, emp.getId());
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
            int idSupervisor = rs.getInt("id_supervisor");
            if (rs.getDate("LAYOFF_DATE") != null) {
                Date layoffDate = rs.getDate("LAYOFF_DATE");
                employee = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, layoffDate, employmentDate, idSupervisor);
            } else {
                employee = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, employmentDate, idSupervisor);
            }
        }
        close(rs);
        close(ps);
        close(conn);
        return employee;
    }

    public String searchByPKName(int idToSearch) throws Exception {
        Connection conn = getConnection();
        String supervisor = "";
        EmployeeTO employee = null;
        PreparedStatement ps = conn.prepareStatement("SELECT first_name, last_name FROM HTH.EMPLOYEE WHERE ID = ?");
        ps.setInt(1, idToSearch);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            String firstName = rs.getString("FIRST_NAME");
            String lastName = rs.getString("LAST_NAME");

            supervisor = firstName + " " + lastName;

        }
        close(rs);
        close(ps);
        close(conn);
        return supervisor;
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
            int idSupervisor = rs.getInt("ID_SUPERVISOR");
            if (rs.getDate("LAYOFF_DATE") != null) {
                Date layoffDate = rs.getDate("LAYOFF_DATE");
                employee = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, layoffDate, employmentDate, idSupervisor);
            } else {
                employee = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, employmentDate, idSupervisor);
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
            int idSupervisor = rs.getInt("ID_SUPERVISOR");
            if (rs.getDate("LAYOFF_DATE") != null) {
                Date layoffDate = rs.getDate("LAYOFF_DATE");
                employee = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, layoffDate, employmentDate, idSupervisor);
            } else {
                employee = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, employmentDate, idSupervisor);
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
            int idSupervisor = rs.getInt("ID_SUPERVISOR");
            if (rs.getDate("layoff_date") != null) {
                Date layoffDate = rs.getDate("layoff_Date");
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, layoffDate, employmentDate, idSupervisor);
            } else {
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, employmentDate, idSupervisor);
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
        PreparedStatement ps = getConn().prepareStatement("SELECT * FROM HTH.EMPLOYEE where email= ? AND password= ? AND id_status_detail != 6");
        ps.setString(1, emailToLogin);
        ps.setString(2, passwordToLogin);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            insertVac();
            update1();
            update365();

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
            int idSupervisor = rs.getInt("ID_SUPERVISOR");
            if (rs.getDate("layoff_date") != null) {
                Date layoffDate = rs.getDate("layoff_Date");
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, layoffDate, employmentDate, idSupervisor);
            } else {
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, employmentDate, idSupervisor);
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
            int idSupervisor = rs.getInt("ID_SUPERVISOR");
            if (rs.getDate("layoff_date") != null) {
                Date layoffDate = rs.getDate("layoff_Date");
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, layoffDate, employmentDate, idSupervisor);
            } else {
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, employmentDate, idSupervisor);
            }
            retorno.add(employeeTO);

        }
        super.close(rs);
        super.close(ps);
        super.close(conn);
        return retorno;
    }
    
    public List<EmployeeTO> getSubordinates(int pk) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        List<EmployeeTO> retorno = new ArrayList<EmployeeTO>();
        ps = getConn().prepareStatement("SELECT * FROM HTH.EMPLOYEE WHERE id_supervisor = ?");
        ps.setInt(1, pk);
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
            int idSupervisor = rs.getInt("ID_SUPERVISOR");
            if (rs.getDate("layoff_date") != null) {
                Date layoffDate = rs.getDate("layoff_Date");
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, layoffDate, employmentDate, idSupervisor);
            } else {
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, employmentDate, idSupervisor);
            }
            retorno.add(employeeTO);

        }
        super.close(rs);
        super.close(ps);
        super.close(conn);
        return retorno;
    }

    public List<EmployeeTO> getSuspendedEmployees() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        List<EmployeeTO> retorno = new ArrayList<EmployeeTO>();
        ps = getConn().prepareStatement("SELECT * FROM HTH.EMPLOYEE WHERE id_status_detail = 6");
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
            int idSupervisor = rs.getInt("ID_SUPERVISOR");
            if (rs.getDate("layoff_date") != null) {
                Date layoffDate = rs.getDate("layoff_Date");
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, layoffDate, employmentDate, idSupervisor);
            } else {
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, employmentDate, idSupervisor);
            }
            retorno.add(employeeTO);

        }
        super.close(rs);
        super.close(ps);
        super.close(conn);
        return retorno;
    }

    public List<EmployeeTO> getNotSuspended() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        List<EmployeeTO> retorno = new ArrayList<EmployeeTO>();

        ps = getConn().prepareStatement("SELECT * FROM HTH.EMPLOYEE WHERE id_status_detail = 4 OR id_status_detail = 5");
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
            int idSupervisor = rs.getInt("ID_SUPERVISOR");
            if (rs.getDate("layoff_date") != null) {
                Date layoffDate = rs.getDate("layoff_Date");
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, layoffDate, employmentDate, idSupervisor);
            } else {
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, employmentDate, idSupervisor);
            }
            retorno.add(employeeTO);

        }

        super.close(rs);
        super.close(ps);
        super.close(conn);

        return retorno;
    }
    public List<EmployeeTO> getSupervisor() throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        List<EmployeeTO> retorno = new ArrayList<EmployeeTO>();

        ps = getConn().prepareStatement("SELECT * FROM HTH.EMPLOYEE WHERE id_type_detail = 1 OR id_type_detail = 2");
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
            int idSupervisor = rs.getInt("ID_SUPERVISOR");
            if(rs.getDate("layoff_date") != null){
                Date layoffDate = rs.getDate("layoff_Date");
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone,type, status, password, layoffDate, employmentDate, idSupervisor);
            }else{
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, employmentDate, idSupervisor);
            }
            retorno.add(employeeTO);

        }

        super.close(rs);
        super.close(ps);
        super.close(conn);

        return retorno;
    }

    public List<EmployeeTO> getEmployeesFromProyect(int idProject) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        List<EmployeeTO> retorno = new ArrayList<EmployeeTO>();
        ps = getConn().prepareStatement("SELECT DISTINCT hth.employee.id, first_name, last_name, identification, email, phone, id_type_detail, id_status_detail, hth.employee.password, layoff_date, employment_date, id_supervisor FROM HTH.PROJECT_X_EMPLOYEE INNER JOIN HTH.EMPLOYEE ON HTH.PROJECT_X_EMPLOYEE.ID_EMPLOYEE = HTH.EMPLOYEE.ID WHERE ID_PROJECT = ?");
        ps.setInt(1, idProject);
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
            int idSupervisor = rs.getInt("id_supervisor");
            if (rs.getDate("layoff_date") != null) {
                Date layoffDate = rs.getDate("layoff_Date");
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, layoffDate, employmentDate, idSupervisor);
            } else {
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, employmentDate, idSupervisor);
            }
            retorno.add(employeeTO);

        }
        super.close(rs);
        super.close(ps);
        super.close(conn);
        return retorno;
    }

    public List<EmployeeTO> getEmployeesNotOnProyect(int idProject) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        List<EmployeeTO> retorno = new ArrayList<>();
        ps = getConn().prepareStatement("SELECT  hth.employee.id, first_name, last_name, identification, email, phone, id_type_detail, id_status_detail, hth.employee.password, layoff_date, employment_date, id_supervisor FROM HTH.EMPLOYEE LEFT JOIN HTH.PROJECT_X_EMPLOYEE ON HTH.EMPLOYEE.id = HTH.PROJECT_X_EMPLOYEE.ID_EMPLOYEE WHERE HTH.PROJECT_X_EMPLOYEE.ID_EMPLOYEE IS NULL UNION SELECT hth.employee.id, first_name, last_name, identification, email, phone, id_type_detail, id_status_detail, hth.employee.password, layoff_date, employment_date, id_supervisor FROM HTH.PROJECT_X_EMPLOYEE INNER JOIN HTH.EMPLOYEE ON HTH.PROJECT_X_EMPLOYEE.ID_EMPLOYEE = HTH.EMPLOYEE.ID WHERE ID_PROJECT != ?");
        ps.setInt(1, idProject);
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
            int idSupervisor = rs.getInt("ID_SUPERVISOR");
            if (rs.getDate("layoff_date") != null) {
                Date layoffDate = rs.getDate("layoff_Date");
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, layoffDate, employmentDate, idSupervisor);
            } else {
                employeeTO = new EmployeeTO(id, firstName, lastName, identification, email, phone, type, status, password, employmentDate, idSupervisor);
            }
            retorno.add(employeeTO);

        }
        super.close(rs);
        super.close(ps);
        super.close(conn);
        return retorno;
    }
    
    public int searchIdByEmailPass(String emailToSearch, String passwordToSearch) throws Exception {
        Connection conn = getConnection();
        EmployeeTO employee = null;
        PreparedStatement ps = conn.prepareStatement("SELECT E.ID FROM HTH.EMPLOYEE E WHERE E.EMAIL = ? AND E.PASSWORD = ?");
        ps.setString(1, emailToSearch);
        ps.setString(2, passwordToSearch);
        ResultSet rs = ps.executeQuery();
        int result = 0;
        if (rs.next()) {
            result = rs.getInt("id");
        }
        close(rs);
        close(ps);
        close(conn);
        return result;
    }

}
