package Mentality.components;

import Mentality.Runner;
import Mentality.components.MyTableModel;
import Mentality.components.User;
import Mentality.frames.Page;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        //redirect to user's page if double clicked
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                try {
                    ResultSet r = Runner.query("SELECT * FROM users WHERE email = '" + table.getValueAt(table.getSelectedRow(), 3).toString() + "';");
                    User u = new User();
                    while (r.next()) {
                        u.setUname(r.getString("username"));
                        u.setEmail(r.getString("email"));
                        u.setId(r.getInt("id"));
                        u.setNameFirst(r.getString("namefirst"));
                        u.setNameLast(r.getString("namelast"));
                    }
                    Runner.getRunnerInstance().changeFrame(new Page(u));
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
                System.out.println("row selected" + table.getValueAt(table.getSelectedRow(), 3).toString());
            }
        });


    }
    public MyTableModel getModel(){return tableModel;}
    public JTable getTable(){return table;}

}
