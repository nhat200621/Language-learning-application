package com.example.demo7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("Language learning application");

        if (DatabaseConnection.getConnection() == null) {
            System.err.println("❌ Lỗi kết nối database! Ứng dụng sẽ đóng.");
            return;
        } else {
            System.out.println("✅ Kết nối database thành công!");
        }

        switchScene("Login.fxml");
        primaryStage.show();
    }

    public static void switchScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/example/demo7/" + fxmlFile));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root, 600, 400)); // Kích thước mặc định
        } catch (IOException e) {
            System.err.println("❌ Lỗi chuyển scene: " + e.getMessage());
        }
    }

    public static void switchToTranslate() {
        switchScene("Translate.fxml");
    }

    public static void switchToSolveExercise() {
        switchScene("SolveExercise.fxml");
    }

    @Override
    public void stop() {
        System.out.println("🛑 Ứng dụng đang đóng...");

    }


    public static void main(String[] args) {
        launch(args);
    }
}




