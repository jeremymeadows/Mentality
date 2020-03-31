package Mentality.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Feed extends JPanel{
    JPanel panel1, panel2, panel3;
    Post post1, post2, post3;
    public Feed (){
        setLayout(new GridLayout(0, 1));

        //there will be three panels on a feed at a given time
        //if user has no friends that have posted, Robots will give her positve affirmations
        panel1 = new JPanel();
        post1 = new Post("Robot #1", new Date(), "I feel pretty good! What about you?", 8);
        panel1.add(post1);


        panel2 = new JPanel();
        post2 = new Post("Robot #2", new Date(), "I'm feeling like you are a pretty fantastic person! " +
                                                                "What a terrific day to be alive! Can you smell the roses?", 10);
        panel2.add(post2);

        panel3 = new JPanel();
        post3 = new Post("Robot #3", new Date(), "Listening to some chill music but wish I could be talking "
                                                                + "to you :) Also, I have a question. Did it hurt when you " +
                                                                    "fell from Heaven?", 9);
        panel3.add(post3);


        add(panel1);
        add(panel2);
        add(panel3);
    }

    public void updateFeed(Post post){

        panel1.remove(post1);
        panel2.remove(post2);
        panel3.remove(post3);
        revalidate();
        repaint();

        post3 = new Post(post2.name, post2.date_posted, post2.post, post2.mood);
        post2 = new Post (post1.name, post1.date_posted, post1.post, post1.mood);
        post1 = new Post(post.name, post.date_posted, post.post, post.mood);

        panel1.add(post1);
        panel2.add(post2);
        panel3.add(post3);

    }
}
