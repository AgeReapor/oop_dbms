package com.dbms.utils;

import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

// Utility class to help with form validation
public class NodeValidation {

    // Takes in either a textfield and a container
    // that contains the label and the field
    public static void addRequiredValidation(TextField textField, Node container) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() <= 0) {
                container.getStyleClass().add("invalid");
                textField.setPromptText("This field is required");
            } else {
                container.getStyleClass().remove("invalid");
            }
        });
    }

    // Takes in either a textfield and a container
    // that contains the label and the field
    public static void addPasswordValidation(PasswordField passwordField, Node container) {
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() <= 0) {
                container.getStyleClass().add("invalid");
                passwordField.setPromptText("This field is required");
            } else {
                container.getStyleClass().remove("invalid");
            }
        });
    }

}
