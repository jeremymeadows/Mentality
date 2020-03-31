package Mentality.frames;

import javax.swing.*;
import java.awt.*;

public class DiaryFrame extends JDialog {
    private JTextArea diaryEntry;
    private JButton saveDiaryEntry;

    public DiaryFrame(JFrame parent, String message) {
        super(parent, message);
        setLayout(new BorderLayout());


        diaryEntry = new JTextArea("Start typing entry here");
        saveDiaryEntry = new JButton("Save Diary Entry");

        add(diaryEntry, BorderLayout.CENTER);
        add(saveDiaryEntry, BorderLayout.SOUTH);

        setSize(600, 500);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //        diaryEntry.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 DiaryFrame d = new DiaryFrame();
//
//             }
//        });
    }
}
