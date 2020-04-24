package Mentality.components;

public class User {
    private String email, pass, nameFirst, nameLast, uname;
    private int id;

    protected User() {
        pass = null;
        id = -1;
    }
    public User(String email, String pass) {
        this.email = email;
        this.pass = pass;
        id = pass.hashCode();
    }
    public User(String email, String pass, String nameFirst, String nameLast, String uname) {
        this(email, pass);
        this.nameFirst = nameFirst;
        this.nameLast = nameLast;
        this.uname = uname;
    }

    public String getEmail() { return email; }
    public String getPass() { return pass; }
    public int getId() { return id; }
    public String getNameFirst() { return nameFirst; }
    public String getNameLast() { return nameLast; }
    public String getUname() { return uname; }

    public void setEmail(String email) { this.email = email; }
    public void setPass(String pass) { this.pass = pass; }
    public void setId(int id) { this.id = id; }
    public void setNameFirst(String nameFirst) { this.nameFirst = nameFirst; }
    public void setNameLast(String nameLast) { this.nameLast = nameLast; }
    public void setUname(String uname) { this.uname = uname; }
}
