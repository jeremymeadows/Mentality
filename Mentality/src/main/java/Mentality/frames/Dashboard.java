package Mentality.frames;

import static Mentality.utils.CustomUtilities.*;

import javax.swing.*;
import java.awt.event.*;

public class Dashboard extends JPanel implements ActionListener, FocusListener, KeyListener {

    public Dashboard() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel logo = new JLabel("dashboard");
        add(center(logo));
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void focusGained(FocusEvent e) {}
    @Override
    public void focusLost(FocusEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
