package Mentality.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Mentality.utils.CustomUtilities.ColorPalette.*;

public class Search extends JPanel implements ActionListener {
    private JTextField searchable = new JTextField(30);
    private JButton searchButton = new JButton("Search");
//    private JPanel searchPanel = new JPanel();

    public Search() throws HeadlessException {
        super();
        this.setLayout(new GridLayout(1,0));
        this.setBackground(mainColor);
        this.setVisible(true);

        searchable.setBounds(200,40,200,40);
        searchButton.setSize( new Dimension(20,20));
        searchButton.setLocation(200, 65);
        searchButton.setVisible(true);

        this.add(searchable);
        this.add(searchButton);
        searchButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,"User Does Not Exist");

    }

}