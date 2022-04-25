package com.example.fitlife;

import android.app.IntentService;
import android.content.Intent;

import java.util.Random;

public class MyTestService extends IntentService {
    public MyTestService() {
        super("MyTestService");
    }

    @Override
    //Tried using this along with MyAlarmReceiver to set up daily challenge to recur daily
    protected void onHandleIntent(Intent intent) {
        String dailyChal;
        String[] arr = {"Do 10 push-ups today", "Do 10 sit-ups today", "Run for 2 miles today",
                "Do 10 squats today", "Take a 20 minute walk today", "Do a 1 minute plank today",
                "Do 10 burpees today", "Take a 5 minute jog today", "Do 5 minutes of HIIT today",
                "Walk an extra 1000 steps today", "Drink more water today", "Do 10 minutes of yoga today",
                "Do 10 leg raises today", "Do high knees for 2 minutes today", "Do 20 lunges today"};
        Random random = new Random();
        int select = random.nextInt(arr.length);
        dailyChal = arr[select];
        //dailyChallenge.setText(String.valueOf(dailyChal));
    }
}
