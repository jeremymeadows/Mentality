package Mentality.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomUtilities {
    public static class ColorPalette {
        public static Color mainColor = new Color(0xDBB6E3);
        // 0xB4F0E0
        // 0x9BB0FA
        // 0xFAC7BB
        // 0xF0DEB4
    }

    public static JComponent center(JComponent component) {
        component.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        return component;
    }

    public static JButton initJButton(String label, ActionListener actionListener, String actionCommand) {
        JButton b = new JButton(label);
        b.addActionListener(actionListener);
        b.setActionCommand(actionCommand);
        return b;
    }
    public static JButton initJButton(String label, ActionListener actionListener) {
        return initJButton(label, actionListener, label.toLowerCase());
    }

    public static JMenuItem initJMenuItem(String label, ActionListener actionListener, String actionCommand, int keyEvent, KeyStroke keyStroke) {
        JMenuItem i = new JMenuItem(label, keyEvent);
        i.addActionListener(actionListener);
        i.setActionCommand(actionCommand);
        i.setAccelerator(keyStroke);
        return i;
    }
    public static JMenuItem initJMenuItem(String label, ActionListener actionListener, int keyEvent, KeyStroke keyStroke) {
        return initJMenuItem(label, actionListener, label.toLowerCase(), keyEvent, keyStroke);
    }
    public static JMenuItem initJMenuItem(String label, ActionListener actionListener, int keyEvent) {
        return initJMenuItem(label, actionListener, label.toLowerCase(), keyEvent, null);
    }
    public static JMenuItem initJMenuItem(String label, ActionListener actionListener, String actionCommand, int keyEvent) {
        return initJMenuItem(label, actionListener, actionCommand, keyEvent, null);
    }
}
