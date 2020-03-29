package Mentality.frames;

import Mentality.components.Chart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Mentality.utils.CustomUtilities.ColorPalette.mainColor;
import static Mentality.utils.CustomUtilities.center;

public class Report extends JDialog {
    private static final long serialVersionUID = 2L;

    public Report(JFrame parent, String title) {
        super(parent, title);

        System.out.println("creating the report window..");

        // set the position of the window
        Point p = new Point(200, 120);
        setLocation(p.x, p.y);

        //set the color of the window
        setBackground(mainColor);


        // Create close me button
        JPanel buttonPane = new JPanel();
        JButton button = new JButton("Close me");
        buttonPane.add(button);
        // set action listener on the button
        button.addActionListener(new Report.MyActionListener());
        getContentPane().add(buttonPane, BorderLayout.PAGE_END);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setBackground(mainColor);
        
        // Display report
        panel.add(center (new JLabel ("Average happiness: 8.2/10")));
        panel.add(center (new JLabel ("Activity that puts you in the best mood: Running")));
        panel.add(center (new JLabel ("Activity that puts you in the worst mood: Software Engineering")));
        panel.add(center (new JLabel ("Person that puts you in the best mood: Cerny")));
        panel.add(center (new JLabel ("Person that puts you in the worst mood: Booth")));
        panel.add(center (new JLabel ("Best day of the week: Wednesday")));
        panel.add(center (new JLabel ("Worst day of the week: Tuesday")));

        getContentPane().add(panel);







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
            System.out.println("disposing the Report window..");
            setVisible(false);
            dispose();
        }
    }

}
