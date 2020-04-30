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
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import static Mentality.utils.CustomUtilities.ColorPalette.mainColor;
import static Mentality.utils.CustomUtilities.initJButton;

public class Exercise extends JDialog implements ActionListener {

    String description;
    JComboBox workoutList, durationList;
    DefaultComboBoxModel comboBoxModel;
    JTextArea desc;
    String[] workouts = {
            "aerobic",
            "strength",
            "flexibility",
            "balance"
    };
    HashMap<String, Integer> duration = new HashMap<String, Integer>(){{
            put("15 min", 15);
            put("30 min", 30);
            put("45 min", 45);
            put("60 min", 60);
            put("1 hr 15 min", 75);
            put("1 hr 30 min", 90);
            put("1 hr 45 min", 105);
            put("2 hr+", 120);
    }};

    public Exercise (JFrame parent, String title){
        super(parent, title);
        setLayout (new GridLayout(0, 1));
        setBackground(mainColor);

        Vector comboBoxItems=new Vector();
        comboBoxModel = new DefaultComboBoxModel(comboBoxItems);

        JPanel workoutPanel = new JPanel();
        workoutPanel.setLayout(new GridLayout(0,1));
        JLabel label = new JLabel("What type of workout?");
        workoutList = new JComboBox(workouts);
        workoutList.setSelectedIndex(0);
        workoutPanel.add(label);
        workoutPanel.add(workoutList);

        JPanel durationPanel = new JPanel();
        durationPanel.setLayout(new GridLayout(0,1));
        label = new JLabel("For how long?");

        durationList = new JComboBox(duration.keySet().toArray());
        durationList.setSelectedIndex(0);
        durationPanel.add(label);
        durationPanel.add(durationList);

        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new GridLayout(0,1));
        desc = new JTextArea("Workout Description", 4, 60);
        descriptionPanel.add(desc);
        // add default text to post box that disappears when selected
        desc.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                desc.setText("");
            }

            public void focusLost(FocusEvent e) {
                description = desc.getText()
                        .replaceAll("'", "\\\\'")
                        .replaceAll("\"", "\\\"");
                if (desc.getText().equals("Workout Description") || desc.getText().equals("")) {
                    desc.setText("Workout Description");
                }
            }
        });


        JPanel submitPanel = new JPanel();
        JButton submitButton;
        submitButton = initJButton("Complete", this, "complete");
        submitPanel.add(submitButton);

        add(workoutPanel);
        add(durationPanel);
        add(descriptionPanel);
        add(submitPanel);

        setSize(250, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("complete")) {
            String q = ("INSERT INTO exercise values(" +
                    "\"" + Runner.getUser().getEmail() + "\", " +
                    "$DATE, " +
                    "\"" + workouts[workoutList.getSelectedIndex()] + "\", " +
                    duration.values().toArray()[durationList.getSelectedIndex()] + ", " +
                    "\"" + desc.getText() + "\");"
            );
            BadDataStorage.data.put("exercise", q);
            System.out.println("Exercise survey completed");
            this.dispose();
        }
    }
}


