package com.example.email02.notifications;

public class NotificationDTO {
    private long contactId;
    private String title;
    private String text;

    public NotificationDTO() {}

    public NotificationDTO(long contactId, String title, String text) {
        this.contactId = contactId;
        this.title = title;
        this.text = text;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
