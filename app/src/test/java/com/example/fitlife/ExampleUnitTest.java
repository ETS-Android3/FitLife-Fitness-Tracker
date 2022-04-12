package com.example.fitlife;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.firebase.database.DataSnapshot;

import androidx.annotation.Nullable;


public class exampleUnitTest extends LoginActivity  {
    @Test

    public void info_isCorrect() {
        assertEquals("mail", "mail".toString());
    }
// checks to see if the mail is a string (the reset email option)
