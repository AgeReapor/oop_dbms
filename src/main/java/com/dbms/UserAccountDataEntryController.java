package com.dbms;

import com.dbms.database.SetupDB;
import com.dbms.database.UserAccountDB;
import com.dbms.models.UserAccount;
import com.dbms.utils.NodeValidation;
import com.dbms.utils.ThrowAlert;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserAccountDataEntryController implements Initializable {
    private int userId;
    private boolean isUpdate;
    private UserAccount userAccount;

    @FXML
    TextField tf_lastname, tf_firstname, tf_middlename, tf_username, tf_password, tf_passwordRepeat;

    @FXML
    PasswordField pf_password, pf_passwordRepeat;

    @FXML
    VBox vb_firstnameValidator, vb_lastnameValidator, vb_middlenameValidator;

    @FXML
    HBox hb_usernameValidator, hb_passwordValidator;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // event flags
        userId = 5;
        isUpdate = false;

        // Load User Account
        userAccount = loadUserAccount(userId);

        // Populate Fields
        tf_lastname.setText(userAccount.getLastname());
        tf_firstname.setText(userAccount.getFirstname());
        tf_middlename.setText(userAccount.getMiddlename());
        tf_username.setText(userAccount.getUsername());
        tf_password.setText(userAccount.getPassword());
        pf_password.setText(userAccount.getPassword());

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

    @FXML
    void togglePasswordVisibility() {
        // toggle password visibility
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
    }

    @FXML
    void addHandler() {
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
            UserAccountDB.addUserAccount(userAccount);
            ThrowAlert.throwAlert("Success", "User Account Added", "User Account added successfully.",
                    Alert.AlertType.INFORMATION);
        } catch (SQLException e) {
            ThrowAlert.throwAlert("Error", "Failed to add User Account", e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    private boolean isAllValid() {
        if (tf_lastname.getText().isEmpty())
            return false;
        if (tf_firstname.getText().isEmpty())
            return false;
        if (tf_middlename.getText().isEmpty())
            return false;
        if (tf_username.getText().isEmpty())
            return false;
        if (tf_password.getText().isEmpty())
            return false;
        return true;
    }
}
