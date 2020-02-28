package Mentality.components;

import java.sql.*;

public class Database {
    private static Connection con;
    private static Statement st;

    public Database() throws SQLException {
        con = DriverManager.getConnection("jdbc:postgresql:test", "mentality", "postgresql");
        st = con.createStatement();
    }

    public void close() throws SQLException {
        con.close();
    }

    public void query(String q) throws SQLException {
        ResultSet res = st.executeQuery(q);
    }
}
