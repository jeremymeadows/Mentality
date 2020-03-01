package Mentality.frames;

import Mentality.Runner;
import Mentality.components.Password;
import Mentality.components.User;
import static Mentality.utils.CustomUtilities.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class Registration extends JPanel implements Runnable, ActionListener, FocusListener, KeyListener {
    private static JFrame popup;

    private JTextField email, uname, name;
    private JPasswordField pass, confpass;

    public Registration() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        email = new JTextField("email");
        email.addFocusListener(this);
        email.addKeyListener(this);
        email.setMaximumSize(new Dimension(200, 30));
        uname = new JTextField("username");
        uname.addFocusListener(this);
        uname.addKeyListener(this);
        uname.setMaximumSize(new Dimension(200, 30));
        name = new JTextField("full name");
        name.addFocusListener(this);
        name.addKeyListener(this);
        name.setMaximumSize(new Dimension(200, 30));

        pass = new JPasswordField("password");
        pass.addFocusListener(this);
        pass.addKeyListener(this);
        pass.setMaximumSize(new Dimension(200, 30));
        confpass = new JPasswordField("password");
        confpass.addFocusListener(this);
        confpass.addKeyListener(this);
        confpass.setMaximumSize(new Dimension(200, 30));

        JButton submit = initJButton("Submit", this);
        JButton cancel = initJButton("Cancel", this);

        add(email);
        add(uname);
        add(name);
        add(pass);
        add(confpass);
        add(center(submit));
        add(center(cancel));
    }

    private void register() {
        boolean success = true;
        String passkey = Password.toKey(email.getText(), pass.getPassword());
        if (!email.getText().matches("\\b[A-Za-z0-9!#$%&'*+-/=?^_`.{|}~]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b")) {
            System.err.println("invalid email");
            success = false;
        }
        if (!name.getText().matches("^[A-Z][a-z]+ [A-Z][a-z]+$")) {
            System.err.println("please enter first and last name");
            success = false;
        }
        if (!Arrays.equals(pass.getPassword(), confpass.getPassword())) {
            System.err.println("passwords don't match");
            success = false;
        }

        if (success && Runner.validateRegistration(new User
                (email.getText(), Password.hashPassword(passkey), name.getText(), uname.getText()))) {
            Runner.changeFrame(new Dashboard());
            popup.dispose();
        } else {
            pass.setText("");
            confpass.setText("");
        }
    }

    private static void startGUI() {
        popup = new JFrame("Register");
        popup.setAlwaysOnTop(true);
        popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Registration regWindow = new Registration();
        regWindow.setOpaque(true);
        popup.setContentPane(regWindow);

        popup.pack();
        popup.setSize(new Dimension(720, 480));
        popup.setLocation(new Point(
                Toolkit.getDefaultToolkit().getScreenSize().width/2-360,
                Toolkit.getDefaultToolkit().getScreenSize().height/2-240));
        popup.setVisible(true);
    }
    public void run() {
        if (popup == null || !popup.isVisible()) {
            startGUI();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("submit")) {
            register();
        }
        if (e.getActionCommand().equals("cancel")) {
            popup.dispose();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getComponent().equals(email)) {
            if (email.getText().equals("email")) {
                email.setText("");
            }
        }
        if (e.getComponent().equals(uname)) {
            if (uname.getText().equals("username")) {
                uname.setText("");
            }
        }
        if (e.getComponent().equals(name)) {
            if (name.getText().equals("full name")) {
                name.setText("");
            }
        }
        if (e.getComponent().equals(pass)) {
            if (new String(pass.getPassword()).equals("password")) {
                pass.setText("");
            }
        }
        if (e.getComponent().equals(confpass)) {
            if (new String(confpass.getPassword()).equals("password")) {
                confpass.setText("");
            }
        }
    }
    @Override
    public void focusLost(FocusEvent e) {
        if (e.getComponent().equals(email)) {
            if (email.getText().equals("")) {
                email.setText("email");
            }
        }
        if (e.getComponent().equals(uname)) {
            if (uname.getText().equals("")) {
                uname.setText("username");
            }
        }
        if (e.getComponent().equals(name)) {
            if (name.getText().equals("")) {
                name.setText("full name");
            }
        }
        if (e.getComponent().equals(pass)) {
            if (pass.getPassword().length == 0) {
                pass.setText("password");
            }
        }
        if (e.getComponent().equals(confpass)) {
            if (confpass.getPassword().length == 0) {
                confpass.setText("password");
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            register();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
