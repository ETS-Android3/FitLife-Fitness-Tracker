package com.example.fitlife;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


//The Home page of the application. This is used as a way to get to the other activities not supposed to have much other functionality besides that
public class MainActivity extends AppCompatActivity {

    Button profile, meal, phys, friends;
    TextView userName, dailyChallenge;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    String dailyChal;
    Handler handler = new Handler();
    Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            String[] arr = {"Do 10 push-ups today", "Do 10 sit-ups today", "Run for 2 miles today",
                    "Do 10 squats today", "Take a 20 minute walk today", "Do a 1 minute plank today",
                    "Do 10 burpees today", "Take a 5 minute jog today", "Do 5 minutes of HIIT today",
                    "Walk an extra 1000 steps today", "Drink more water today", "Do 10 minutes of yoga today",
                    "Do 10 leg raises today", "Do high knees for 2 minutes today", "Do 20 lunges today"};
            Random random = new Random();
            int select = random.nextInt(arr.length);
            dailyChal = arr[select];
            dailyChallenge = findViewById(R.id.dailyChallenge);
            dailyChallenge.setText(String.valueOf(dailyChal));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.userP);

        handler.post(runnableCode);
        handler.postDelayed(runnableCode, 86400000);

        //getDailyChallenge();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        String uid = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String First = snapshot.child("FirstName").getValue(String.class);
                userName.setText(First);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), userProfileActivity.class));
                finish();
            }
        });

        meal = findViewById(R.id.btnTrack);
        meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MealWaterTracking.class));
                finish();
            }
        });

        phys = findViewById(R.id.physicals);
        phys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PhysicalActivity.class));
                finish();
            }
        });

        friends = findViewById(R.id.btnFriend);
        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SearchFriends.class));
            }
        });

    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();//Log out of User
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();

    }

    public class setDailyChallenge extends TimerTask {

        @Override
        public void run() {
            String dailyChal;
            String[] arr = {"Do 10 push-ups today", "Do 10 sit-ups today", "Run for 2 miles today",
                    "Do 10 squats today", "Take a 20 minute walk today", "Do a 1 minute plank today",
                    "Do 10 burpees today", "Take a 5 minute jog today", "Do 5 minutes of HIIT today",
                    "Walk an extra 1000 steps today", "Drink more water today", "Do 10 minutes of yoga today",
                    "Do 10 leg raises today", "Do high knees for 2 minutes today", "Do 20 lunges today"};
            Random random = new Random();
            int select = random.nextInt(arr.length);
            dailyChal = arr[select];
            dailyChallenge = findViewById(R.id.dailyChallenge);
            dailyChallenge.setText(String.valueOf(dailyChal));
        }

    }

    private Date getTomorrowMorning12AM(){

        Date date12am = new java.util.Date();
        date12am.setHours(0);
        date12am.setMinutes(0);

        return date12am;
    }
    public void getDailyChallenge()
    {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        //Set time as 12 AM
        Timer timer = new Timer();
        timer.schedule(new setDailyChallenge(), getTomorrowMorning12AM(), 1000*60*60*24);
    }
    public void scheduleAlarm() {
        // Construct an intent that will execute the AlarmReceiver
        Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
        // Create a PendingIntent to be triggered when the alarm goes off
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, MyAlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Setup periodic alarm every every half hour from this point onwards
        long firstMillis = System.currentTimeMillis(); // alarm is set right away
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        // First parameter is the type: ELAPSED_REALTIME, ELAPSED_REALTIME_WAKEUP, RTC_WAKEUP
        // Interval can be INTERVAL_FIFTEEN_MINUTES, INTERVAL_HALF_HOUR, INTERVAL_HOUR, INTERVAL_DAY
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                AlarmManager.INTERVAL_DAY, pIntent);
    }






}