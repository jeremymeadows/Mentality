package Mentality;

import Mentality.components.Database;
import Mentality.components.MenuBar;
import Mentality.components.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Runner extends Thread implements Runnable {
    private static JFrame frame;
    private static Database db;
    private static User user;

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
        //query("check database for user");
        user = new User("test@example.com","passw0rd");
        return true;
    }

    public static void query(String q) {
        try {
            db.query(q);
        } catch (SQLException ex) {
            System.err.println("cannot query");
        }
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
}
