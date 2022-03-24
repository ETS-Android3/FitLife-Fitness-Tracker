package com.example.fitlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DailyChallenge extends AppCompatActivity {

    TextView dailyChallenge;
    Button completed, notYet;
    DatabaseReference reference, getData;
    FirebaseAuth fAuth;
    FirebaseUser user;
    int totalPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_challenge);

        fAuth = FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();
        String uid = user.getUid();


        getDailyChallenge();
        getData = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        getData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String points = snapshot.child("Points").getValue().toString();
                totalPoints = Integer.parseInt(points);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        completed = findViewById(R.id.completeTask);
        completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
                totalPoints += 1;
                HashMap newPoints = new HashMap();
                newPoints.put("Points", totalPoints);
                reference.updateChildren(newPoints).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Toast.makeText(DailyChallenge.this, "User Points Updated", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(DailyChallenge.this, "Update Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
        notYet = findViewById(R.id.notYet);
        notYet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

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
            dailyChallenge = findViewById(R.id.dailyChallengeText);
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
}