package Mentality.frames;

import Mentality.Runner;
import Mentality.components.Feed;
import Mentality.components.PostToWall;
import Mentality.components.Search;
import Mentality.components.User;
import Mentality.utils.BadDataStorage;
import Mentality.utils.CronScheduler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static Mentality.utils.CustomUtilities.ColorPalette.mainColor;
import static Mentality.utils.CustomUtilities.center;
import static Mentality.utils.CustomUtilities.initJButton;

public class Page extends JPanel implements ActionListener, FocusListener, KeyListener {
    private static final int BUTTONNUM = 5;
    User user;

    public Page(User u) {
        super();
        user = u;
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
        homeButton.setActionCommand("home");
        homeButton.addActionListener(this);

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
        Feed feedPaneltemp = new Feed(user);
        feedPanel.add(feedPaneltemp);
        feedPanel.setLocation(200, 240);
        feedPanel.setSize(1400, 400);

        //within wall panel a user can post to wall
        PostToWall posttoWallPanel = new PostToWall(u, feedPaneltemp);
        posttoWallPanel.setBackground(new Color(180, 180, 200));
        posttoWallPanel.setVisible(true);
        postPanel.add(posttoWallPanel);

        //title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(mainColor);
        titlePanel.setVisible(true);
        titlePanel.setLocation(0, 0);
        titlePanel.setSize(1600, 80);
        titlePanel.add(center(new JLabel("Welcome to " + user.getUname() + "'s page!")));

        //search panel
        Search searchPanel = new Search();
        searchPanel.setLocation(680, 60);
        searchPanel.setSize(500, 15);

        //add friend option
        JButton addFriendButton = new JButton("add friend");
        addFriendButton.setSize(new Dimension(40,10));
        postPanel.add(addFriendButton);
        addFriendButton.setLocation(new Point (0, 20));
        addFriendButton.setActionCommand("add");
        addFriendButton.addActionListener(this);



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
            JDialog MainFrame = new Mood(Runner.getFrame(),"Survey");
            MainFrame.setSize(new Dimension(600, 600));
            MainFrame.setLocation (new Point (300, 230));
            MainFrame.getContentPane().setBackground(mainColor);
        }

        if (e.getActionCommand().equals("friends")) {
            System.out.println ("Redirecting to Friends");
        }
        if (e.getActionCommand().equals("diary")) {
            System.out.println ("Redirecting to Diary");
            JDialog DiaryFrame = new DiaryFrame(Runner.getFrame(),"Diary");
            DiaryFrame.setSize(new Dimension(600, 600));
            DiaryFrame.setLocation (new Point (300, 230));
            DiaryFrame.getContentPane().setBackground(mainColor);
        }
        if (e.getActionCommand().equals("report")) {
            System.out.println ("Opening Report");

            JDialog reportDialogue = new Report(Runner.getFrame(),"Report");
            reportDialogue.setSize(new Dimension(600, 600));
            reportDialogue.setLocation (new Point (300, 230));
            reportDialogue.getContentPane().setBackground(mainColor);
        }
        if (e.getActionCommand().equals("graph")) {
            System.out.println ("Opening Happiness Graph");
            CronScheduler cs = new CronScheduler();
            Date d = cs.getDate();
            String date = d.toString();
            JDialog graphDialogue = new HappinessGraph(Runner.getFrame(),"Happiness Graph", date);

            graphDialogue.setSize(new Dimension(1000, 600));
            graphDialogue.setLocation (new Point (300, 230));
            graphDialogue.setResizable(false);
        }
        if (e.getActionCommand().equals("home")){
            System.out.println ("Going back to the dashboard");
            Runner.getRunnerInstance().changeFrame(new Dashboard());
        }
        if (e.getActionCommand().equals("add")){
            System.out.println ("Adding a Friend");

            //check to see if user is already in database
            ResultSet rs = Runner.query("SELECT * FROM friends where email = '\" + Runner.getUser().getEmail() + \"' );");

            try {
                System.out.println ( Runner.getUser().getEmail());
                System.out.println (user.getNameFirst() + " " + user.getNameLast());

                ResultSet r = Runner.query("SELECT friend FROM friends WHERE email = '" + Runner.getUser().getEmail() + "' AND friend = '" + user.getNameFirst() + " " + user.getNameLast() +  "';");
                if (r.next()) {
                    System.out.println("Already friends");
                    JOptionPane.showMessageDialog(null, "You are already friends with " + r.getString(1));
                }
                else {
                    System.out.println("Added friends");
                    Runner.update("INSERT INTO friends values('" + Runner.getUser().getEmail() + "', '" + user.getNameFirst() + " " + user.getNameLast() + "' );");
                    JOptionPane.showMessageDialog(null, "Added " + r.getString(1) + "!");
                }
            } catch (SQLException ex) {
                System.err.println(ex);
            }
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
