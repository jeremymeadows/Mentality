package Mentality.frames;

import Mentality.Runner;
import Mentality.components.StarRater;
import Mentality.components.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import static Mentality.utils.CustomUtilities.ColorPalette.mainColor;
import static Mentality.utils.CustomUtilities.initJButton;

public class Person extends JDialog implements ActionListener {
    int energy;
    JComboBox comboBox;
    DefaultComboBoxModel comboBoxModel;
    String friend;

    public Person (JFrame parent, String title){
        super(parent, title);
        setLayout (new GridLayout(0, 1));
        setBackground(mainColor);

        Vector comboBoxItems=new Vector();
        comboBoxModel = new DefaultComboBoxModel(comboBoxItems);
        final JComboBox comboBox = new JComboBox(comboBoxModel);

        JPanel peoplePanel = new JPanel();
        peoplePanel.setLayout(new GridLayout(0,1));
        JLabel label = new JLabel("Who did you hang out with?");
        Vector<Object> users = new Vector<>();
        users.add("--");
        try {
            ResultSet r = Runner.query("SELECT username FROM users;");
            while (r.next()) {
                String un = r.getString("username");
                users.add(un);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        JComboBox peopleList = new JComboBox(users);
        peopleList.setSelectedIndex(0);
        JTextArea area = new JTextArea("Or enter your friend's name here", 4, 60);
        peoplePanel.add(label);
        peoplePanel.add(peopleList);
        peoplePanel.add(area);
        //add default text to post box that disappears when selected
        area.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                area.setText("");
            }

            public void focusLost(FocusEvent e) {
                friend = area.getText()
                        .replaceAll("'", "\\\\'")
                        .replaceAll("\"", "\\\"");
                if (area.getText().equals("Or enter your friend's name here") || area.getText().equals("")) {
                    area.setText("Or enter your friend's name here");
                }
            }
        });


        JPanel submitPanel = new JPanel();
        JButton submitButton;
        submitButton = initJButton("Complete", this, "complete");
        submitPanel.add(submitButton);

        add(peoplePanel);
        add(submitPanel);

        setSize(250, 150);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("complete")){
            System.out.println("Sleep survey completed");
            this.dispose();
        }

    }
}
