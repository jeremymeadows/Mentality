package Mentality.components;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Post extends JPanel{
    String name;
    Date date_posted;
    String post;
    int mood;

    JLabel labelName;
    JLabel dateLabel;
    JTextArea postMessage;
    JScrollPane jp;
    StarRater starRater;

    public Post(String name, Date date_posted, String post, int mood) {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        this.name = name;
        this.date_posted = date_posted;
        this.post = post;
        this.mood = mood;

        labelName = new JLabel(name);
        dateLabel = new JLabel(date_posted.toString());
        postMessage = new JTextArea(post, 4, 80);
        postMessage.setEditable(false);
        //put message on scroll pane
        jp = new JScrollPane(postMessage);

        starRater = new StarRater(10, mood, mood);


        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = .2;
        gc.weighty = .2;
        add(labelName, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        add(jp, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        gc.weightx = .2;
        gc.weighty = .2;
        add(dateLabel, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        gc.weightx = .2;
        gc.weighty = .2;
        add(starRater, gc);
    }

}
