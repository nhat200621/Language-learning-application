package com.example.demo7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProgressController {

    @FXML private TableView<Progress> progressTable;
    @FXML
    private TableColumn<Progress, Integer> progressIdColumn;

    @FXML
    private TableColumn<Progress, Integer> userIdColumn;

    @FXML
    private TableColumn<Progress, String> progressNameColumn;

    @FXML
    private TableColumn<Progress, String> descriptionColumn;

    private ObservableList<Progress> progressList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        progressIdColumn.setCellValueFactory(new PropertyValueFactory<>("progressId"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        progressNameColumn.setCellValueFactory(new PropertyValueFactory<>("progressName"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        loadProgressData(2);
    }

    private Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/LanguageLearningApp";
        String user = "root";
        String password = "200621";
        return DriverManager.getConnection(url, user, password);
    }

    // Phương thức lấy dữ liệu từ database
    private void loadProgressData(int userId) {
        String query = "SELECT progress_id, user_id, progress_name, description FROM Progress WHERE user_id = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            progressList.clear();

            while (rs.next()) {
                int progressId = rs.getInt("progress_id");
                int userID = rs.getInt("user_id");
                String progressName = rs.getString("progress_name");
                String description = rs.getString("description");

                progressList.add(new Progress(progressId, userID, progressName, description));
            }

            progressTable.setItems(progressList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack() throws IOException {
        Main.switchScene("Home.fxml");
    }
}

