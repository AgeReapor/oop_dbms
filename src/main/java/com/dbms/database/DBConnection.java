package com.dbms.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
    static private final String DB_URL = "jdbc:mysql://localhost:3309/";
    static private final String USER = "root";
    static private final String PASS = "NotCat24";
    static private final String DB_NAME = "oop_dbms";

    public static String getDBName() {
        return DB_NAME;
    }

    public static ResultSet executeQuery(String query, String successMsg, String failMsg)
            throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery();
            System.out.println(successMsg);
        } catch (SQLException e) {
            System.out.println(failMsg);
            e.printStackTrace();
        }
        return rs;
    }

    public static boolean execute(String query, String successMsg, String failMsg) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt = conn.prepareStatement(query);
        boolean result = false;
        try {
            result = stmt.execute();
            System.out.println(successMsg);
        } catch (SQLException e) {
            System.out.println(failMsg);
            e.printStackTrace();
        }
        return result;
    }
}
