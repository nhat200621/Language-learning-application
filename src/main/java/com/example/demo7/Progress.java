package com.example.demo7;

public class Progress {
    private int progressId;
    private int userId;
    private String progressName;
    private String description;

    public Progress(int progressId, int userId, String progressName, String description) {
        this.progressId = progressId;
        this.userId = userId;
        this.progressName = progressName;
        this.description = description;
    }

    public int getProgressId() {
        return progressId;
    }

    public int getUserId() {
        return userId;
    }

    public String getProgressName() {
        return progressName;
    }

    public String getDescription() {
        return description;
    }
}


