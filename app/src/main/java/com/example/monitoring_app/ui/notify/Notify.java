package com.example.monitoring_app.ui.notify;

import java.util.Date;

public class Notify {
    private int Id;
    private String Title;
    private String Description;
    private Boolean Status;

    public Notify(int id, String title, String description, Boolean status) {
        Id = id;
        Title = title;
        Description = description;
        Status = status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }
}
