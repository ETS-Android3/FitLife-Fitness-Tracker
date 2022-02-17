package com.example.fitlife;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
// test comment
public class qeustionaireActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qeustionaire);//get the spinner from the xml.

        //Spinner Information for Sex of user
        //get the spinner from the xml.
        Spinner sexDropdown = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        String[] sex = new String[]{"Male", "Female"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sex);
        //set the spinners adapter to the previously created one.
        sexDropdown.setAdapter(adapter);

        //Spinner Information for Activity Level
        Spinner workoutDropdown = findViewById(R.id.spinner2);
        String[] workoutLevel = new String[]{"No Activity", "Light Activity = Workout 2-3 Times a Week", "Moderate activity= Workout 3-4 Times Per Week" ,
                "Heavy Activity = Workout 4-5 Times Per Week"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, workoutLevel);
        workoutDropdown.setAdapter(adapter2);

        //Drop Down Menu For Fitness Goals
        Spinner goalDropdown = findViewById(R.id.spinner3);
        String[] goal = new String[]{"Maintaining", "Cutting", "Bulking"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, goal);
        goalDropdown.setAdapter(adapter3);

        button = (Button) findViewById(R.id.btnRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}