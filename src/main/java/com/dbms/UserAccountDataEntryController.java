package com.dbms;

import com.dbms.database.SetupDB;
import com.dbms.database.UserAccountDB;
import com.dbms.models.UserAccount;
import com.dbms.utils.NodeValidation;
import com.dbms.utils.ThrowAlert;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class UserAccountDataEntryController implements Initializable {

    private final int userId;
    private final boolean isUpdate;
    private final UserAccount userAccount;
    private final MainViewController mainViewController;

    public UserAccountDataEntryController(MainViewController mainViewController) {
        System.out.println("New User Account.");
        userId = -1;
        isUpdate = false;
        this.mainViewController = mainViewController;
        userAccount = loadUserAccount(userId);
    }

    public UserAccountDataEntryController(MainViewController mainViewController, int userId) {
        System.out.println("Update User Account.");
        this.userId = userId;
        isUpdate = true;
        userAccount = loadUserAccount(userId);
        this.mainViewController = mainViewController;
        System.out.println("UserId: " + userId);
    }

    @FXML
    Text t_headerAdd, t_headerUpdate;

    @FXML
    TextField tf_lastname, tf_firstname, tf_middlename, tf_username, tf_password, tf_passwordRepeat;

    @FXML
    PasswordField pf_password, pf_passwordRepeat;

    @FXML
    VBox vb_firstnameValidator, vb_lastnameValidator, vb_middlenameValidator;

    @FXML
    HBox hb_usernameValidator, hb_passwordValidator;

    @FXML
    Button btn_add, btn_update;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // Populate Fields
        tf_lastname.setText(userAccount.getLastname());
        tf_firstname.setText(userAccount.getFirstname());
        tf_middlename.setText(userAccount.getMiddlename());
        tf_username.setText(userAccount.getUsername());
        tf_password.setText(userAccount.getPassword());
        pf_password.setText(userAccount.getPassword());

        System.out.println("Populated fields.");

        // Bind PasswordFields to their respective TextFields
        tf_password.textProperty().bindBidirectional(pf_password.textProperty());

        // hide password textfields
        tf_password.setVisible(false);

        // add event handlers for validation
        NodeValidation.addRequiredValidation(tf_lastname, vb_lastnameValidator);
        NodeValidation.addRequiredValidation(tf_firstname, vb_firstnameValidator);
        NodeValidation.addRequiredValidation(tf_middlename, vb_middlenameValidator);
        NodeValidation.addRequiredValidation(tf_username, hb_usernameValidator);
        NodeValidation.addPasswordValidation(pf_password, hb_passwordValidator);

        // hide add if updating
        if (isUpdate) {
            btn_add.setVisible(false);
            t_headerAdd.setVisible(false);
        }
        // hide update if not updating
        else {
            btn_update.setVisible(false);
            t_headerUpdate.setVisible(false);
        }
    }

    private UserAccount loadUserAccount(int userId) {
        if (!isUpdate) {
            System.out.println("Loaded new User Account.");
            return new UserAccount(userId, "", "", "", "", "");
        }
        try {
            UserAccount ret = UserAccountDB.fetchUserAccount(userId);
            System.out.println("Loaded User Account.");
            return ret;
        } catch (SQLException e) {
            ThrowAlert.throwAlert("Error", "Database Error", e.getMessage(), Alert.AlertType.ERROR);
        }
        return new UserAccount(-1, "", "", "", "", "");
    }

    // toggle password visibility
    @FXML
    void togglePasswordVisibility() {
        pf_password.setVisible(!pf_password.isVisible());
        tf_password.setVisible(!tf_password.isVisible());
    }

    @FXML
    void clearHandler() {
        tf_lastname.clear();
        tf_firstname.clear();
        tf_middlename.clear();
        tf_username.clear();
        tf_password.clear();
        NodeValidation.removeInvalidStyle(vb_firstnameValidator);
        NodeValidation.removeInvalidStyle(vb_lastnameValidator);
        NodeValidation.removeInvalidStyle(vb_middlenameValidator);
        NodeValidation.removeInvalidStyle(hb_usernameValidator);
        NodeValidation.removeInvalidStyle(hb_passwordValidator);
    }

    @FXML
    void addHandler() {
        if (!isAllValid()) {
            ThrowAlert.throwAlert("Error", "Invalid Input", "Please fill out all required fields.",
                    Alert.AlertType.ERROR);
            return;
        }
        if (!ThrowAlert.confirmAlert("Add User Account", "Are you sure you want to add this User Account?", "")) {
            return;
        }
        userAccount.setLastname(tf_lastname.getText());
        userAccount.setFirstname(tf_firstname.getText());
        userAccount.setMiddlename(tf_middlename.getText());
        userAccount.setUsername(tf_username.getText());
        userAccount.setPassword(tf_password.getText());

        try {
            UserAccountDB.addUserAccount(userAccount);
            ThrowAlert.throwAlert("Success", "User Account Added", "User Account added successfully.",
                    Alert.AlertType.INFORMATION);
            mainViewController.openUserAccountListView();
        } catch (Exception e) {
            ThrowAlert.throwAlert("Error", "Failed to add User Account", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void updateHandler() {
        if (!isAllValid()) {
            ThrowAlert.throwAlert("Error", "Invalid Input", "Please fill out all required fields.",
                    Alert.AlertType.ERROR);
            return;
        }

        userAccount.setLastname(tf_lastname.getText());
        userAccount.setFirstname(tf_firstname.getText());
        userAccount.setMiddlename(tf_middlename.getText());
        userAccount.setUsername(tf_username.getText());
        userAccount.setPassword(tf_password.getText());

        try {
            UserAccountDB.updateUserAccount(userAccount);
            ThrowAlert.throwAlert("Success", "User Account Updated", "User Account updated successfully.",
                    Alert.AlertType.INFORMATION);
        } catch (SQLException e) {
            ThrowAlert.throwAlert("Error", "Failed to update User Account", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void closeHandler() {
        try {
            if (ThrowAlert.confirmAlert("Close", "Are you sure? Any unsaved changes will be lost.", ""))
                mainViewController.openUserAccountListView();
        } catch (IOException e) {
            ThrowAlert.throwAlert("Error", "Failed to open User Account List View", e.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }

    private boolean isAllValid() {
        boolean isValid = true;
        if (tf_lastname.getText().isEmpty()) {
            NodeValidation.addInvalidStyle(vb_lastnameValidator);
            isValid = false;
        }
        if (tf_firstname.getText().isEmpty()) {
            NodeValidation.addInvalidStyle(vb_firstnameValidator);
            isValid = false;
        }
        if (tf_middlename.getText().isEmpty()) {
            NodeValidation.addInvalidStyle(vb_middlenameValidator);
            isValid = false;
        }
        if (tf_username.getText().isEmpty()) {
            NodeValidation.addInvalidStyle(hb_usernameValidator);
            isValid = false;
        }
        if (tf_password.getText().isEmpty()) {
            NodeValidation.addInvalidStyle(hb_passwordValidator);
            isValid = false;
        }
        if (!pf_password.getText().equals(tf_password.getText())) {
            NodeValidation.addInvalidStyle(hb_passwordValidator);
            isValid = false;
        }

        return isValid;
    }
}
