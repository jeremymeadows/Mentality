package Mentality.components;

import static Mentality.utils.CustomUtilities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MenuBar extends JMenuBar implements ActionListener {
    private JMenuBar menuBar;

    private MenuBar() {
        menuBar = new JMenuBar();
        JMenu menu;
        JMenuItem menuItem;

        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext().setAccessibleDescription("Mentality Menu");

        menuItem = initJMenuItem("Settings", this, KeyEvent.VK_T,
                KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, InputEvent.CTRL_DOWN_MASK));
        menu.add(menuItem);
        menuItem = initJMenuItem("Exit", this, KeyEvent.VK_X,
                KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        menu.add(menuItem);

        menuBar.add(menu);

        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);
        menu.getAccessibleContext().setAccessibleDescription("Help Menu");

        menuItem = initJMenuItem("Help", this, KeyEvent.VK_H);
        menu.add(menuItem);

        menuBar.add(menu);
    }

    public static JMenuBar newMenuBar() {
        return new MenuBar().menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("settings")) {
            System.err.println("open settings menu");
        }
        if (e.getActionCommand().equals("exit")) {
            System.exit(0);
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
