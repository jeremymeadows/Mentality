package Mentality.DomainLayer;

public class PersonObj {
    protected String name;
    protected MoodObj mood = new MoodObj();

    public MoodObj getMood() {
        return mood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
