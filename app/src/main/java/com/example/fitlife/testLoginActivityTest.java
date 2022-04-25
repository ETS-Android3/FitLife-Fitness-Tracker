package com.example.fitlife;

//
public class testLoginActivityTest {
    private  String email;
    private String password;

    testLoginActivityTest(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public boolean emailNotNull(){
        if(email == null){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean passwordNotNull(){
        if(email == null){
            return false;
        }
        else{
            return true;
        }
    }

    public int checkLength(){
        int x = password.length();
        if(x >= 6){
            return x;
        }
        else{
            return -1;
        }
    }

    public String getPassword(){
        return password;
    }

}
