
package com.mycompany.edu.ulatina.hth_db_connetion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ScheduleVacationService extends Service implements ICrud<ScheduleVacationTO> {

    public int getVacationIdByEmployeeId(int employeeId) throws Exception {
        Connection conn = getConnection();
        int vacationId = 0;

        PreparedStatement ps = conn.prepareStatement("SELECT ID FROM HTH.VACATION WHERE ID_EMPLOYEE = ?");
        ps.setInt(1, employeeId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            vacationId = rs.getInt("id");
        }

        close(rs);
        close(ps);
        close(conn);

        return vacationId;
    }

    @Override
    public void insert(ScheduleVacationTO scheduleVacationTO) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO HTH.SCHEDULE_VACATION VALUES(?,?,?,?,?,?)");
        ps.setInt(1, 0);
        ps.setInt(2, scheduleVacationTO.getIdVacation());
        ps.setDate(3, scheduleVacationTO.getStartDate());
        ps.setDate(4, scheduleVacationTO.getEndDate());
        ps.setInt(5, scheduleVacationTO.getIdStatus());
        ps.setString(6, scheduleVacationTO.getDescription());
        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    public void insert(int idVacation, Date startDate, Date endDate, int idStatus, String description) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO HTH.SCHEDULE_VACATION VALUES(?,?,?,?,?,?)");
        ps.setInt(1, 0);
        ps.setInt(2, idVacation);
        ps.setDate(3, startDate);
        ps.setDate(4, endDate);
        ps.setInt(5, idStatus);
        ps.setString(6, description);
        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    @Override
    public void delete(ScheduleVacationTO scheduleVacationTO) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM HTH.SCHEDULE_VACATION WHERE ID = ?");
        ps.setInt(1, scheduleVacationTO.getId());
        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    public void delete(int pK) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM HTH.SCHEDULE_VACATION WHERE ID = ?");
        ps.setInt(1, pK);
        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    public void update(ScheduleVacationTO scheduleVacationTO) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE HTH.SCHEDULE_VACATION SET ID_VACATION = ?, START_DATE = ?, END_DATE = ?, ID_STATUS_DETAIL = ?, DESCRIPTION = ? WHERE ID = ?");
        ps.setInt(1, scheduleVacationTO.getIdVacation());
        ps.setDate(2, scheduleVacationTO.getStartDate());
        ps.setDate(3, scheduleVacationTO.getEndDate());
        ps.setInt(4, scheduleVacationTO.getIdStatus());
        ps.setString(5, scheduleVacationTO.getDescription());
        ps.setInt(6, scheduleVacationTO.getId());
        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    public void update(ScheduleVacationTO scheduleVacationTO, int newIdVacation, Date newStarDate, Date newEndDate, int newIdStatus, String newDescription) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE HTH.SCHEDULE_VACATION SET ID_VACATION = ?, START_DATE = ?, END_DATE = ?, ID_STATUS_DETAIL = ?, DESCRIPTION = ? WHERE ID = ?");
        ps.setInt(1, newIdVacation);
        ps.setDate(2, newStarDate);
        ps.setDate(3, newEndDate);
        ps.setInt(4, newIdStatus);
        ps.setString(5, newDescription);
        ps.setInt(6, scheduleVacationTO.getId());
        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    public ScheduleVacationTO searchByPK(int pK) throws Exception {
        Connection conn = getConnection();
        ScheduleVacationTO scheduleVacationTO = null;
        PreparedStatement ps = conn.prepareStatement("SELECT ID,ID_VACATION,START_DATE,END_DATE,ID_STATUS_DETAIL,DESCRIPTION FROM HTH.SCHEDULE_VACATION WHERE ID = ?");
        ps.setInt(1, pK);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            int idVacation = rs.getInt("id_vacation");
            Date startDate = rs.getDate("start_date");
            Date endDate = rs.getDate("end_date");
            int idStatus = rs.getInt("id_status_detail");
            String description = rs.getString("description");
            scheduleVacationTO = new ScheduleVacationTO(id, idVacation, startDate, endDate, idStatus, description);
        }
        close(rs);
        close(ps);
        close(conn);
        return scheduleVacationTO;
    }

    public int getVacationDaysOff(int pK) throws Exception {
        Connection conn = getConnection();
        int vacationDays = 0;
        PreparedStatement ps = conn.prepareStatement("SELECT SUM(DATEDIFF(START_DATE, END_DATE))\n"
                + "FROM HTH.SCHEDULE_VACATION,  HTH.VACATION\n"
                + "WHERE HTH.VACATION.id_employee = ?\n"
                + "AND HTH.VACATION.id = HTH.SCHEDULE_VACATION.id_vacation AND id_status_detail = 15");
        ps.setInt(1, pK);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            vacationDays = rs.getInt("SUM(DATEDIFF(START_DATE, END_DATE))");
        }

        close(rs);
        close(ps);
        close(conn);

        return vacationDays;
    }

    public List<ScheduleVacationTO> getScheduleVacation() throws Exception {
        Connection conn = getConnection();
        List<ScheduleVacationTO> scheduleVacationList = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT ID,ID_VACATION,START_DATE,END_DATE,ID_STATUS_DETAIL,DESCRIPTION FROM HTH.SCHEDULE_VACATION");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            int idVacation = rs.getInt("id_vacation");
            Date startDate = rs.getDate("start_date");
            Date endDate = rs.getDate("end_date");
            int idStatus = rs.getInt("id_status_detail");
            String description = rs.getString("description");
            ScheduleVacationTO scheduleVacationTO = new ScheduleVacationTO(id, idVacation, startDate, endDate, idStatus, description);
            scheduleVacationList.add(scheduleVacationTO);
        }
        close(rs);
        close(ps);
        close(conn);
        return scheduleVacationList;
    }

    public List<ScheduleVacationTO> getScheduleVacationOf(int pK) throws Exception {
        Connection conn = getConnection();
        List<ScheduleVacationTO> scheduleVacationList = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT sv.ID, sv.ID_VACATION, sv.START_DATE, sv.END_DATE, sv.ID_STATUS_DETAIL, sv.DESCRIPTION "
                + "FROM HTH.SCHEDULE_VACATION sv "
                + "INNER JOIN HTH.VACATION v ON sv.ID_VACATION = v.ID "
                + "WHERE v.ID_EMPLOYEE = ?");
        ps.setInt(1, pK);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("ID");
            int idVacation = rs.getInt("ID_VACATION");
            Date startDate = rs.getDate("START_DATE");
            Date endDate = rs.getDate("END_DATE");
            int idStatus = rs.getInt("ID_STATUS_DETAIL");
            String description = rs.getString("DESCRIPTION");
            ScheduleVacationTO scheduleVacationTO = new ScheduleVacationTO(id, idVacation, startDate, endDate, idStatus, description);
            scheduleVacationList.add(scheduleVacationTO);
        }

        close(rs);
        close(ps);
        close(conn);

        return scheduleVacationList;
    }

    public int searchScheduleVacationStatusPK(int pK) throws Exception {
        Connection conn = getConnection();
        int status = 17;
        PreparedStatement ps = conn.prepareStatement("SELECT ID_STATUS_DETAIL FROM HTH.SCHEDULE_VACATION WHERE ID = ?");
        ps.setInt(1, pK);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {

            status = rs.getInt("id_status_detail");
            return status;
        }
        close(rs);
        close(ps);
        close(conn);
        return status;
    }

    public List<ScheduleVacationTO> getScheduleVacationsPending() throws Exception {
        Connection conn = getConnection();
        List<ScheduleVacationTO> scheduleVacationList = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT ID,ID_VACATION,START_DATE,END_DATE,ID_STATUS_DETAIL,DESCRIPTION FROM HTH.SCHEDULE_VACATION WHERE ID_STATUS_DETAIL = ?");
        ps.setInt(1, 17);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("ID");
            int idVacation = rs.getInt("ID_VACATION");
            Date startDate = rs.getDate("START_DATE");
            Date endDate = rs.getDate("END_DATE");
            int idStatus = rs.getInt("ID_STATUS_DETAIL");
            String description = rs.getString("DESCRIPTION");
            ScheduleVacationTO scheduleVacationTO = new ScheduleVacationTO(id, idVacation, startDate, endDate, idStatus, description);
            scheduleVacationList.add(scheduleVacationTO);
        }

        close(rs);
        close(ps);
        close(conn);

        return scheduleVacationList;
    }
    
    public List<ScheduleVacationTO> getScheduleVacationsSupervisor(int supervisor) throws Exception {
        Connection conn = getConnection();
        List<ScheduleVacationTO> scheduleVacationList = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT HTH.SCHEDULE_VACATION.ID,ID_VACATION,START_DATE,END_DATE,HTH.SCHEDULE_VACATION.ID_STATUS_DETAIL,DESCRIPTION \n"
                + "FROM HTH.SCHEDULE_VACATION, HTH.EMPLOYEE, hth.vacation \n"
                + "WHERE HTH.SCHEDULE_VACATION.ID_STATUS_DETAIL = ?  AND id_supervisor = ? AND HTH.EMPLOYEE.id = hth.vacation.id_employee AND hth.vacation.id = HTH.SCHEDULE_VACATION.id_vacation");
        ps.setInt(1, 17);
        ps.setInt(2, supervisor);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("ID");
            int idVacation = rs.getInt("ID_VACATION");
            Date startDate = rs.getDate("START_DATE");
            Date endDate = rs.getDate("END_DATE");
            int idStatus = rs.getInt("ID_STATUS_DETAIL");
            String description = rs.getString("DESCRIPTION");
            ScheduleVacationTO scheduleVacationTO = new ScheduleVacationTO(id, idVacation, startDate, endDate, idStatus, description);
            scheduleVacationList.add(scheduleVacationTO);
        }

        close(rs);
        close(ps);
        close(conn);

        return scheduleVacationList;
    }

    public List<ScheduleVacationTO> getNewScheduleVacation(int pk) throws Exception {
        Connection conn = getConnection();
        List<ScheduleVacationTO> listScheduleVacation = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT SV.ID, SV.ID_VACATION, SV.START_DATE, SV.END_DATE, SV.ID_STATUS_DETAIL, SV.DESCRIPTION "
                + "FROM HTH.SCHEDULE_VACATION SV "
                + "INNER JOIN HTH.VACATION V ON SV.ID_VACATION = V.ID "
                + "WHERE (SV.ID_STATUS_DETAIL = 17 OR current_date() < SV.START_DATE) AND V.ID_EMPLOYEE = ?");
        ps.setInt(1, pk);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("ID");
            int idVacation = rs.getInt("ID_VACATION");
            Date startDate = rs.getDate("START_DATE");
            Date endDate = rs.getDate("END_DATE");
            int idStatus = rs.getInt("ID_STATUS_DETAIL");
            String description = rs.getString("DESCRIPTION");
            ScheduleVacationTO scheduleVacation = new ScheduleVacationTO(id, idVacation, startDate, endDate, idStatus, description);
            listScheduleVacation.add(scheduleVacation);
        }
        close(rs);
        close(ps);
        close(conn);
        return listScheduleVacation;
    }

    public List<ScheduleVacationTO> getOldScheduleVacation(int pk) throws Exception {
        Connection conn = getConnection();
        List<ScheduleVacationTO> listScheduleVacation = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT SV.ID, SV.ID_VACATION, SV.START_DATE, SV.END_DATE, SV.ID_STATUS_DETAIL, SV.DESCRIPTION "
                + "FROM HTH.SCHEDULE_VACATION SV "
                + "INNER JOIN HTH.VACATION V ON SV.ID_VACATION = V.ID "
                + "WHERE V.ID_EMPLOYEE = ? AND SV.ID_STATUS_DETAIL != 17 AND current_date() > SV.START_DATE");
        ps.setInt(1, pk);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("ID");
            int idVacation = rs.getInt("ID_VACATION");
            Date startDate = rs.getDate("START_DATE");
            Date endDate = rs.getDate("END_DATE");
            int idStatus = rs.getInt("ID_STATUS_DETAIL");
            String description = rs.getString("DESCRIPTION");
            ScheduleVacationTO scheduleVacation = new ScheduleVacationTO(id, idVacation, startDate, endDate, idStatus, description);
            listScheduleVacation.add(scheduleVacation);
        }
        close(rs);
        close(ps);
        close(conn);
        return listScheduleVacation;
    }
    
    public String getEmployeeName(int pk) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        String name = " ";
       
        ps = getConn().prepareStatement("Select first_name, last_name \n"
                + "from hth.employee, hth.vacation, hth.schedule_vacation\n"
                + "WHERE employee.id = vacation.id and vacation.id = schedule_vacation.id_vacation AND schedule_vacation.id = ?");
        ps.setInt(1, pk);
        rs = ps.executeQuery();
        while (rs.next()) {
            String first = rs.getString("first_name");
            String last = rs.getString("last_name");
            
            name = first + " " + last;

        }
        super.close(rs);
        super.close(ps);
        super.close(conn);
        return name;
    }

}
