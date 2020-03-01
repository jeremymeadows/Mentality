package Mentality.frames;

import Mentality.Runner;
import Mentality.components.Search;

import static Mentality.utils.CustomUtilities.*;
import static Mentality.utils.CustomUtilities.ColorPalette.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class Template extends JPanel implements ActionListener {
    private static final int BUTTONNUM = 6;

    public Template() {
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
        String[] buttonLabels = { "Button #1", "Button #2", "Button #3", "Button #4", "Button #5", "Button #6" };
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
        wallPanel.setBackground(mainColor);
        wallPanel.setVisible(true);

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
        searchPanel.setSize(220, 15);



        add(redirectPanel);
        add(wallPanel);
        add(titlePanel);
        add(searchPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}
