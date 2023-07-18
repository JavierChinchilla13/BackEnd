package com.mycompany.edu.ulatina.hth_db_connetion;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Tester {

    public static void main(String[] args) throws SQLException, Exception {

        try {
            ProjectXEmployeeService p = new ProjectXEmployeeService();

            List<ProjectXEmployeeTO> projectEmployeeList = p.getProjectEmployeeById(1);

            for (ProjectXEmployeeTO projectEmployee : projectEmployeeList) {
                System.out.println("ID: " + projectEmployee.getId());
                System.out.println("Project ID: " + projectEmployee.getIdProject());
                System.out.println("Project Name: " + projectEmployee.getProjectName());
                System.out.println("Employee ID: " + projectEmployee.getIdEmployee());
                System.out.println("Hours Invested: " + projectEmployee.getHoursInvested());
                System.out.println("Feedback: " + projectEmployee.getFeedBack());
                System.out.println("--------------------------");
            }
            
            
            
            EmployeeService j = new EmployeeService();
            
            EmployeeTO i = new EmployeeTO(0,"Ja","Chin","12333","ju@mal","",3,5,"1234");
            
            j.insert(i);
            
            j.login("master@gmail.com", "1234");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
