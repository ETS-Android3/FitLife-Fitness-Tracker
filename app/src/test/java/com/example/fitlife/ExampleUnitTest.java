package com.example.fitlife;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void addNoPoints(){
        int x = 0;
        x += 0;
        assertEquals(0, x);
    }

    @Test
    public void add5Points(){
        int x = 0;
        x += 5;
        assertEquals(5,x);
    }

    @Test void add10Points(){
        int x = 10;
        x+= 10;
        assertEquals(10, x);
    }

    @Test
    void add15Points(){
        int x = 15;
        x+= 15;
        assertEquals(15, x);
    }

    @Test
    void add3Points(){
        int x = 3;
        x+= 3;
        assertEquals(3, x);
    }

    @Test
    void addTotal(){
        //Random Check of user

        int temp = 15 + 10 + 0 + 3 + 3;
        assertEquals(31, temp);
    }



}
//Test
//hello