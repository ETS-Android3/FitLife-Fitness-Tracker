package com.example.fitlife;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestMealWater {

    @Test
    public void MaleBMR_isCorrect()
    {
        MealWaterTest test = new MealWaterTest(70, "male", 180, 20, "No Activity" , "Bulking");
        double x = 70;
        String y = "male";
        int w = 180;
        int z = 20;
        assertEquals(1967, test.getBMR(y),5);
    }

    @Test
    public void FemaleBMR_isCorrect()
    {
        MealWaterTest test = new MealWaterTest(60, "female", 120, 20, "No Activity" , "Bulking");
        double x = 60;
        String y = "female";
        int w = 120;
        int z = 20;
        assertEquals(1359, test.getBMR(y),5);
    }
    @Test
    public void ActivityLevel_isCorrect()
    {
        MealWaterTest test = new MealWaterTest(60, "female", 120, 20, "No Activity" , "Bulking");
        String x = "No Activity";
        String y = "Bulking";
        assertEquals(250, test.setTotalCalories(x,y),5);
    }
    @Test
    public void CuttingLevel_isCorrect()
    {
        MealWaterTest test = new MealWaterTest(60, "female", 120, 20, "No Activity" , "Cutting");
        String x = "No Activity";
        String y = "Cutting";
        assertEquals(-250, test.setTotalCalories(x,y),5);
    }

    @Test
    public void BulkingLevel_isCorrect()
    {
        MealWaterTest test = new MealWaterTest(60, "female", 120, 20, "No Activity" , "Bulking");
        String x = "No Activity";
        String y = "Bulking";
        assertEquals(250, test.setTotalCalories(x,y), 5);
    }

    @Test
    public void WaterIntake_isCorrect(){
        MealWaterTest test = new MealWaterTest(60, "female", 120, 20, "No Activity" , "Bulking");
        assertEquals(60, test.setWaterIntakeLevel(), 5);
    }
}
