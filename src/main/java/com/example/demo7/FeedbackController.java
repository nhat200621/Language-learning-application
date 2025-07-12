package com.example.demo7;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedbackController {

    @FXML private TextArea feedbackArea;

    @FXML
    private void handleSubmitFeedback() {
        String feedback = feedbackArea.getText().trim();
        if (feedback.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please enter your feedback!");
            return;
        }

        if (saveFeedback("guest", feedback)) { // hoặc truyền username nếu đã đăng nhập
            showAlert(Alert.AlertType.INFORMATION, "Success", "Thank you for your feedback!");
            feedbackArea.clear();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to submit feedback!");
        }
    }

    private boolean saveFeedback(String username, String message) {
        String insert = "INSERT INTO feedback (username, message) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insert)) {

            stmt.setString(1, username);
            stmt.setString(2, message);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void goBack() throws IOException {
        Main.switchScene("Home.fxml");
    }
}

