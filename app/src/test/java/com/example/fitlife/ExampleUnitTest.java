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
    public void WaterIntake_isCorrect()
    {
        MealWaterTest test = new MealWaterTest(70, "male", 180, 20, "No Activity" , "Bulking");
        int x = 180;
        assertEquals(90, test.setWaterIntakeLevel(x));
    }
    @Test
    public void MaleBMR_isCorrect()
    {
        MealWaterTest test = new MealWaterTest(70, "male", 180, 20, "No Activity" , "Bulking");
        double x = 70;
        String y = "male";
        int w = 180;
        int z = 20;
        assertEquals(1967, test.getBMR(x,y, w,z));

    }
    @Test
    public void FemaleBMR_isCorrect()
    {
        MealWaterTest test = new MealWaterTest(60, "female", 120, 20, "No Activity" , "Bulking");
        double x = 60;
        String y = "female";
        int w = 120;
        int z = 20;
        assertEquals(1359, test.getBMR(x,y, w,z));
    }
    @Test
    public void ActivityLevel_isCorrect()
    {
        MealWaterTest test = new MealWaterTest(60, "female", 120, 20, "No Activity" , "Bulking");
        String x = "No Activity";
        String y = "Bulking";
        assertEquals(2133, test.setTotalCalories(x,y));
    }
    @Test
    public void CuttingLevel_isCorrect()
    {
        MealWaterTest test = new MealWaterTest(60, "female", 120, 20, "No Activity" , "Cutting");
        String x = "No Activity";
        String y = "Cutting";
        assertEquals(2133, test.setTotalCalories(x,y));
    }
    @Test
    public void BulkingLevel_isCorrect()
    {
        MealWaterTest test = new MealWaterTest(60, "female", 120, 20, "No Activity" , "Cutting");
        String x = "No Activity";
        String y = "Bulking";
        assertEquals(2133, test.setTotalCalories(x,y));
    }


}
//hello