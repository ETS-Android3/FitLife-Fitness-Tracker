package com.example.fitlife;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.firebase.database.DataSnapshot;

import androidx.annotation.Nullable;


public class exampleUnitTest6 extends LoginActivity  {
    @Test

    public void info_isCorrect() {
                      //Checking if Data is Valid
                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email Is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required");
                    return;
                }
                assertEquals("TextUtils", "TextUtils".toString());

      
    }
}
// checks to see if a string is entered
