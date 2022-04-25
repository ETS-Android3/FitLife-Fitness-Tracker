package com.example.fitlife;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginActivityTest {
    @Test
    public void checkEmail(){
        testLoginActivityTest test = new testLoginActivityTest("fitlifecsc@gmail.com", "123456");
        assertEquals("fitlifecsc@gmail.com", test.getEmail());
    }

    @Test
    public void fail(){
        assertEquals(3,1);
    }

    @Test
    public void emailNotNull(){
        testLoginActivityTest test = new testLoginActivityTest("fitlifecsc@gmail.com", "123456");
        assertEquals(true, test.emailNotNull());
    }

    @Test
    public void checkPassword(){
        testLoginActivityTest test = new testLoginActivityTest("fitlifecsc@gmail.com", "123456");
        assertEquals("123456", test.getPassword());
    }

    @Test
    public void checkPassLength(){
        testLoginActivityTest test = new testLoginActivityTest("fitlifecsc@gmail.com", "123456");
        assertEquals(6, test.checkLength());
    }

    @Test
    public void passNotNull(){
        testLoginActivityTest test = new testLoginActivityTest("fitlifecsc@gmail.com", "123456");
        assertEquals(true, test.passwordNotNull());
    }
}
