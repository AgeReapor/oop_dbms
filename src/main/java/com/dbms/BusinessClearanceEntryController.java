package com.dbms;

import com.dbms.database.BusinessClearanceDB;
import com.dbms.models.BusinessClearanceTransaction;
import com.dbms.models.InspectionType;
import com.dbms.utils.NodeValidation;
import com.dbms.utils.ThrowAlert;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class BusinessClearanceEntryController implements Initializable {

    private final int transactionId;
    private final boolean isUpdate;
    private final BusinessClearanceTransaction transaction;
    private final MainViewController mainViewController;

    public BusinessClearanceEntryController(MainViewController mainViewController) {
        System.out.println("New User Account.");
        transactionId = -1;
        isUpdate = false;
        this.mainViewController = mainViewController;
        transaction = loadBusinessClearanceTransaction(transactionId);
    }

    public BusinessClearanceEntryController(MainViewController mainViewController, int transactionId) {
        System.out.println("Update User Account.");
        this.transactionId = transactionId;
        isUpdate = true;
        this.mainViewController = mainViewController;
        System.out.println("TransactionId: " + transactionId);
        transaction = loadBusinessClearanceTransaction(transactionId);
    }

    @FXML
    ScrollPane sp_scrollpane;

    @FXML
    VBox vb_scrollContent;

    @FXML
    Text t_headerAdd, t_headerUpdate;

    @FXML
    ToggleGroup propertyTypeGroup, inspectionTypeGroup;

    @FXML
    RadioButton rb_owned, rb_rented, rb_lessor, rb_new, rb_renewal;

    @FXML
    TextField tf_owner, tf_ownerAddress, tf_businessName, tf_businessAddress, tf_businessType, tf_contactNumber,
            tf_regNo, tf_inspector, tf_amount, tf_ORNo;
    @FXML
    DatePicker dp_date;
    @FXML
    HBox hb_ownerValidator, hb_ownerAddressValidator, hb_businessNameValidator, hb_businessAddressValidator,
            hb_businessTypeValidator, hb_contactNumberValidator, hb_propertyTypeValidator, hb_regNoValidator,
            hb_inspectionTypeValidator, hb_inspectorValidator, hb_dateValidator, hb_amountValidator, hb_ORNoValidator;

    @FXML
    Button btn_add, btn_update;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // Populate Fields
        tf_owner.setText(transaction.getOwner());
        tf_ownerAddress.setText(transaction.getOwnerAddress());
        tf_businessName.setText(transaction.getBusinessName());
        tf_businessAddress.setText(transaction.getBusinessAddress());
        tf_businessType.setText(transaction.getBusinessType());
        tf_contactNumber.setText(transaction.getContactNumber());
        tf_regNo.setText(transaction.getRegistrationNumber());
        tf_inspector.setText(transaction.getInspector());
        tf_amount.setText(transaction.getAmount().toString());
        tf_ORNo.setText(transaction.getOfficialReceiptNumber());
        dp_date.setValue(transaction.getInspectionDate());

        // Populate ToggleGroups if not null
        if (transaction != null && transaction.getInspectionType() != null) {
            switch (transaction.getInspectionType()) {
                case NEW:
                    rb_new.fire();
                    break;
                case RENEWAL:
                    rb_renewal.fire();
                    break;
            }
        }

        if (transaction != null && transaction.getInspectionType() != null) {
            switch (transaction.getPropertyType()) {
                case OWNED:
                    rb_owned.fire();
                    break;
                case RENTED:
                    rb_rented.fire();
                    break;
                case LESSOR:
                    rb_lessor.fire();
                    break;
            }
        }

        // Always resize width of scroll content to scrollpane width
        sp_scrollpane.widthProperty()
                .addListener((observable, oldValue, newValue) -> vb_scrollContent.setPrefWidth(newValue.doubleValue()));

        // add event handlers for validation
        bindValidationEvents();

        // Hide add if updating
        if (isUpdate) {
            btn_add.setVisible(false);
            t_headerAdd.setVisible(false);
        } else {
            btn_update.setVisible(false);
            t_headerUpdate.setVisible(false);
        }

        // increase scroll speed
        final double SPEED = 0.01;
        sp_scrollpane.getContent().setOnScroll(scrollEvent -> {
            double deltaY = scrollEvent.getDeltaY() * SPEED;
            sp_scrollpane.setVvalue(sp_scrollpane.getVvalue() - deltaY);
        });

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

    private void bindValidationEvents() {
        NodeValidation.addRequiredValidation(tf_owner, hb_ownerValidator);
        NodeValidation.addRequiredValidation(tf_ownerAddress, hb_ownerAddressValidator);
        NodeValidation.addRequiredValidation(tf_businessName, hb_businessNameValidator);
        NodeValidation.addRequiredValidation(tf_businessAddress, hb_businessAddressValidator);
        NodeValidation.addRequiredValidation(tf_businessType, hb_businessTypeValidator);
        NodeValidation.addRequiredValidation(tf_contactNumber, hb_contactNumberValidator);
        NodeValidation.addRequiredValidation(tf_regNo, hb_regNoValidator);
        NodeValidation.addRequiredValidation(tf_inspector, hb_inspectorValidator);
        NodeValidation.addRequiredValidation(tf_amount, hb_amountValidator);
        NodeValidation.addRequiredValidation(tf_ORNo, hb_ORNoValidator);
        NodeValidation.addDatePickerValidation(dp_date, hb_dateValidator);
        NodeValidation.addToggleGroupValidation(inspectionTypeGroup, hb_inspectionTypeValidator);
        NodeValidation.addToggleGroupValidation(propertyTypeGroup, hb_propertyTypeValidator);

        NodeValidation.forceLengthLimit(tf_owner, 75);
        NodeValidation.forceLengthLimit(tf_ownerAddress, 255);
        NodeValidation.forceLengthLimit(tf_businessName, 255);
        NodeValidation.forceLengthLimit(tf_businessAddress, 255);
        NodeValidation.forceLengthLimit(tf_businessType, 25);
        NodeValidation.forceLengthLimit(tf_contactNumber, 20);
        NodeValidation.forceLengthLimit(tf_regNo, 25);
        NodeValidation.forceLengthLimit(tf_inspector, 75);
        NodeValidation.forceLengthLimit(tf_amount, 12);
        NodeValidation.forceLengthLimit(tf_ORNo, 25);

        NodeValidation.forceIntegerInputs(tf_contactNumber);
        NodeValidation.forceIntegerInputs(tf_regNo);
        NodeValidation.forceIntegerInputs(tf_ORNo);
        NodeValidation.forceDecimalInputs(tf_amount);
    }

    @FXML
    void addHandler() {
        if (!isAllValid()) {
            ThrowAlert.throwAlert("Error", "Invalid Input", "Please fill out all required fields.",
                    Alert.AlertType.ERROR);
            return;
        }
        if (!ThrowAlert.confirmAlert("Add Business Clearance Transaction",
                "Are you sure you want to add this Business Clearance Transaction?", "")) {
            return;
        }

        try {
            transaction.setOwner(tf_owner.getText());
            transaction.setOwnerAddress(tf_ownerAddress.getText());
            transaction.setBusinessName(tf_businessName.getText());
            transaction.setBusinessAddress(tf_businessAddress.getText());
            transaction.setBusinessType(tf_businessType.getText());
            transaction.setContactNumber(tf_contactNumber.getText());
            transaction.setOfficialReceiptNumber(tf_ORNo.getText());
            transaction.setInspector(tf_inspector.getText());
            transaction.setAmount(new BigDecimal(tf_amount.getText()));
            transaction.setOfficialReceiptNumber(tf_ORNo.getText());
            transaction.setInspectionDate(dp_date.getValue());
            transaction.setRegistrationNumber(tf_regNo.getText());

            RadioButton selectedInspectionType = (RadioButton) inspectionTypeGroup.getSelectedToggle();
            String inspectionTypeStr = selectedInspectionType.getText();

            RadioButton selectedPropertyType = (RadioButton) propertyTypeGroup.getSelectedToggle();
            String propertyTypeStr = selectedPropertyType.getText();

            transaction.setInspectionType(inspectionTypeStr);
            transaction.setPropertyType(propertyTypeStr);
        } catch (Exception e) {
            ThrowAlert.throwAlert("Error", "Invalid Input", e.getMessage(), Alert.AlertType.ERROR);
            return;
        }

        try {
            BusinessClearanceDB.addBusinessClearanceTransaction(transaction);
            ThrowAlert.throwAlert("Success", "Business Clearance Transaction Added",
                    "Business Clearance Transaction added successfully.",
                    Alert.AlertType.INFORMATION);
            mainViewController.openbusinessClearanceListView();
        } catch (Exception e) {
            ThrowAlert.throwAlert("Error", "Failed to add Business Clearance Transaction", e.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }

    @FXML
    void updateHandler() {
        if (!isAllValid()) {
            ThrowAlert.throwAlert("Error", "Invalid Input", "Please fill out all required fields.",
                    Alert.AlertType.ERROR);
            return;
        }
        if (!ThrowAlert.confirmAlert("Update Business Clearance Transaction",
                "Are you sure you want to update this Business Clearance Transaction?", "")) {
            return;
        }

        try {
            transaction.setOwner(tf_owner.getText());
            transaction.setOwnerAddress(tf_ownerAddress.getText());
            transaction.setBusinessName(tf_businessName.getText());
            transaction.setBusinessAddress(tf_businessAddress.getText());
            transaction.setBusinessType(tf_businessType.getText());
            transaction.setContactNumber(tf_contactNumber.getText());
            transaction.setOfficialReceiptNumber(tf_ORNo.getText());
            transaction.setInspector(tf_inspector.getText());
            transaction.setAmount(new BigDecimal(tf_amount.getText()));
            System.out.println(transaction.getAmount());
            transaction.setOfficialReceiptNumber(tf_ORNo.getText());
            transaction.setInspectionDate(dp_date.getValue());
            transaction.setRegistrationNumber(tf_regNo.getText());

            RadioButton selectedInspectionType = (RadioButton) inspectionTypeGroup.getSelectedToggle();
            String inspectionTypeStr = selectedInspectionType.getText();

            RadioButton selectedPropertyType = (RadioButton) propertyTypeGroup.getSelectedToggle();
            String propertyTypeStr = selectedPropertyType.getText();

            transaction.setInspectionType(inspectionTypeStr);
            transaction.setPropertyType(propertyTypeStr);
        } catch (Exception e) {
            ThrowAlert.throwAlert("Error", "Invalid Input", e.getMessage(), Alert.AlertType.ERROR);
            return;
        }

        try {
            BusinessClearanceDB.updateBusinessClearanceTransaction(transaction);
            ThrowAlert.throwAlert("Success", "Business Clearance Transaction Updated",
                    "Business Clearance Transaction updated successfully.",
                    Alert.AlertType.INFORMATION);
            mainViewController.openbusinessClearanceListView();
        } catch (Exception e) {
            ThrowAlert.throwAlert("Error", "Failed to update Business Clearance Transaction", e.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }

    @FXML
    void clearHandler() {
        tf_owner.clear();
        tf_ownerAddress.clear();
        tf_businessName.clear();
        tf_businessAddress.clear();
        tf_businessType.clear();
        tf_contactNumber.clear();
        tf_regNo.clear();
        tf_inspector.clear();
        tf_amount.clear();
        tf_ORNo.clear();
        dp_date.setValue(null);
        inspectionTypeGroup.selectToggle(null);
        propertyTypeGroup.selectToggle(null);
        NodeValidation.removeInvalidStyle(hb_ownerValidator);
        NodeValidation.removeInvalidStyle(hb_ownerAddressValidator);
        NodeValidation.removeInvalidStyle(hb_businessNameValidator);
        NodeValidation.removeInvalidStyle(hb_businessAddressValidator);
        NodeValidation.removeInvalidStyle(hb_businessTypeValidator);
        NodeValidation.removeInvalidStyle(hb_contactNumberValidator);
        NodeValidation.removeInvalidStyle(hb_propertyTypeValidator);
        NodeValidation.removeInvalidStyle(hb_regNoValidator);
        NodeValidation.removeInvalidStyle(hb_inspectionTypeValidator);
        NodeValidation.removeInvalidStyle(hb_inspectorValidator);
        NodeValidation.removeInvalidStyle(hb_dateValidator);
        NodeValidation.removeInvalidStyle(hb_amountValidator);
        NodeValidation.removeInvalidStyle(hb_ORNoValidator);
        NodeValidation.removeInvalidStyle(hb_inspectionTypeValidator);
        NodeValidation.removeInvalidStyle(hb_propertyTypeValidator);
    }

    @FXML
    void closeHandler() {
        try {
            if (ThrowAlert.confirmAlert("Close", "Are you sure? Any unsaved changes will be lost.", ""))
                mainViewController.openbusinessClearanceListView();
        } catch (IOException e) {
            ThrowAlert.throwAlert("Error", "Failed to open Business Clearance List View", e.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }

    private boolean isAllValid() {
        boolean isValid = true;
        if (tf_owner.getText().isEmpty()) {
            hb_ownerValidator.getStyleClass().add("invalid");
            isValid = false;
        }
        if (tf_ownerAddress.getText().isEmpty()) {
            hb_ownerAddressValidator.getStyleClass().add("invalid");
            isValid = false;
        }
        if (tf_businessName.getText().isEmpty()) {
            hb_businessNameValidator.getStyleClass().add("invalid");
            isValid = false;
        }
        if (tf_businessAddress.getText().isEmpty()) {
            hb_businessAddressValidator.getStyleClass().add("invalid");
            isValid = false;
        }
        if (tf_businessType.getText().isEmpty()) {
            hb_businessTypeValidator.getStyleClass().add("invalid");
            isValid = false;
        }
        if (tf_contactNumber.getText().isEmpty()) {
            hb_contactNumberValidator.getStyleClass().add("invalid");
            isValid = false;
        }
        if (tf_regNo.getText().isEmpty()) {
            hb_regNoValidator.getStyleClass().add("invalid");
            isValid = false;
        }
        if (tf_inspector.getText().isEmpty()) {
            hb_inspectorValidator.getStyleClass().add("invalid");
            isValid = false;
        }
        if (tf_amount.getText().isEmpty()) {
            hb_amountValidator.getStyleClass().add("invalid");
            isValid = false;
        }
        if (tf_ORNo.getText().isEmpty()) {
            hb_ORNoValidator.getStyleClass().add("invalid");
            isValid = false;
        }
        if (dp_date.getValue() == null) {
            hb_dateValidator.getStyleClass().add("invalid");
            isValid = false;
        }
        if (inspectionTypeGroup.getSelectedToggle() == null) {
            hb_inspectionTypeValidator.getStyleClass().add("invalid");
            isValid = false;
        }
        if (propertyTypeGroup.getSelectedToggle() == null) {
            hb_propertyTypeValidator.getStyleClass().add("invalid");
            isValid = false;
        }
        return isValid;
    }
}
