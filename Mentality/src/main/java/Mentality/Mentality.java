package Mentality;

import Mentality.components.Password;
import Mentality.components.User;
import Mentality.frames.Dashboard;
import Mentality.frames.Registration;
import Mentality.frames.Template;
import static Mentality.Runner.*;
import static Mentality.utils.CustomUtilities.*;
import static Mentality.utils.SpringUtilities.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Mentality extends JPanel implements ActionListener, FocusListener, KeyListener {
    private static final boolean DEBUG = false;
    private JTextField email;
    private JPasswordField pass;

    public Mentality() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel logo = new JLabel(new ImageIcon(getResource("logo.png"), "Mentality Logo"));
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

        JButton login = initJButton("Login", this);
        JButton register = initJButton("Register", this);

        add(center(logo));
        add(erow);
        add(prow);
        add(center(login));
        add(center(register));
    }

    private void login() {
        String passkey = Password.toKey(email.getText(), pass.getPassword());
        pass.setText("");
        if (getRunnerInstance().validateLogin(new User(email.getText(), Password.hashPassword(passkey)))) {
            getRunnerInstance().changeFrame(new Dashboard());
        } else {
            System.err.println("login failed");
        }
        System.out.println("email: " + email.getText());
        System.out.println("password: " + Password.hashPassword(passkey));
        System.out.println("id: " + Password.hashPassword(passkey).hashCode());
    }

    private static void login_DEBUG() {
        String emaildbg = "test@example.com";
        JPasswordField passdbg = new JPasswordField("passw0rd");
        String passkey = Password.toKey(emaildbg, passdbg.getPassword());
        if (getRunnerInstance().validateLogin(new User(emaildbg, Password.hashPassword(passkey)))) {
            getRunnerInstance().changeFrame(new Template());
        } else {
            System.err.println("login failed");
        }
    }

    private static void startGUI() {
        if (DEBUG) {
            login_DEBUG();
            return;
        }
        getRunnerInstance().changeFrame(new Mentality());
    }
    public static void main(String[] args) {
        Thread runner = Runner.getRunnerInstance();
        runner.start();
        try {
            getRunnerInstance().connecting.acquire();
        } catch (InterruptedException ex) {
            System.err.println("connection terminated");
            System.exit(1);
        }

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
