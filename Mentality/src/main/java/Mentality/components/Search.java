package Mentality.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Mentality.utils.CustomUtilities.ColorPalette.*;

public class Search extends JPanel implements ActionListener {
    private JTextField searchable = new JTextField(50);
    private JButton searchButton = new JButton("Search Users");
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


        this.add(searchable);
        this.add(searchButton);
        searchButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,"User Does Not Exist");

    }

}