package com.dbms.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Utility class to connect to the database
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
}
