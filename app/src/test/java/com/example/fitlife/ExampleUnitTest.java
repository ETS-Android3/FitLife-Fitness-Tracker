package com.example.fitlife;
import org.junit.BeforeClass;
import org.testng.annotations.Test;

import static org.junit.Assert.*;

public class ExampleUnitTest {
    @Test
    public void checkemail(){
        Item item =new Item("FitLife", "CSC", "Fit", "10101","fitlifecsc@gmail.com","123456");
        assertEquals("fitlifecsc@gmail.com",item.getEmail());

    }


    @Test
    public void checkpassword(){
        Item item =new Item("FitLife", "CSC", "Fit", "10101","fitlifecsc@gmail.com","123456");
        assertEquals("fitlifecsc@gmail.com",item.getPassword());
    }
    @Test
    public void checkfirstname(){
        Item item = new Item("FitLife", "CSC", "Fit", "10101","fitlifecsc@gmail.com","123456");
        assertEquals("FitLife", item.getFirstName());
    }

    @Test
    public void checklastname(){
        Item item = new Item("FitLife", "CSC", "Fit", "10101","fitlifecsc@gmail.com","123456");
        assertEquals("FitLife", item.getLastName());
    }
    @Test
    public void checkpasswordlength(){
        Item item = new Item("FitLife", "CSC", "Fit", "10101","fitlifecsc@gmail.com","123456");
        String x=item.getPassword();
        assertEquals(6,x.length());
    }
    @Test
    public void checkUsername(){
        Item item = new Item("FitLife", "CSC", "Fit", "10101","fitlifecsc@gmail.com","123456");
        assertEquals("Fit", item.getUsername());
    }

}
