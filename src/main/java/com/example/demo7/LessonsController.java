package com.example.demo7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.sql.*;
import javafx.scene.input.MouseEvent;

public class LessonsController {
    @FXML private ListView<Lesson> lessonList;

    private ObservableList<Lesson> lessonObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        loadLessonsFromDatabase();

        lessonList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Click 2 lần
                Lesson selectedLesson = lessonList.getSelectionModel().getSelectedItem();
                if (selectedLesson != null) {
                    openLinkInBrowser(selectedLesson.getLink());
                }
            }
        });
    }

    private void loadLessonsFromDatabase() {
        String query = "SELECT title, link FROM lessons";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String title = rs.getString("title");
                String link = rs.getString("link");
                lessonObservableList.add(new Lesson(title, link));
            }

            lessonList.setItems(lessonObservableList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void openLinkInBrowser(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack() throws IOException {
        Main.switchScene("Home.fxml");
    }
}

