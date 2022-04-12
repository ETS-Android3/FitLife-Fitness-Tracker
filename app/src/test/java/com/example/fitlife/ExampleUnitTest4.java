package com.example.fitlife;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.firebase.database.DataSnapshot;

import androidx.annotation.Nullable;


public class exampleUnitTest4 extends LoginActivity  {
    @Test

    public void info_isCorrect() {
    
    	  EditText restMail = new EditText(view.getContext());
        AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
        passwordResetDialog.setTitle("Reset Password?");
        passwordResetDialog.setMessage("Enter Your Email To Receive Reset Link");
        passwordResetDialog.setView(restMail);
        assertfalse("restMail", true);
    }
}
// checks to see if reset mail option worked properly and is updated
