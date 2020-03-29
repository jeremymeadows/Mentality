package Mentality.frames;

import Mentality.components.Search;
import Mentality.utils.CronScheduler;

import static Mentality.utils.CustomUtilities.*;
import static Mentality.utils.CustomUtilities.ColorPalette.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Template extends JPanel implements ActionListener {

    private static final int BUTTONNUM = 6;
    JFrame frame;

    public Template(JFrame frame) {
        super();
        this.frame = frame;
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
        Point[] buttonLocs = {
                new Point(0, 30), new Point(0, 100), new Point(0, 170),
                new Point(0, 240), new Point(0, 310), new Point(0, 380)
        };
        for (int i = 0; i < BUTTONNUM; ++i) {
            buttons[i] = initJButton(buttonLabels[i], this);
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

        //add actionListener
        buttons[0].addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println ("Redirecting to Mood Survey");
                JLabel label = new JLabel("Hello, User" + "!");
                wallPanel.add(label);


            }
        });

        buttons[4].addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println ("Opening Report");

                JDialog reportDialogue = new Report(frame,"Report");
                reportDialogue.setSize(new Dimension(1250, 780));
                reportDialogue.setResizable(false);
                reportDialogue.getContentPane().setBackground(mainColor);


            }
        });

        buttons[5].addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println ("Opening Happiness Graph");
                CronScheduler cs = new CronScheduler();
                Date d = cs.getDate();
                String date = d.toString();
                JDialog graphDialogue = new HappinessGraph(frame,"Happiness Graph", date);

                graphDialogue.setSize(new Dimension(1250, 780));
                graphDialogue.setResizable(false);

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}
