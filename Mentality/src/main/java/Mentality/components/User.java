package Mentality.components;

public class User {
    private String email, pass, name, uname;
    private int id;

    public User(String email, String pass) {
        this.email = email;
        this.pass = pass;
        id = email.hashCode();
    }
    public User(String email, String pass, String name, String uname) {
        this(email, pass);
        this.name = name;
        this.uname = uname;
    }

    public String getEmail() { return email; }
    public String getPass() { return pass; }
    public int getId() { return id; }
    public String getName() { return name; }
    public String getUname() { return uname; }

    public void setEmail(String email) { this.email = email; }
    public void setPass(String pass) { this.pass = pass; }
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setUname(String uname) { this.uname = uname; }
}
