package Mentality;

import Mentality.components.MenuBar;
import Mentality.components.Password;
import Mentality.components.User;
import Mentality.windows.Registration;
import static Mentality.utils.CustomUtilities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mentality extends JPanel implements ActionListener {
    private static JFrame frame;
    private User user;

    private JTextArea uname;
    private JPasswordField pass;
    private JButton login, register;

    public Mentality() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel(new ImageIcon("src/main/resources/logo.png", "Mentality Logo"));
        uname = new JTextArea("username");
        pass = new JPasswordField("password");
        uname.setMaximumSize(new Dimension(200, 30));
        pass.setMaximumSize(new Dimension(200, 30));
        login = new JButton("Login");
        login.addActionListener(this);
        login.setActionCommand("login");
        register = new JButton("Register");
        register.addActionListener(this);
        register.setActionCommand("register");
        add(center(logo));

        add(uname);
        add(pass);
        add(center(login));
        add(center(register));
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
        if (e.getActionCommand().equals("login")) {
            user = new User(uname.getText(), Password.getHashedPassword(pass));
            System.out.println(uname.getText());
            System.out.println(Password.getHashedPassword(pass));
            pass.setText("");
        }
        if (e.getActionCommand().equals("register")) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new Registration().run();
                }
            });
        }
    }
}
