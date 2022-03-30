package com.example.fitlife;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

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
    public void WaterIntake_isCorrect()
    {
        int weight = 150;
        int totalWater = weight/2;

        assertEquals(totalWater, weight/2);
    }
    @Test
    public void MaleBMR_isCorrect()
    {
        //double weight = 150;
        //double height = 70;
        //int age = 20;
        //double BMR;


        long actual = (long) (66 + (6.3 * 150) + (12.9 * 70) - (6.8 * 20));
        assertEquals(1778, actual);

    }
    @Test
    public void FemaleBMR_isCorrect()
    {
        //weight = 120, height = 60, age =20
        long actual = (long) (655 + (4.3 * 120) + (4.7 * 60) - (4.7 * 20));
        assertEquals(1359, actual);
    }
    @Test
    public void ActivityLevel_isCorrect()
    {
        //No activity
        long BMRwithactivity = (long) (1778*1.2);
        assertEquals(2133, BMRwithactivity);
    }
    @Test
    public void preferenceLevel_isCorrect()
    {
        //Cutting
        long preference = (long)((1778*1.2)-250);
        assertEquals(1883, preference);
    }


}
//hello