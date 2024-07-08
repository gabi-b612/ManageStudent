package com.etudiant.managestudent.views;

import com.etudiant.managestudent.model.Student;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SaveStudent {
    public ImageView studentPicture;
    public TextField studentName;
    public TextField studentPostName;
    public TextField studentPreName;
    public TextField studentEmail;
    public ImageView closeIcone;
    private boolean changePicture = false;
    private String profilePicturePath;

    public void Submit(ActionEvent actionEvent) {
        if (studentName.getText().isEmpty() ||
                studentPostName.getText().isEmpty() ||
                studentPreName.getText().isEmpty() ||
                studentEmail.getText().isEmpty() || !changePicture) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        } else {
            String name = studentName.getText();
            String postName = studentPostName.getText();
            String preName = studentPreName.getText();
            String email = studentEmail.getText();
            String picturePath = profilePicturePath;

            Student student = new Student(name, postName, preName, picturePath, email);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText(null);
            alert.setContentText("Student registered successfully!");
            alert.showAndWait();
        }

    }

    public void changeProfilPicture(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("New picture");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {

                Image newImage = new Image(new FileInputStream(file));
                studentPicture.setImage(newImage);

                profilePicturePath = file.toURI().toString();

                applyRoundClipToImageView();
                applyDropShadowEffect();
                changePicture = true;
            } catch (FileNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error upload");
                alert.setHeaderText(null);
                alert.setContentText("Impossible to choose a profile picture, please try again");
                alert.showAndWait();
            }
        }
    }

    private void applyRoundClipToImageView() {
        double radius = Math.min(studentPicture.getFitWidth() / 2, studentPicture.getFitHeight() / 2);
        Circle clip = new Circle(radius);
        clip.setCenterX(studentPicture.getFitWidth() / 2);
        clip.setCenterY(studentPicture.getFitHeight() / 2);
        studentPicture.setClip(clip);
    }

    private void applyDropShadowEffect() {
        studentPicture.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0.5, 0, 0);");
    }

    public void closeApplication(MouseEvent mouseEvent) {
        Platform.exit();
    }
}
