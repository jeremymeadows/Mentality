//package Mentality.frames;
//
//
//import Mentality.Runner;
//import Mentality.components.StarRater;
//import Mentality.components.User;
//import Mentality.utils.BadDataStorage;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import static Mentality.utils.CustomUtilities.ColorPalette.mainColor;
//import static Mentality.utils.CustomUtilities.initJButton;
//
//public class Friend extends JDialog implements ActionListener {
//    int energy;
//
//    public Friend (JFrame parent, String title) {
//        super(parent, title);
//        setLayout(new GridLayout(0, 1));
//        setBackground(mainColor);
//
//        JLabel label = new JLabel("Friends List");
//        add(label, CENTER_ALIGNMENT);
//
//        try {
//            ResultSet r = Runner.query("SELECT * FROM friends WHERE email = '" + Runner.getUser().getEmail() + "';");
//            User u = new User();
//            while (r.next()) {
//                u.setUname(un);
//                u.setEmail(r.getString("email"));
//                u.setId(r.getInt("id"));
//            }
//            Runner.getRunnerInstance().changeFrame(new Page(u));
//        } catch (SQLException ex) {
//            System.err.println(ex);
//        }
//    } else {
//        JOptionPane.showMessageDialog(null, "User Does Not Exist");
//    }
//
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getActionCommand().equals("complete")){
//            String q = ("INSERT INTO sleep values(" +
//                    "\"" + Runner.getUser().getEmail() + "\", " +
//                    "$DATE, " +
//                    hours[hoursList.getSelectedIndex()] + ", " +
//                    energy + ");"
//            );
//            BadDataStorage.data.put("sleep", q);
//            System.out.println("Sleep survey completed");
//            this.dispose();
//        }
//    }
//}
//
