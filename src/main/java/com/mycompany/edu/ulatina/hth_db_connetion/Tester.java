package com.mycompany.edu.ulatina.hth_db_connetion;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class Tester {

    public static void main(String[] args) throws SQLException, Exception {

        try {
            ProjectService pServ = new ProjectService();
            EmployeeService eServ = new EmployeeService();
            PermitService per= new PermitService();
            ActivityService act= new ActivityService();
            ScheduleVacationService vac = new ScheduleVacationService();
            FeedbackService fed = new FeedbackService();
            List<EmployeeTO> list = eServ.getEmployeesFromProyect(3);
            List<EmployeeTO> list2 = eServ.getEmployeesNotOnProyect(3);
            
            List<ActivityTO> listAct = act.getSearchActivity(1, 1);
            
            for(ActivityTO u : listAct){
                

            }
            System.out.println("Act ID: " + act.searchByPk(1).getId());
            System.out.println("Act ID act: " + act.searchByPk(1).getIdActivity());
            System.out.println("Act hours: " + act.searchByPk(1).getHours());
            
            System.out.println("Act ID: " + act.searchByPk(2).getId());
            System.out.println("Act ID act: " + act.searchByPk(2).getIdActivity());
            System.out.println("Act hours: " + act.searchByPk(2).getHours());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
