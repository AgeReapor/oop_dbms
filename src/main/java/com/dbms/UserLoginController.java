package com.dbms;

import com.dbms.database.LoginDB;
import com.dbms.database.SetupDB;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        int userId = fetchUserId(username, password);

        if (userId == -1) {
            t_invalidWarning.setOpacity(1);
            return;
        }

        t_invalidWarning.setOpacity(0);
    }

    @FXML
    void onClearClick() throws IOException {
        tf_username.clear();
        pf_password.clear();
    }

    // Returns user_id, or -1 if not found
    private int fetchUserId(String username, String password) {
        return LoginDB.fetchUserId(username, password);
    }

}
