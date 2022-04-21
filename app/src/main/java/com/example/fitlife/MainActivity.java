
package com.example.fitlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//The Home page of the application. This is used as a way to get to the other activities not supposed to have much other functionality besides that
public class MainActivity extends AppCompatActivity {

    Button profile, meal, leader, phys, friends, timely, daily, table;
    TextView userName;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.userP);
        // Checks FireBase for user login data
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
            // Name error if the user is not in FireBase
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // Button for the user profile
        profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),userProfileActivity.class));
                finish();
            }
        });
        // Button for meal and water tracking data
        meal = findViewById(R.id.btnTrack);
        meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MealWaterTracking.class));
                finish();
            }
        });
        // Button for the leaderboard
        leader = findViewById(R.id.btnleader);
        leader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), leaderBoard.class));
                finish();
            }
        });


        // Button for friends
        friends = findViewById(R.id.btnFriend);
        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SearchFriends.class));
            }
        });
        // Physical activity button
        phys = findViewById(R.id.physicals);
        phys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PhysicalActivity.class));
            }
        });
        // Timely review button (calendar)
        timely = findViewById(R.id.btnTime);
        timely.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity((new Intent(getApplicationContext(), TimelyReviewActivity.class)));
            }
        });
        // Daily challenge button
        daily = findViewById(R.id.daily);
        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity((new Intent(getApplicationContext(), DailyChallenge.class)));

            }
        });
        table = findViewById(R.id.table);
            table.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity((new Intent(getApplicationContext(), tableList.class)));
                }
            });


    }
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();//Log out of User
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();

    }
}