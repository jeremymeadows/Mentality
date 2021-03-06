package Mentality.components;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static java.util.Calendar.*;

public class Calendar extends JPanel implements ItemListener, MouseListener {
    JPanel p1, p2;
    JComboBox<String> month;
    JComboBox<Integer> year;
    JLabel sel;
    final int yearmin = 2020, yearmax = 2030;
    String[] weekdays = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
    String[] months = { "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December" };
    int[] days ={ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    public String[] selected = new String[3]; // day, month, year

    public Calendar() {
        super();
        p1 = new JPanel();
        month = new JComboBox<>();
        for (String s : months) {
            month.addItem(s);
        }
        month.addItemListener(this);
        year = new JComboBox<>();
        for (int i = yearmin; i <= yearmax; i++) {
            year.addItem(i);
        }
        year.addItemListener(this);
        p1.add(month);
        p1.add(year);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(0,7,5,5));
        Date date = new Date();

        drawCalendar(months[date.getMonth()], (1900+date.getYear()));
        year.setSelectedItem((1900+date.getYear()));
        month.setSelectedItem(months[date.getMonth()]);
        add(p1);
        add(p2);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            drawCalendar((String)month.getSelectedItem(), (Integer)year.getSelectedItem());
        }
    }

    public void drawCalendar(String inputMonth, int inputYear) {
        p2.removeAll();
        for (String weekday : weekdays) {
            JLabel label = new JLabel(weekday);
            label.setHorizontalAlignment(SwingConstants.RIGHT);
            p2.add(label);
        }
        Date date = new Date("01-"+inputMonth+"-"+inputYear);
        int noOfDaysInMonth = days[date.getMonth()];
        if (date.getYear()%4==0 && date.getMonth()== FEBRUARY) {
            noOfDaysInMonth = 29;
        }

        for (int i = 1, day = 1; day <= noOfDaysInMonth; i++) {
            for (int j = 0; j < 7; j++) {
                if (day == 1) {
                    if (j == date.getDay()) {
                        JLabel label = new JLabel(String.valueOf(day));
                        label.setHorizontalAlignment(SwingConstants.RIGHT);
                        label.addMouseListener(this);
                        p2.add(label);
                        day++;
                    } else {
                        JLabel label = new JLabel("");
                        p2.add(label);
                    }
                } else {
                    JLabel label = new JLabel(String.valueOf(day));
                    label.setHorizontalAlignment(SwingConstants.RIGHT);
                    label.addMouseListener(this);
                    p2.add(label);
                    day++;
                } if (day > noOfDaysInMonth) {
                    break;
                }
            }
        }
        p2.validate();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getSource().getClass().equals(JLabel.class)) {
            JLabel l = (JLabel)mouseEvent.getSource();

            if (sel != null) {
                sel.setText(selected[0]);
            }
            sel = l;

            selected[0] = l.getText();
            selected[1] = String.valueOf(month.getSelectedIndex() + 1);
            selected[2] = String.valueOf(yearmin + year.getSelectedIndex());

            l.setText("*");
            l.setBackground(Color.green);
            p2.repaint();
        }
    }
    @Override
    public void mousePressed(MouseEvent mouseEvent) {}
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}
    @Override
    public void mouseExited(MouseEvent mouseEvent) {}
}
