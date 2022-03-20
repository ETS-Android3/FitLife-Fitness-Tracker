package com.example.fitlife;

public class Item {
    String FirstName, LastName, Username;

    public Item(String FirstName, String LastName, String Username) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Username = Username;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }
}
