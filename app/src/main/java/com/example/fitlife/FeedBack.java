package com.example.fitlife;

public class FeedBack {
//Create variables for email name and message
    String email,name,message;


    public FeedBack() {
    }
//Constructor to set email, name, and messages
    public FeedBack(String email, String name, String message) {
        this.email = email;
        this.name = name;
        this.message = message;
    }
//Functions for email, name, and messages
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
