package com.example.monitoring_app.ui.physical;



public class Physical {
    private int id;



    private String title;
    private String age;
    private String height;
    private String weight;

    public Physical(int id,String title, String age, String height, String weight) {
        this.id = id;
        this.title = title;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
