package Mentality;

class User {
    private String email, pass, name;
    private int id;

    User(String email, String pass) {
        this.email = email;
        this.pass = pass;
        id = email.hashCode();
    }
    User(String email, String pass, String name) {
        this(email, pass);
        this.name = name;
    }
}
