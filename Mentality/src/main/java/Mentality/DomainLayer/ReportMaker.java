package Mentality.DomainLayer;

import Mentality.Runner;
import Mentality.utils.CronScheduler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportMaker {
    ReportObj report;

    //ReportMaker is a singleton so we will need to set the report in
    //a separate function instead of constructor
    public void setReport(ReportObj report) {
        this.report = report;
    }

    public ReportMaker(){}

    // static variable single_instance of type Singleton
    private static ReportMaker single_instance = null;

    // static method to create instance of Singleton class
    public static ReportMaker getInstance()
    {
        if (single_instance == null)
            single_instance = new ReportMaker();

        return single_instance;
    }

    public ReportObj getReportObj() {
        return report;
    }

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
                exerciseObj.setDescription(rs.getString("description"));
                report.exerciseObjs.add(exerciseObj);
                System.out.println(exerciseObj.getActivity() + " " + exerciseObj.getDuration() + " " + exerciseObj.getDescription());
            }

            rs.close();

        }catch (SQLException ex){
            System.err.println(ex);
        }
    }

    public void generateSleepData(){
        System.out.println("generating sleep data");
        try {
//            get data from past 7 days from database
            ResultSet rs = Runner.query("SELECT * FROM sleep WHERE email = '" + Runner.getUser().getEmail() +  "'" +
                    "AND date >= curdate() - INTERVAL DAYOFWEEK(curdate())+6 DAY");

            while(rs.next()) {
                SleepObj sleepObj = new SleepObj();
                sleepObj.setDuration(Integer.parseInt(rs.getString("duration")));
                sleepObj.setQuality(Double.parseDouble(rs.getString("quality")));
                report.sleepObjs.add(sleepObj);
                System.out.println(sleepObj.getDuration() + " " + sleepObj.getQuality());
            }

            rs.close();

        }catch (SQLException ex){
            System.err.println(ex);
        }
    }

    public void generatePersonData(){
        System.out.println("generating person data");
        try {
//            get data from past 7 days from database
            ResultSet rs = Runner.query("SELECT * FROM people WHERE email = '" + Runner.getUser().getEmail() +  "'" +
                    "AND date >= curdate() - INTERVAL DAYOFWEEK(curdate())+6 DAY");

            while(rs.next()) {
                PersonObj personObj = new PersonObj();
                personObj.setName(rs.getString("person"));
                report.personObjs.add(personObj);
                System.out.println(personObj.getName());
            }

            rs.close();

            ResultSet r2 = Runner.query("SELECT * FROM people");

            while(r2.next()) {
                PersonObj personObj = new PersonObj();
                personObj.setName(r2.getString("person"));
                System.out.println(personObj.getName() + " " + r2.getString("date"));
            }
            r2.close();

        }catch (SQLException ex){
            System.err.println(ex);
        }
    }

    public void generateMoodData(){
        System.out.println("generating mood data");
        try {
//            get data from past 7 days from database
            ResultSet rs = Runner.query("SELECT * FROM mood WHERE email = '" + Runner.getUser().getEmail() +  "'" +
                    "AND date >= curdate() - INTERVAL DAYOFWEEK(curdate())+6 DAY");

            while(rs.next()) {
                MoodObj moodObj = new MoodObj();
                moodObj.setHappiness(Integer.parseInt(rs.getString("happiness")));
                moodObj.setSadness(Integer.parseInt(rs.getString("sadness")));
                moodObj.setStress(Integer.parseInt(rs.getString("stress")));
                report.moodObjs.add(moodObj);
                System.out.println(moodObj.getHappiness() + " " + moodObj.getSadness() + " " + moodObj.getStress());
            }

            rs.close();

        }catch (SQLException ex){
            System.err.println(ex);
        }
    }


    public void generateReport()  {

        System.out.println("In generate report");
        generateExerciseData();
        generateSleepData();
        generatePersonData();
        generateMoodData();

        }

}
