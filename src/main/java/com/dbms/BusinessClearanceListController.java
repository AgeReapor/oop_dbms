package com.dbms;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.dbms.database.BusinessClearanceDB;
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
 * Controller for managing the business clearance list view.
 * Handles interactions with the business clearance table, search functionality,
 * and
 * CRUD operations.
 */
public class BusinessClearanceListController implements Initializable {

    final String[] searchOptions = { "owner", "owner_address", "business_name", "business_address", "business_type",
            "property_type", "registration_number", "inspector", "inspection_type", "inspection_date", "amount",
            "official_receipt_number" };

    int selectedTransactionId;
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
    TableView tv_transactionList;

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
        selectedTransactionId = -1;

        // Populate table from database
        populateTable();

        // adjust column widths
        tv_transactionList.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        for (Object col : tv_transactionList.getColumns()) {
            TableColumn column = (TableColumn) col;
            column.setPrefWidth(140);
        }

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
     * Handles click events on the transaction table.
     * Sets the selectedTransactionId based on the selected row.
     */
    @FXML
    void onTableClick() {
        try {
            TableColumn colName = (TableColumn) tv_transactionList.getColumns().get(1);
            ObservableList<ObservableList> data = tv_transactionList.getItems();
            int index = tv_transactionList.getSelectionModel().getSelectedIndex();
            // Do nothing if clicked on header
            if (index == -1)
                return;
            int transactionId = Integer.parseInt(data.get(index).get(0).toString());
            selectedTransactionId = transactionId;
        } catch (Exception e) {
            ThrowAlert.throwAlert("Error", "Failed to load Transaction data", e.getMessage(), AlertType.ERROR);
        }
    }

    /**
     * Handles the delete action for the selected transaction.
     */
    @FXML
    void deleteHandler() {
        if (!validateSelectedTransactionId()) {
            return;
        }
        if (!ThrowAlert.confirmAlert("Delete", "Are you sure?", "")) {
            return;
        }
        try {
            BusinessClearanceDB.deleteBusinessClearanceTransaction(selectedTransactionId);
            selectedTransactionId = -1;
            populateTable();
        } catch (SQLException e) {
            ThrowAlert.throwAlert("Error", "Failed to delete Transaction", e.getMessage(), AlertType.ERROR);
        }
    }

    /**
     * Handles the update action for the selected transaction.
     */
    @FXML
    void updateHandler() {
        if (!validateSelectedTransactionId()) {
            return;
        }
        try {
            mainViewController.openbusinessClearanceEntryView(selectedTransactionId);
        } catch (IOException e) {
            ThrowAlert.throwAlert("Error", "Failed to open Transaction Data Entry View", e.getMessage(),
                    AlertType.ERROR);
        }
    }

    /**
     * Handles the add action to create a new transaction.
     */
    @FXML
    void addHandler() {
        try {
            mainViewController.openbusinessClearanceEntryView();
        } catch (IOException e) {
            ThrowAlert.throwAlert("Error", "Failed to open Transaction Data Entry View", e.getMessage(),
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
                BusinessClearanceDB.populateTable(tv_transactionList);
            } catch (SQLException e) {
                ThrowAlert.throwAlert("Error", "Failed to load  Business Clearance data", e.getMessage(),
                        AlertType.ERROR);
            }
            return;
        }

        // If no search options are selected, do nothing
        if (options.isEmpty())
            return;

        // Else, populate table with search results
        try {
            BusinessClearanceDB.populateSearchResults(tv_transactionList, searchString, options);
        } catch (SQLException e) {
            ThrowAlert.throwAlert("Error", "Failed to load search results", e.getMessage(), AlertType.ERROR);
        }
    }

    /**
     * Validates if a transaction is selected.
     * 
     * @return true if a transaction is selected, false otherwise.
     */
    private boolean validateSelectedTransactionId() {
        if (selectedTransactionId == -1) {
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
            BusinessClearanceDB.populateTable(tv_transactionList);
        } catch (SQLException e) {
            ThrowAlert.throwAlert("Error", "Failed to load Transaction data", e.getMessage(), AlertType.ERROR);
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
