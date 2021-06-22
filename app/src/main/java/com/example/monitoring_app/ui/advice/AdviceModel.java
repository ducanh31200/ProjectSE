package com.example.monitoring_app.ui.advice;

public class AdviceModel {
    private String title;
    private String doctor;
    private String timeStart;
    private int image;

    public AdviceModel(String title, String doctor, String timeStart, int image) {
        this.title = title;
        this.doctor = doctor;
        this.timeStart = timeStart;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

