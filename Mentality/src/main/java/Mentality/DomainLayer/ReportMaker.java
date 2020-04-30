package Mentality.DomainLayer;

import Mentality.Runner;
import Mentality.components.Chart;
import Mentality.utils.CronScheduler;

import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

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
                exerciseObj.setDuration(parseInt(rs.getString("duration")));
                exerciseObj.setDescription(rs.getString("description"));
                try {
                    exerciseObj.mood.setHappiness(parseInt(rs.getString("happiness")));
                    exerciseObj.mood.setSadness(parseInt(rs.getString("sadness")));
                    exerciseObj.mood.setStress(parseInt(rs.getString("stress")));
                }catch (NumberFormatException e){System.err.println(e);}

                report.exerciseObjs.add(exerciseObj);
//                System.out.println(exerciseObj.getActivity() + " " + exerciseObj.getDuration() + " " + exerciseObj.getDescription());
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
                sleepObj.setDuration(parseInt(rs.getString("duration")));
                sleepObj.setQuality(Double.parseDouble(rs.getString("quality")));
                try {
                    sleepObj.mood.setHappiness(parseInt(rs.getString("happiness")));
                    sleepObj.mood.setSadness(parseInt(rs.getString("sadness")));
                    sleepObj.mood.setStress(parseInt(rs.getString("stress")));
                }catch (NumberFormatException e) {System.err.println(e);}

                report.sleepObjs.add(sleepObj);
//                System.out.println(sleepObj.getDuration() + " " + sleepObj.getQuality());
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
                try {
                    personObj.mood.setHappiness(parseInt(rs.getString("happiness")));
                    personObj.mood.setSadness(parseInt(rs.getString("sadness")));
                    personObj.mood.setStress(parseInt(rs.getString("stress")));
                }catch (NumberFormatException e){System.err.println(e);}

                report.personObjs.add(personObj);
//                System.out.println(personObj.getName());
            }

            rs.close();

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

            int i = 0;

            while(rs.next()) {
                MoodObj moodObj = new MoodObj();
                moodObj.setHappiness(parseInt(rs.getString("happiness")));
                moodObj.setSadness(parseInt(rs.getString("sadness")));
                moodObj.setStress(parseInt(rs.getString("stress")));
                report.moodObjs.add(moodObj);
//                System.out.println(moodObj.getHappiness() + " " + moodObj.getSadness() + " " + moodObj.getStress());
            }

            rs.close();

        }catch (SQLException ex){
            System.err.println(ex);
        }
    }

    public void generateHappinessGraph(){
        System.out.println("generating happiness graph data");
        try {
//            get data from past 7 days from database
            ResultSet rs = Runner.query("SELECT happiness FROM mood WHERE email = '" + Runner.getUser().getEmail() +  "'" +
                    "AND date >= curdate() - INTERVAL DAYOFWEEK(curdate())+6 DAY LIMIT 7");

            double data [] = new double[7];
            for (int i = 0; i < 7; i++)
                data[i] = 0;

            int i = 6;
            while(rs.next()) {
                data[i] = parseInt(rs.getString(1));
                System.out.println ("data[i] " + data[i]);
                i--;
            }
            //set the values for the happiness graph
            Chart chart = Chart.getInstance();
            chart.setHappiness(data);


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
