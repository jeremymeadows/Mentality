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
            // look up friend in users table to display username, email, id, first, and last
            ResultSet r = Runner.query("SELECT DISTINCT username, u.email, namefirst, namelast FROM " +
                    "users u, friends f WHERE f.email='"+ Runner.getUser().getEmail() + "' AND u.email=f.friend;");
            if (!r.next()) {
                System.out.println("User has no friends");
                JOptionPane.showMessageDialog(null, "Looks like you don't have any friends but I'd love to be yours!");
            }
            do {
                System.out.println(r.getString("username") + " " + r.getString("email")
                        + " " + r.getString("namefirst") + " " + r.getString("namelast"));

                User u = new User();
                u.setUname(r.getString("username"));
                u.setEmail(r.getString("email"));
                u.setNameFirst(r.getString("namefirst"));
                u.setNameLast(r.getString("namelast"));
                System.out.println("Displaying friends in a table");

                newContentPane.getModel().addData(u);
            } while (r.next());
        } catch (SQLException ex) { // the illegal operation on empty set is OK
            System.err.println(ex);
        }
        setVisible(true);
    }
}
