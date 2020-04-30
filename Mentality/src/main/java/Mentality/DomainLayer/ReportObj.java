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
            double average = sum / value;
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



}
