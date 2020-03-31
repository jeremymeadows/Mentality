package Mentality.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mood extends JDialog{
    private JButton moodSurvey;

    public Mood(JFrame parent, String title) {
        super(parent, title);

        moodSurvey = new JButton("Fill Mood Survey");

        setLayout(new FlowLayout());
        add(moodSurvey);

        moodSurvey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textDate = new JTextField();
                JTextField textMood = new JTextField();
                JTextField textIrritability = new JTextField();

                Object[] fields = {
                        "Today's Date", textDate,
                        "On a scale of 1 to 10, how would you rate your overall mood today?", textMood,
                        "On a scale of 1-3, how irritable do you feel today?", textIrritability,
                };

                JOptionPane.showConfirmDialog(null, fields, "Mood Survey", JOptionPane.OK_CANCEL_OPTION);

                //Save info
                String dateToday = textDate.getText();
                String moodToday = textMood.getText();
                String irritabilityToday = textIrritability.getText();
            }
        });


        setSize(200, 300);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
