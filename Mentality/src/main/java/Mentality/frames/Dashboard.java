package Mentality.frames;

import Mentality.Runner;
import Mentality.components.Feed;
import Mentality.components.PostToWall;
import Mentality.components.Search;
import Mentality.utils.CronScheduler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import static Mentality.utils.CustomUtilities.ColorPalette.mainColor;
import static Mentality.utils.CustomUtilities.center;
import static Mentality.utils.CustomUtilities.initJButton;


public class Dashboard extends JPanel implements ActionListener {
    private static final int BUTTONNUM = 5;
    CronScheduler cron = CronScheduler.getInstance();

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

        //create home button
        JButton homeButton = new JButton("Home");
        add(homeButton);
        homeButton.setSize(new Dimension(200,80));
        homeButton.setBackground(mainColor);
        homeButton.setLocation(new Point (0, 0));

        JButton[] buttons = new JButton[BUTTONNUM];
        Dimension buttonSize = new Dimension(200,40);
        String[] buttonLabels = { "Mood Survey", "Friends", "Diary", "Weekly Report", "Happiness Graph" };
        String[] buttonCommands = { "survey", "friends", "diary", "report", "graph" };
        Point[] buttonLocs = {
                new Point(0, 100), new Point(0, 170),
                new Point(0, 240), new Point(0, 310), new Point(0, 380)
        };
        for (int i = 0; i < BUTTONNUM; ++i) {
            buttons[i] = initJButton(buttonLabels[i], this, buttonCommands[i]);
            buttons[i].setSize(buttonSize);
            buttons[i].setBackground(Color.white);
            buttons[i].setLocation(buttonLocs[i]);
            redirectPanel.add(buttons[i]);
        }

        //post panel
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        postPanel.setBackground(Color.white);
        postPanel.setVisible(true);
        postPanel.setLocation(200, 80);
        postPanel.setSize(1400, 160); //1520

        //feed Panel
        JPanel feedPanel = new JPanel();
        feedPanel.setBackground(Color.white);
        feedPanel.setVisible(true);
        Feed feedPaneltemp = new Feed(Runner.getUser());
        feedPanel.add(feedPaneltemp);
        feedPanel.setLocation(200, 240);
        feedPanel.setSize(1400, 400);

        //within wall panel a user can post to wall
        PostToWall posttoWallPanel = new PostToWall(Runner.getUser(), feedPaneltemp);
        posttoWallPanel.setBackground(new Color(180, 180, 200));
        posttoWallPanel.setVisible(true);
        postPanel.add(posttoWallPanel);

        //title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(mainColor);
        titlePanel.setVisible(true);
        titlePanel.setLocation(0, 0);
        titlePanel.setSize(1600, 80);
        titlePanel.add(center(new JLabel("Hello, " + Runner.getUser().getUname() + "!")));

        //search panel
        Search searchPanel = new Search();
        searchPanel.setLocation(680, 60);
        searchPanel.setSize(500, 15);


        add(redirectPanel);
        add(postPanel);
        add(titlePanel);
        add(searchPanel);
        add(feedPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("survey")) {
            System.out.println ("Redirecting to Mood Survey");
            JDialog MainFrame = new MoodSurvey(Runner.getFrame(),"Survey");
            MainFrame.setSize(new Dimension(600, 600));
            MainFrame.setLocation (new Point (300, 230));
            MainFrame.getContentPane().setBackground(mainColor);
        }

        if (e.getActionCommand().equals("friends")) {
            System.out.println ("Redirecting to Friends");
            JDialog friendDialog = new Friend(Runner.getFrame(),"Friend");
            friendDialog.setSize(new Dimension(600, 400));
            friendDialog.setLocation (new Point (300, 230));
            friendDialog.getContentPane().setBackground(mainColor);

        }
        if (e.getActionCommand().equals("diary")) {
            System.out.println ("Redirecting to Diary");
            JDialog DiaryFrame = new DiaryFrame(Runner.getFrame(),"Previous Entries");
            DiaryFrame.setSize(new Dimension(600, 600));
            DiaryFrame.setLocation (new Point (300, 230));
            DiaryFrame.getContentPane().setBackground(mainColor);
        }
        if (e.getActionCommand().equals("report")) {
            System.out.println ("Opening Report");

            JDialog reportDialogue = new ReportWindow(Runner.getFrame(),"Report", cron.getReportObj());
            reportDialogue.setSize(new Dimension(600, 600));
            reportDialogue.setLocation (new Point (300, 230));
            reportDialogue.getContentPane().setBackground(mainColor);
        }
        if (e.getActionCommand().equals("graph")) {
            System.out.println ("Opening Happiness Graph");
            JDialog graphDialogue = new HappinessGraphWindow(Runner.getFrame(),"Happiness Graph", new Date().toString());

            graphDialogue.setSize(new Dimension(1000, 600));
            graphDialogue.setLocation (new Point (300, 230));
            graphDialogue.setResizable(false);
        }
    }

}
