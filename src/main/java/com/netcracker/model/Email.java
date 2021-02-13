package com.netcracker.model;

public class Email {
    private String title;
    private String content;
    private String to;

    public Email() {
    }

    public Email(String to) {
        this.to = to;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Email{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
