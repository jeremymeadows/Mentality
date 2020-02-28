package Mentality.windows;

import Mentality.components.Password;
import Mentality.components.User;
import static Mentality.utils.CustomUtilities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registration extends JPanel implements Runnable, ActionListener {
    private static JFrame frame;
    private User user;

    private JTextArea uname;
    private JPasswordField pass;
    private JButton submit;

    public Registration() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        uname = new JTextArea("username");
        pass = new JPasswordField("password");
        uname.setMaximumSize(new Dimension(200, 30));
        pass.setMaximumSize(new Dimension(200, 30));
        submit = initJButton("submit", this);

        add(uname);
        add(pass);
        add(center(submit));
    }

    private static void startGUI() {
        frame = new JFrame("Register");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Registration mainWindow = new Registration();
        mainWindow.setOpaque(true);
        frame.setContentPane(mainWindow);

        frame.pack();
        frame.setSize(new Dimension(720, 480));
        frame.setLocation(new Point(
                Toolkit.getDefaultToolkit().getScreenSize().width/2-360,
                Toolkit.getDefaultToolkit().getScreenSize().height/2-240));
        frame.setVisible(true);
    }
    public void run() {
        startGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("submit")) {
            user = new User(uname.getText(), Password.getHashedPassword(pass));
            System.out.println(uname.getText());
            System.out.println(Password.getHashedPassword(pass));
            pass.setText("");
        }
    }
}
