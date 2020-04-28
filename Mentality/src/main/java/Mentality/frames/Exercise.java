package Mentality.frames;

import Mentality.Runner;

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

public class Exercise extends JDialog implements ActionListener {
    int workout;
    String description;
    JComboBox comboBox;
    DefaultComboBoxModel comboBoxModel;

    public Exercise (JFrame parent, String title){
        super(parent, title);
        setLayout (new GridLayout(0, 1));
        setBackground(mainColor);

        Vector comboBoxItems=new Vector();
        comboBoxModel = new DefaultComboBoxModel(comboBoxItems);
        final JComboBox comboBox = new JComboBox(comboBoxModel);

        JPanel workoutPanel = new JPanel();
        workoutPanel.setLayout(new GridLayout(0,1));
        JLabel label = new JLabel("What type of workout?");
        Vector<Object> workouts = new Vector<>();
        workouts.add("aerobic");
        workouts.add("strength");
        workouts.add("flexibility");
        workouts.add("balance");
        JComboBox workoutList = new JComboBox(workouts);
        workoutList.setSelectedIndex(0);
        workoutPanel.add(label);
        workoutPanel.add(workoutList);

        JPanel durationPanel = new JPanel();
        durationPanel.setLayout(new GridLayout(0,1));
        label = new JLabel("For how long?");
        Vector<Object> duration = new Vector<>();
        duration.add("15 min");
        duration.add("30 min");
        duration.add("45 min");
        duration.add("60 min");
        duration.add("1 hr 15 min");
        duration.add("1 hr 30 min");
        duration.add("1 hr 45 min");
        duration.add("2 hr+");

        JComboBox durationList = new JComboBox(duration);
        durationList.setSelectedIndex(0);
        durationPanel.add(label);
        durationPanel.add(durationList);


        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new GridLayout(0,1));
        JTextArea area = new JTextArea("Workout Description", 4, 60);
        descriptionPanel.add(area);
        //add default text to post box that disappears when selected
        area.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                area.setText("");
            }

            public void focusLost(FocusEvent e) {
                description = area.getText()
                        .replaceAll("'", "\\\\'")
                        .replaceAll("\"", "\\\"");
                if (area.getText().equals("Workout Description") || area.getText().equals("")) {
                    area.setText("Workout Description");
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
        if (e.getActionCommand().equals("complete")){
            System.out.println("Sleep survey completed");
            this.dispose();
        }

    }

}


