package com.dbms.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDB {

    // returns user_id, or -1 if not found
    public static int fetchUserId(String username, String password) {
        int ret = -1;

        String query = "SELECT user_id FROM user_account WHERE username = '" + username + "' AND password = '"
                + password + "'";

        try {
            ResultSet rs = DBConnection.executeQuery(query, "User found.", "User not found.");
            if (rs.next()) {
                ret = rs.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

}
