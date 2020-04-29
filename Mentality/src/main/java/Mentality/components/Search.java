package Mentality.components;

import Mentality.Runner;
import Mentality.frames.Page;

import static Mentality.utils.CustomUtilities.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Mentality.utils.CustomUtilities.ColorPalette.*;

public class Search extends JPanel implements ActionListener {
    private JTextField searchable = new JTextField("", 50);
    private JButton searchButton = initJButton("Search Users", this);

    public Search()  {
        super();
        this.setLayout(new GridLayout(1,1));
        this.setBackground(mainColor);
        this.setVisible(true);

        searchable.setBounds(180,40,220,40);
        searchButton.setSize( new Dimension(5,10));
        searchButton.setLocation(200, 65);
        searchButton.setVisible(true);
        searchable.setVisible(true);

        add(searchable);
        add(searchButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = searchable.getText();
        ArrayList<Object> users = new ArrayList<>();
        try {
            ResultSet r = Runner.query("SELECT username FROM users WHERE username LIKE '%" + s + "%';");
            while (r.next()) {
                String un = r.getString("username");
                users.add(un);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        if (users.size() > 0) {
            String un = (String)JOptionPane.showInputDialog(
                    Runner.getFrame(),
                    "Go to user's profile:\n",
                    "Search Results",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    users.toArray(),
                    users.get(0)
            );
            try {
                ResultSet r = Runner.query("SELECT * FROM users WHERE username = '" + un + "';");
                User u = new User();
                while (r.next()) {
                    u.setUname(un);
                    u.setEmail(r.getString("email"));
                    u.setId(r.getInt("id"));
                    u.setNameFirst(r.getString("namefirst"));
                    u.setNameLast(r.getString("namelast"));
                }
                Runner.getRunnerInstance().changeFrame(new Page(u));
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "User Does Not Exist");
        }
    }
}
