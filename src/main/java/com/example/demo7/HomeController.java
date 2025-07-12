package com.example.demo7;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeController {
    @FXML
    private void goToLessons(ActionEvent event) throws IOException {
        changeScene(event, "Lessons.fxml");
    }

    @FXML
    private void goToProgress(ActionEvent event) throws IOException {
        changeScene(event, "Progress.fxml");
    }

    @FXML
    private void goToFeedback() throws IOException {
        Main.switchScene("Feedback.fxml");
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        changeScene(event, "Login.fxml");
    }

    @FXML
    private void goToTranslate() throws IOException {
        Main.switchScene("Translate.fxml");
    }

    @FXML
    private void goToSolveExercise() throws IOException {
        Main.switchScene("SolveExercise.fxml");
    }

    private void changeScene(ActionEvent event, String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo7/" + fxmlFile));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

