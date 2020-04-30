package Mentality.utils;

import Mentality.DomainLayer.ExerciseObj;
import Mentality.Runner;
import Mentality.components.User;
import Mentality.frames.Exercise;
import Mentality.frames.Report;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportMaker {
    public ReportMaker(){}

    public void generateExerciseData(){
        System.out.println("generating exercise data");
        try {
//            get data from past 7 days from database
            ResultSet rs = Runner.query("SELECT * FROM exercise WHERE email = '" + Runner.getUser().getEmail() +  "'" +
                    "AND date >= curdate() - INTERVAL DAYOFWEEK(curdate())+6 DAY");


            while(rs.next()) {
                ExerciseObj exerciseObj = new ExerciseObj();
                exerciseObj.setActivity(rs.getString("activity"));
                exerciseObj.setDuration(Integer.parseInt(rs.getString("duration")));

                System.out.println(exerciseObj.getActivity() + " " + exerciseObj.getDuration());
            }

            rs.close();


        }catch (SQLException ex){
            System.err.println(ex);
        }
    }


    public void generateReport()  {
        System.out.println("In generate report");
        generateExerciseData();

        }

}
