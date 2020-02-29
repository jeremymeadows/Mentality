package Mentality;

import Mentality.components.Password;
import Mentality.components.User;
import Mentality.frames.Dashboard;
import Mentality.frames.Registration;
import static Mentality.utils.CustomUtilities.*;
import static Mentality.utils.SpringUtilities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Mentality extends JPanel implements ActionListener, FocusListener, KeyListener {

    private JTextField email;
    private JPasswordField pass;
    private JButton login, register;

    public Mentality() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel logo = new JLabel(new ImageIcon("src/main/resources/logo.png", "Mentality Logo"));
        JPanel erow = new JPanel(new SpringLayout()), prow = new JPanel(new SpringLayout());
        JLabel elabel = new JLabel("email: "), plabel = new JLabel("password: ");

        email = new JTextField("email");
        email.addFocusListener(this);
        email.addKeyListener(this);
        email.setMaximumSize(new Dimension(200, 30));
        elabel.setHorizontalAlignment(JLabel.RIGHT);
        erow.add(elabel);
        erow.add(email);
        pass = new JPasswordField("password");
        pass.addFocusListener(this);
        pass.addKeyListener(this);
        pass.setMaximumSize(new Dimension(200, 30));
        plabel.setHorizontalAlignment(JLabel.RIGHT);
        prow.add(plabel);
        prow.add(pass);

        makeGrid(erow, 1, 2, 0,0,0,0);
        makeGrid(prow, 1, 2, 0,0,0,0);

        login = initJButton("Login", this);
        register = initJButton("Register", this);

        add(center(logo));
        add(erow);
        add(prow);
        add(center(login));
        add(center(register));
    }

    private void login() {
        if (Runner.validateLogin(new User(email.getText(), Password.getHashedPassword(pass)))) {
            Runner.changeFrame(new Dashboard());
        } else {
            System.err.println("login failed");
        }
        System.out.println(email.getText());
        System.out.println(Password.getHashedPassword(pass));
        pass.setText("");
    }

    private static void startGUI() {
        Runner.changeFrame(new Mentality());
    }
    public static void main(String[] args) {
        Thread runner = new Runner();
        runner.start();

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
            login();
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

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getComponent().equals(email)) {
            if (email.getText().equals("email")) {
                email.setText("");
            }
        }
        if (e.getComponent().equals(pass)) {
            if (new String(pass.getPassword()).equals("password")) {
                pass.setText("");
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
        if (e.getComponent().equals(pass)) {
            if (pass.getPassword().length == 0) {
                pass.setText("password");
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            login();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
