package com.example.fitlife;



public class TstFriendPoints {
    String FirstName, LastName, Username, key, email;
    int points;


    TstFriendPoints(String FirstName, String LastName, String Username, String key, String email, int points){
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Username = Username;
        this.key = key;
        this.points = points;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
