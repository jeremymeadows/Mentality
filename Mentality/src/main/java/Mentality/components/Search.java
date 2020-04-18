package Mentality.components;

import Mentality.Runner;

import static Mentality.utils.CustomUtilities.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Mentality.utils.CustomUtilities.ColorPalette.*;

public class Search extends JPanel implements ActionListener {
    private JTextField searchable = new JTextField(50);
    private JButton searchButton = initJButton("Search Users", this);
//    private JPanel searchPanel = new JPanel();

    public Search() throws HeadlessException {
        super();
        this.setLayout(new GridLayout(1,1));
        this.setBackground(mainColor);
        this.setVisible(true);

        searchable.setBounds(180,40,220,40);
        searchButton.setSize( new Dimension(5,10));
        searchButton.setLocation(200, 65);
        searchButton.setVisible(true);
        searchable.setVisible(true);


        add(searchable);
        add(searchButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = searchable.getText();
        ResultSet r = Runner.query("SELECT * FROM users WHERE username LIKE '%" + s + "%'");
        try {
            while (r.next()) {
                System.out.println(r.getString("username"));
            }
            return;
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        JOptionPane.showMessageDialog(null,"User Does Not Exist");
    }

}