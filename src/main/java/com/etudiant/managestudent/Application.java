package com.etudiant.managestudent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("save-student.fxml")));

        AtomicReference<Double> xOffset = new AtomicReference<>((double) 0);
        AtomicReference<Double> yOffset = new AtomicReference<>((double) 0);
        root.setOnMousePressed(event -> {
            xOffset.set(event.getSceneX());
            yOffset.set(event.getSceneY());
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset.get());
            stage.setY(event.getScreenY() - yOffset.get());
        });

        Scene scene = new Scene(root, 1020, 600);
        stage.setTitle("Manage Student");

        stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}