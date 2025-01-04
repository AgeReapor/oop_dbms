package com.dbms.utils;

import com.dbms.App;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

// Utility class to throw alerts
public class ThrowAlert {

    // Throws an alert
    public static void throwAlert(String title, String headerText, String contentText, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(App.loadImage("brgy_logo.png"));
        alert.showAndWait();
    }

    // Throws a confirmation alert
    public static boolean confirmAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(App.loadImage("brgy_logo.png"));
        return alert.showAndWait().get() == ButtonType.OK;
    }

}