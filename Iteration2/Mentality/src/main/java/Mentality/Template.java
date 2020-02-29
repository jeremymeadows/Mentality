package Mentality;

import javax.swing.*;
import java.awt.*;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Template extends JPanel implements ActionListener {
    private static JFrame frame;
    private static JButton button1 = new JButton ("button");
    private static JButton button2 = new JButton ("button");
    private static JButton button3 = new JButton ("button");
    private static JButton button4 = new JButton ("button");
    private static JButton button5 = new JButton ("button");
    private static JButton button6 = new JButton ("button");

    //size for all buttons
    Dimension buttonSize = new Dimension(200,40);

    public Template() {

//        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setLayout(null);
        this.setBackground(Color.white);

        //panel where redirect panels are positioned
        JPanel redirectPanel = new JPanel();
        redirectPanel.setLayout(null);
        redirectPanel.setBackground(new java.awt.Color(195, 156, 155));
        redirectPanel.setVisible(true);
        redirectPanel.setLocation(0, 75);
        redirectPanel.setSize(200, 800);


        button1.setSize(buttonSize);
        button2.setSize(buttonSize);
        button3.setSize(buttonSize);
        button4.setSize(buttonSize);
        button5.setSize(buttonSize);
        button6.setSize(buttonSize);

        button1.setLocation(new Point (0,30));
        button2.setLocation(new Point (0,100));
        button3.setLocation(new Point (0, 170));
        button4.setLocation(new Point (0, 240));
        button5.setLocation(new Point (0,310));
        button6.setLocation(new Point (0,380));

        button1.setBackground(Color.red);
        button2.setBackground(Color.red);
        button3.setBackground(Color.red);
        button4.setBackground(Color.red);
        button5.setBackground(Color.red);
        button6.setBackground(Color.red);

        redirectPanel.add(button1);
        redirectPanel.add(button2);
        redirectPanel.add(button3);
        redirectPanel.add(button4);
        redirectPanel.add(button5);
        redirectPanel.add(button6);
        

        //wall panel
        JPanel wallPanel = new JPanel();
        wallPanel.setBackground(new java.awt.Color(195, 156, 155));
        JLabel postLabel = new JLabel ("What's on your mind");
        postLabel.setLocation(20, 20);
        postLabel.setSize (new Dimension (20, 20));
        postLabel.setVisible(true);
        wallPanel.add(postLabel);
        wallPanel.setVisible(true);



        //title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new java.awt.Color(195, 156, 155));
        titlePanel.setVisible(true);
        titlePanel.setLocation(0, 0);
        titlePanel.setSize(1600, 75);

//panel1.set[Preferred/Maximum/Minimum]Size()

        this.add(redirectPanel);
        this.add(wallPanel);
        this.add(titlePanel);
    }


    private static void startGUI() {
        frame = new JFrame("Mentality");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Template dashboard = new Template();
        dashboard.setOpaque(true);
        frame.setContentPane(dashboard);

        frame.pack();
        frame.setSize(new Dimension(1280, 720));
        frame.setLocation(new Point(
                Toolkit.getDefaultToolkit().getScreenSize().width/2-640,
                Toolkit.getDefaultToolkit().getScreenSize().height/2-360));
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                startGUI();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("exit")) {
            System.exit(0);
        }
        if (e.getActionCommand().equals("settings")) {
            System.err.println("open settings menu");
        }
        if (e.getActionCommand().equals("help")) {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/jeremymeadows/Mentality"));
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        }
    }


}
