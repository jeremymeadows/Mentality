package Mentality.components;

import java.sql.*;

public class Database {
    private static Connection con;
    private static Statement st;

    public Database() throws SQLException {
        con = DriverManager.getConnection("jdbc:postgresql:test", "mentality", "postgresql");
        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    public void close() throws SQLException {
        con.close();
    }

    public ResultSet query(String q) throws SQLException {
        return st.executeQuery(q);
    }
}
