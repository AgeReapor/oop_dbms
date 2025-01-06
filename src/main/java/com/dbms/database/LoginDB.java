package com.dbms.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDB {

    // returns user_id, or -1 if not found
    public static int fetchUserId(String username, String password) throws SQLException {
        int ret = -1;

        String query = "SELECT user_id FROM `" + DBConnection.getDBName() + "`.`user_account` WHERE username = '"
                + username
                + "' AND password = '" + password
                + "' AND status = 1";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            ret = rs.getInt("user_id");
        }
        conn.close();

        return ret;
    }

}
