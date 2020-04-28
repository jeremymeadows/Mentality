package Mentality.frames;

import Mentality.Runner;
import Mentality.components.Calendar;
import Mentality.components.StarRater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Mentality.utils.CustomUtilities.ColorPalette.mainColor;

public class Mood extends JDialog implements ActionListener{
    private JButton peopleSurvey = new JButton("Fill out People Survey");
    private JButton sleepSurvey = new JButton("Fill out Sleep Survey");
    private JButton exerciseSurvey = new JButton("Fill out Exercise Survey");
    private JButton weatherSurvey = new JButton("Fill out Weather Survey");

    int happiness, stress, sadness;
    JFrame parent;
    String title;

    public Mood(JFrame parent, String title) {
        super(parent, title);
        this.parent = parent;
        this.title = title;

        setLayout(new GridLayout(0, 1));

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setBackground(mainColor);

        JPanel calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(0 ,1));
        calendarPanel.setBackground(mainColor);

        JPanel otherSurveys = new JPanel();
        otherSurveys.setLayout(new GridLayout(0, 1));



        Calendar calendar = new Calendar();
        String day = calendar.selected[0];
        String month = calendar.selected[1];
        String year = calendar.selected[2];

        JLabel happyLabel = new JLabel("How happy did you feel?");
        StarRater starRater1 = new StarRater(10, 0, 0);
        starRater1.addStarListener(new StarRater.StarListener()   {
            public void handleSelection(int selection) {
                System.out.println(selection);
                happiness = selection;
            }
        });

        JLabel stressLabel = new JLabel("How sad did you feel?");
        StarRater starRater2 = new StarRater(10, 0, 0);
        starRater2.addStarListener(new StarRater.StarListener()   {
            public void handleSelection(int selection) {
                System.out.println(selection);
                sadness = selection;
            }
        });

        JLabel sadLabel = new JLabel("How stressed did you feel?");
        StarRater starRater3 = new StarRater(10, 0, 0);
        starRater3.addStarListener(new StarRater.StarListener()   {
            public void handleSelection(int selection) {
                System.out.println(selection);
                stress = selection;
            }
        });


//        contentPane.add(calendar);
        calendarPanel.add(calendar);
        scorePanel.add(happyLabel);
        scorePanel.add(starRater1);
        scorePanel.add(stressLabel);
        scorePanel.add(starRater2);
        scorePanel.add(sadLabel);
        scorePanel.add(starRater3);
        otherSurveys.add(peopleSurvey);
        otherSurveys.add(sleepSurvey);
        otherSurveys.add(exerciseSurvey);
        otherSurveys.add(weatherSurvey);

        add(calendarPanel);
        add(scorePanel);
        add(otherSurveys);

        peopleSurvey.addActionListener(this);
        peopleSurvey.setActionCommand("people");
        sleepSurvey.addActionListener(this);
        sleepSurvey.setActionCommand("sleep");
        exerciseSurvey.addActionListener(this);
        exerciseSurvey.setActionCommand("exercise");
        weatherSurvey.addActionListener(this);
        weatherSurvey.setActionCommand("weather");


        setSize(200, 300);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("people")){
            System.out.println("Redirecting to the people survey");
            JDialog MainFrame = new Person(Runner.getFrame(),"Survey");
//            MainFrame.setSize(new Dimension(600, 600));
            MainFrame.setLocation (new Point (300, 230));
            MainFrame.getContentPane().setBackground(mainColor);
        }
        if (e.getActionCommand().equals("sleep")){
            System.out.println("Redirecting to the sleep survey");
            JDialog MainFrame = new Sleep(Runner.getFrame(),"Survey");
//            MainFrame.setSize(new Dimension(600, 600));
            MainFrame.setLocation (new Point (300, 230));
            MainFrame.getContentPane().setBackground(mainColor);
        }
        if (e.getActionCommand().equals("exercise")){
            System.out.println("Redirecting to the exercise survey");
        }
        if (e.getActionCommand().equals("weather")){
            System.out.println("Redirecting to the weather survey");
        }

    }
}
