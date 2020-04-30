package Mentality.components;

import Mentality.Runner;
import Mentality.frames.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class Post extends JPanel {
    private String email;
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

        this.name = name;
        this.date_posted = date_posted;
        this.post = post;
        this.mood = mood;

        labelName = new JLabel(name);
        labelName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("clicked username");
                User u = new User(email, null, null, null, name);
                Runner.getRunnerInstance().changeFrame(new Page(u));
            }
        });
        dateLabel = new JLabel(date_posted.toString());
        postMessage = new JTextArea(post, 4, 80);
        postMessage.setEditable(false);
        //put message on scroll pane
        jp = new JScrollPane(postMessage);

        starRater = new StarRater(10, mood, mood);
    }

    void buildPost() {
        GridBagConstraints gc = new GridBagConstraints();

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

    void setEmail(String email) {
        this.email = email;
    }
    void setUsername(String name) {
        this.name = name;
        labelName = new JLabel(name);
        if (email.equals(Runner.getUser().getEmail())) {
            labelName.setOpaque(true);
            labelName.setBackground(Color.YELLOW);
        } else {
            labelName.setForeground(Color.BLUE);
        }
        labelName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!email.equals(Runner.getUser().getEmail())) {
                    User u = new User(email, "-", null, null, name);
                    Runner.getRunnerInstance().changeFrame(new Page(u));
                }
            }
        });
    }
}
