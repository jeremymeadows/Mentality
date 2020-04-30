package Mentality.DomainLayer;

import java.util.*;

public class ReportObj {
    public ReportObj(){}

    protected List<ExerciseObj> exerciseObjs = new ArrayList();
    protected List<SleepObj> sleepObjs = new ArrayList();
    protected List<MoodObj> moodObjs = new ArrayList();
    protected List<PersonObj> personObjs = new ArrayList();

    double averageHappiness = 0, averageSadness = 0, averageStress = 0;
    String bestWorkout, worstWorkout;
    double workoutBestMood, workoutWorstMood;
    double avgSleep = 0;
    double avgSleepQ;
    String bestPerson, worstPerson;
    double bestPersonMood, worstPersonMood;


    public double getAverageHappiness() {
        return averageHappiness;
    }


    public double getAverageSadness() {
        return averageSadness;
    }

    public double getAverageStress() {
        return averageStress;
    }


    public String getBestWorkout() {
        return bestWorkout;
    }

    public double getWorkoutBestMood() {
        return workoutBestMood;
    }

    public String getWorstWorkout() {
        return worstWorkout;
    }

    public double getWorkoutWorstMood() {
        return workoutWorstMood;
    }

    public double getAvgSleep() {
        return avgSleep;
    }

    public double getAvgSleepQ() {
        return avgSleepQ;
    }

    public String getBestPerson() {
        return bestPerson;
    }

    public String getWorstPerson() {
        return worstPerson;
    }

    public double getBestPersonMood() {
        return bestPersonMood;
    }

    public double getWorstPersonMood() {
        return worstPersonMood;
    }

    public void averageMoods(){
        int sumHappy = 0, sumSad = 0, sumStress = 0;

        for (MoodObj mood : moodObjs){
            sumHappy += mood.getHappiness();
            sumSad += mood.getSadness();
            sumStress += mood.getStress();
        }

        if (moodObjs.size() > 0) {
            averageHappiness = sumHappy / moodObjs.size();
            averageSadness = sumSad / moodObjs.size();
            averageStress = sumStress / moodObjs.size();
        }

    }

    //to determine the best exercise, we will take the average happiness score of each type
    //of workout
    HashMap<String, Integer> activityToCount = new HashMap<String, Integer>();
    HashMap<String, Integer> activityToHappySum = new HashMap<String, Integer>();
    HashMap<String, Double> activityToHappyAvg = new HashMap<String, Double>();



    public void workoutInfo(){
        for (ExerciseObj exerciseObj : exerciseObjs){
            //find average happiness score of each type of exercise
            int count = activityToCount.containsKey(exerciseObj.getActivity()) ? activityToCount.get(exerciseObj.getActivity()) : 0;
            activityToCount.put(exerciseObj.getActivity(), count + 1);

            int sum = activityToHappySum.containsKey(exerciseObj.getActivity()) ? activityToHappySum.get(exerciseObj.getActivity()) + exerciseObj.getMood().getHappiness(): exerciseObj.getMood().getHappiness();
            activityToHappySum.put(exerciseObj.getActivity(), sum);
        }

        double max = 0;
        double min = 11;
        for (String workout: activityToCount.keySet()){
            String key = workout;
            int value = Integer.parseInt(activityToCount.get(workout).toString());
            int sum = Integer.parseInt(activityToHappySum.get(workout).toString());
            double average = 0;
            if (value != 0)
                average = sum / value;

            if (average > max)
                max = average;

            if (average < min )
                min = average;

            activityToHappyAvg.put(workout, average);
        }

        String bestWorkoutString = "";
        String worstWorkoutString = "";

        for (String workout: activityToHappyAvg.keySet()){
            double avg = Double.parseDouble(activityToHappyAvg.get(workout).toString());
            if (avg == max)
                bestWorkoutString += workout + " ";
            if (avg == min)
                worstWorkoutString += workout + " ";

        }
            this.bestWorkout = bestWorkoutString;
            this.workoutBestMood = max;

            this.worstWorkout = worstWorkoutString;
            this.workoutWorstMood = min;
    }

    public void averageSleep(){
        int hours = 0;
        int quality = 0;
        int i = 0;
        for (SleepObj sleep : sleepObjs){
            hours += sleep.getDuration();
            quality += sleep.getQuality();
            i++;
        }
        if (i != 0){
            avgSleep = hours / i;
            avgSleepQ = quality / i;
        }
    }

    //to determine the best person, we will take the average happiness score of each person
    HashMap<String, Integer> personToCount = new HashMap<String, Integer>();
    HashMap<String, Integer> personToHappySum = new HashMap<String, Integer>();
    HashMap<String, Double> personToHappyAvg = new HashMap<String, Double>();



    public void personInfo(){
        for (PersonObj personObj : personObjs){
            //find average happiness score of each type of exercise
            int count = personToCount.containsKey(personObj.getName()) ? personToCount.get(personObj.getName()) : 0;
            personToCount.put(personObj.getName(), count + 1);

            int sum = personToHappySum.containsKey(personObj.getName()) ? personToHappySum.get(personObj.getName()) + personObj.getMood().getHappiness(): personObj.getMood().getHappiness();
            personToHappySum.put(personObj.getName(), sum);
        }

        double max = 0;
        double min = 11;
        for (String workout: personToCount.keySet()){
            String key = workout;
            int value = Integer.parseInt(personToCount.get(workout).toString());
            int sum = Integer.parseInt(personToHappySum.get(workout).toString());
            double average = 0;
            if (value != 0)
                average = sum / value;

            if (average > max)
                max = average;

            if (average < min )
                min = average;

            personToHappyAvg.put(workout, average);
        }

        String bestPersonString = "";
        String worstPersonString = "";

        for (String workout: personToHappyAvg.keySet()){
            double avg = Double.parseDouble(personToHappyAvg.get(workout).toString());
            if (avg == max)
                bestPersonString += workout + " ";
            if (avg == min)
                worstPersonString += workout + " ";

        }
        this.bestPerson = bestPersonString;
        this.bestPersonMood = max;

        this.worstPerson = worstPersonString;
        this.worstPersonMood = min;
    }



}
