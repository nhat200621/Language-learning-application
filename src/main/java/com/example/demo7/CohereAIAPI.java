package com.example.demo7;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class CohereAIAPI {
    private static final String API_URL = "https://api.cohere.ai/generate";
    private static final String API_KEY = "TEj2USnpwt6MEiuN5YbY5jtdBwqEINRzWCIVXX4Q";

    public static String translateText(String text) {
        String prompt = "Translate the following text to English: " + text;
        String jsonResponse = callCohere(prompt);
        return extractText(jsonResponse);
    }

    public static String solveExercise(String question) {
        String prompt = "Solve the following exercise: " + question;
        String jsonResponse = callCohere(prompt);
        return extractText(jsonResponse);
    }

    private static String callCohere(String prompt) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Cohere-Version", "2022-12-06");
            connection.setDoOutput(true);

            String body = "{ \"model\": \"command\", \"prompt\": \"" + prompt.replace("\"", "\\\"") + "\", \"max_tokens\": 200, \"temperature\": 0.5 }";

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                try (Scanner scanner = new Scanner(connection.getInputStream(), StandardCharsets.UTF_8)) {
                    return scanner.useDelimiter("\\A").next();
                }
            } else {
                try (Scanner scanner = new Scanner(connection.getErrorStream(), StandardCharsets.UTF_8)) {
                    String errorBody = scanner.useDelimiter("\\A").next();
                    return "Lỗi khi gọi API, mã lỗi: " + responseCode + "\n" + errorBody;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Đã xảy ra lỗi trong quá trình gọi API";
        }
    }

    private static String extractText(String jsonResponse) {
        try {
            int start = jsonResponse.indexOf("\"text\":\"") + 8;
            int end = jsonResponse.indexOf("\",", start);
            if (start > 7 && end > start) {
                String result = jsonResponse.substring(start, end).replace("\\n", "\n").trim();
                return result;
            } else {
                return "Không thể tìm thấy kết quả.";
            }
        } catch (Exception e) {
            return "Lỗi khi phân tích kết quả.";
        }
    }
}






