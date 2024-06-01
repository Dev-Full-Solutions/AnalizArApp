package com.dpozzo68.analizarapp.entidades;

public class NotificationText {
    private String title;
    private String body;
    public NotificationText(String title, String body) {
        this.title = title;
        this.body = body;
    }
    public String getTitle() {
        return title;
    }
    public String getBody() {
        return body;
    }
}
