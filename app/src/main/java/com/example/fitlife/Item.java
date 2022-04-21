package com.example.fitlife;

//Item class to store the user information when it is being called on in the recycler view. This is a model class
public class Item {
    String FirstName, LastName, Username, key;

    public Item(String FirstName, String LastName, String Username, String key) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Username = Username;
        this.key = key;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
