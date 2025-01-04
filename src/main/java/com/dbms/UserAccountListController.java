package com.dbms;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.dbms.database.UserAccountDB;
import com.dbms.utils.ThrowAlert;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class UserAccountListController implements Initializable {

    int selectedUserId;
    MainViewController mainViewController;

    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    @FXML
    TableView tv_userAccountList;

    @FXML
    void nop() {
        System.out.println("Nothing happened.");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        selectedUserId = -1;
        populateTable();
        nop();
    }

    public void populateTable() {
        try {
            UserAccountDB.populateTable(tv_userAccountList);
        } catch (SQLException e) {
            ThrowAlert.throwAlert("Error", "Failed to load User Account data", e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    void onTableClick() {
        try {
            TableColumn colName = (TableColumn) tv_userAccountList.getColumns().get(1);
            ObservableList<ObservableList> data = tv_userAccountList.getItems();
            int index = tv_userAccountList.getSelectionModel().getSelectedIndex();
            int userId = Integer.parseInt(data.get(index).get(0).toString());
            selectedUserId = userId;
        } catch (Exception e) {
            ThrowAlert.throwAlert("Error", "Failed to load User Account data", e.getMessage(), AlertType.ERROR);
        }
        System.out.println("Selected User Id: " + selectedUserId);
    }

    private boolean validateSelectedUserId() {
        if (selectedUserId == -1) {
            ThrowAlert.throwAlert("Error", "Please select a row first.", "", AlertType.ERROR);
            return false;
        }
        return true;
    }

    @FXML
    void deleteHandler() {
        if (!validateSelectedUserId()) {
            return;
        }
        if (!ThrowAlert.confirmAlert("Delete", "Are you sure?", "")) {
            return;
        }
        try {
            UserAccountDB.deleteUserAccount(selectedUserId);
            selectedUserId = -1;
            populateTable();
        } catch (SQLException e) {
            ThrowAlert.throwAlert("Error", "Failed to delete User Account", e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    void updateHandler() {
        if (!validateSelectedUserId()) {
            return;
        }
        try {
            mainViewController.openUserAccountDataEntryView(selectedUserId);
        } catch (IOException e) {
            ThrowAlert.throwAlert("Error", "Failed to open User Account Data Entry View", e.getMessage(),
                    AlertType.ERROR);
        }
    }

    @FXML
    void addHandler() {
        try {
            mainViewController.openUserAccountDataEntryView();
        } catch (IOException e) {
            ThrowAlert.throwAlert("Error", "Failed to open User Account Data Entry View", e.getMessage(),
                    AlertType.ERROR);
        }

    }
}
