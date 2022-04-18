package com.example.fitlife;

public class MealWaterTest {
    double BMR;
    double totalCalories;
    double totalWater;
    double height;
    int weight;
    int age;
    String gender;
    String activityLevel;
    String preference;

    MealWaterTest(double height, String gender, int weight, int age, String activityLevel, String preference)
    {
        this.height = height;
        this.gender = gender;
        this.weight = weight;
        this.age = age;
        this.activityLevel = activityLevel;
        this.preference = preference;
    }

    public double getBMR(double height, String gender, int weight, int age)
    {

        if(gender.equals("Male"))
        {
            BMR = 66 + (6.3 * weight) + (12.9 * height) - (6.8 * age);
        }
        else if (gender.equals("Female"))
        {
            BMR = 655 + (4.3 * weight) + (4.7 * height) - (4.7 * age);
        }
        return BMR;
    }

    public double setTotalCalories(String activityLevel, String preference)
    {

        totalCalories = BMR;
        if(activityLevel.equals("No Activity"))
        {
            totalCalories = (BMR * 1.2);
        }
        else if(activityLevel.equals("Light Activity = Workout 2-3 Times a Week"))
        {
            totalCalories =(BMR * 1.375);

        }
        else if(activityLevel.equals("Moderate activity= Workout 3-4 Times Per Week"))
        {
            totalCalories = (BMR * 1.55);

        }
        else if(activityLevel.equals("Heavy Activity = Workout 4-5 Times Per Week"))
        {
            totalCalories = (BMR * 1.725);

        }


        if (preference.equals("Cutting"))
        {
            totalCalories = totalCalories - 250;

        }
        else if (preference.equals("Maintaining"))
        {
            totalCalories=totalCalories+0;

        }
        else if (preference.equals("Bulking"))
        {
            totalCalories = totalCalories + 250;

        }
       return totalCalories;

    }
    //test3
    public double setWaterIntakeLevel(int weight)
    {

        totalWater = weight/2;
        return totalWater;

    }
}
