package com.example.fitlife;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class SignInTest {
    @Test
    public void checkEmail(){
        testSignInTest test = new testSignInTest("fitlifecsc@gmail.com", "fitlife", "csc", "slik", "123456");
        assertEquals("fitlifecsc@gmail.com", test.getEmail());
    }

    @Test
    public void checkFirst(){

    }
}
