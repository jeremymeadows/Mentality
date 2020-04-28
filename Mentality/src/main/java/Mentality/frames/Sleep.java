package Mentality.frames;

import Mentality.Runner;
import Mentality.components.StarRater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Mentality.utils.CustomUtilities.ColorPalette.mainColor;
import static Mentality.utils.CustomUtilities.initJButton;

public class Sleep extends JDialog implements ActionListener {
    int energy;

    public Sleep (JFrame parent, String title){
        super(parent, title);
        setLayout (new GridLayout(0, 1));
        setBackground(mainColor);

        JPanel hoursPanel = new JPanel();
        hoursPanel.setLayout(new GridLayout(0,1));
        JLabel label = new JLabel("How many hours of sleep did you get?");
        String[] hours = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10+" };
        JComboBox hoursList = new JComboBox(hours);
        hoursList.setSelectedIndex(7);
        hoursList.addActionListener(this);
        hoursPanel.add(label);
        hoursPanel.add(hoursList);

        JPanel ratePanel = new JPanel();
        ratePanel.setLayout(new GridLayout(0,1));
        label = new JLabel("How energized did you feel?");
        StarRater starRater = new StarRater(10, 0, 0);
        starRater.addStarListener(new StarRater.StarListener()   {
            public void handleSelection(int selection) {
                System.out.println(selection);
                energy = selection;
            }
        });
        ratePanel.add(label);
        ratePanel.add(starRater);

        JPanel submitPanel = new JPanel();
        JButton submitButton;
        submitButton = initJButton("Complete", this, "complete");
        submitPanel.add(submitButton);

        add(hoursPanel);
        add(ratePanel);
        add(submitPanel);

        setSize(250, 150);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("complete")){
            System.out.println("Sleep survey completed");
            this.dispose();
        }

    }
}
