package com.dbms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("userAccountDataEntryView"), 1280, 800);
        stage.setTitle("Barangay Business Clearance Database Management System");

        Image icon = loadImage("brgy_logo.png");
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void setRoot(String fxml, Object controller) throws IOException {
        scene.setRoot(loadFXML(fxml));
        scene.setUserData(controller);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Image loadImage(String filename) {
        return new Image(App.class.getResourceAsStream("/com/dbms/images/" + filename));
    }

    public static void main(String[] args) {
        launch(args);
    }

}