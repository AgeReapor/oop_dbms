package com.dbms;

import com.dbms.database.BusinessClearanceDB;
import com.dbms.models.BusinessClearanceTransaction;
import com.dbms.utils.ThrowAlert;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class BusinessClearanceEntryController implements Initializable {

    private final int transactionId;
    private final boolean isUpdate;
    private final BusinessClearanceTransaction businessClearanceTransaction;
    private final MainViewController mainViewController;

    @FXML
    ScrollPane sp_scrollpane;

    @FXML
    VBox vb_scrollContent;

    public BusinessClearanceEntryController() {
        System.out.println("New User Account.");
        transactionId = -1;
        isUpdate = false;
        this.mainViewController = null;
        businessClearanceTransaction = loadBusinessClearanceTransaction(transactionId);
    }

    public BusinessClearanceEntryController(MainViewController mainViewController) {
        System.out.println("New User Account.");
        transactionId = -1;
        isUpdate = false;
        this.mainViewController = mainViewController;
        businessClearanceTransaction = loadBusinessClearanceTransaction(transactionId);
    }

    public BusinessClearanceEntryController(MainViewController mainViewController, int transactionId) {
        System.out.println("Update User Account.");
        this.transactionId = transactionId;
        isUpdate = true;
        businessClearanceTransaction = loadBusinessClearanceTransaction(transactionId);
        this.mainViewController = mainViewController;
        System.out.println("UserId: " + transactionId);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // Always resize width of scroll content to scrollpane width
        sp_scrollpane.widthProperty()
                .addListener((observable, oldValue, newValue) -> vb_scrollContent.setPrefWidth(newValue.doubleValue()));

    }

    private BusinessClearanceTransaction loadBusinessClearanceTransaction(int transactionId) {
        if (!isUpdate) {
            System.out.println("Loaded new Business Clearance Transaction.");
            return new BusinessClearanceTransaction();
        }
        try {
            return BusinessClearanceDB.fetchBusinessClearanceTransaction(transactionId);
        } catch (SQLException e) {
            ThrowAlert.throwAlert("Error", "Database Error", e.getMessage(), Alert.AlertType.ERROR);
            return new BusinessClearanceTransaction();
        }
    }
}
