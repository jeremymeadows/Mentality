package Mentality.frames;

import Mentality.DomainLayer.ReportObj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**********************************
 * singleton design pattern
 **********************************/

public class ReportSurvey extends JDialog {
    JPanel topPanel;
    ReportObj reportObj;

    public ReportSurvey(JFrame parent, String title, ReportObj reportObj) {
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
        button.addActionListener(new ReportSurvey.MyActionListener());
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
