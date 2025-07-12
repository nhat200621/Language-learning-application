package com.example.demo7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/LanguageLearningApp";
    private static final String USER = "root";
    private static final String PASSWORD = "200621";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("❌ Lỗi kết nối database: " + e.getMessage());
            return null;
        }
    }
    public static void markLessonAsCompleted(int userId, int lessonId) {
        String sql = "INSERT INTO Progress (user_id, lesson_id) VALUES (?, ?) ON DUPLICATE KEY UPDATE completed_date = NOW()";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, lessonId);
            stmt.executeUpdate();
            System.out.println("Đã cập nhật tiến trình học tập!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("🔌 Đã đóng kết nối database.");
            } catch (SQLException e) {
                System.err.println("⚠️ Lỗi khi đóng kết nối database: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {

        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("🎉 Kết nối thành công!");
            closeConnection(conn);
        } else {
            System.out.println("⚠️ Kết nối thất bại!");
        }
    }
}

