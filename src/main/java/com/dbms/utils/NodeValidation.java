package com.dbms.utils;

import java.time.LocalDate;

import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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

    // Takes in either a textfield and container
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

    // Takes in a toggleGroup and container
    public static void addToggleGroupValidation(ToggleGroup toggleGroup, Node container) {
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                container.getStyleClass().add("invalid");
            } else {
                container.getStyleClass().remove("invalid");
            }
        });
    }

    // Takes in a datePicker and container
    public static void addDatePickerValidation(DatePicker datePicker, Node container) {
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                LocalDate date = LocalDate.parse(newValue.toString());
                datePicker.setValue(date);
                System.out.println("Date set: " + date);
                container.getStyleClass().remove("invalid");
            } catch (Exception e) {
                container.getStyleClass().add("invalid");
            }
        });

    }

    public static void addInvalidStyle(Node node) {
        // check if already invalid
        if (!node.getStyleClass().contains("invalid")) {
            node.getStyleClass().add("invalid");
        }
    }

    public static void removeInvalidStyle(Node node) {
        while (node.getStyleClass().contains("invalid"))
            node.getStyleClass().remove("invalid");
    }

}
