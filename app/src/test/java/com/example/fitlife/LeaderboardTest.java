package com.example.fitlife;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeaderboardTest {
    @Test
    public void checkWeights(){
        testLeaderboard test = new testLeaderboard(12, 13, 12, 10, 5);
        String x = "0 mins";
        assertEquals(0, test.addWeightPoints(x));
    }

    @Test
    public void checkRun(){
        testLeaderboard test = new testLeaderboard(12, 13, 12, 10, 5);
        String x = "15 mins";
        assertEquals(5, test.addRunPoints(x));
    }

    @Test
    public void checkWater(){
        testLeaderboard test = new testLeaderboard(12, 13, 12, 10, 5);
        String x = "I almost reached my goal";
        assertEquals(3, test.addWaterPoints(x));
    }

    @Test
    public void checkCalorie(){
        testLeaderboard test = new testLeaderboard(12, 13, 12, 10, 5);
        String x = "I reached my calorie goal!";
        assertEquals(15, test.addCaloriePoints(x));
    }

    @Test
    public void checkTotal(){
        testLeaderboard test = new testLeaderboard(12, 13, 12, 10, 5);
        assertEquals(52, test.addTotalPoints());
    }


}
