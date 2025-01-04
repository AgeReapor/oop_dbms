package com.dbms;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class MainViewController implements Initializable {

    @FXML
    BorderPane bp_mainView;

    @FXML
    void nop() {
        System.out.println("Nothing happened.");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            openUserAccountDataEntryView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openUserAccountDataEntryView() throws IOException {
        Parent view = App.loadFXML("userAccountDataEntryView");
        bp_mainView.setCenter(view);
    }

}
