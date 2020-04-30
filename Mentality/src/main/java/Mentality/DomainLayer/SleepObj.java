package Mentality.DomainLayer;

public class SleepObj {

    protected int duration;
    protected double quality;
    protected MoodObj mood = new MoodObj();

    public MoodObj getMood() {
        return mood;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }
}
