package Mentality.frames;

import Mentality.components.Chart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Mentality.utils.CustomUtilities.ColorPalette.mainColor;
import static Mentality.utils.CustomUtilities.center;

/**********************************
 * singleton design pattern
 **********************************/

public class Report extends JDialog {
    //default dummy values
    Double avgHappiness = 8.2;
    String bestActivity = "Software Engineering";
    String worstActivity = "Isolation";
    String bestPerson = "Cerny";
    String worstPerson = "Perez Hilton";
    String bestDay = "Monday";
    String worstDay = "Tuesday";

    private static final long serialVersionUID = 2L;
    JPanel topPanel;

    public Report(JFrame parent, String title) {
        super(parent, title);

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
        button.addActionListener(new Report.MyActionListener());
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
        text = new JTextField ("Activity that puts you in the best mood:");
        text.setEditable(false);
        labelPanel.add(text);
        text = new JTextField ("Activity that puts you in the worst mood:");
        text.setEditable(false);
        labelPanel.add(text);
        text = new JTextField ("Person that puts you in the best mood:");
        text.setEditable(false);
        labelPanel.add(text);
        text = new JTextField ("Person that puts you in the worst mood:");
        text.setEditable(false);
        labelPanel.add(text);
        text = new JTextField ("Best day of the week:");
        text.setEditable(false);
        labelPanel.add(text);
        text = new JTextField ("Worst day of the week:");
        text.setEditable(false);
        labelPanel.add(text);
        topPanel.add(labelPanel);
    }

    public void addReportResults(){

        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new GridLayout(0, 1));
        resultsPanel.setBackground(new Color(180, 180, 200));

        // Add labels to resultsPanel
        JTextField text = new JTextField ((String.valueOf(avgHappiness)));
        text.setEditable(false);
        resultsPanel.add(text);
        text = new JTextField (bestActivity);
        text.setEditable(false);
        resultsPanel.add(text);
        text = new JTextField (worstActivity);
        text.setEditable(false);
        resultsPanel.add(text);
        text = new JTextField (bestPerson);
        text.setEditable(false);
        resultsPanel.add(text);
        text = new JTextField (worstPerson);
        text.setEditable(false);
        resultsPanel.add(text);
        text = new JTextField (bestDay);
        text.setEditable(false);
        resultsPanel.add(text);
        text = new JTextField (worstDay);
        text.setEditable(false);
        resultsPanel.add(text);
        topPanel.add(resultsPanel);

    }

    public void setReportValues(Double avgHappiness, String bestActivity, String worstActivity, String bestPerson,
            String worstPerson, String bestDay, String worstDay){
        this.avgHappiness = avgHappiness;
        this.bestActivity = bestActivity;
        this.worstActivity = worstActivity;
        this.bestPerson = bestPerson;
        this.worstPerson = worstPerson;
        this.bestDay = bestDay;
        this.worstDay = worstDay;
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
