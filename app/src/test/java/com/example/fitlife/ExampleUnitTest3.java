package com.example.fitlife;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.firebase.database.DataSnapshot;

import androidx.annotation.Nullable;


public class exampleUnitTest extends LoginActivity  {
    @Test

    public void info_isCorrect() {
        assertEquals("password", "password".toString());
    }
// checks to see if the password entered by the user is a string 
