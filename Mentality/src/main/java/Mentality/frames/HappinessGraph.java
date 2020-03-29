package Mentality.frames;

import Mentality.components.Chart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HappinessGraph extends JDialog {
    private static final long serialVersionUID = 1L;

    public HappinessGraph(JFrame parent, String title, String message) {
        super(parent, title);

        System.out.println("creating the happiness graph window..");

        // set the position of the window
        Point p = new Point(200, 120);
        setLocation(p.x, p.y);


        // Display message
        JPanel messagePane = new JPanel();
        messagePane.add(new JLabel(message));
        // get content pane, which is usually the
        // Container of all the dialog's components.
        getContentPane().add(messagePane);

        // Create close me button
        JPanel buttonPane = new JPanel();
        JButton button = new JButton("Close me");
        buttonPane.add(button);
        // set action listener on the button
        button.addActionListener(new MyActionListener());
        getContentPane().add(buttonPane, BorderLayout.PAGE_END);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);

        // Display chart
        Chart chart = new Chart();
        getContentPane().add(chart.getChartPanel());
        chart.getChartPanel().setPreferredSize(new Dimension(200, 200));

    }

    // override the createRootPane inherited by the JDialog, to create the rootPane.
    // create functionality to close the window when "Escape" button is pressed
    public JRootPane createRootPane() {
        JRootPane rootPane = new JRootPane();
        KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
        Action action = new AbstractAction() {

            private static final long serialVersionUID = 1L;

            public void actionPerformed(ActionEvent e) {
                System.out.println("escaping..");
                setVisible(false);
                dispose();
            }
        };
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(stroke, "ESCAPE");
        rootPane.getActionMap().put("ESCAPE", action);
        return rootPane;
    }

    // an action listener to be used when an action is performed
    // (e.g. button is pressed)
    class MyActionListener implements ActionListener {

        //close and dispose of the window.
        public void actionPerformed(ActionEvent e) {
            System.out.println("disposing the happiness graph window..");
            setVisible(false);
            dispose();
        }
    }
}
