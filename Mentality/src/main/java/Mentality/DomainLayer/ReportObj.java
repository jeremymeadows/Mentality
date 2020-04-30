package Mentality.DomainLayer;

import java.util.ArrayList;
import java.util.List;

public class ReportObj {
    public ReportObj(){};

    protected List<ExerciseObj> exerciseObjs = new ArrayList();
    protected List<SleepObj> sleepObjs = new ArrayList();
    protected List<MoodObj> moodObjs = new ArrayList();
    protected List<PersonObj> personObjs = new ArrayList();


    public List<ExerciseObj> getExerciseObjs() {
        return exerciseObjs;
    }

    public void setExerciseObjs(List<ExerciseObj> exerciseObjs) {
        this.exerciseObjs = exerciseObjs;
    }

    public List<SleepObj> getSleepObjs() {
        return sleepObjs;
    }

    public void setSleepObjs(List<SleepObj> sleepObjs) {
        this.sleepObjs = sleepObjs;
    }

    public List<MoodObj> getMoodObjs() {
        return moodObjs;
    }

    public void setMoodObjs(List<MoodObj> moodObjs) {
        this.moodObjs = moodObjs;
    }

    public List<PersonObj> getPersonObjs() {
        return personObjs;
    }

    public void setPersonObjs(List<PersonObj> personObjs) {
        this.personObjs = personObjs;
    }
}
