package com.example.fitlife;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

//This test takes the functionality or friendPoints and make sure that none of the
public class friendPointsTest {
    @Test
    public void checkPoints(){
        TstFriendPoints test = new TstFriendPoints("Eric", "Smith", "eric", "10102", "Eric@gmail.com",24);
        test.setPoints(45);
        assertEquals(45, test.getPoints());
    }

    @Test
    public void checkLeaderName(){
        TstFriendPoints test = new TstFriendPoints("Eric", "Smith", "eric", "10102", "Eric@gmail.com",24);
        assertEquals("Eric", test.getFirstName());
    }

    @Test
    public void checkLeaderLast(){
        TstFriendPoints test = new TstFriendPoints("Eric", "Smith", "eric", "10102", "Eric@gmail.com",24);
        assertEquals("Smith", test.getLastName());
    }

    @Test
    public void checkUser(){
        TstFriendPoints test = new TstFriendPoints("Eric", "Smith", "bigBrother", "10102", "Eric@gmail.com",24);
        assertEquals("bigBrother", test.getUsername());
    }

    @Test
    public void checkKey(){
        TstFriendPoints test = new TstFriendPoints("Eric", "Smith", "eric", "10102", "Eric@gmail.com",24);
        test.setKey("1010");
        assertEquals("1010", test.getKey());
    }

    @Test
    public void checkEmail(){
        TstFriendPoints test = new TstFriendPoints("Eric", "Smith", "eric", "10102", "Eric@gmail.com",24);
        assertEquals("Eric@gmail.com", test.getEmail());
    }
}
