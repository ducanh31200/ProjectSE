package com.example.monitoring_app.ui.notify;

import java.util.Date;

public class Notify {


    private int Id;
    private String Title;
    private String Time;

    public Notify(int id,String title, String time) {
        Id = id;
        Title = title;
        Time = time;
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

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

}
