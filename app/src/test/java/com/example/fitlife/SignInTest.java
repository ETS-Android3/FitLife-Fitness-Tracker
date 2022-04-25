package com.example.fitlife;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class SignInTest {
    testSignInTest test;
    testSignInTest x;

    @BeforeEach
    void setUp(){
        test = new testSignInTest("fitlifecsc@gmail.com", "fitlife", "csc", "slik", "123456");
        x =new testSignInTest();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    public void checkEmail(){
        test = new testSignInTest("fitlifecsc@gmail.com", "fitlife", "csc", "slik", "123456");

        assertEquals("fitlifecsc@gmail.com", test.getEmail());
    }

    @Test
    public void checkFirst(){
        test = new testSignInTest("fitlifecsc@gmail.com", "fitlife", "csc", "slik", "123456");

        assertEquals("fitlife",test.getFirst());
    }

    @Test
    public void checkEmpty() throws Exception {
        assertThrows(Exception.class, ()->{
           x.emptyField();
        });

    }

    @Test public void filled() throws Exception {
        test = new testSignInTest("fitlifecsc@gmail.com", "fitlife", "csc", "slik", "123456");
        assertEquals(true,test.emptyField());
    }

    @Test
    public void checkLength() throws Exception {
        test = new testSignInTest("fitlifecsc@gmail.com", "fitlife", "csc", "slik", "123456");
        assertEquals(true, test.checkPassWordLength());
    }

    @Test
    public void checkLast(){
        test = new testSignInTest("fitlifecsc@gmail.com", "fitlife", "csc", "slik", "123456");
        assertEquals("csc", test.getLast());
    }

    @Test
    public void checkUser(){
        test = new testSignInTest("fitlifecsc@gmail.com", "fitlife", "csc", "slik", "123456");
        assertEquals("slik",test.getUser());
    }

    @Test
    public void checkPass(){
        test = new testSignInTest("fitlifecsc@gmail.com", "fitlife", "csc", "slik", "123456");
        assertEquals("123456",test.getPass());
    }

    @Test
    public void throwError() throws Exception {
        assertThrows(Exception.class,()->{
            x.checkPassWordLength();
        });
    }

    @Test
    public void checkUnique() throws Exception {
        test = new testSignInTest("fitlifecsc@gmail.com", "fitlife", "csc", "slik", "123456");
        x= new testSignInTest("johhny@gmail.com", "john", "smith", "slik", "123456");
        testSignInTest y= new testSignInTest("johhny@gmail.com", "john", "smith", "johhny", "123456");

        assertThrows(Exception.class, ()->{
           test.checkUnique(test,x);
        });

        assertEquals(true, x.checkUnique(x,y));

    }
}
