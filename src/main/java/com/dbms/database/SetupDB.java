package com.dbms.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SetupDB {

    // Initializes the database and tables
    public static void run() throws SQLException {
        initDB();
        initTables();
    }

    // Initializes the tables
    private static void initTables() throws SQLException {
        createUserAccountTable();
        createBusinessClearanceTransactionTable();
    }

    // Initializes the database
    private static void initDB() throws SQLException {
        String dbName = DBConnection.getDBName();
        String query = "CREATE DATABASE IF NOT EXISTS " + dbName + ";";
        PreparedStatement stmt = DBConnection.getConnection().prepareStatement(query);

        String successMsg = "Database `" + dbName + "` initialized.";
        String failMsg = "Failed to initialize database `" + dbName + "`.";

        DBConnection.execute(stmt, successMsg, failMsg);
    }

    // Initializes the `user_account` table
    private static void createUserAccountTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS `oop_dbms`.`user_account` (\n" +
                "  `user_id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `username` VARCHAR(20) NOT NULL,\n" +
                "  `password` VARCHAR(20) NOT NULL,\n" +
                "  `lastname` VARCHAR(25) NOT NULL,\n" +
                "  `firstname` VARCHAR(25) NOT NULL,\n" +
                "  `middlename` VARCHAR(25) NOT NULL,\n" +
                "  `status` INT NOT NULL DEFAULT 1,\n" +
                "  PRIMARY KEY (`user_id`),\n" +
                "  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE\n" +
                ");";
        PreparedStatement stmt = DBConnection.getConnection().prepareStatement(query);
        String successMsg = "Table `user_account` initialized.";
        String failMsg = "Failed to initialize table `user_account`.";
        DBConnection.execute(stmt, successMsg, failMsg);
    }

    // Initializes the `business_clearance_transaction` table
    private static void createBusinessClearanceTransactionTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS `oop_dbms`.`business_clearance_transaction` (\n" +
                "  `transaction_id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `inspection_type` ENUM('new', 'renewal') NOT NULL,\n" +
                "  `owner` VARCHAR(75) NOT NULL,\n" +
                "  `owner_address` VARCHAR(255) NOT NULL,\n" +
                "  `business_name` VARCHAR(255) NOT NULL,\n" +
                "  `business_type` VARCHAR(25) NOT NULL,\n" +
                "  `contact_number` VARCHAR(20) NOT NULL,\n" +
                "  `property_type` ENUM('owned', 'rented', 'lessor') NOT NULL,\n" +
                "  `registration_number` VARCHAR(25) NOT NULL,\n" +
                "  `inspector` VARCHAR(75) NOT NULL,\n" +
                "  `inspection_date` DATE NOT NULL,\n" +
                "  `amount` DECIMAL NOT NULL,\n" +
                "  `official_receipt_number` VARCHAR(25) NOT NULL,\n" +
                "  `status` INT NOT NULL DEFAULT 1,\n" +
                "  PRIMARY KEY (`transaction_id`)\n" +
                ");";
        PreparedStatement stmt = DBConnection.getConnection().prepareStatement(query);

        String successMsg = "Table `business_clearance_transaction` initialized.";
        String failMsg = "Failed to initialize table `business_clearance_transaction`.";

        DBConnection.execute(stmt, successMsg, failMsg);
    }

}
