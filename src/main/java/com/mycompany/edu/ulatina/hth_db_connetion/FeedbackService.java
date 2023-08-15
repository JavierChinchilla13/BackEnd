
package com.mycompany.edu.ulatina.hth_db_connetion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FeedbackService extends Service implements ICrud<FeedbackTO> {

    @Override
    public void insert(FeedbackTO feedback) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO HTH.FEEDBACK VALUES(?,?,?,?,?,?,?)");
        
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        ps.setInt(1, 0);
        ps.setString(2, feedback.getName());
        ps.setString(3, feedback.getDescription());
        ps.setDate(4, date);
        ps.setInt(5, feedback.getIdStatus());
        ps.setInt(6, feedback.getIdType());
        ps.setInt(7, feedback.getIdCreator());

        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    public void insert(String name, String description, Date dateOfFeedback, int idStatus, int idType, int idCreator) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO HTH.FEEDBACK VALUES(?,?,?,?,?,?,?)");
        
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        ps.setInt(1, 0);
        ps.setString(2, name);
        ps.setString(3, description);
        ps.setDate(4, date);
        ps.setInt(5, idStatus);
        ps.setInt(6, idType);
        ps.setInt(7, idCreator);

        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    @Override
    public void delete(FeedbackTO feedback) throws Exception {

        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = getConn().prepareStatement("DELETE HTH.FEEDBACK, HTH.feedback_x_employee \n"
                + "FROM HTH.FEEDBACK\n"
                + "JOIN HTH.feedback_x_employee \n"
                + "ON HTH.feedback_x_employee.id_feedback = HTH.FEEDBACK.ID\n"
                + "WHERE HTH.FEEDBACK.ID = ?");
        ps.setInt(1, feedback.getId());

        ps.executeUpdate();

        close(ps);
        close(conn);
    }

    public void delete(int id) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = null;

        ps = getConn().prepareStatement("DELETE HTH.FEEDBACK, HTH.feedback_x_employee \n"
                + "FROM HTH.FEEDBACK\n"
                + "JOIN HTH.feedback_x_employee \n"
                + "ON HTH.feedback_x_employee.id_feedback = HTH.FEEDBACK.ID\n"
                + "WHERE HTH.FEEDBACK.ID = ?");
        ps.setInt(1, id);

        ps.executeUpdate();

        super.close(ps);
        super.close(conn);

    }

    public void update(FeedbackTO feedback, String name, String description, Date dateOfFeedback, int idStatus, int idType, int idCreator) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE HTH.FEEDBACK SET name=?, description = ?, date_of_feedback = ?, id_status = ?, id_Type=?, id_creator=? WHERE id = ?");
        
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        ps.setString(1, name);
        ps.setString(2, description);
        ps.setDate(3, date);
        ps.setInt(4, idStatus);
        ps.setInt(5, idType);
        ps.setInt(6, idCreator);
        ps.setInt(7, feedback.getId());
        ps.executeUpdate();
        close(ps);
        close(conn);
        close(conn);
    }

    public void insertAct(int idEmployee, int idProject) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO hth.feedback_x_project_x_employee (id_employee, id_project, id_feedback)VALUES(?,?,(SELECT MAX(id) \n"
                + "FROM hth.feedback where id_type = '23' ))");

        ps.setInt(1, idEmployee);
        ps.setInt(2, idProject);

        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    public void insertEmp(int idEmployee) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO hth.feedback_x_employee (id_employee, id_feedback)VALUES(?,(SELECT MAX(id) \n" +
"                FROM hth.feedback where id_type = '22' ))");
       
        ps.setInt(1, idEmployee);
        

        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    public void insertPj(int idProject) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO hth.feedback_x_project  (id_project, id_feedback)VALUES(?,(SELECT MAX(id) \n" +
"               FROM hth.feedback where id_type = '21' ))");
       
        ps.setInt(1, idProject);
       

        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    public void update(FeedbackTO feedbackTO) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE HTH.FEEDBACK SET name=?, description = ?, date_of_feedback = ?, id_status = ?, id_Type=?, id_creator=? WHERE id = ?");
        
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        ps.setString(1, feedbackTO.getName());
        ps.setString(2, feedbackTO.getDescription());
        ps.setDate(3, date);
        ps.setInt(4, feedbackTO.getIdStatus());
        ps.setInt(5, feedbackTO.getIdType());
        ps.setInt(6, feedbackTO.getIdCreator());
        ps.setInt(7, feedbackTO.getId());
        ps.executeUpdate();
        close(ps);
        close(conn);
    }

    public FeedbackTO searchByPK(int pK) throws Exception {
        Connection conn = getConnection();
        FeedbackTO feedbackTO = null;
        PreparedStatement ps = conn.prepareStatement("SELECT ID, NAME, DESCRIPTION, DATE_OF_FEEDBACK, ID_STATUS, ID_TYPE, ID_CREATOR FROM HTH.FEEDBACK WHERE ID = ?");
        ps.setInt(1, pK);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("ID");
            String name = rs.getString("NAME");
            String description = rs.getString("DESCRIPTION");
            Date dateOfFeedback = rs.getDate("DATE_OF_FEEDBACK");
            int idStatus = rs.getInt("ID_STATUS");
            int idType = rs.getInt("ID_TYPE");
            int idCreator = rs.getInt("ID_CREATOR");

            feedbackTO = new FeedbackTO(id, name, description, dateOfFeedback, idStatus, idType, idCreator);
        }
        close(rs);
        close(ps);
        close(conn);
        return feedbackTO;
    }

    public List<FeedbackTO> getFeedback() throws Exception {
        Connection conn = getConnection();
        List<FeedbackTO> feedbackList = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT ID, NAME, DESCRIPTION, DATE_OF_FEEDBACK, ID_STATUS, ID_TYPE, ID_CREATOR FROM HTH.FEEDBACK");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("ID");
            String name = rs.getString("NAME");
            String description = rs.getString("DESCRIPTION");
            Date dateOfFeedback = rs.getDate("DATE_OF_FEEDBACK");
            int idStatus = rs.getInt("ID_STATUS");
            int idType = rs.getInt("ID_TYPE");
            int idCreator = rs.getInt("ID_CREATOR");

            FeedbackTO feedbackTO = new FeedbackTO(id, name, description, dateOfFeedback, idStatus, idType, idCreator);
            feedbackList.add(feedbackTO);
        }
        close(rs);
        close(ps);
        close(conn);
        return feedbackList;
    }
    
    public List<FeedbackTO> getFeedback(int type) throws Exception {
        Connection conn = getConnection();
        List<FeedbackTO> feedbackList = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT ID, NAME, DESCRIPTION, DATE_OF_FEEDBACK, ID_STATUS, ID_TYPE, ID_CREATOR FROM HTH.FEEDBACK WHERE ID_TYPE = ?");
        ps.setInt(1, type);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("ID");
            String name = rs.getString("NAME");
            String description = rs.getString("DESCRIPTION");
            Date dateOfFeedback = rs.getDate("DATE_OF_FEEDBACK");
            int idStatus = rs.getInt("ID_STATUS");
            int idType = rs.getInt("ID_TYPE");
            int idCreator = rs.getInt("ID_CREATOR");

            FeedbackTO feedbackTO = new FeedbackTO(id, name, description, dateOfFeedback, idStatus, idType, idCreator);
            feedbackList.add(feedbackTO);
        }
        close(rs);
        close(ps);
        close(conn);
        return feedbackList;
    }
    
    public List<FeedbackTO> getFeedback(int employee, int project) throws Exception {
        Connection conn = getConnection();
        List<FeedbackTO> feedbackList = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT hth.feedback.id, hth.feedback.name, hth.feedback.description, hth.feedback.date_of_feedback, hth.feedback.id_status, hth.feedback.id_type, id_creator  \n"
                + "FROM hth.feedback, hth.feedback_x_project_x_employee \n"
                + "where id_employee= ? and id_project = ? and hth.feedback.id = id_feedback");
        ps.setInt(1, employee);
        ps.setInt(2, project);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("ID");
            String name = rs.getString("NAME");
            String description = rs.getString("DESCRIPTION");
            Date dateOfFeedback = rs.getDate("DATE_OF_FEEDBACK");
            int idStatus = rs.getInt("ID_STATUS");
            int idType = rs.getInt("ID_TYPE");
            int idCreator = rs.getInt("ID_CREATOR");

            FeedbackTO feedbackTO = new FeedbackTO(id, name, description, dateOfFeedback, idStatus, idType, idCreator);
            feedbackList.add(feedbackTO);
        }
        close(rs);
        close(ps);
        close(conn);
        return feedbackList;
    }
    
    public List<FeedbackTO> getEFeedback(int employee) throws Exception {
        Connection conn = getConnection();
        List<FeedbackTO> feedbackList = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT hth.feedback.id, hth.feedback.description, hth.feedback.name, hth.feedback.date_of_feedback, hth.feedback.id_status, hth.feedback.id_type, hth.feedback.id_creator \n"
                + "FROM hth.feedback, hth.feedback_x_employee\n"
                + "where id_employee = ? and hth.feedback.id = id_feedback");
        ps.setInt(1, employee);
        

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("ID");
            String name = rs.getString("NAME");
            String description = rs.getString("DESCRIPTION");
            Date dateOfFeedback = rs.getDate("DATE_OF_FEEDBACK");
            int idStatus = rs.getInt("ID_STATUS");
            int idType = rs.getInt("ID_TYPE");
            int idCreator = rs.getInt("ID_CREATOR");

            FeedbackTO feedbackTO = new FeedbackTO(id, name, description, dateOfFeedback, idStatus, idType, idCreator);
            feedbackList.add(feedbackTO);
        }
        close(rs);
        close(ps);
        close(conn);
        return feedbackList;
    }
    
    public List<FeedbackTO> getPFeedback(int project) throws Exception {
        Connection conn = getConnection();
        List<FeedbackTO> feedbackList = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT hth.feedback.id, hth.feedback.description, hth.feedback.name, hth.feedback.date_of_feedback, hth.feedback.id_status, hth.feedback.id_type, hth.feedback.id_creator \n" +
"                FROM hth.feedback, hth.feedback_x_project \n" +
"                where id_project = ? and hth.feedback.id = id_feedback");
        ps.setInt(1, project);
        

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("ID");
            String name = rs.getString("NAME");
            String description = rs.getString("DESCRIPTION");
            Date dateOfFeedback = rs.getDate("DATE_OF_FEEDBACK");
            int idStatus = rs.getInt("ID_STATUS");
            int idType = rs.getInt("ID_TYPE");
            int idCreator = rs.getInt("ID_CREATOR");

            FeedbackTO feedbackTO = new FeedbackTO(id, name, description, dateOfFeedback, idStatus, idType, idCreator);
            feedbackList.add(feedbackTO);
        }
        close(rs);
        close(ps);
        close(conn);
        return feedbackList;
    }

}
