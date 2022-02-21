package com.example.fitlife;

import android.content.Intent;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class qeustionaireActivity extends AppCompatActivity{

    private Button button, next;
    FirebaseAuth fAuth;
    EditText editAge, editWeight, editSex, editGoal, editActivity, editHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qeustionaire);//get the spinner from the xml.

        editAge = findViewById(R.id.etAge);
        editWeight=findViewById(R.id.etWeight);
        editActivity = findViewById(R.id.etActivity);
        editSex = findViewById(R.id.etSex);
        editGoal = findViewById(R.id.etGoal);
        editHeight = findViewById(R.id.etHeight);

        fAuth = FirebaseAuth.getInstance();
        next = findViewById(R.id.btnNext);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String age = editAge.toString().trim();
                String weight = editWeight.toString().trim();
                String activity = editActivity.toString().trim();
                String sex = editSex.toString().trim();
                String goal = editGoal.toString().trim();
                String height = editHeight.toString().trim();


                if(TextUtils.isEmpty(age)){
                    editAge.setError("Age Required");
                    return;
                }
                if(TextUtils.isEmpty(weight)){
                    editWeight.setError("Weight Required");
                    return;
                }
                if(TextUtils.isEmpty(height)){
                    editHeight.setError("Height Required");
                    return;
                }
                if(TextUtils.isEmpty(activity))
                {
                    editActivity.setError("Activity Level Required");
                    return;
                }
                if(TextUtils.isEmpty(sex))
                {
                    editSex.setError("Biological Sex is Required");
                    return;
                }
                if(TextUtils.isEmpty(goal)){
                    editGoal.setError("Goal is Required");
                    return;
                }
                if(sex != "Male" && sex != "Female" && sex != "male" && sex != "female"){
                    editSex.setError("Must input Male or Female");
                    return;
                }
                if(activity != "none" && activity != "None" && activity != "light" && activity != "Light" && activity != "moderate" && activity != "Moderate"
                        && activity != "Heavy" && activity != "heavy"){
                    editActivity.setError("Must input None, Light, Moderate, or Heavy");
                    return;
                }
                if(goal != "Cut" && goal != "cut" && goal != "Maintain" && goal != "maintain" && goal != "Bulk" && goal != "bulk"){
                    editGoal.setError("Must input Cut, Maintain, or Bulk");
                    return;
                }

                String u_id = fAuth.getCurrentUser().getUid();
                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(u_id);

                Map questions = new HashMap();
                questions.put("Age", age);
                questions.put("Height", height);
                questions.put("Weight", weight);
                questions.put("Goal", goal);
                questions.put("Sex", sex);
                questions.put("Activity", activity);
                current_user_db.setValue(questions);

                Toast.makeText(qeustionaireActivity.this, "Questionnaire Added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));



            }
        });

        /*/
        //Spinner Information for Sex of user
        //get the spinner from the xml.
        Spinner sexDropdown = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        String[] sex = new String[]{"Make Selection","Male", "Female"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sex);
        //set the spinners adapter to the previously created one.
        sexDropdown.setAdapter(adapter);

        //Spinner Information for Activity Level
        Spinner workoutDropdown = findViewById(R.id.spinner2);
        String[] workoutLevel = new String[]{"Make Selection", "No Activity", "Light Activity = Workout 2-3 Times a Week", "Moderate activity= Workout 3-4 Times Per Week" ,
                "Heavy Activity = Workout 4-5 Times Per Week"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, workoutLevel);
        workoutDropdown.setAdapter(adapter2);


        //Drop Down Menu For Fitness Goals
        Spinner goalDropdown = findViewById(R.id.spinner3);
        String[] goal = new String[]{"Make Selection","Maintaining", "Cutting", "Bulking"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, goal);
        goalDropdown.setAdapter(adapter3);

         */

        button = findViewById(R.id.btnRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(qeustionaireActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                openMainActivity();
            }
        });

    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}