package Mentality.DomainLayer;

import java.util.ArrayList;
import java.util.List;

public class ReportObj {
    public ReportObj(){}

    protected List<ExerciseObj> exerciseObjs = new ArrayList();
    protected List<SleepObj> sleepObjs = new ArrayList();
    protected List<MoodObj> moodObjs = new ArrayList();
    protected List<PersonObj> personObjs = new ArrayList();

    double averageHappiness = 0, averageSadness = 0, averageStress = 0;

    public double getAverageHappiness() {
        return averageHappiness;
    }


    public double getAverageSadness() {
        return averageSadness;
    }

    public double getAverageStress() {
        return averageStress;
    }

    public void averageMoods(){
        int sumHappy = 0, sumSad = 0, sumStress = 0;

        for (MoodObj mood : moodObjs){
            sumHappy += mood.getHappiness();
            System.out.println ("sumHappy " + sumHappy);
            sumSad += mood.getSadness();
            sumStress += mood.getStress();
        }

        if (moodObjs.size() > 0) {
            averageHappiness = sumHappy / moodObjs.size();
            averageSadness = sumSad / moodObjs.size();
            averageStress = sumStress / moodObjs.size();
        }

        System.out.println ("average happiness " + averageHappiness);
    }


}
