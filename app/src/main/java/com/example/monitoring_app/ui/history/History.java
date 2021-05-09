package com.example.monitoring_app.ui.history;

import java.util.Date;

public class History {
    private int Id;
    private String Title;
    private String Description;
    private Date CreatedDate;
    private Boolean Status;

    public History(int id, String title, String description, Date createdDate, Boolean status) {
        Id = id;
        Title = title;
        Description = description;
        CreatedDate = createdDate;
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

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }
}
