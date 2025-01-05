package com.dbms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        loadAllFonts();

        String homeScene = "UserLoginView";

        scene = new Scene(loadFXML(homeScene), 1280, 800);

        stage.setTitle("Barangay Business Clearance Database Management System");
        stage.setMinWidth(1024);
        stage.setMinHeight(768);

        Image icon = loadImage("brgy_logo.png");
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.show();

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void setRoot(Parent root) throws IOException {
        scene.setRoot(root);
    }

    public static void setRoot(String fxml, String controller) throws IOException {
        scene.setRoot(loadFXML(fxml));
        scene.setUserData(controller);
    }

    public static FXMLLoader getFXMLLoader(String fxml) throws IOException {
        return new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        return getFXMLLoader(fxml).load();
    }

    public static Image loadImage(String filename) {
        return new Image(App.class.getResourceAsStream("/com/dbms/images/" + filename));
    }

    public static String loadCSS(String filename) {
        return App.class.getResource("/com/dbms/css/" + filename).toExternalForm();
    }

    public static void loadFont(String filename) {
        Font.loadFont(App.class.getResourceAsStream("/com/dbms/fonts/" + filename), 12);
    }

    private void loadAllFonts() {
        String path = "./src/main/resources/com/dbms/fonts";
        File dir = new File(path);
        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.getName().endsWith(".ttf")) {
                loadFont(file.getName());
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}