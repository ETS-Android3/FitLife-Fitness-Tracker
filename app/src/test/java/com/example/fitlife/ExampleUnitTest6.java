package com.example.fitlife;

import org.junit.Test;

import static org.junit.Assert.*;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.FirebaseDatabase;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest6 extends SearchFriends {
    @Test
    public void info_isCorrect() {
        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        recyclerView = findViewById(R.id.searchResultList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.smoothScrollToPosition(0);
        myAdapter = new MyAdapter(mUploads, listen);
        assertFalse("Users",true);

    }
}

// checks if the condition for firebase ability to access users to ensure login activity works.
// checks to see if the conversion works correctly within the test as well.
// self checking
// isolated