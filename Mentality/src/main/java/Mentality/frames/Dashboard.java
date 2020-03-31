package Mentality.frames;

import Mentality.Runner;
import Mentality.components.PostToWall;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static Mentality.utils.CustomUtilities.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JPanel {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                frame.setSize(1600, 800);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
                Dashboard dashboard = new Dashboard(frame);

                frame.add(dashboard);
                frame.setVisible(true);
            }
        });
    }

    JFrame frame;


    public Dashboard(JFrame frame) {
        super();
        this.frame = frame;
        Template template = new Template(frame);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        JLabel logo = new JLabel("Hello, " + Runner.getUser().getUname());
        JLabel logo = new JLabel("Hello, User ");
        add(center(logo));
        add(template);

        template.setVisible(true);



    }
}
