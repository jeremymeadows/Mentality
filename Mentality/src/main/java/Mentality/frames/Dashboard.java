package Mentality.frames;

import Mentality.Runner;
import Mentality.components.Search;
import Mentality.utils.CronScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static Mentality.utils.CustomUtilities.*;
import static Mentality.utils.CustomUtilities.ColorPalette.mainColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class Dashboard extends JPanel implements ActionListener, FocusListener, KeyListener {
    private static final int BUTTONNUM = 6;

    public Dashboard() {
        super();
        setLayout(null);
        setBackground(Color.white);

        //panel where redirect buttons are positioned
        JPanel redirectPanel = new JPanel();
        redirectPanel.setLayout(null);
        redirectPanel.setBackground(mainColor);
        redirectPanel.setVisible(true);
        redirectPanel.setLocation(0, 75);
        redirectPanel.setSize(200, 800);

        JButton[] buttons = new JButton[BUTTONNUM];
        Dimension buttonSize = new Dimension(200,40);
        String[] buttonLabels = { "Mood Survey", "Button #2", "Button #3", "Button #4", "Weekly Report", "Happiness Graph" };
        String[] buttonCommands = { "survey", "", "", "", "report", "graph" };
        Point[] buttonLocs = {
                new Point(0, 30), new Point(0, 100), new Point(0, 170),
                new Point(0, 240), new Point(0, 310), new Point(0, 380)
        };
        for (int i = 0; i < BUTTONNUM; ++i) {
            buttons[i] = initJButton(buttonLabels[i], this, buttonCommands[i]);
            buttons[i].setSize(buttonSize);
            buttons[i].setBackground(Color.red);
            buttons[i].setLocation(buttonLocs[i]);
            redirectPanel.add(buttons[i]);
        }

        //wall panel
        JPanel wallPanel = new JPanel();
        wallPanel.setBackground(Color.white);
        wallPanel.setVisible(true);
        wallPanel.setLocation(200, 80);
        wallPanel.setSize(1400, 1520);

        //title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(mainColor);
        titlePanel.setVisible(true);
        titlePanel.setLocation(0, 0);
        titlePanel.setSize(1600, 80);
        titlePanel.add(center(new JLabel("Hello, User" + "!")));

        //search panel
        Search searchPanel = new Search();
        searchPanel.setLocation(680, 60);
        searchPanel.setSize(220, 15);

        add(redirectPanel);
        add(wallPanel);
        add(titlePanel);
        add(searchPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("survey")) {
            System.out.println ("Redirecting to Mood Survey");
        }
        if (e.getActionCommand().equals("report")) {
            System.out.println ("Opening Report");

            JDialog reportDialogue = new Report(Runner.getFrame(),"Report");
            reportDialogue.setSize(new Dimension(1250, 780));
            reportDialogue.setResizable(false);
            reportDialogue.getContentPane().setBackground(mainColor);
        }
        if (e.getActionCommand().equals("graph")) {
            System.out.println ("Opening Happiness Graph");
            CronScheduler cs = new CronScheduler();
            Date d = cs.getDate();
            String date = d.toString();
            JDialog graphDialogue = new HappinessGraph(Runner.getFrame(),"Happiness Graph", date);

            graphDialogue.setSize(new Dimension(1250, 780));
            graphDialogue.setResizable(false);
        }
        if (e.getActionCommand().equals("")) {
            System.out.println("button clicked");
        }
    }

    @Override
    public void focusGained(FocusEvent e) {}
    @Override
    public void focusLost(FocusEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
