package com.dbms.database;

import java.sql.SQLException;

public class SetupDB {
    private static void createDB() throws SQLException {
        String dbName = DBConnection.getDBName();
        String query = "CREATE DATABASE IF NOT EXISTS " + dbName + ";";
        String successMsg = "Database `" + dbName + "` initialized.";
        String failMsg = "Failed to initialize database `" + dbName + "`.";
        DBConnection.execute(query, successMsg, failMsg);
    }

    private static void createPersonTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS `oop_dbms`.`person` (\n" + //
                "  `person_id` INT NOT NULL AUTO_INCREMENT,\n" + //
                "  `lastname` VARCHAR(25) NOT NULL,\n" + //
                "  `firstname` VARCHAR(25) NOT NULL,\n" + //
                "  `middlename` VARCHAR(25) NOT NULL,\n" + //
                "  PRIMARY KEY (`person_id`));\n" + //
                "";
        String successMsg = "Table `person` initialized.";
        String failMsg = "Failed to initialize table `person`.";
        DBConnection.execute(query, successMsg, failMsg);
        return;
    }

    private static void createAddressTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS `oop_dbms`.`address` (\n" + //
                "  `address_id` INT NOT NULL AUTO_INCREMENT,\n" + //
                "  `street` VARCHAR(25) NULL,\n" + //
                "  `barangay` VARCHAR(25) NOT NULL,\n" + //
                "  `city` VARCHAR(25) NOT NULL,\n" + //
                "  `province` VARCHAR(25) NOT NULL,\n" + //
                "  PRIMARY KEY (`address_id`));\n" + //
                "";
        String successMsg = "Table `address` initialized.";
        String failMsg = "Failed to initialize table `address`.";
        DBConnection.execute(query, successMsg, failMsg);
        return;
    }

    private static void createBusinessTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS `oop_dbms`.`business` (\n" + //
                "  `business_id` INT NOT NULL AUTO_INCREMENT,\n" + //
                "  `business_name` VARCHAR(255) NOT NULL,\n" + //
                "  `business_type` VARCHAR(255) NOT NULL,\n" + //
                "  `owner_name_id` INT NOT NULL,\n" + //
                "  `owner_address_id` INT NOT NULL,\n" + //
                "  `contact_number` VARCHAR(20) NOT NULL,\n" + //
                "  `property_type` ENUM('owned', 'rented', 'lessor') NOT NULL,\n" + //
                "  `retistration_number` VARCHAR(25) NOT NULL,\n" + //
                "  PRIMARY KEY (`business_id`),\n" + //
                "  INDEX `owner_name_id_idx` (`owner_name_id` ASC) VISIBLE,\n" + //
                "  INDEX `owner_address_id_idx` (`owner_address_id` ASC) VISIBLE,\n" + //
                "  CONSTRAINT `owner_name_id`\n" + //
                "    FOREIGN KEY (`owner_name_id`)\n" + //
                "    REFERENCES `oop_dbms`.`person` (`person_id`)\n" + //
                "    ON DELETE NO ACTION\n" + //
                "    ON UPDATE NO ACTION,\n" + //
                "  CONSTRAINT `owner_address_id`\n" + //
                "    FOREIGN KEY (`owner_address_id`)\n" + //
                "    REFERENCES `oop_dbms`.`address` (`address_id`)\n" + //
                "    ON DELETE NO ACTION\n" + //
                "    ON UPDATE NO ACTION);\n" + //
                "";
        String successMsg = "Table `business` initialized.";
        String failMsg = "Failed to initialize table `business`.";
        DBConnection.execute(query, successMsg, failMsg);
        return;
    }

    private static void createUserAccountTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS `oop_dbms`.`user_account` (\n" + //
                "  `user_id` INT NOT NULL AUTO_INCREMENT,\n" + //
                "  `username` VARCHAR(20) NOT NULL,\n" + //
                "  `password` VARCHAR(20) NOT NULL,\n" + //
                "  `person_id` INT NOT NULL,\n" + //
                "  `status` INT NOT NULL DEFAULT 1,\n" + //
                "  PRIMARY KEY (`user_id`),\n" + //
                "  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,\n" + //
                "  INDEX `person_id_idx` (`person_id` ASC) VISIBLE,\n" + //
                "  CONSTRAINT `person_id`\n" + //
                "    FOREIGN KEY (`person_id`)\n" + //
                "    REFERENCES `oop_dbms`.`person` (`person_id`)\n" + //
                "    ON DELETE NO ACTION\n" + //
                "    ON UPDATE NO ACTION);\n" + //
                "";
        String successMsg = "Table `user_account` initialized.";
        String failMsg = "Failed to initialize table `user_account`.";
        DBConnection.execute(query, successMsg, failMsg);
        return;
    }

    private static void createBusinessClearanceTransactionTable() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS `oop_dbms`.`business_clearance_transaction` (\n" + //
                "  `transaction_id` INT NOT NULL AUTO_INCREMENT,\n" + //
                "  `transaction_type` ENUM('none', 'new', 'renewal') NOT NULL,\n" + //
                "  `business_id` INT NOT NULL,\n" + //
                "  `inspector_name_id` INT NULL,\n" + //
                "  `inspection_date` DATE NULL,\n" + //
                "  `amount` DECIMAL NOT NULL,\n" + //
                "  `official_receipt_number` VARCHAR(25) NOT NULL DEFAULT '0',\n" + //
                "  `status` INT NOT NULL DEFAULT 1,\n" + //
                "  PRIMARY KEY (`transaction_id`),\n" + //
                "  INDEX `business_id_idx` (`business_id` ASC) VISIBLE,\n" + //
                "  INDEX `inspector_name_id_idx` (`inspector_name_id` ASC) VISIBLE,\n" + //
                "  CONSTRAINT `business_id`\n" + //
                "    FOREIGN KEY (`business_id`)\n" + //
                "    REFERENCES `oop_dbms`.`business` (`business_id`)\n" + //
                "    ON DELETE NO ACTION\n" + //
                "    ON UPDATE NO ACTION,\n" + //
                "  CONSTRAINT `inspector_name_id`\n" + //
                "    FOREIGN KEY (`inspector_name_id`)\n" + //
                "    REFERENCES `oop_dbms`.`person` (`person_id`)\n" + //
                "    ON DELETE NO ACTION\n" + //
                "    ON UPDATE NO ACTION);\n" + //
                "";
        String successMsg = "Table `business_clearance_transaction` initialized.";
        String failMsg = "Failed to initialize table `business_clearance_transaction`.";
        DBConnection.execute(query, successMsg, failMsg);
        return;
    }

    private static void initTables() throws SQLException {
        createPersonTable();
        createAddressTable();
        createBusinessTable();
        createUserAccountTable();
        createBusinessClearanceTransactionTable();
    }

    public static void run() throws SQLException {
        createDB();
        initTables();
    }
}
