package Mentality.components;

public class User {
    private String email, pass, name;
    private int id;

    public User(String email, String pass) {
        this.email = email;
        this.pass = pass;
        id = email.hashCode();
    }
    public User(String email, String pass, String name) {
        this(email, pass);
        this.name = name;
    }
}
