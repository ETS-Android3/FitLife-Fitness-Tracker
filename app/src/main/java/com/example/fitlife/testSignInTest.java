package com.example.fitlife;

//Logic for Signin
public class testSignInTest {
    String email, first, last, user, pass;

    testSignInTest(String email, String first, String last, String user, String pass){
        this.email = email;
        this.first = first;
        this.last = last;
        this.user = user;
        this.pass = pass;
    }

    testSignInTest(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean emptyField() throws Exception {
        if(email == null){
            throw new Exception("Email Cannot be Empty");
        }
        else if(first == null){
            throw new Exception("First Name Cannot Be Empty");
        }
        else if(last == null){
            throw new Exception("Last Name Cannot Be Empty");
        }
        else if(user == null){
            throw new Exception("UserName Cannot Be Empty");
        }
        else if(pass == null){
            throw new Exception("Password Cannot Be Empty");
        }
        else return true;
    }

    public boolean checkPassWordLength() throws Exception {
        if(pass == null){
            throw new Exception("Password Cannot Be Empty");
        }
        if(pass.length() >=6){
            return true;
        }
        else return false;
    }

    public boolean checkUnique(testSignInTest x, testSignInTest y) throws Exception {
        String user1 = x.getUser();
        String user2 = y.getUser();
        if(user1.equals(user2)){
            throw new Exception("Error Username Taken");
        }
        else
            return true;
    }
}
