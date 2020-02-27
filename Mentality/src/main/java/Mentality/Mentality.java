package Mentality;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Mentality extends JPanel implements ActionListener {
    private static JFrame frame;

    public Mentality() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel(new ImageIcon("src/main/resources/logo.png", "Mentality Logo"));
        JTextArea uname = new JTextArea("username"), pass = new JTextArea("password");
        JButton login = new JButton("Login"), register = new JButton("Register");
        uname.setMaximumSize(new Dimension(200, 30));
        pass.setMaximumSize(new Dimension(200, 30));
        add(center(logo));

        add(uname);
        add(pass);
        add(center(login));
    }

    private JComponent center(JComponent c) {
        c.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        return c;
    }

    private static void startGUI() {
        frame = new JFrame("Mentality");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Mentality mainWindow = new Mentality();
        mainWindow.setOpaque(true);
        frame.setContentPane(mainWindow);

        frame.pack();
        frame.setSize(new Dimension(1280, 720));
        frame.setLocation(new Point(
                Toolkit.getDefaultToolkit().getScreenSize().width/2-640,
                Toolkit.getDefaultToolkit().getScreenSize().height/2-360));
        frame.setJMenuBar(MenuBar.newMenuBar());
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
