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

    // Returns a connection to the database
    protected static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    // Returns the database name
    protected static String getDBName() {
        return DB_NAME;
    }

    // Executes a query and returns a result set
    protected static ResultSet executeQuery(PreparedStatement stmt, String successMsg, String failMsg)
            throws SQLException {
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

    // Executes a query
    // returns true if the first result is a ResultSet object;
    // returns false if the first result is an update count or there is no result
    protected static boolean execute(PreparedStatement stmt, String successMsg, String failMsg) throws SQLException {
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
