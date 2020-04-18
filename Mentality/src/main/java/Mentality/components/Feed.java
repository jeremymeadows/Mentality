package Mentality.components;

import Mentality.Runner;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Feed extends JPanel{
    JPanel[] panels = new JPanel[3];
    Post[] posts = new Post[3];
    public Feed (){
        setLayout(new GridLayout(0, 1));

        //there will be three panels on a feed at a given time
        //if user has no friends that have posted, Robots will give her positive affirmations
        panels[0] = new JPanel();
        posts[0] = new Post("Robot #1", new Date(), "I feel pretty good! What about you?", 8);

        panels[1] = new JPanel();
        posts[1] = new Post("Robot #2", new Date(), "I'm feeling like you are a pretty fantastic person! " +
                "What a terrific day to be alive! Can you smell the roses?", 10);

        panels[2] = new JPanel();
        posts[2] = new Post("Robot #3", new Date(), "Listening to some chill music but wish I could be talking "
                + "to you :) Also, I have a question. Did it hurt when you " + "fell from Heaven?", 9);

        for (int i = 0; i < 3; i++) {
            posts[i].buildPost();
            panels[i].add(posts[i]);
            add(panels[i]);
        }
        updateFeed();
    }

    public void updateFeed(){
        for (int i = 0; i < 3; i++) {
            panels[i].remove(posts[i]);
        }
        revalidate();
        repaint();

        try {
            ResultSet post = Runner.query("SELECT * FROM wall WHERE email = '" + Runner.getUser().getEmail() + "' ORDER BY timestamp DESC LIMIT 3;");
            int c = -1;
            while (post.next()) {
                posts[++c] = new Post(post.getString("author"), post.getDate("timestamp"), post.getString("post"), post.getInt("mood"));
            }
            for (int i = 0; i < 3; i++) {
                if (!posts[i].name.matches("Robot #[123]")) {
                    ResultSet r = Runner.query("SELECT username FROM users WHERE email = '" + posts[i].name + "';");
                    r.next();
                    posts[i].setUserame(r.getString("username"));
                }
                posts[i].buildPost();
                panels[i].add(posts[i]);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
