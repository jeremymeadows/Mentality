package Mentality.frames;

import Mentality.components.MyTableModel;
import Mentality.components.User;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Vector;

public class FriendTable extends JPanel{

    private final JTable table;
    private MyTableModel tableModel;
    Vector<User> vectorList;


    private JTextField filterText;
    private TableRowSorter<MyTableModel> sorter;


    public FriendTable() {
        super(new GridLayout(1,1));

        vectorList = new Vector<User>();

        //create table model
        this.tableModel = new MyTableModel(vectorList);

        //filter
        //Create a table with a sorter.
        sorter = new TableRowSorter<MyTableModel>(tableModel);

        //create jtable
        this.table = new JTable(tableModel);
        table.setRowSorter(sorter);


        this.table.setPreferredScrollableViewportSize(new Dimension(400, 100));
        this.table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setPreferredSize(new Dimension(400,100));

        //Add the scroll pane to this panel.
        add(scrollPane);

    }
    public MyTableModel getModel(){return tableModel;}
    public JTable getTable(){return table;}

}
