package com.example.fitlife;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void checkFirstName(){
        Item item = new Item("FitLife", "CSC", "Fit", "10101");
        assertEquals("FitLife", item.getFirstName());
    }

    @Test
    public void checkLast(){
        Item item = new Item("Fitlife", "CSC", "Fit", "10101");
        assertEquals("CSC", item.getLastName());

    }

    @Test
    public void checkUser(){
        Item item = new Item("Fitlife", "CSC", "Fit", "10101");
        assertEquals("Fit", item.getUsername());
    }

    @Test
    public void checkKey(){
        Item item = new Item("Fitlife", "CSC", "Fit", "10101");
        assertEquals("10101", item.getKey());
    }
}
