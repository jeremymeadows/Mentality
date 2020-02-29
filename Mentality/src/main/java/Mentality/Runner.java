package Mentality;

import Mentality.components.Database;
import Mentality.components.MenuBar;
import Mentality.components.User;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Runner extends Thread implements Runnable {
    private static JFrame frame;
    private static Database db;
    private static User user;

    public static User getUser() { return user; }

    private void initDB() throws SQLException {
        db = new Database();
    }

    public static void changeFrame(JPanel f) {
        f.setOpaque(true);
        frame.setContentPane(f);

        frame.pack();
        frame.setSize(new Dimension(1280, 720));
        frame.setLocation(new Point(
                Toolkit.getDefaultToolkit().getScreenSize().width/2-640,
                Toolkit.getDefaultToolkit().getScreenSize().height/2-360));
        frame.setJMenuBar(MenuBar.newMenuBar());
        frame.setVisible(true);
    }

    public static boolean validateLogin(User u) {
        String q = "SELECT * FROM users WHERE id = '" + u.getId() + "';";
        try {
            ResultSet r = db.query(q);
            if (r.next()) {
                if (r.getString("email").equals(u.getEmail()) && r.getString("password").equals(u.getPass())) {
                    u.setName(r.getString("name"));
                    u.setUname(r.getString("username"));
                    u.setId(r.getInt("id"));
                    user = u;
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.err.println("bad connection");
        }
        return false;
    }
    public static boolean validateRegistration(User u) {
        String q = "SELECT * FROM users WHERE email = '" + u.getEmail() + "';";
        try {
            ResultSet r = db.query(q);
            while (r.next()) {
                if (r.getString("email").equals(u.getEmail())) {
                    System.err.println("account already registered");
                    return false;
                }
            }
            q = u.getEmail() + "','" + u.getPass() + "'," + Integer.toUnsignedLong(u.getId()) + ",'" + u.getName() + "','" + u.getUname();
            db.update("INSERT INTO users (email, password, id, name, username) VALUES ('" + q + "');");
            user = u;
        } catch (SQLException ex) {
            System.err.println("bad connection");
            return false;
        }
        return true;
    }

    @Override
    public void run() {
        frame = new JFrame("Mentality");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            initDB();
        } catch (SQLException ex) {
            System.err.println("cannot establish connection to database");
            //System.exit(1);
        }
    }

    public static void logout() {
        user = null;
        changeFrame(new Mentality());
    }

    public static void cleanup() {
        try {
            db.close();
        } catch (SQLException ex) {
            System.err.println("failed to close JDBC connection");
        }
    }
}
