package Mentality.frames;

import Mentality.Runner;
import Mentality.utils.BadDataStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static Mentality.utils.CustomUtilities.ColorPalette.mainColor;
import static Mentality.utils.CustomUtilities.initJButton;

public class Person extends JDialog implements ActionListener {
    JComboBox peopleList;
    JTextArea name;
    Vector<Object> users;
    DefaultComboBoxModel comboBoxModel;
    String friend;

    public Person (JFrame parent, String title){
        super(parent, title);
        setLayout (new GridLayout(0, 1));
        setBackground(mainColor);

        Vector comboBoxItems=new Vector();
        comboBoxModel = new DefaultComboBoxModel(comboBoxItems);

        JPanel peoplePanel = new JPanel();
        peoplePanel.setLayout(new GridLayout(0,1));
        JLabel label = new JLabel("Who did you hang out with?");
        users = new Vector<>();
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

        peopleList = new JComboBox(users);
        peopleList.setSelectedIndex(0);
        name = new JTextArea("Or enter your friend's name here", 4, 60);
        peoplePanel.add(label);
        peoplePanel.add(peopleList);
        peoplePanel.add(name);
        //add default text to post box that disappears when selected
        name.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                name.setText("");
            }

            public void focusLost(FocusEvent e) {
                friend = name.getText()
                        .replaceAll("'", "\\\\'")
                        .replaceAll("\"", "\\\"");
                if (name.getText().equals("Or enter your friend's name here") || name.getText().equals("")) {
                    name.setText("Or enter your friend's name here");
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
        if (e.getActionCommand().equals("complete")) {
            Object u = users.elementAt(peopleList.getSelectedIndex());
            String q = ("INSERT INTO people values(" +
                    "\"" + Runner.getUser().getEmail() + "\", " +
                    "$DATE, " +
                    "\"" + (u.equals("--") ? name.getText() : u) + "\");"
            );
            BadDataStorage.data.put("person", q);
            System.out.println("People survey completed");
            this.dispose();
        }
    }
}
