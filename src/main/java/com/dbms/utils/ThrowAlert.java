package com.dbms.utils;

import com.dbms.App;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ThrowAlert {
    public static void throwAlert(String title, String headerText, String contentText, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(App.loadImage("brgy_logo.png"));
        alert.showAndWait();
    }

}