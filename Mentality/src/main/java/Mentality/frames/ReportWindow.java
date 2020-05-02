package Mentality.frames;

import Mentality.DomainLayer.ReportObj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**********************************
 * singleton design pattern
 **********************************/

public class ReportWindow extends JDialog {
    JPanel topPanel;
    ReportObj reportObj;

    public ReportWindow(JFrame parent, String title, ReportObj reportObj) {
        super(parent, title);
        this.reportObj = reportObj;

        //panel to hold all panels
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //create top panel
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        parent.setBackground(new Color(180, 180, 200));

        System.out.println("creating the report window..");

        // set the position of the window
        Point p = new Point(200, 120);
        setLocation(p.x, p.y);

        // Create close me button
        JPanel buttonPanel = new JPanel();
        JButton button = new JButton("Close me");
        buttonPanel.add(button);


        addLabels();
        addReportResults();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        add(topPanel, gc);
        // set action listener on the button
        button.addActionListener(new ReportWindow.MyActionListener());
        gc.gridx = 0;
        gc.gridy = 1;
        gc.weightx = .5;
        gc.weighty = .5;
        add(buttonPanel, gc);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void addLabels(){

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(0, 1));
        labelPanel.setBackground(new Color(180, 180, 200));

        // Add labels to label Panel
        JTextField text = new JTextField ("Average happiness:");
        text.setEditable(false);
        labelPanel.add(text);
        text = new JTextField ("Average sadness:");
        text.setEditable(false);
        labelPanel.add(text);
        text = new JTextField ("Average stress:");
        text.setEditable(false);
        labelPanel.add(text);
        text = new JTextField ("Best workout(s):");
        text.setEditable(false);
        labelPanel.add(text);
        text = new JTextField ("Best workout(s) mood of:");
        text.setEditable(false);
        labelPanel.add(text);
        text = new JTextField ("Worst workout(s):");
        text.setEditable(false);
        labelPanel.add(text);
        text = new JTextField ("Worst workout(s) mood:");
        text.setEditable(false);
        labelPanel.add(text);
        text = new JTextField ("Best person(s):");
        text.setEditable(false);
        labelPanel.add(text);
        text = new JTextField ("Best person(s) mood:");
        text.setEditable(false);
        labelPanel.add(text);
        text = new JTextField ("Worst person(s):");
        text.setEditable(false);
        labelPanel.add(text);
        text = new JTextField ("Worst person(s) mood:");
        text.setEditable(false);
        labelPanel.add(text);
        text = new JTextField ("Average sleep duration:");
        text.setEditable(false);
        labelPanel.add(text);
        text = new JTextField ("Average sleep quality:");
        text.setEditable(false);
        labelPanel.add(text);

        topPanel.add(labelPanel);
    }

    public void addReportResults(){

        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new GridLayout(0, 1));
        resultsPanel.setBackground(new Color(180, 180, 200));

        //calculate the report values
        reportObj.averageMoods();

        // Add labels to resultsPanel
        JTextField text = new JTextField ((String.valueOf(reportObj.getAverageHappiness())));
        text.setEditable(false);
        resultsPanel.add(text);

        text = new JTextField ((String.valueOf(reportObj.getAverageSadness())));
        text.setEditable(false);
        resultsPanel.add(text);

        text = new JTextField ((String.valueOf(reportObj.getAverageStress())));
        text.setEditable(false);
        resultsPanel.add(text);

        text = new JTextField ((String.valueOf(reportObj.getBestWorkout())));
        text.setEditable(false);
        resultsPanel.add(text);

        text = new JTextField ((String.valueOf(reportObj.getWorkoutBestMood())));
        text.setEditable(false);
        resultsPanel.add(text);

        text = new JTextField ((String.valueOf(reportObj.getWorstWorkout())));
        text.setEditable(false);
        resultsPanel.add(text);

        text = new JTextField ((String.valueOf(reportObj.getWorkoutWorstMood())));
        text.setEditable(false);
        resultsPanel.add(text);

        text = new JTextField ((String.valueOf(reportObj.getBestPerson())));
        text.setEditable(false);
        resultsPanel.add(text);

        text = new JTextField ((String.valueOf(reportObj.getBestPersonMood())));
        text.setEditable(false);
        resultsPanel.add(text);

        text = new JTextField ((String.valueOf(reportObj.getWorstPerson())));
        text.setEditable(false);
        resultsPanel.add(text);

        text = new JTextField ((String.valueOf(reportObj.getWorstPersonMood())));
        text.setEditable(false);
        resultsPanel.add(text);

        text = new JTextField ((String.valueOf(reportObj.getAvgSleep())));
        text.setEditable(false);
        resultsPanel.add(text);

        text = new JTextField ((String.valueOf(reportObj.getAvgSleepQ())));
        text.setEditable(false);
        resultsPanel.add(text);


        topPanel.add(resultsPanel);

    }

    // an action listener to be used when an action is performed
    // (e.g. button is pressed)
    class MyActionListener implements ActionListener {

        //close and dispose of the window.
        public void actionPerformed(ActionEvent e) {
            System.out.println("disposing the Report window..");
            setVisible(false);
            dispose();
        }
    }

}
