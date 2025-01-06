package com.dbms;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.dbms.database.SetupDB;
import com.dbms.utils.ThrowAlert;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
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
            openUserAccountListView();
        } catch (IOException e) {
            ThrowAlert.throwAlert("Error", "Failed to open User Account List View", e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    public void openUserAccountDataEntryView() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("userAccountDataEntryView");
        UserAccountDataEntryController controller = new UserAccountDataEntryController(this);
        loader.setController(controller);
        Parent view = loader.load();
        bp_mainView.setCenter(view);
    }

    public void openUserAccountDataEntryView(int userId) throws IOException {
        FXMLLoader loader = App.getFXMLLoader("userAccountDataEntryView");
        UserAccountDataEntryController controller = new UserAccountDataEntryController(this, userId);
        loader.setController(controller);
        Parent view = loader.load();
        bp_mainView.setCenter(view);
    }

    @FXML
    public void openUserAccountListView() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("userAccountListView");
        Parent view = loader.load();
        UserAccountListController controller = loader.getController();
        controller.setMainViewController(this);
        bp_mainView.setCenter(view);
    }

    @FXML
    public void openbusinessClearanceEntryView() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("businessClearanceEntryView");
        BusinessClearanceEntryController controller = new BusinessClearanceEntryController(this);
        loader.setController(controller);
        Parent view = loader.load();
        bp_mainView.setCenter(view);
    }

    public void openbusinessClearanceEntryView(int transactionId) throws IOException {
        FXMLLoader loader = App.getFXMLLoader("businessClearanceEntryView");
        BusinessClearanceEntryController controller = new BusinessClearanceEntryController(this, transactionId);
        loader.setController(controller);
        Parent view = loader.load();
        bp_mainView.setCenter(view);
    }

    @FXML
    public void openbusinessClearanceListView() throws IOException {
        FXMLLoader loader = App.getFXMLLoader("businessClearanceListView");
        Parent view = loader.load();
        BusinessClearanceListController controller = loader.getController();
        controller.setMainViewController(this);
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
