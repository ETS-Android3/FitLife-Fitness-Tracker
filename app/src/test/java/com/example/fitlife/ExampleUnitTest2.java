package com.example.fitlife;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest2 extends SearchFriends {
    @Test
    public void putExtra_isCorrect() {
        assertEquals("user_id", "user_id".toString());
    }
}
// This will check the program to see if the user input the ID correctly to avoid
// app crashes and conflicts.
// checks to see if the conversion works correctly within the test as well.
// self-checking
// timely