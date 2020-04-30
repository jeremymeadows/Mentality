package Mentality.DomainLayer;

import java.util.Date;

public class ExerciseObj {

    public ExerciseObj(){}

    String activity, description;
    int duration;


    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descr) {
        this.description = descr;
    }


    public int getDuration() {
        return duration;
    }

    public void setDuration(int dur) {
        this.duration = dur;
    }

}
