package com.dbms;

import com.dbms.database.SetupDB;

import java.sql.SQLException;

import javafx.fxml.FXML;

public class UserAccountDataEntryController {
    @FXML
    void nop() {
        System.out.println("Nothing happened.");
    }

    @FXML
    void createDB() {
        try {
            SetupDB.run();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Database setup comlete.");
        }
    }

}
