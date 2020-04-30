package Mentality.components;

import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.util.List;

public class MyTableModel extends DefaultTableModel {
    private final String [] colNames = {"Username", "First Name", "Last Name", "Email"};
    private List<User> userList;

    public MyTableModel(Vector<User> UserList){
        this.userList = UserList;
    }

    /**
     *
     * @return number of columns in table
     */
    @Override
    public int getColumnCount(){
        return colNames.length;
    }

    /**
     *
     * @return number of rows in table
     */
    @Override
    public int getRowCount(){
        int size;
        if(userList == null){
            size = 0;
        }else{
            size = userList.size();
        }
        return size;
    }

    /**
     *
     * @param aValue
     * @param row
     * @param col
     */

    @Override
    public void setValueAt(Object aValue, int row, int col){
        User user = userList.get(row);

        if(col == 0){
            user.setUname((String) aValue);
        }else if (col == 1){
            user.setNameFirst((String) aValue);
        }else if (col == 2){
            user.setNameLast((String) aValue);
        }else if(col == 3) {
            user.setNameLast((String) aValue);
        }

        fireTableCellUpdated(row, col);
    }

    /**
     *
     * @param row
     */
    public void deleteRow(int row){
        userList.remove(row);
        fireTableRowsDeleted(row,row);
    }

    /**
     *
     * @param user
     */
    public void addData(User user){
        userList.add(user);
        fireTableRowsInserted(getRowCount()-1,getRowCount()-1);
    }

    /**
     *
     * @param row
     * @param col
     * @return
     */
    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }

    /**
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return userList.get(rowIndex).getUname();
        }else if (columnIndex == 1){
            return userList.get(rowIndex).getNameFirst();
        }else if (columnIndex == 2){
            return userList.get(rowIndex).getNameLast();
        }else if(columnIndex == 3) {
            return userList.get(rowIndex).getEmail();
        }
        else
            return null;
    }

    /**
     *
     * @param col
     * @return column name
     */
    public String getColumnName(int col){
        return colNames[col];
    }

    /**
     *
     * @return string array that holds all column names
     */
    public String[] getColNames() {
        return colNames;
    }

    /**
     *
     * @return TableModel
     */
    public MyTableModel getModel(){return this;}

    /**
     *
     * @param col
     * @return Class
     */
    public Class getColumnClass(int col){
        return getValueAt(0, col).getClass();
    }

    /**
     *
     * @return institution list
     */
    public List<User> getInstitutionList() {
        return userList;
    }


    /**
     *
     * @return number of institutions
     */
    public int getCount(){
        return userList.size();
    }


}

