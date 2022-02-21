package com.example.fitlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MealWaterTracking extends AppCompatActivity {
    double BMR;
    double totalCalories;
    double totalWater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_water_tracking);
    }
    public void getBMR()
    {
        String gender = "male";
        double height = 70;
        double weight = 150;
        int age = 20;
        if(gender == "male")
        {
         BMR = 66 + (6.3 * weight) + (12.9 * height) - (6.8 * age);
        }
        else if (gender == "female")
        {
            BMR = 655 + (4.3 * weight) + (4.7 * height) - (4.7 * age);
        }

    }

    public void setTotalCalories()
    {

        String activityLevel = "moderate";
        String preference = "maintain";
        if(activityLevel == "no activity")
        {
            totalCalories = BMR * 1.2;
        }
        else if(activityLevel == "light")
        {
            totalCalories = BMR * 1.375;
        }
        else if(activityLevel == "moderate")
        {
            totalCalories = BMR * 1.55;
        }
        else if(activityLevel == "heavy")
        {
            totalCalories = BMR * 1.725;
        }


        if (preference == "cutting")
        {
            totalCalories = totalCalories - 250;
        }
        else if (preference == "maintain")
        {
            totalCalories = totalCalories;
        }
        else if (preference == "bulking")
        {
            totalCalories = totalCalories + 250;
        }

    }
    //test
    public void setWaterIntakeLevel()
    {
        double weight = 150;
        totalWater = weight/2;

    }
}