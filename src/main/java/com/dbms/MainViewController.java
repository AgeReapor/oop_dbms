package com.dbms;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.dbms.database.SetupDB;
import com.dbms.utils.ThrowAlert;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class MainViewController implements Initializable {

    @FXML
    BorderPane bp_mainView;

    @FXML
    Text t_username;

    @FXML
    void nop() {
        System.out.println("Nothing happened.");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            SetupDB.run();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            openUserAccountDataEntryView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openUserAccountDataEntryView() throws IOException {
        Parent view = App.loadFXML("userAccountDataEntryView");

        bp_mainView.setCenter(view);
    }

    @FXML
    public void openUserAccountListView() throws IOException {
        Parent view = App.loadFXML("userAccountListView");
        bp_mainView.setCenter(view);
    }

    @FXML
    public void openbusinessClearanceEntryView() throws IOException {
        Parent view = App.loadFXML("businessClearanceEntryView");
        bp_mainView.setCenter(view);
    }

    @FXML
    public void openbusinessClearanceListView() throws IOException {
        Parent view = App.loadFXML("businessClearanceListView");
        bp_mainView.setCenter(view);
    }

    @FXML
    public void logout() throws IOException {
        boolean doLogout = ThrowAlert.confirmAlert("Logout", "Logout", "Are you sure you want to exit?");
        if (doLogout)
            App.setRoot("UserLoginView");
    }

    public void setUsername(String username) {
        t_username.setText(username);
    }
}
