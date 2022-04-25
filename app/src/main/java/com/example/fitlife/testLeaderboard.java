package com.example.fitlife;

//Test to check the logic for the leaderBoard activity.
public class testLeaderboard {
    private int runPoints;
    private int weightPoints;
    private int waterPoints;
    private int caloriePoints;
    private int totalPoints;

    testLeaderboard(int runPoints, int weightPoints, int waterPoints, int caloriePoints, int totalPoints){
        this.runPoints = runPoints;
        this.weightPoints = weightPoints;
        this.waterPoints = waterPoints;
        this.caloriePoints = caloriePoints;
        this.totalPoints = totalPoints;
    }

    public int addWaterPoints(String upWater) {
        if (upWater.equals("I almost reached my goal")) {
            waterPoints = 3;

        }
        else if (upWater.equals("I reached my goal!")) {
            waterPoints = 15;
        }

        return waterPoints;
    }

    public int addCaloriePoints(String upCal) {
        if (upCal.equals("I almost reached my calorie goal")) {
            caloriePoints = 3;
        }
        else if (upCal.equals("I reached my calorie goal!")) {
            caloriePoints = 15;
        }

        return  caloriePoints;
    }

    public int addWeightPoints(String upWeight) {
        if (upWeight.equals("0 mins")) {
            weightPoints = 0;
        }
        else if (upWeight.equals("15 mins")) {
            weightPoints = 5;
        }
        else if(upWeight.equals("30 mins")) {
            weightPoints = 10;
        }
        else if (upWeight.equals("45 mins")) {
            weightPoints = 15;
        }
        return weightPoints;
    }

    //This Function adds up the values for
    public int addRunPoints(String upRun) {
        if (upRun.equals("0 mins")) {
            runPoints = 0;
        }
        else if (upRun.equals("15 mins")) {
            runPoints = 5;
        }
        else if(upRun.equals("30 mins")) {
            runPoints = 10;
        }
        else if (upRun.equals("45 mins")) {
            runPoints = 15;
        }
        return runPoints;
    }

    public int addTotalPoints() {
        int x = totalPoints;
        int temp = waterPoints + caloriePoints + weightPoints + runPoints + x;
        totalPoints = temp;
        return totalPoints;

    }
}
