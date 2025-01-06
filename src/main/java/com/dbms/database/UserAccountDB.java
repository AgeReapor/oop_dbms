package com.dbms.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dbms.models.UserAccount;
import com.dbms.utils.PopulateTable;

import javafx.scene.control.TableView;

//  Handles CRUD operations for user accounts in the database.
public class UserAccountDB {

    // Retrieves a user account from the database using the given user ID.
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

    // Adds a new user account to the database.
    public static void addUserAccount(UserAccount userAccount) throws SQLException {
        String query = "INSERT INTO `" + DBConnection.getDBName()
                + "`.`user_account` (`username`, `password`, `lastname`, `firstname`, `middlename`) VALUES (?, ?, ?, ?, ?)";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.setString(1, userAccount.getUsername());
        stmt.setString(2, userAccount.getPassword());
        stmt.setString(3, userAccount.getLastname());
        stmt.setString(4, userAccount.getFirstname());
        stmt.setString(5, userAccount.getMiddlename());

        stmt.execute();
        conn.close();
    }

    // Updates an existing user account in the database.
    public static void updateUserAccount(UserAccount userAccount) throws SQLException {
        String query = "UPDATE `" + DBConnection.getDBName()
                + "`.`user_account` SET `username` = ?, `password` = ?, `lastname` = ?, `firstname` = ?, `middlename` = ? WHERE `user_account`.`user_id` = ?";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.setString(1, userAccount.getUsername());
        stmt.setString(2, userAccount.getPassword());
        stmt.setString(3, userAccount.getLastname());
        stmt.setString(4, userAccount.getFirstname());
        stmt.setString(5, userAccount.getMiddlename());
        stmt.setInt(6, userAccount.getUserId());

        stmt.execute();
        conn.close();
    }

    // Deletes a user account from the database.
    public static void deleteUserAccount(int userId) throws SQLException {
        // update status to 0 (deleted)
        String query = "UPDATE `" + DBConnection.getDBName()
                + "`.`user_account` SET `status` = 0 WHERE `user_account`.`user_id` = " + userId + ";";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);

        stmt.execute();
        conn.close();
    }

    // Populates a TableView with user accounts from the database.
    public static void populateTable(TableView tableView) throws SQLException {
        String query = "SELECT user_id, username, password, firstname, middlename, lastname FROM `"
                + DBConnection.getDBName() + "`.`user_account` WHERE status = 1;";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        PopulateTable.populateTable(tableView, rs);
        conn.close();
    }

    // Populates a TableView with search results from the database.
    public static void populateSearchResults(TableView tableView, String searchString, ArrayList<String> searchOptions)
            throws SQLException {
        ResultSet rs = searchQuery(searchString, searchOptions);
        PopulateTable.populateTable(tableView, rs);
    }

    // Executes a search query on the user accounts in the database.
    private static ResultSet searchQuery(String searchString, ArrayList<String> searchOptions) throws SQLException {
        String query = "SELECT user_id, username, password, firstname, middlename, lastname FROM `"
                + DBConnection.getDBName() + "`.`user_account` WHERE status = 1 AND (";
        for (int i = 0; i < searchOptions.size(); i++) {
            query += searchOptions.get(i) + " LIKE ? ";
            if (i < searchOptions.size() - 1) {
                query += "OR ";
            }
        }
        query += ");";

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        for (int i = 0; i < searchOptions.size(); i++) {
            stmt.setString(i + 1, "%" + searchString + "%");
        }
        ResultSet rs = stmt.executeQuery();

        return rs;
    }
}
