package com.dbms;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.dbms.database.UserAccountDB;
import com.dbms.utils.ThrowAlert;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import org.controlsfx.control.CheckComboBox;

/**
 * Controller for managing the user account list view.
 * Handles interactions with the user account table, search functionality, and
 * CRUD operations.
 */
public class UserAccountListController implements Initializable {

    final String[] searchOptions = { "username", "firstname", "middlename", "lastname" };

    int selectedUserId;
    MainViewController mainViewController;

    /**
     * Sets the main view controller.
     * 
     * @param mainViewController The main view controller.
     */
    public void setMainViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    @FXML
    TableView tv_userAccountList;

    @FXML
    CheckComboBox ccb_searchOptions;
    @FXML
    TextField tf_searchField;

    /**
     * Initializes the controller class. This method is automatically called after
     * the fxml file has been loaded.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        selectedUserId = -1;

        // Populate table from database
        populateTable();

        tv_userAccountList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Initialize search options
        ccb_searchOptions.getItems().addAll(searchOptions);
        ccb_searchOptions.getCheckModel().check(0);

        // Bind search field to search event handler
        tf_searchField.textProperty().addListener((observable, oldValue, newValue) -> onSearchFieldChange());

        // Bind checkComboBox to search event handler
        ccb_searchOptions.getCheckModel().getCheckedItems().addListener(new ListChangeListener() {
            public void onChanged(ListChangeListener.Change c) {
                onSearchFieldChange();
            }
        });
    }

    /**
     * Handles click events on the user account table.
     * Sets the selectedUserId based on the selected row.
     */
    @FXML
    void onTableClick() {
        try {
            TableColumn colName = (TableColumn) tv_userAccountList.getColumns().get(1);
            ObservableList<ObservableList> data = tv_userAccountList.getItems();
            int index = tv_userAccountList.getSelectionModel().getSelectedIndex();
            // Do nothing if clicked on header
            if (index == -1)
                return;
            int userId = Integer.parseInt(data.get(index).get(0).toString());
            selectedUserId = userId;
        } catch (Exception e) {
            ThrowAlert.throwAlert("Error", "Failed to load User Account data", e.getMessage(), AlertType.ERROR);
        }
    }

    /**
     * Handles the delete action for the selected user account.
     */
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

    /**
     * Handles the update action for the selected user account.
     */
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

    /**
     * Handles the add action to create a new user account.
     */
    @FXML
    void addHandler() {
        try {
            mainViewController.openUserAccountDataEntryView();
        } catch (IOException e) {
            ThrowAlert.throwAlert("Error", "Failed to open User Account Data Entry View", e.getMessage(),
                    AlertType.ERROR);
        }
    }

    /**
     * Handles changes in the search field and updates the table based on search
     * criteria.
     */
    @FXML
    void onSearchFieldChange() {
        // Get search attributes
        ArrayList<String> options = getSearchOptions();
        String searchString = tf_searchField.getText();

        // If search field is empty, populate table with all records
        if (searchString.isEmpty()) {
            try {
                UserAccountDB.populateTable(tv_userAccountList);
            } catch (SQLException e) {
                ThrowAlert.throwAlert("Error", "Failed to load User Account data", e.getMessage(), AlertType.ERROR);
            }
            return;
        }

        // If no search options are selected, do nothing
        if (options.isEmpty())
            return;

        // Else, populate table with search results
        try {
            UserAccountDB.populateSearchResults(tv_userAccountList, searchString, options);
        } catch (SQLException e) {
            ThrowAlert.throwAlert("Error", "Failed to load search results", e.getMessage(), AlertType.ERROR);
        }
    }

    /**
     * Validates if a user account is selected.
     * 
     * @return true if a user account is selected, false otherwise.
     */
    private boolean validateSelectedUserId() {
        if (selectedUserId == -1) {
            ThrowAlert.throwAlert("Error", "Please select a row first.", "", AlertType.ERROR);
            return false;
        }
        return true;
    }

    /**
     * Populates the user account table with data from the database.
     */
    private void populateTable() {
        try {
            UserAccountDB.populateTable(tv_userAccountList);
        } catch (SQLException e) {
            ThrowAlert.throwAlert("Error", "Failed to load User Account data", e.getMessage(), AlertType.ERROR);
        }
    }

    /**
     * Retrieves the search options selected in the CheckComboBox.
     * 
     * @return A list of selected search options.
     */
    private ArrayList<String> getSearchOptions() {
        ArrayList<String> options = new ArrayList<String>();
        ObservableList data = ccb_searchOptions.getCheckModel().getCheckedItems();
        for (Object item : data) {
            options.add(item.toString());
        }
        return options;
    }
}
