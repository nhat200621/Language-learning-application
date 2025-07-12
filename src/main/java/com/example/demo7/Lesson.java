package com.example.demo7;

public class Lesson {
    private String title;
    private String link;

    public Lesson( String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return title; // để ListView hiển thị title
    }
}
