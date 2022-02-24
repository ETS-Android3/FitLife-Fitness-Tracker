package com.example.fitlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button profile, meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();//Log out of User
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();

    }


}