package com.example.fitlife;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    Button profile, meal, phys;
    TextView userName;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    TextView dailyChallenge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.userP);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        String uid = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String First = snapshot.child("First Name").getValue(String.class);
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
                startActivity(new Intent(getApplicationContext(),userProfileActivity.class));
                finish();
            }
        });

        meal = findViewById(R.id.btnTrack);
        meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MealWaterTracking.class));
                finish();
            }
        });

        phys = findViewById(R.id.physicals);
        phys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),PhysicalActivity.class));
                finish();
            }
        });

    }
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();//Log out of User
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();

    }
    class setDailyChallenge extends TimerTask{
        @Override
        public void run() {
            String dailychal;
            String [] arr = {"Do 10 push-ups today", "Do 10 sit-ups today", "Run for 2 miles today",
                    "Do 10 squats today", "Take a 20 minute walk today", "Do a 1 minute plank today",
                    "Do 10 burpees today", "Take a 5 minute jog today", "Do 5 minutes of HIIT today",
                    "Walk an extra 1000 steps today", "Drink more water today", "Do 10 minutes of yoga today",
                    "Do 10 leg raises today", "Do high knees for 2 minutes today", "Do 20 lunges today"};
            Random random = new Random();
            int select = random.nextInt(arr.length);
            dailychal = arr[select];
            dailyChallenge.setText(String.valueOf(dailychal));

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



}