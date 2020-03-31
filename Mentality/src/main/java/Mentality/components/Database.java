package Mentality.components;

import java.sql.*;

public class Database {
    private static Connection con;
    private static Statement st;

    public Database() throws SQLException {
//        con = DriverManager.getConnection("jdbc:postgresql://jeremymeadows.duckdns.org:5432/mentality", "mentality", "mentalityadmin");
        con = DriverManager.getConnection("jdbc:mysql://mentality.jeremy.not-pc.com:3306/mentality", "jeremybmeadows", "passw0rd");

        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    public void close() throws SQLException {
        con.close();
    }

    public ResultSet query(String q) throws SQLException {
        return st.executeQuery(q);
    }
    public int update(String q) throws SQLException {
        return st.executeUpdate(q);
    }
}