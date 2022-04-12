package com.example.fitlife;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest3 extends SearchFriends {
    @Test
    public void info_isCorrect() {
        assertEquals("dataSnapshot", "dataSnapshot".toString());
    }
}
// Checks if the user input for fist name contains only strings. This will allow us to avoid
// anything that it is not a string input that may cause error for our app.
// checks to see if the conversion works correctly within the test as well.
// self-checking
// timely
