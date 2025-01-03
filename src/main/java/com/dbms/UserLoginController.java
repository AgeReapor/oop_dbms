package com.dbms;

import java.io.IOException;

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

        boolean valid = validate(username, password);
        if (valid) {
            t_invalidWarning.setOpacity(1);
        }

        if (!valid) {
            t_invalidWarning.setOpacity(0);
        }
    }

    @FXML
    void onClearClick() throws IOException {
        tf_username.clear();
        pf_password.clear();
    }

    private boolean validate(String username, String password) {
        return false;
    }

}
