package Mentality;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {
    private JMenuBar menuBar;

    private MenuBar() {
        menuBar = new JMenuBar();
        JMenu menu;
        JMenuItem menuItem;

        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext().setAccessibleDescription("Mentality Menu");

        menuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        menuItem.setActionCommand("exit");
        menuItem.addActionListener(new Mentality());
        menu.add(menuItem);

        menuItem = new JMenuItem("Settings", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, InputEvent.CTRL_DOWN_MASK));
        menuItem.setActionCommand("settings");
        menuItem.addActionListener(new Mentality());
        menu.add(menuItem);

        menuBar.add(menu);

        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);
        menu.getAccessibleContext().setAccessibleDescription("Help Menu");

        menuItem = new JMenuItem("Help", KeyEvent.VK_H);
        menuItem.setActionCommand("help");
        menuItem.addActionListener(new Mentality());
        menu.add(menuItem);

        menuBar.add(menu);
    }

    public static JMenuBar newMenuBar() {
        return new MenuBar().menuBar;
    }
}
