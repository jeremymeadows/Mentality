package Mentality.frames;

import Mentality.components.Calendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MoodSurvey extends JPanel implements ActionListener, FocusListener, KeyListener {

    JPanel moodPanel;

    public MoodSurvey(){

        super();
        setLayout(null);
        moodPanel.setBackground(Color.yellow);
        setVisible(true);


        Calendar calendar = new Calendar();
        add(calendar);
        calendar.setVisible(true);
        calendar.setLocation(200, 80);
        calendar.setSize(1400, 1520);

    }

    JPanel getMoodSurveyPanel (){
        return moodPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
