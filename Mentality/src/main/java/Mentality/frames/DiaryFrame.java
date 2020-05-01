package Mentality.frames;

import Mentality.Runner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**********************************
 * singleton design pattern
 **********************************/

public class DiaryFrame extends JDialog {
    private JButton newDiaryEntry;
    final private JTable jtbl;
    private JScrollPane jps;
    final private DefaultTableModel mtm;


    public DiaryFrame(JFrame parent, String message) {
        super(parent, message);
        setLayout(new BorderLayout());
        mtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        mtm.addColumn("Date");
        mtm.addColumn("Entry");

        jtbl = new JTable(mtm);
        jtbl.setPreferredScrollableViewportSize(new Dimension(600, 800));
        jtbl.setFillsViewportHeight(true);
        jtbl.setFocusable(false);
        jtbl.setRowSelectionAllowed(true);

        jps = new JScrollPane(jtbl);

        //
        try {
            String query = "select date, journal FROM journal";
            ResultSet rs = Runner.query(query);
            while (rs.next()) {
                LocalDate id = rs.getObject("date", LocalDate.class);
                //java.sql.Date id = rs.getDate("date");
                String entry = rs.getString("journal");
                mtm.addRow(new Object[]{id.toString(), entry});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //

        jtbl.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if(me.getClickCount() == 2) {
                    JTextArea textArea = new JTextArea();
                    int row = jtbl.getSelectedRow();
                    String date = (String) jtbl.getValueAt(row, 0);
                    String message = (String) jtbl.getValueAt(row, 1);
                    textArea.setText(message);
                    textArea.setEditable(false);
                    textArea.setLineWrap(true);
                    JScrollPane scrollPane = new JScrollPane(textArea,
                            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    JDialog d = new JDialog(parent, date);
                    d.setSize(300,500);
                    d.setLocationRelativeTo(null);
                    d.setVisible(true);
                    d.add(scrollPane);
                    d.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                }
            }
        });

        newDiaryEntry = new JButton("New Diary Entry");

        add(jps, BorderLayout.CENTER);
        add(newDiaryEntry, BorderLayout.SOUTH);

        setSize(600, 500);
        setVisible(true);

        newDiaryEntry.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 NewDiaryEntry d = new NewDiaryEntry();
             }
        });
    }
}
