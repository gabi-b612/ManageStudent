package com.etudiant.managestudent.views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ViewAllStudent {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}