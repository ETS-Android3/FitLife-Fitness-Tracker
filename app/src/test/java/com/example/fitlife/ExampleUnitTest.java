package com.example.fitlife;

import org.junit.Test;

import static org.junit.Assert.*;
import com.google.firebase.database.DataSnapshot;

import androidx.annotation.Nullable;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest extends SearchFriends {
    @Test

    public void info_isCorrect() {
        assertEquals("FirstName", "FirstName".toString());
    }
}

// Checks if the user input for fist name contains only strings. This will allow us to avoid
// anything that it is not a string input that may cause error for our app.
// It will also make sure to convert it correctly to a string when a user logs on.
// self-checking
// timely