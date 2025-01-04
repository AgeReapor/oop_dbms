package com.dbms.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbms.models.UserAccount;

public class UserAccountDB {

    public static UserAccount fetchUserAccount(int userId) throws SQLException {
        UserAccount userAccount = null;
        String query = "SELECT * FROM `" + DBConnection.getDBName() + "`.`user_account` WHERE user_id = "
                + userId + ";";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int USER_ID = rs.getInt("user_id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String lastname = rs.getString("lastname");
            String firstname = rs.getString("firstname");
            String middlename = rs.getString("middlename");
            userAccount = new UserAccount(USER_ID, username, password, lastname, firstname, middlename);
        }
        conn.close();

        return userAccount;
    }

    public static void addUserAccount(UserAccount userAccount) throws SQLException {
        String username = userAccount.getUsername();
        String password = userAccount.getPassword();
        String lastname = userAccount.getLastname();
        String firstname = userAccount.getFirstname();
        String middlename = userAccount.getMiddlename();

        String query = "INSERT INTO `" + DBConnection.getDBName()
                + "`.`user_account` (`username`, `password`, `lastname`, `firstname`, `middlename`) VALUES ('"
                + username + "', '" + password + "', '" + lastname + "', '" + firstname + "', '" + middlename + "');";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.execute();
        conn.close();
    }

    public static void updateUserAccount(UserAccount userAccount) throws SQLException {
        int userId = userAccount.getUserId();
        String username = userAccount.getUsername();
        String password = userAccount.getPassword();
        String lastname = userAccount.getLastname();
        String firstname = userAccount.getFirstname();
        String middlename = userAccount.getMiddlename();

        String query = "UPDATE `" + DBConnection.getDBName() + "`.`user_account` SET `username` = '" + username
                + "', `password` = '" + password + "', `lastname` = '" + lastname + "', `firstname` = '" + firstname
                + "', `middlename` = '" + middlename + "' WHERE `user_account`.`user_id` = " + userId + ";";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.execute();
        conn.close();
    }

    public void deleteUserAccount(int userId) throws SQLException {
        // update status to 0 (deleted)
        String query = "UPDATE `" + DBConnection.getDBName()
                + "`.`user_account` SET `status` = 0 WHERE `user_account`.`user_id` = " + userId + ";";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.execute();
        conn.close();
    }
}