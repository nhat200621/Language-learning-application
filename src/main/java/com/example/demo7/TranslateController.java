package com.example.demo7;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.io.IOException;

public class TranslateController {
    @FXML
    private TextField textToTranslate;
    @FXML
    private TextArea translatedText;
    @FXML
    private Button translateButton;

    @FXML
    private void handleTranslate() {
        String inputText = textToTranslate.getText().trim();

        if (!inputText.isEmpty()) {
            try {
                String translation = CohereAIAPI.translateText(inputText);
                translatedText.setText(translation);
            } catch (Exception e) {
                translatedText.setText("Lỗi khi gọi API: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            translatedText.setText("Please enter the text to translate.");
        }
    }
    @FXML
    private void goBack() throws IOException {
        Main.switchScene("Home.fxml");
    }
}

