package com.example.fitlife;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.HashMap;

public class leaderBoard extends AppCompatActivity {

    Button leader;
    Spinner runningDropdown, weightsDropdown, waterDropdown, calorieDropdown;
    /*
    int runPoints;
    int weightPoints;
    int waterPoints;
    int caloriePoints;

     */
    int totalPoints;
    String calorieDrop, weightDrop, watersDrop, running;
    String upCal, upWeight, upWater, upRun;
    DatabaseReference reference, getData, update;
    FirebaseAuth fAuth;
    FirebaseUser user;

    // Running for 15 mins increments = 5 points
    // lifting for 30 mins increments = 5 points
    // If water goal not met, 3 points for attempting goal, 15 points for completing goal
    // meeting water goals = 15 points
    // meeting calorie goals = 15 points

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);//get the spinner from the xml.

        fAuth = FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();
        String uid = user.getUid();
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

        runningDropdown = findViewById(R.id.spinners1);
        //create a list of items for the spinner.
        String[] run = new String[]{"Enter data", "15 mins", "30 mins", "45 mins"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, run);
        //set the spinners adapter to the previously created one.
        runningDropdown.setAdapter(adapter);


        /*
        if (runningDropdown.equals("15 mins")) {
            runPoints = 5;
        }
        else if(running.equals("30 mins")) {
            runPoints = 10;
        }
        else if (running.equals("45 mins")) {
            runPoints = 15;
        }

         */

        weightsDropdown = findViewById(R.id.spinners2);
        //create a list of items for the spinner.
        String[] weight = new String[]{"Enter data","30 mins", "60 mins", "120 mins"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, weight);
        //set the spinners adapter to the previously created one.
        weightsDropdown.setAdapter(adapter2);

        /*
        if (weightsDropdown.equals("30 mins")) {
            weightPoints = 5;
        }
        else if (weightsDropdown.equals("60 mins")) {
            weightPoints = 10;
        }
        else if (weightsDropdown.equals("120 mins")) {
            weightPoints = 15;
        }

         */

        waterDropdown = findViewById(R.id.spinners3);
        //create a list of items for the spinner.
        String[] water = new String[]{"Enter data", "I almost reached my goal", "I reached my goal"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, water);
        //set the spinners adapter to the previously created one.
        waterDropdown.setAdapter(adapter3);


        /*
        if (waterDropdown.equals("I almost reached my goal")) {
            waterPoints = 3;
        }
        else if (waterDropdown.equals("I reached my goal!")) {
            waterPoints = 15;
        }

         */

        calorieDropdown = findViewById(R.id.spinners4);
        //create a list of items for the spinner.
        String[] calorie = new String[]{"Enter data", "I almost reached my calorie goal", "I reached my calorie goal"};
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, calorie);
        //set the spinners adapter to the previously created one.
        calorieDropdown.setAdapter(adapter4);

        /*
        if (calorieDropdown.equals("I almost reached my calorie goal")) {
            caloriePoints = 3;
        }
        else if (calorieDropdown.equals("I reached my calorie goal!")) {
            caloriePoints = 15;
        }

         */

        leader = findViewById(R.id.btnSendData);
        leader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("LeaderData");
                calorieDrop = calorieDropdown.getSelectedItem().toString().trim();
                watersDrop = waterDropdown.getSelectedItem().toString().trim();
                running = runningDropdown.getSelectedItem().toString().trim();
                weightDrop = weightsDropdown.getSelectedItem().toString().trim();

                // Sends data of the respected challenge completed to FireBase
                HashMap newPoints = new HashMap();
                newPoints.put("Run", running);
                newPoints.put("Weights", weightDrop);
                newPoints.put("Water", watersDrop);
                newPoints.put("Calorie", calorieDrop);
                reference.setValue(newPoints);
                Toast.makeText(leaderBoard.this, "Data Has Sent", Toast.LENGTH_SHORT).show();

                startActivity((new Intent(getApplicationContext(), MainActivity.class)));
            }
        });

    }
                // Sends point data from spinners to FireBase to be stored and updated
    public void updatePoints(String uid){
        update = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("LeaderData");
        update.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                upCal = snapshot.child("Calorie").getValue().toString();
                upWeight = snapshot.child("Weights").getValue().toString();
                upRun = snapshot.child("Run").getValue().toString();
                upWater = snapshot.child("Water").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}