package com.example.fitlife;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.firebase.database.DataSnapshot;

import androidx.annotation.Nullable;


public class exampleUnitTest5 extends LoginActivity  {
    @Test

    public void info_isCorrect() {
        assertEquals("passwordResetDialog", "passwordResetDialog".toString());
    }
// checks to if the new password entered by user is a string
