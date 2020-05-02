package Mentality.frames;

import Mentality.Runner;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class NewDiaryEntry {
    private JFrame fr;
    private JTextArea diaryEntry;
    private JButton saveEntry;
    private JLabel someLabel;
    public NewDiaryEntry () {
        fr = new JFrame();
        fr.setSize(600,600);
        fr.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        fr.setVisible(true);
        fr.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        someLabel = new JLabel("Select date for entry below");
        diaryEntry = new JTextArea("Start typing entry here");
        diaryEntry.setLineWrap(true);
        saveEntry = new JButton("Save Entry");

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("MM/dd/yyyy");

        gc.weightx = 0;
        gc.weighty = 0;

        gc.gridx = 0;
        gc.gridy = 0;

        gc.fill = GridBagConstraints.NONE;

        fr.add (someLabel, gc);

        gc.weightx = 0;
        gc.weighty = 0;

        gc.gridy = 1;
        gc.gridx = 0;

        fr.add (dateChooser, gc);

        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridy = 2;
        gc.gridx = 0;
        gc.fill = GridBagConstraints.BOTH;
        fr.add(diaryEntry, gc);

        gc.fill = GridBagConstraints.NONE;
        gc.weightx = 0;
        gc.weighty = 0;

        gc.gridy = 3;
        gc.gridx = 0;

        fr.add(saveEntry, gc);



        saveEntry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    java.util.Date utilDate =  new java.util.Date();
                    utilDate = dateChooser.getDate();
                    System.out.println("utildate: " + utilDate.toString());
                    String email = Runner.getUser().getEmail();
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    System.out.println("sqldate: " + sqlDate.toString());
                    String text = diaryEntry.getText();
                    Connection conn = DriverManager.getConnection("jdbc:mysql://mentality.jeremy.not-pc.com" +
                            ":3306/mentality?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=" +
                            "false&serverTimezone=UTC", "jeremybmeadows", "passw0rd");
                    Statement s = conn.createStatement();
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO journal (email, date, journal) VALUES (?,?,?)");
                    ps.setString(1, email);
                    ps.setDate(2, sqlDate);
                    ps.setString(3, text);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(fr, "Entry saved.");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }
}
