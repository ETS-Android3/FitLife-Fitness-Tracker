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

public class leaderBoard extends AppCompatActivity {

    Button leader;
    Spinner runningDropdown, weightsDropdown, waterDropdown, calorieDropdown;
    int runPoints = 0;
    int weightPoints = 0;
    int waterPoints = 0;
    int caloriePoints = 0;
    int totalPoints = 0;

    // Running for 15 mins increments = 5 points
    // lifting for 30 mins increments = 5 points
    // If water goal not met, 3 points for attempting goal, 15 points for completing goal
    // meeting water goals = 15 points
    // meeting calorie goals = 15 points
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderlist);//get the spinner from the xml.

        runningDropdown = findViewById(R.id.spinners1);
        //create a list of items for the spinner.
        String[] run = new String[]{"Enter data", "15 mins", "30 mins", "45 mins"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, run);
        //set the spinners adapter to the previously created one.
        runningDropdown.setAdapter(adapter);

        if (runningDropdown.equals("15 mins")) {
            int runPoints = 5;
        }
        if (runningDropdown.equals("30 mins")) {
            int runPoints = 10;
        }
        if (runningDropdown.equals("60 mins")) {
            int runPoints = 15;
        }

        weightsDropdown = findViewById(R.id.spinners2);
        //create a list of items for the spinner.
        String[] weight = new String[]{"Enter data","30 mins", "60 mins", "120 mins"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, weight);
        //set the spinners adapter to the previously created one.
        weightsDropdown.setAdapter(adapter2);

        if (weightsDropdown.equals("30 mins")) {
            int weightPoints = 5;
        }
        if (weightsDropdown.equals("60 mins")) {
            int weightPoints = 10;
        }
        if (weightsDropdown.equals("120 mins")) {
            int weightPoints = 15;
        }

        waterDropdown = findViewById(R.id.spinners3);
        //create a list of items for the spinner.
        String[] water = new String[]{"Enter data", "I almost reached my goal", "I reached my goal"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, water);
        //set the spinners adapter to the previously created one.
        waterDropdown.setAdapter(adapter3);

        if (waterDropdown.equals("I almost reached my goal")) {
            int waterPoints = 3;
        }
        if (waterDropdown.equals("I reached my goal!")) {
            int waterPoints = 15;
        }

        calorieDropdown = findViewById(R.id.spinners4);
        //create a list of items for the spinner.
        String[] calorie = new String[]{"Enter data", "I almost reached my calorie goal", "I reached my calorie goal"};
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, calorie);
        //set the spinners adapter to the previously created one.
        calorieDropdown.setAdapter(adapter4);

        if (calorieDropdown.equals("I almost reached my calorie goal")) {
            int caloriePoints = 3;
        }
        if (calorieDropdown.equals("I reached my calorie goal!")) {
            int caloriePoints = 15;
        }

        int totalPoints = runPoints + weightPoints + waterPoints + caloriePoints;
    }
}