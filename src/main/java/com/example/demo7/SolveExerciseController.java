package com.example.demo7;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

import java.io.IOException;

public class SolveExerciseController {
    @FXML
    private TextArea exerciseInput;
    @FXML
    private TextArea exerciseSolution;
    @FXML
    private Button solveButton;

    @FXML
    private void handleSolve() {
        String inputQuestion = exerciseInput.getText().trim();
        if (!inputQuestion.isEmpty()) {
            String solution = CohereAIAPI.solveExercise(inputQuestion);
            exerciseSolution.setText(solution);
        }
    }
    @FXML
    private void goBack() throws IOException {
        Main.switchScene("Home.fxml");
    }
}

