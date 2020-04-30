package Mentality;

import Mentality.components.Database;
import Mentality.components.MenuBar;
import Mentality.components.User;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Semaphore;

// Runner is a singleton class which is ran as a separate thread to manage the
// database connection, the current frame being displayed, and the user's info
public class Runner extends Thread implements Runnable {
    private static Runner instance;
    public Semaphore connecting = new Semaphore(0);
    private JFrame frame;
    private Database db;
    private User user;

    private Runner() {}
    public static synchronized Runner getRunnerInstance() {
        if (instance == null) {
            instance = new Runner();
        }
        return instance;
    }

    public static User getUser() { return getRunnerInstance().user; }
    public static JFrame getFrame() { return getRunnerInstance().frame; }

    private void initDB() throws SQLException {
        db = new Database();
    }

    public void changeFrame(JPanel f) {
        f.setOpaque(true);
        getFrame().setContentPane(f);

        getFrame().pack();
        getFrame().setSize(new Dimension(1400, 720));
        getFrame().setLocation(new Point(
                Toolkit.getDefaultToolkit().getScreenSize().width/2-640,
                Toolkit.getDefaultToolkit().getScreenSize().height/2-360));
        getFrame().setJMenuBar(MenuBar.newMenuBar());
        getFrame().setVisible(true);
    }

    public boolean validateLogin(User u) {
        String q = "SELECT * FROM users WHERE id = '" + u.getId() + "';";
        try {
            ResultSet r = getRunnerInstance().db.query(q);
            if (r.next()) {
                if (r.getString("email").equals(u.getEmail()) && r.getString("password").equals(u.getPass())) {
                    u.setNameFirst(r.getString("namefirst"));
                    u.setNameLast(r.getString("namelast"));
                    u.setUname(r.getString("username"));
                    u.setId(r.getInt("id"));
                    getRunnerInstance().user = u;
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.err.println("bad connection");
        }
        return false;
    }
    public boolean validateRegistration(User u) {
        String q = "SELECT * FROM users WHERE email = '" + u.getEmail() + "';";
        try {
            ResultSet r = getRunnerInstance().db.query(q);
            while (r.next()) {
                if (r.getString("email").equals(u.getEmail())) {
                    System.err.println("account already registered");
                    return false;
                }
            }
            q = u.getEmail() + "','" + u.getPass() + "'," + Integer.toUnsignedLong(u.getId()) + ",'" + u.getNameFirst() + "','" + u.getNameLast() +  "','" + u.getUname();
            getRunnerInstance().db.update("INSERT INTO users (email, password, id, namefirst, namelast, username) VALUES ('" + q + "');");
            getRunnerInstance().user = u;
        } catch (SQLException ex) {
            System.err.println("bad connection");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void run() {
        frame = new JFrame("Mentality");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            if (db != null) {
                cleanup();
            }
            initDB();
            connecting.release();
            System.err.println("connected to database");
        } catch (SQLException ex) {
            System.err.println("cannot establish connection to database");
            System.exit(1);
        }
    }

    public static ResultSet query(String query) {
        try {
            return getRunnerInstance().db.query(query);
        } catch (SQLException ex) {
            System.err.println("query failed");
            System.err.println(ex);
            return null;
        }
    }

    public static int update(String query) {
        try {
            return getRunnerInstance().db.update(query);
        } catch (SQLException ex) {
            System.err.println("update failed");
            System.err.println(ex);
            return -1;
        }
    }

    public static void logout() {
        getRunnerInstance().user = null;
        getRunnerInstance().changeFrame(new Mentality());
    }

    public static void cleanup() {
        try {
            getRunnerInstance().db.close();
        } catch (SQLException ex) {
            System.err.println("failed to close JDBC connection");
            System.exit(1);
        }
    }
}
