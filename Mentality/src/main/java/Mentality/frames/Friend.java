package Mentality.frames;


import Mentality.Runner;
import Mentality.components.User;
import Mentality.utils.FilterDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import static Mentality.utils.CustomUtilities.ColorPalette.mainColor;

public class Friend extends JDialog {

    public Friend(JFrame parent, String title) {
        super(parent, title);
        setLayout(new GridLayout(0, 1));
        setBackground(mainColor);

        //Create and set up the content pane.
        FriendTable newContentPane = new FriendTable();
        newContentPane.setOpaque(true); //content panes must be opaque
        JScrollPane pane = new JScrollPane(newContentPane);
        add(pane, BorderLayout.CENTER);

        add(new JLabel("Filter friends in the box below and double click a friend to go to his/her profile!"), CENTER_ALIGNMENT);

        //filter table
        JTextField filterField = FilterDemo.createRowFilter(newContentPane.getTable());
        JPanel jp = new JPanel();
        jp.add(filterField);
        jp.setBackground(mainColor);
        add(jp, BorderLayout.NORTH);

        try {
            ResultSet r = Runner.query("SELECT friend FROM friends WHERE email = '" + Runner.getUser().getEmail() + "';");
            //list contains all of the emails of the user's friends
            ArrayList <String> userFriends = new ArrayList<>();
            while (r.next()) {
                    userFriends.add(r.getString(1));
            }


            r.close();

            for (String userName : userFriends) {
                //look up friend in users table to display username, email, id, first, and last
                //if there is time fix this, we are looking up by username but we need to look up by email because only
                //email is unique. This means we need to include an email field in table friends
                ResultSet r2 = Runner.query("SELECT * FROM users WHERE username = '" + userName + "';");
                if (r2.next()) {

                    System.out.println(userName);
                    System.out.println(r2.getString("username") + " " + r2.getString("email")
                            + " " + r2.getString("namefirst") + " " + r2.getString("namelast"));

                    User u = new User();
                    u.setUname(r2.getString("username"));
                    u.setEmail(r2.getString("email"));
                    u.setNameFirst(r2.getString("namefirst"));
                    u.setNameLast(r2.getString("namelast"));
                    System.out.println("Displaying friends in a table");

                    newContentPane.getModel().addData(u);
                }
            }
            if (r == null) {
                System.out.println("User has no friends");
                JOptionPane.showMessageDialog(null, "Looks like you don't have any friends but I'd love to be yours!");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        setVisible(true);

    }
}