package com.dbms;

import com.dbms.database.LoginDB;
import com.dbms.utils.ThrowAlert;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

public class UserLoginController {

    @FXML
    TextField tf_username;

    @FXML
    PasswordField pf_password;

    @FXML
    Text t_invalidWarning;

    @FXML
    Button btn_submit;

    @FXML
    Button btn_clear;

    @FXML
    void onSubmitClick() throws IOException {
        String username = tf_username.getText();
        String password = pf_password.getText();

        int userId = fetchUserId(username, password);
        if (userId == -1) {
            t_invalidWarning.setOpacity(1);
            return;
        }

        t_invalidWarning.setOpacity(0);

        FXMLLoader loader = App.getFXMLLoader("mainView");
        Parent root = loader.load();

        MainViewController mainViewController = loader.getController();
        mainViewController.setUsername(username);

        App.setRoot(root);

    }

    @FXML
    void onClearClick() throws IOException {
        tf_username.clear();
        pf_password.clear();
        t_invalidWarning.setOpacity(0);
    }

    // Returns user_id, or -1 if not found
    private int fetchUserId(String username, String password) {
        try {
            return LoginDB.fetchUserId(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            ThrowAlert.throwAlert("Error", "SQL Error", e.getMessage(), AlertType.ERROR);
            return -1;
        }
    }

}
