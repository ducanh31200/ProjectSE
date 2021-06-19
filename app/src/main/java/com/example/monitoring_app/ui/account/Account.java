package com.example.monitoring_app.ui.account;

public class Account {
    private String Username;
    private String Password;
    private String Name;
    private String Mail;
    private String Phone;
    private String Birthday;
    private String Address;
    public Account(String username, String password, String name, String mail, String phone, String birthday, String address) {
            this.Username = username;
            this.Password = password;
            this.Name = name;
            this.Mail = mail;
            this.Phone = phone;
            this.Birthday = birthday;
            this.Address = address;
        }
    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

}
