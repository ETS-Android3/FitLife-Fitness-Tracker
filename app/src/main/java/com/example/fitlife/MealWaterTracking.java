package com.example.fitlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MealWaterTracking extends AppCompatActivity {
    double BMR;
    double totalCalories;
    double totalWater;
    double height;
    double weight;
    int age;
    String gender;
    String activityLevel;
    String preference;
    String firstName;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_water_tracking);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child(user.getUid());
        reference.addValueEventListener(new ValueEventListener)() {
            @Override
                    public void onDataChange(DataSnapshot dataSnapshot){
                firstName = dataSnapshot.child(uid).child("First Name:").getValue(String.class);
                String userHeight = dataSnapshot.child(uid).child("User Info").child("Height:").getValue(String.class);
                height = double.parseDouble(userHeight);
                String userWeight = dataSnapshot.child(uid).child("User Info").child("Weight:").getValue(String.class);
                weight = double.parseDouble(userWeight);
                String userAge = dataSnapshot.child(uid).child("User Info").child("Age:").getValue(String.class);
                age = int.parseInt(userAge);
                gender = dataSnapshot.child(uid).child("User Info").child("Sex:").getValue(String.class);
                activityLevel = dataSnapshot.child(uid).child("User Info").child("Activity:").getValue(String.class);
                preference = dataSnapshot.child(uid).child("User Info").child("Goal:").getValue(String.class);

            }

        }
    }

    public void getBMR()
    {

        if(gender == "Male")
        {
         BMR = 66 + (6.3 * weight) + (12.9 * height) - (6.8 * age);
        }
        else if (gender == "Female")
        {
            BMR = 655 + (4.3 * weight) + (4.7 * height) - (4.7 * age);
        }

    }

    public void setTotalCalories()
    {

        if(activityLevel == "No Activity")
        {
            totalCalories = BMR * 1.2;
        }
        else if(activityLevel == "Light Activity = Workout 2-3 Times a Week")
        {
            totalCalories = BMR * 1.375;
        }
        else if(activityLevel == "Moderate activity= Workout 3-4 Times Per Week")
        {
            totalCalories = BMR * 1.55;
        }
        else if(activityLevel == "Heavy Activity = Workout 4-5 Times Per Week")
        {
            totalCalories = BMR * 1.725;
        }


        if (preference == "Cutting")
        {
            totalCalories = totalCalories - 250;
        }
        else if (preference == "Maintaining")
        {
            totalCalories = totalCalories;
        }
        else if (preference == "Bulking")
        {
            totalCalories = totalCalories + 250;
        }

    }
    //test3
    public void setWaterIntakeLevel()
    {
        weight = 150;
        totalWater = weight/2;

    }
}