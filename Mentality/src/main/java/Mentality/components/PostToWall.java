package Mentality.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;


public class PostToWall extends JPanel {
    int mood = 0;
    String postMessage;

    public PostToWall(Feed feed) {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        final JLabel moodLabel = new JLabel("How do you feel?");
        final JTextArea area = new JTextArea("What's on Your Mind?", 4, 60);

        //add default text to post box that disappears when selected
        area.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                area.setText("");
            }

            public void focusLost(FocusEvent e) {
                postMessage = area.getText();
                area.setText("What's on Your Mind?");
            }
        });

        //rating system to select how you felt that day
        StarRater starRater = new StarRater(10, 0, 0);
        starRater.addStarListener(
                new StarRater.StarListener()   {

                    public void handleSelection(int selection) {
                        System.out.println(selection);
                        mood = selection;
                    }
                });
        JButton postButton = new JButton("Post to Wall");

        //panel that contains post text
        JPanel areaPanel = new JPanel();
        areaPanel.add(area);
        areaPanel.add(postButton);

        //panel that contains rating system
        JPanel starPanel = new JPanel();
        starPanel.add(moodLabel);
        starPanel.add(starRater);

        //add Panels to main panel
        gc.gridx = 0;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        add(areaPanel, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = .5;
        add(starPanel);

        postButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Posted to Wall");
                Post postPanel = new Post("user_name", new Date(), postMessage, mood);
                feed.updateFeed(postPanel);


            }
        });

    }


}
